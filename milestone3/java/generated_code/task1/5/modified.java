public void draw(java.awt.Graphics2D g, AffineTransform pixelAT) {
    // Define the rendering hint and stroke for shapes to be drawn
    RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    java.awt.Stroke stroke = new java.awt.BasicStroke(0.0f);

    // Set rendering hints and stroke to the graphics object
    g.setRenderingHints(renderingHints);
    g.setStroke(stroke);

    // Set color for the shapes
    g.setColor(color);

    // Get the clip rectangle of the graphics object
    Rectangle2D clipRect = (Rectangle2D) g.getClip();

    // Get the shapes iterator based on the graphics object and AffineTransform
    Iterator siter = getShapes(g, pixelAT);

    // Loop through the shapes and draw only the ones that intersect with the clip rectangle
    while (siter.hasNext()) {
        Shape s = (Shape) siter.next();
        Rectangle2D shapeBounds = s.getBounds2D();
        if (shapeBounds.intersects(clipRect)) {
            g.draw(s);
        }
    }
}