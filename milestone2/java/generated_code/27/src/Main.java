import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        // Run the tests
        org.junit.runner.JUnitCore.main("Main");
    }

    public static void externalize(DataOutputStream sink, SomeObject[] arrayVar, HashMap<String, ClientIO> mapVars) throws IOException {
        arrayVar.externalize(sink);
        for (Enumeration<String> e = mapVars.keys(); e.hasMoreElements();) {
            String key = e.nextElement();
            ClientIO bt = mapVars.get(key);
            bt.externalize(sink);
        }
    }

    @Test
    public void testSerializeEmptyData() {
        // Test serializing empty data
        try {
            SomeObject[] arrayVar = {};
            HashMap<String, ClientIO> mapVars = new HashMap<>();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DataOutputStream aos = new DataOutputStream(out);
            externalize(aos, arrayVar, mapVars);

            byte[] bytes = out.toByteArray();
            assertEquals(0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException should not have been thrown");
        }
    }

    @Test
    public void testSerializeSomeObjects() {
        // Test serializing some objects
        try {
            SomeObject[] arrayVar = {new SomeObject(), new SomeObject(), new SomeObject()};
            HashMap<String, ClientIO> mapVars = new HashMap<>();
            mapVars.put("client1", new ClientIO());
            mapVars.put("client2", new ClientIO());
            mapVars.put("client3", new ClientIO());

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DataOutputStream aos = new DataOutputStream(out);
            externalize(aos, arrayVar, mapVars);

            byte[] bytes = out.toByteArray();
            int expectedLength = 3 * SomeObject.EXPECTED_BYTE_COUNT + 3 * ClientIO.EXPECTED_BYTE_COUNT;
            assertEquals(expectedLength, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException should not have been thrown");
        }
    }

    @Test
    public void testSerializeDeserialize() {
        // Test serializing and deserializing data
        try {
            SomeObject[] arrayVar = {new SomeObject(), new SomeObject(), new SomeObject()};
            HashMap<String, ClientIO> mapVars = new HashMap<>();
            ClientIO client1 = new ClientIO();
            client1.setIntValue(42);
            mapVars.put("client1", client1);
            mapVars.put("client2", new ClientIO());
            mapVars.put("client3", new ClientIO());

            // Serialize the objects
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DataOutputStream aos = new DataOutputStream(out);
            externalize(aos, arrayVar, mapVars);

            // Deserialize the objects
            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
            DataInputStream ais = new DataInputStream(in);
            SomeObject[] deserializedArray = SomeObject.createArrayFromStream(ais);
            HashMap<String, ClientIO> deserializedMap = new HashMap<>();
            ClientIO deserializedClient1 = new ClientIO();
            deserializedClient1.internalize(ais);
            deserializedMap.put("client1", deserializedClient1);
            deserializedMap.put("client2", new ClientIO());
            deserializedMap.put("client3", new ClientIO());

            // Verify the deserialized objects
            assertEquals(arrayVar.length, deserializedArray.length);
            for (int i = 0; i < arrayVar.length; i++) {
                assertEquals(arrayVar[i], deserializedArray[i]);
            }

            assertEquals(client1.getIntValue(), deserializedClient1.getIntValue());

            assertEquals(mapVars.size(), deserializedMap.size());
            for (String key : mapVars.keySet()) {
                assertTrue(deserializedMap.containsKey(key));
                assertEquals(mapVars.get(key), deserializedMap.get(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException should not have been thrown");
        }
    }

    // Example classes
    static class SomeObject {
        public static final int EXPECTED_BYTE_COUNT = 10;

        private int intValue = 42;
        private float floatValue = 3.14f;

        public void externalize(DataOutputStream sink) throws IOException {
            sink.writeInt(intValue);
            sink.writeFloat(floatValue);
        }

        public void internalize(DataInputStream source) throws IOException {
            intValue = source.readInt();
            floatValue = source.readFloat();
        }

        public static SomeObject[] createArrayFromStream(DataInputStream source) throws IOException {
            int count = source.readInt();
            SomeObject[] array = new SomeObject[count];
            for (int i = 0; i < count; i++) {
                array[i] = new SomeObject();
                array[i].internalize(source);
            }
            return array;
        }

        // ... hashCode, equals, toString, getters and setters elided
    }

    static class ClientIO {
        public static final int EXPECTED_BYTE_COUNT = 8;

        private int intValue = 0;
        private boolean boolValue = false;

        public void externalize(DataOutputStream sink) throws IOException {
            sink.writeInt(intValue);
            sink.writeBoolean(boolValue);
        }

        public void internalize(DataInputStream source) throws IOException {
            intValue = source.readInt();
            boolValue = source.readBoolean();
        }

        // ... hashCode, equals, toString, getters and setters elided
    }
}