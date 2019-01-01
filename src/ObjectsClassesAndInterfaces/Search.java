package ObjectsClassesAndInterfaces;

/**
 * Using the Sorter.Comparer and Sorter.Comparable interfaces. A Search class with a static
 * search() method that performs an efficient binary search for a specified object within a
 * sorted array of objects. If the object is found in the array, search() should return the
 * array index at which it is located. Otherwise, it should return -1.
 */
public class Search {
    /**
     * Search a specified object b within a sorted array of objects a. Objects must be implemented with
     * Sorter.Comparable interfaces.
     * @param a: a sorted array of objects
     * @param b: a specified object that needs to be found
     * @return: an array index or -1
     */
    public static int search(Object[] a, Object b) {
        Sorter.Comparer c = new Sorter.Comparer() {
            public int compare(Object a, Object b) {
                return ((Sorter.Comparable)a).compareTo((Sorter.Comparable)b);
            }
        };
        return search(a, b, c);
    }

    /**
     * This is the main search routine, which performs an efficient binary search
     * algorithm.
     * @param a: a sorted array of objects
     * @param b: a specified object that needs to be found
     * @param c: a comparer used to compare two objects
     * @return:
     *        - if b is found, returns an array index at which it is located;
     *        - otherwise, returns -1
     */
    public static int search(Object[] a, Object b, Sorter.Comparer c) {
        int l = 0;
        int r = a.length - 1;
        for(;;) {
            if(l > r) return -1;
            int m = (l + r) / 2;
            int result = c.compare(a[m], b);
            if(result == 0)
                return m;
            else if(result > 0)
                r = m - 1;
            else
                l = m + 1;
        }
    }

    /**
     * This nested class defines a test program that demonstrates how to use
     * Search class to search specified Circle within a sorted array of SortableCircle.
     */
    public static class Test {
        /**
         * This subclass of Circle implements the Sorter.Comparable interface
         * and defines a compareTo() method for comparing circles.
         * It compares circles based on their radius.
         */
        static class SortableCircle extends Circle implements Sorter.Comparable {
            public SortableCircle(int radius, Circle.Dot center) { super(radius, center); }
            public int compareTo(Object other) { return radius - ((Circle)other).radius;}
        }

        public static void main(String[] args) {
            SortableCircle[] a = new SortableCircle[5];
            for(int i=0; i<5; i++) {
                Circle.Dot center = new Circle.Dot((int)(Math.random()*10),
                                                    (int)(Math.random()*10));
                a[i] = new SortableCircle((int)(Math.random()*10), center);
            }
            Sorter.sort(a);
            Circle.Dot center = new Circle.Dot(0, 0);
            SortableCircle b = new SortableCircle(5, center);
            System.out.println(search(a, b));
        }
    }
}
