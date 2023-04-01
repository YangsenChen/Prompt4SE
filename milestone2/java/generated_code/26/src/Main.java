public class Main {

    public static void main(String[] args) {
        // Example usage in the main method
        DataType dataType = DataType.FLOAT;
        Class<?> elementType = float.class;
        Object storage = new float[] {1.0f, 2.0f, 3.0f, 4.0f};
        boolean isVlen = false;
        Index index = new Index(new int[] {1, 2});

        Array viewArray = createView(dataType, elementType, isVlen, index, storage);
        System.out.println("Value at index (1, 2): " + viewArray.getObject(index));
    }

    @Test
    public void testSameInstanceReturned() {
        DataType dataType = DataType.INT;
        Class<?> elementType = int.class;
        Object storage = new int[] {1, 2, 3, 4, 5, 6};
        boolean isVlen = false;
        Index index = new Index(new int[] {2, 3, 4});

        Array array1 = createView(dataType, elementType, isVlen, index, storage);
        Array array2 = createView(dataType, elementType, isVlen, index, storage);

        assertSame("createView() should return the same instance for the same inputs.", array1, array2);
    }

    @Test
    public void testNullStorage() {
        DataType dataType = DataType.STRING;
        Class<?> elementType = String.class;
        Object storage = null;
        boolean isVlen = true;
        Index index = new Index(new int[] {0});

        Array array = createView(dataType, elementType, isVlen, index, storage);

        assertNull("createView() should return null when the storage is null.", array);
    }

    @Test
    public void testVlenStorage() {
        DataType dataType = DataType.OBJECT;
        Class<?> elementType = Object.class;
        Object storage = new Object[] {"foo", "bar", "baz"};
        boolean isVlen = true;
        Index index = new Index(new int[] {1});

        Array array = createView(dataType, elementType, isVlen, index, storage);

        assertEquals("createView() should return the expected value when using variable-length storage.",
                "bar", array.getObject(index));
    }

    protected static Array createView(DataType dataType, Class<?> elementType, boolean isVlen, Index index, Object storage) {
        return ArrayObject.factory(dataType, elementType, isVlen, index, storage);
    }
}