package ObjectsClassesAndInterfaces;

/**
 * This is a subclass of Rect that allows itself to be drawn on a screen.
 * It inherits all the fields and methods of Rect.
 * It relies on the java.awt.Graphics object to preform the drawing.
 */
public class DrawableRect extends Rect {
    /* The DrawableRect constructor just invokes the Rect() constructor */
    public DrawableRect(int x1, int y1, int x2, int y2) { super(x1, y1, x2, y2); }

    /* This is the new method defined by DrawableRect */
    public void draw(java.awt.Graphics g) {
        g.drawRect(x1, y1, (x2-x1), (y2-y1));
    }
}
