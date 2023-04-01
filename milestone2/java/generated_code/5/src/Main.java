import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    @Test
    void testDrawWithNoShapes() {
        Graphics2D g = (Graphics2D) new DemoGraphics();
        Color color = Color.BLACK;
        AffineTransform pixelAT = new AffineTransform();

        ExampleDraw drawObj = new ExampleDraw(color);
        drawObj.draw(g, pixelAT);

        Iterator siter = getShapes(g, pixelAT);
        assertFalse(siter.hasNext(), "No shapes should have been drawn");
    }

    @Test
    void testDrawWithOneShape() {
        Graphics2D g = (Graphics2D) new DemoGraphics();
        Color color = Color.RED;
        AffineTransform pixelAT = new AffineTransform();

        ArrayList<Shape> shapes = new ArrayList<Shape>();
        shapes.add(new Rectangle2D.Double(10, 10, 50, 50));

        ExampleDraw drawObj = new ExampleDraw(color, shapes);
        drawObj.draw(g, pixelAT);

        Iterator siter = getShapes(g, pixelAT);
        assertTrue(siter.hasNext(), "A shape should have been drawn");
    }

    @Test
    void testDrawWithMultipleShapes() {
        Graphics2D g = (Graphics2D) new DemoGraphics();
        Color color = Color.BLUE;
        AffineTransform pixelAT = new AffineTransform();

        ArrayList<Shape> shapes = new ArrayList<Shape>();
        shapes.add(new Rectangle2D.Double(10, 10, 50, 50));
        shapes.add(new Ellipse2D.Double(50, 50, 30, 40));

        ExampleDraw drawObj = new ExampleDraw(color, shapes);
        drawObj.draw(g, pixelAT);

        Iterator siter = getShapes(g, pixelAT);
        assertTrue(siter.hasNext(), "At least one shape should have been drawn");
    }

    private static Iterator getShapes(Graphics2D g, AffineTransform pixelAT) {
        // Returns an empty iterator for now
        return new ArrayList<Shape>().iterator();
    }
}

class ExampleDraw {
    private final Color color;
    private final ArrayList<Shape> shapes;

    public ExampleDraw(Color color) {
        this.color = color;
        this.shapes = new ArrayList<Shape>();
    }

    public ExampleDraw(Color color, ArrayList<Shape> shapes) {
        this.color = color;
        this.shapes = shapes;
    }

    public void draw(Graphics2D g, AffineTransform pixelAT) {
        g.setColor(color);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g.setStroke(new java.awt.BasicStroke(0.0f));

        Rectangle2D clipRect = (Rectangle2D) g.getClip();

        Iterator siter = shapes.iterator();
        while (siter.hasNext()) {
            Shape s = (Shape) siter.next();
            Rectangle2D shapeBounds = s.getBounds2D();
            if (shapeBounds.intersects(clipRect))
                g.draw(s);
        }
    }
}

class DemoGraphics extends Graphics {
    @Override
    public Graphics create() {
        return null;
    }

    @Override
    public void translate(int x, int y) {

    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void setColor(Color c) {

    }

    @Override
    public void setPaintMode() {

    }

    @Override
    public void setXORMode(Color c1) {

    }

    @Override
    public Font getFont() {
        return null;
    }

    @Override
    public void setFont(Font font) {

    }

    @Override
    public FontMetrics getFontMetrics(Font f) {
        return null;
    }

    @Override
    public Rectangle getClipBounds() {
        return null;
    }

    @Override
    public void clipRect(int x, int y, int width, int height) {

    }

    @Override
    public void setClip(int x, int y, int width, int height) {

    }

    @Override
    public Shape getClip() {
        return null;
    }

    @Override
    public void setClip(Shape clip) {

    }

    @Override
    public void copyArea(int x, int y, int width, int height, int dx, int dy) {

    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {

    }

    @Override
    public void fillRect(int x, int y, int width, int height) {

    }

    @Override
    public void clearRect(int x, int y, int width, int height) {

    }

    @Override
    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

    }

    @Override
    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

    }

    @Override
    public void drawOval(int x, int y, int width, int height) {

    }

    @Override
    public void fillOval(int x, int y, int width, int height) {

    }

    @Override
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

    }

    @Override
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

    }

    @Override
    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {

    }

    @Override
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {

    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {

    }

    @Override
    public void drawString(String str, int x, int y) {

    }

    @Override
    public void drawString(AttributedCharacterIterator iterator, int x, int y) {

    }

    @Override
    public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
        return false;
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
        return false;
    }

    @Override
    public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
        return false;
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
        return false;
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
        return false;
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
        return false;
    }

    @Override
    public void dispose() {

    }
    // Implement methods of abstract class
}