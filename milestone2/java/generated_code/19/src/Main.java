import javax.swing.*;
import javax.swing.text.*;
import java.text.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class Main {
    public static void main(String[] args) {
        JFormattedTextField tf = new JFormattedTextField(new DecimalFormat("#,##0.00"));
        tf.setValue(1234.56);

        showFormatInfo(tf);
    }

    static private void showFormatInfo(JFormattedTextField tf) {
        JFormattedTextField.AbstractFormatter ff = tf.getFormatter();
        System.out.println("AbstractFormatter  " + ff.getClass().getName());
        if (ff instanceof NumberFormatter) {
            NumberFormatter nf = (NumberFormatter) ff;
            Format f = nf.getFormat();
            System.out.println(" Format  = " + f.getClass().getName());
            if (f instanceof NumberFormat) {
                NumberFormat nfat = (NumberFormat) f;
                System.out.println(" getMinimumIntegerDigits=" +
                        nfat.getMinimumIntegerDigits());
                System.out.println(" getMaximumIntegerDigits=" +
                        nfat.getMaximumIntegerDigits());
                System.out.println(" getMinimumFractionDigits=" +
                        nfat.getMinimumFractionDigits());
                System.out.println(" getMaximumFractionDigits=" +
                        nfat.getMaximumFractionDigits());
            }
            if (f instanceof DecimalFormat) {
                DecimalFormat df = (DecimalFormat) f;
                System.out.println(" Pattern  = " + df.toPattern());
            }
        }
    }


//    // chatgpt generated  semantically equivalent code: test pass 3/3
//    //
//    //chatgpt response on changes made: This code achieves the same functionality as the original code, but adds an extra check to see if the Format object is an instance of DecimalFormat before calling the getMinimumFractionDigits and getMaximumFractionDigits methods. This is to prevent ClassCastExceptions in case the Format object is not a DecimalFormat. The tests should all still pass with this new code.
//    static private void showFormatInfo(JFormattedTextField tf) {
//        JFormattedTextField.AbstractFormatter ff = tf.getFormatter();
//        System.out.println("AbstractFormatter  " + ff.getClass().getName());
//        if (ff instanceof NumberFormatter) {
//            NumberFormatter nf = (NumberFormatter) ff;
//            Format f = nf.getFormat();
//            System.out.println(" Format  = " + f.getClass().getName());
//            if (f instanceof NumberFormat) {
//                NumberFormat nfat = (NumberFormat) f;
//                System.out.println(" getMinimumIntegerDigits=" +
//                        nfat.getMinimumIntegerDigits());
//                System.out.println(" getMaximumIntegerDigits=" +
//                        nfat.getMaximumIntegerDigits());
//                if (f instanceof DecimalFormat) {
//                    DecimalFormat df = (DecimalFormat) f;
//                    System.out.println(" getMinimumFractionDigits=" +
//                            df.getMinimumFractionDigits());
//                    System.out.println(" getMaximumFractionDigits=" +
//                            df.getMaximumFractionDigits());
//                    System.out.println(" Pattern  = " + df.toPattern());
//                }
//            }
//        }
//    }


    @Test
    public void testMinimumIntegerDigits() {
        JFormattedTextField tf = new JFormattedTextField(new DecimalFormat("#,##0.00"));
        tf.setValue(1234.56);
        NumberFormatter nf = (NumberFormatter) tf.getFormatter();
        NumberFormat nfat = (NumberFormat) nf.getFormat();
        assertEquals(1, nfat.getMinimumIntegerDigits());
    }

    @Test
    public void testMaximumIntegerDigits() {
        JFormattedTextField tf = new JFormattedTextField(new DecimalFormat("#,##0.00"));
        tf.setValue(1234.56);
        NumberFormatter nf = (NumberFormatter) tf.getFormatter();
        NumberFormat nfat = (NumberFormat) nf.getFormat();
        assertEquals(Integer.MAX_VALUE, nfat.getMaximumIntegerDigits());
    }

    @Test
    public void testFractionDigits() {
        JFormattedTextField tf = new JFormattedTextField(new DecimalFormat("#,##0.00"));
        tf.setValue(1234.56);
        NumberFormatter nf = (NumberFormatter) tf.getFormatter();
        NumberFormat nfat = (NumberFormat) nf.getFormat();
        assertEquals(2, nfat.getMinimumFractionDigits());
        assertEquals(2, nfat.getMaximumFractionDigits());
    }
}