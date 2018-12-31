package ObjectsClassesAndInterfaces;

public class Search {
    public Object search(Object[] a, Object b, Sorter.Comparer c) {

    }

    public static class Test {
        static class SortableCircle extends Circle implements Sorter.Comparable {
            public SortableCircle(int radius, Circle.Dot center) { super(radius, center); }
            public int compareTo(Object other) { return radius - ((Circle)other).radius;}
        }

    }
}
