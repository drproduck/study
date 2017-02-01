import java.util.Arrays;
import java.util.Set;
import java.util.Stack;
import java.util.StringJoiner;

/**
 * Created by drproduck on 1/30/17.
 */
public class convexHull {
    public static void main(String[] args) {
        Point a = new Point(0, 2);
        Point b = new Point(2, 0);
        Point c = new Point(2, 2);
        Point d = new Point(0, 0);
        Point[] array = {a, b, c, d};
        System.out.println(Arrays.toString(solution(array)));
    }

    public static Point[] solution(Point[] points) {
        int lowY = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[lowY].y > points[i].y) {
                lowY = i;
            }
            else if (points[lowY].y == points[i].y) {
                lowY = (points[lowY].x < points[i].x) ? lowY : i;
            }
        }
        swap(points, 0, lowY);
        Arrays.sort(points, 1, points.length-1, (Point a, Point b) -> Double.compare(slope(a, points[0]), slope(b, points[0])));
        int n = points.length-1;
       int m = 1;
        for (int i = 2; i <= n; i++) {
            while (ccw(points[m - 1], points[m], points[i]) <= 0) {
                if (m > 1) {
                    m -= 1;
                } else if (i == n) {
                    break;
                } else i += 1;
            }
            m += 1;
            swap(points, m, i);
        }
        return points;
    }

    static double slope(Point p1, Point p2) {
        return ((p1.y - p2.y) / (p1.x - p2.x));
    }
    static void swap(Point[] points, int p1, int p2) {
        Point temp = points[p1];
        points[p1] = points[p2];
        points[p2] = temp;
    }

    static double ccw(Point p1, Point p2, Point p3) {
       return  (p2.x - p1.x)*(p3.y - p1.y) - (p2.y - p1.y)*(p3.x - p1.x);
    }

    static class Point {
        double x,y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public static double angle(Point p1, Point p2, Point p3) {
            return  (p2.x - p1.x)*(p3.y - p1.y) - (p2.y - p1.y)*(p3.x - p1.x);
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    static class Function {
            double a,b; //represents y + ax + b = 0;

            public Function(Point x, Point y) {
                double delta = (x.y - y.y) / (x.x - y.x);
                a = -1 * delta;
                b = -(x.y - delta * x.x);
            }

            public double solve(Point x) {
                return x.y + a * x.x + b;
            }
        }
}

