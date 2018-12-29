package ObjectsClassesAndInterfaces;
import java.awt.*;

/**
 * This class subclasses DrawableRect and adds colors to the rectangle it draws.
 */
public class ColoredRect extends DrawableRect {
    // These are new fields defined by this class.
    // x1, y1, x2 and y2 are inherited from our super-superclass, Rect.
    protected Color border, fill;

    /**
     * This constructor uses super() to invoke the superclass constructor, and
     * also does some initialization of its own.
     */
    public ColoredRect(int x1, int y1, int x2, int y2,
                       Color border, Color fill)
    {
        super(x1, y1, x2, y2);
        this.border = border;
        this.fill = fill;
    }

    /**
     * This method overrides the draw() method of our superclass so that it
     * can make use of the colors that have been specified.
     */
    public void draw(Graphics g) {
        g.setColor(fill);
        g.fillRect(x1, y1, (x2-x1), (y2-y1));
        g.setColor(border);
        g.drawRect(x1, y1, (x2-x1), (y2-y1));
    }
}
