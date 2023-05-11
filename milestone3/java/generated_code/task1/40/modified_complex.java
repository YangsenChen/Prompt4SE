import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.util.concurrent.*;

public class ParallelByteArrayEncoder {
    private static final int MAX_THREAD_POOL_SIZE = 10;
    
    public static byte[] toByteArray(Reader input, String encoding) throws IOException, InterruptedException, ExecutionException {
        Charset charset = Charsets.toCharset(encoding);
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(MAX_THREAD_POOL_SIZE);
        Future<ByteArrayOutputStream>[] futures = new Future[MAX_THREAD_POOL_SIZE];
        int nextChar;
        ByteArrayOutputStream[] outputStreams = new ByteArrayOutputStream[MAX_THREAD_POOL_SIZE];
        
        // create a thread pool and submit tasks to encode the characters in parallel
        for(int i = 0; i < MAX_THREAD_POOL_SIZE; i++) {
            outputStreams[i] = new ByteArrayOutputStream();
            futures[i] = threadPoolExecutor.submit(new CharacterEncoder(input, charset, outputStreams[i]));
        }
        
        // wait for all tasks to complete and combine the results
        ByteArrayOutputStream dataOutputStream = new ByteArrayOutputStream();
        for(int i = 0; i < MAX_THREAD_POOL_SIZE; i++) {
            futures[i].get();
            dataOutputStream.write(outputStreams[i].toByteArray());
        }
        
        // shut down the thread pool executor
        threadPoolExecutor.shutdown();
        
        return dataOutputStream.toByteArray();
    }
    
    private static class CharacterEncoder implements Callable<Void> {
        private Reader input;
        private Charset charset;
        private ByteArrayOutputStream outputStream;
        
        public CharacterEncoder(Reader input, Charset charset, ByteArrayOutputStream outputStream) {
            this.input = input;
            this.charset = charset;
            this.outputStream = outputStream;
        }
        
        public Void call() throws IOException {
            int nextChar;
            while((nextChar = input.read()) != -1) {
                CharsetEncoder encoder = charset.newEncoder();
                CharBuffer charBuffer = CharBuffer.allocate(1);
                charBuffer.put((char)nextChar);
                charBuffer.flip();
                ByteBuffer byteBuffer = encoder.encode(charBuffer);
                byte[] byteArray = byteBuffer.array();
                outputStream.write(byteArray);
            }
            return null;
        }
    }
}