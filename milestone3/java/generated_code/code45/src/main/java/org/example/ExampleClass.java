package org.example;

public class ExampleClass {

    private String privateField;

    public ExampleClass() {}

    public ExampleClass(String privateField) {
        this.privateField = privateField;
    }

    public String getPrivateField() {
        return privateField;
    }

    public void setPrivateField(String privateField) {
        this.privateField = privateField;
    }

    public void publicMethod() {}

    private void privateMethod() {}

    protected void protectedMethod() {}
}
