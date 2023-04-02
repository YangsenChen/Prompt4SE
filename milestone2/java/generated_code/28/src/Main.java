import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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

//    // chatgpt generated  semantically equivalent code: test pass 3/3
//    //chatgpt response on changes made: It achieves the same functionality as the original code but does not need to cast the DecimalFormat object to retrieve the decimal separator. The tests should all still pass with this new code.
//    public static String getDecimalSeparatorByDefaultLocale() {
//        Locale defaultLocale = Locale.getDefault();
//        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(defaultLocale);
//        return "" + symbols.getDecimalSeparator();
//    }


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