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