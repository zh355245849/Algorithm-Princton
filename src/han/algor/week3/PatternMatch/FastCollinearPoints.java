package han.algor.week3.PatternMatch;

import java.util.*;

/**
 * Created by zh355245849 on 2017/2/16.
 */
public class FastCollinearPoints {
    private int count;
    private  LineSegment[] lines;
    private HashMap<Double, List<Point>> map;
    private List<LineSegment> list = new ArrayList<LineSegment>();
    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        if (points == null) {
            throw new NullPointerException();
        }
        this.map = new HashMap<>();
        Point[] copy = Arrays.copyOf(points, points.length);
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        for (Point pivot : points) {
            Arrays.sort(copy, pivot.slopeOrder());
            double preSlop = Double.POSITIVE_INFINITY;
            double slop = 0.0;
            List<Point> pp = new ArrayList<>();
            for (int j = 1;  j < copy.length; j++) {
                slop = pivot.slopeTo(copy[j]);

                if (slop == preSlop) {
                    pp.add(copy[j]);
                }
                else {
                    if (pp.size() >= 3) {
                        pp.add(pivot);
                        addToSegmentsIfNotExist(pp, preSlop);
                    }
                    pp.clear();
                    pp.add(copy[j]);
                }
                preSlop = slop;
            }
            if (pp.size() >= 3) {
                pp.add(pivot);
                addToSegmentsIfNotExist(pp, slop);
            }

        }
    }

    private void addToSegmentsIfNotExist(List<Point> pp, double slop) {
        List<Point> endPoints = map.get(slop);
        Collections.sort(pp);
        Point start = pp.get(0);
        Point end = pp.get(pp.size() - 1);
        if (endPoints == null) {
            List<Point> temp = new ArrayList<>();
            temp.add(end);
            map.put(slop, temp);
            list.add(new LineSegment(start, end));
        }
        else {
            if (endPoints.contains(end)) {
                return ;
            }
            endPoints.add(end);
            list.add(new LineSegment(start, end));
        }
    }

    public int numberOfSegments() {
        // the number of line segments
        return count;
    }

    public LineSegment[] segments() {
        return list.toArray(new LineSegment[list.size()]);
    }
}
