package han.algor.week5.kdTree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

/**
 * Created by zh355245849 on 2017/2/24.
 */
public class PointSET {

    private TreeSet<Point2D> points;

    public PointSET() {
        // construct an empty set of points
        points = new TreeSet<>();
    }

    public boolean isEmpty() {
        // is the set empty?
        return points.isEmpty();
    }

    public int size() {
        // number of points in the set
        return points.size();
    }

    public void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        if (!points.contains(p)) {
            points.add(p);
        }
        return ;
    }

    public boolean contains(Point2D p) {
        // does the set contain point p?
        return points.contains(p);
    }

    public void draw() {
        // draw all points to standard draw
        for (Point2D p : points) {
            StdDraw.point(p.x(), p.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        // all points that are inside the rectangle
        Queue<Point2D> queue = new LinkedList<>();
        for (Point2D p : points) {
            if (rect.contains(p)) {
                queue.offer(p);
            }
        }
        return queue;
    }

    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to point p; null if the set is empty
        Point2D nearest = null;
        double minDist = Integer.MAX_VALUE;
        if (points.size() == 0) {
            return nearest;
        }
        for (Point2D pp : points) {
            double distance = p.distanceTo(pp);
            if (distance < minDist) {
                nearest = pp;
                minDist = distance;
            }
        }
        return nearest;
    }

    public static void main(String[] args) {
        // unit testing of the methods (optional)
        PointSET ps = new PointSET();
        Point2D p1 = new Point2D(1, 1);
        Point2D p2 = new Point2D(2, 1);
        Point2D p3 = new Point2D(2, 2);
        Point2D p4 = new Point2D(3, 3);
        ps.insert(p1);
        ps.insert(p2);
        ps.insert(p3);
        ps.insert(p4);
        RectHV rect = new RectHV(0, 0, 2, 3);
        StdOut.println(ps.nearest(new Point2D(1.6, 1)));
        StdOut.println(ps.range(rect));
    }
}