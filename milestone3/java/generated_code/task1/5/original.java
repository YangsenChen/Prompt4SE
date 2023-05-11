public void draw(java.awt.Graphics2D g, AffineTransform pixelAT) {
    g.setColor(color);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    g.setStroke(new java.awt.BasicStroke(0.0f));

    Rectangle2D clipRect = (Rectangle2D) g.getClip();
    Iterator siter = getShapes(g, pixelAT);
    while (siter.hasNext()) {
      Shape s = (Shape) siter.next();
      Rectangle2D shapeBounds = s.getBounds2D();
      if (shapeBounds.intersects(clipRect))
        g.draw(s);
    }
  }