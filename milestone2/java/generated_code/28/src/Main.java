import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {
    public static void main(String[] args) {
        String decimalSeparator = getDecimalSeparatorByDefaultLocale();
        System.out.println("Decimal separator: " + decimalSeparator);
    }

    public static String getDecimalSeparatorByDefaultLocale() {
        final DecimalFormat nf = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault());
        return "" + nf.getDecimalFormatSymbols().getDecimalSeparator();
    }

    @Test
    public void testDecimalSeparatorForUSLocale() {
        Locale.setDefault(Locale.US);
        String expected = ".";
        String actual = getDecimalSeparatorByDefaultLocale();
        assertEquals(expected, actual);
    }

    @Test
    public void testDecimalSeparatorForFrenchLocale() {
        Locale.setDefault(Locale.FRANCE);
        String expected = ",";
        String actual = getDecimalSeparatorByDefaultLocale();
        assertEquals(expected, actual);
    }

    @Test
    public void testDecimalSeparatorForJapaneseLocale() {
        Locale.setDefault(Locale.JAPAN);
        String expected = ".";
        String actual = getDecimalSeparatorByDefaultLocale();
        assertEquals(expected, actual);
    }
}