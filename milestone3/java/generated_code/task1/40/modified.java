public static byte[] toByteArray(Reader input, String encoding) throws IOException {
        Charset charset = Charsets.toCharset(encoding);
        ByteArrayOutputStream dataOutputStream = new ByteArrayOutputStream();
        int nextChar;
        while((nextChar = input.read()) != -1) {
            CharsetEncoder encoder = charset.newEncoder();
            CharBuffer charBuffer = CharBuffer.allocate(1);
            charBuffer.put((char)nextChar);
            charBuffer.flip();
            ByteBuffer byteBuffer = encoder.encode(charBuffer);
            byte[] byteArray = byteBuffer.array();
            dataOutputStream.write(byteArray);
        }
        return dataOutputStream.toByteArray();
    }