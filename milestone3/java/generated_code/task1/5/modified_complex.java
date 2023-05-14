public void drawShapes(Graphics2D g, AffineTransform pixelAT, List<Shape> shapes) {
    // Define rendering hints for high-quality rendering
    RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

    // Set rendering hints to the graphics object
    g.setRenderingHints(renderingHints);

    // Define colors for different groups of shapes
    Color[] colors = new Color[]{Color.RED, Color.GREEN, Color.BLUE};
    int colorIndex = 0;
    boolean changeColor = false;

    // Define size and aspect ratio constraints for shapes
    double minSize = 10.0;
    double aspectRatio = 0.5;

    // Loop through the shapes and apply a complex algorithm to calculate their positions and rotations
    for (Shape shape : shapes) {
        // Check if the shape satisfies size and aspect ratio constraints
        Rectangle2D bounds = shape.getBounds2D();
        if (bounds.getWidth() < minSize || bounds.getHeight() < minSize || bounds.getWidth() / bounds.getHeight() > aspectRatio) {
            continue;
        }

        // Calculate position and rotation of the shape
        AffineTransform shapeAT = new AffineTransform(pixelAT);
        Rectangle2D.Double shapeBounds = new Rectangle2D.Double(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        Point2D center = new Point2D.Double(shapeBounds.getCenterX(), shapeBounds.getCenterY());
        shapeAT.rotate(Math.toRadians(30), center.getX(), center.getY());
        shapeAT.translate(center.getX(), center.getY());

        // Set color of the shape
        if (changeColor) {
            colorIndex = (colorIndex + 1) % colors.length;
        }
        changeColor = !changeColor;
        g.setColor(colors[colorIndex]);

        // Draw the shape
        Shape transformedShape = shapeAT.createTransformedShape(shape);
        g.fill(transformedShape);
    }
}