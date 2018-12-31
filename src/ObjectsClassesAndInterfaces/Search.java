package ObjectsClassesAndInterfaces;

public class Search {
    public static int search(Object[] a, Object b) {
        Sorter.Comparer c = new Sorter.Comparer() {
            public int compare(Object a, Object b) {
                return ((Sorter.Comparable)a).compareTo((Sorter.Comparable)b);
            }
        };
        return search(a, b, c);
    }

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

    public static class Test {
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
