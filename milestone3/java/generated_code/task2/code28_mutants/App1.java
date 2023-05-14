package org.example;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class App {
    public static void main(String[] args) {
        String decimalSeparator = getDecimalSeparatorByDefaultLocale();

    }

    public static String getDecimalSeparatorByDefaultLocale() {
        final DecimalFormat nf = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault());
        return "" + nf.getDecimalFormatSymbols().getDecimalSeparator();
    }

//    // chatgpt generated  semantically equivalent code: test pass 3/3
    //chatgpt response on changes made: It achieves the same functionality as the original code but does not need to cast the DecimalFormat object to retrieve the decimal separator. The tests should all still pass with this new code.
    public static String getDecimalSeparatorByDefaultLocale1() {
        Locale defaultLocale = Locale.getDefault();
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(defaultLocale);
        return "" + symbols.getDecimalSeparator();
    }

}