package han.algor.week5.kdTree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.*;

/**
 * Created by zh355245849 on 2017/2/24.
 */
public class KdTree {

    private class KdTreeNode {
        private double x, y;
        private KdTreeNode left, right;
        boolean isVertical;
        public KdTreeNode(double x, double y, boolean isVertical) {
            this.x = x;
            this.y = y;
            this.left = this.right = null;
            this.isVertical = isVertical;
        }
    }

    private KdTreeNode root;
    private int size;
    private RectHV canvas;

    public KdTree() {
        // construct an empty set of points
        this.size = 0;
        this.root = null;
        this.canvas = new RectHV(0, 0, 1, 1);
    }
    public boolean isEmpty() {
        // is the set empty?
        return size == 0;
    }

    public int size() {
        // number of points in the set
        return size;
    }

    public void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        root = insert(root, p, true);
    }

    private KdTreeNode insert(KdTreeNode root, Point2D point, boolean isVertical) {
        if (root == null) {
            size++;
            return new KdTreeNode(point.x(), point.y(), isVertical);
        }
        if (root.x == point.x() && root.y == point.y()) {
            return root;
        }
        if (root.isVertical && point.x() < root.x || (!root.isVertical && point.y() < root.y)) {
            root.left = insert(root.left, point, !isVertical);
        }
        else {
            root.right = insert(root.right, point, !isVertical);
        }
        return root;
    }

    public boolean contains(Point2D p) {
        // does the set contain point p?
        return contains(root, p);
    }

    private boolean contains(KdTreeNode root, Point2D point) {
        if (root == null) {
            return false;
        }
        if (root.x == point.x() && root.y == point.y()) {
            return true;
        }
        if (root.isVertical && root.x > point.x() || (!root.isVertical && root.y > point.y())) {
            return contains(root.left, point);
        }
        else {
            return contains(root.right, point);
        }
    }

    public void draw() {
        // draw all points to standard draw
        StdDraw.setPenColor(Color.black);
        StdDraw.setScale(0, 1);
        StdDraw.setPenRadius();
        canvas.draw();
        draw(root, canvas);
    }

    private void draw(KdTreeNode root, RectHV canvas) {
        if (root == null) {
            return ;
        }
        StdDraw.setPenColor(Color.black);
        StdDraw.setPenRadius();
        new Point2D(root.x, root.y).draw();
        Point2D lbound = null, rbound = null;
        if (root.isVertical) {
            StdDraw.setPenColor(Color.red);
            lbound = new Point2D(root.x, canvas.ymin());
            rbound = new Point2D(root.x, canvas.ymax());
        }
        else {
            StdDraw.setPenColor(Color.blue);
            lbound = new Point2D(canvas.xmin(), root.y);
            rbound = new Point2D(canvas.xmax(), root.y);
        }
        lbound.drawTo(rbound);
        draw(root.left, canvas);
        draw(root.right, canvas);
    }

    public Iterable<Point2D> range(RectHV rect) {
        // all points that are inside the rectangle
        Queue<Point2D> iter = new LinkedList<>();
        range(root, canvas, rect, iter);
        return iter;
    }

    private void range(KdTreeNode root, RectHV canvas1, RectHV rect, Queue<Point2D> iter) {
        if (root == null) {
            return ;
        }
        if (canvas1.intersects(rect)) {
            Point2D pp = new Point2D(root.x, root.y);
            if (rect.contains(pp)) {
                iter.add(pp);
            }

            if (root.isVertical) {
                range(root.left, new RectHV(canvas1.xmin(), canvas1.ymin(), root.x, canvas1.ymax()), rect, iter);
            }
            else {
                range(root.left, new RectHV(canvas1.xmin(), canvas1.ymin(), canvas1.xmax(), root.y), rect, iter);
            }

            if (root.isVertical) {
                range(root.right, new RectHV(root.x, canvas1.ymin(), canvas1.xmax(), canvas1.ymax()), rect, iter);
            }
            else {
                range(root.right, new RectHV(canvas1.xmin(), root.y, canvas1.xmax(), canvas1.ymax()), rect, iter);
            }
        }
    }

    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to point p; null if the set is empty
        return nearest(root, canvas, p, null);
    }

    private Point2D nearest(KdTreeNode root, RectHV rect, Point2D p, Point2D candidate) {
        if (root == null) {
            return candidate;
        }
        double p2p = 0.0;
        double p2r = 0.0;
        RectHV left = null, right = null;
        Point2D nearest = candidate;
        if (nearest != null) {
            p2p = p.distanceSquaredTo(nearest);
            p2r = rect.distanceSquaredTo(p);
        }

        if (nearest == null || p2p > p2r) {
            Point2D point = new Point2D(root.x, root.y);
            if (nearest == null || p2p > p.distanceSquaredTo(point))
                nearest = point;

            if (root.isVertical) {
                left = new RectHV(rect.xmin(), rect.ymin(), root.x, rect.ymax());
                right = new RectHV(root.x, rect.ymin(), rect.xmax(), rect.ymax());

                if (p.x() < root.x) {
                    nearest = nearest(root.left, left, p, nearest);
                    nearest = nearest(root.right, right, p, nearest);
                } else {
                    nearest = nearest(root.right, right, p, nearest);
                    nearest = nearest(root.left, left, p, nearest);
                }
            } else {
                left = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), root.y);
                right = new RectHV(rect.xmin(), root.y, rect.xmax(), rect.ymax());

                if (p.y() < root.y) {
                    nearest = nearest(root.left, left, p, nearest);
                    nearest = nearest(root.right, right, p, nearest);
                } else {
                    nearest = nearest(root.right, right, p, nearest);
                    nearest = nearest(root.left, left, p, nearest);
                }
            }
        }
        return candidate;
    }

    public static void main(String[] args) {

    }
}
