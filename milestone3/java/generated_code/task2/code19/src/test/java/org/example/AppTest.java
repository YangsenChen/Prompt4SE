package org.example;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;
import junit.framework.TestCase;

import static org.example.App.showFormatInfo;

public class AppTest extends TestCase {

    // Helper method to create a JFormattedTextField with a specific format
    private JFormattedTextField createFormattedTextField(NumberFormat format) {
        NumberFormatter formatter = new NumberFormatter(format);
        return new JFormattedTextField(formatter);
    }

    // Normalize line endings to '\n'
    private String normalizeLineEndings(String str) {
        return str.replace("\r\n", "\n").replace('\r', '\n');
    }

    public void testShowFormatInfo() {
        // Create a JFormattedTextField with specific number format
        NumberFormat numberFormat = new DecimalFormat("#,##0.00");
        JFormattedTextField textField = createFormattedTextField(numberFormat);

        // Redirect the System.out to capture the printed output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Invoke the method showFormatInfo
        showFormatInfo(textField);

        // Verify the output
        String expectedOutput = "AbstractFormatter  javax.swing.text.NumberFormatter\n" +
                " Format  = java.text.DecimalFormat\n" +
                " getMinimumIntegerDigits=1\n" +
                " getMaximumIntegerDigits=2147483647\n" +
                " getMinimumFractionDigits=2\n" +
                " getMaximumFractionDigits=2\n" +
                " Pattern  = #,##0.00\n";
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(outContent.toString()));

        // Reset the System.out to the original output stream
        System.setOut(new java.io.PrintStream(new java.io.FileOutputStream(java.io.FileDescriptor.out)));
    }
}
