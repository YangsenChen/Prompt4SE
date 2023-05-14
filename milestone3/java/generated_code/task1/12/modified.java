private static void adjustPen() {
    int nextColor = ColorWheel.getNextColor();
    double penWidth = Tortoise.getPenWidth();
    boolean isPenWidthGreaterThanFour = penWidth > 4;

    changePenColor(nextColor);
    increasePenWidth(penWidth);
    resetPenWidth(isPenWidthGreaterThanFour);
}

private static void changePenColor(int nextColor) {
    // Change the color of the line the tortoise draws to the next color on the color wheel
    Tortoise.setPenColor(nextColor);
}

private static void increasePenWidth(double penWidth) {
    // Increase the tortoise's pen width by 1
    Tortoise.setPenWidth(penWidth + 1.0);
}

private static void resetPenWidth(boolean isPenWidthGreaterThanFour) {
    // If the tortoise's pen width is greater than 4, then reset the pen width to 1
    if (isPenWidthGreaterThanFour) {
        Tortoise.setPenWidth(1);
    }
}