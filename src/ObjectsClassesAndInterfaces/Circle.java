package ObjectsClassesAndInterfaces;

/**
 * A Circle class that is similar to the Rect class. Define a move() method
 * and an isInside() method. (Recall that a circle is defined as all points
 * within a given radius from the center. Test for insideness by using the
 * Pythagorean theorem to compute the distance between a point and the center
 * of the circle.) Also, define a boundingBox() method that returns the smallest
 * Rect that encloses the complete Circle. It contains a simple program to test
 * the methods.
 */
public class Circle {
    /* A Dot class that represents any dot on a xy-plane */
    public static class Dot {
        public int x, y;
        public Dot(int x, int y) { this.x = x; this.y = y; }

        public String toString() { return "(" + x + ", " + y + ")"; }

        public void move(int deltax, int deltay) {
            x += deltax;
            y += deltay;
        }

        public int distance(Dot dot) {
            return (int) Math.hypot(x - dot.x, y - dot.y);
        }
    }
    public int radius;
    public Dot center;

    public Circle(int radius, Dot center) {
        this.radius = radius;
        this.center = center;
    }

    /**
     * A move() method to move the circle, it calls Center.move()
     * as the movement of a circle is actually a movement of its center
     */
    public void move(int deltax, int deltay) {
        center.move(deltax, deltay);
    }

    /**
     * To see if a point(x, y) is inside of a circle, compute the distance between
     * it and the center(xc, yc) using Pythagorean theorem. In this case, the formula is:
     * distance = Math.sqrt(Math.pow(x - xc) + Math.pow(y - yc))
     */
    public boolean isInside(Dot dot) {
        return center.distance(dot) < radius*radius;
    }

    public Rect boundingBox() {
        return new Rect(center.x - radius,
                        center.y + radius,
                        center.x + radius,
                        center.y - radius);
    }

    public String toString() {
        return "radius: " + radius + "; " + "center: " + center.toString();
    }

    /* A simple test program*/
    public static class Test {
        public static void main(String[] args) {
            Dot center = new Dot(0, 0);
            Circle circle = new Circle(1, center);
            System.out.println(circle);
            circle.move(1, 1);
            System.out.println(circle);
            Dot d = new Dot(2, 2);
            if(circle.isInside(d))
                System.out.println(d + "is inside of circle: " + circle);
            else
                System.out.println(d + "is not inside of circle： " + circle);
            System.out.println(circle.boundingBox());
        }
    }
}
