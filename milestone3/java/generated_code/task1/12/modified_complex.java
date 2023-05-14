private static void adjustPen() {
    int nextColor = getNextColor(1, 10); // Generate a random color index between 1 and 10.
    double penWidth = Tortoise.getPenWidth();

    if (isRandomEventOccurred(0.1)) { // 10% probability that the pen width is randomly set to 1.
        setPenWidth(1);
    } else if (penWidth < 4) {
        setColorAndIncreaseWidth(nextColor, penWidth);
    } else {
        setColorAndDecreaseWidth(nextColor, penWidth);
    }
}

private static int getNextColor(int min, int max) {
    int range = max - min + 1;
    return (int) (Math.random() * range) + min; // Generate a random integer within the range [min, max]
}

private static boolean isRandomEventOccurred(double probability) {
    return Math.random() < probability;
}

private static void setColorAndIncreaseWidth(int colorIndex, double penWidth) {
    int color = ColorWheel.getColor(colorIndex);
    Tortoise.setPenColor(color);
    Tortoise.setPenWidth(penWidth + 1.0);
}

private static void setColorAndDecreaseWidth(int colorIndex, double penWidth) {
    int color = ColorWheel.getColor(colorIndex);
    Tortoise.setPenColor(color);
    Tortoise.setPenWidth(penWidth - 1.0);
}

private static void setPenWidth(double penWidth) {
    Tortoise.setPenWidth(penWidth);
}