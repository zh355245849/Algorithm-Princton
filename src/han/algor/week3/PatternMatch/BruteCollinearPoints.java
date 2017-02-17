package han.algor.week3.PatternMatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Created by zh355245849 on 2017/2/16.
 */
public class BruteCollinearPoints {

    private int count;
    private  LineSegment[] lines;
    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null) {
            throw new NullPointerException();
        }
        Point[] copy = Arrays.copyOf(points, points.length);
        Arrays.sort(copy);
        for (int i = 1; i < copy.length; i++) {
            if (copy[i].compareTo(copy[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        List<LineSegment> list = new ArrayList<LineSegment>();
        for (int i = 0; i < copy.length; i++) {
            Point p1 = copy[i];
            for (int j = i + 1; j < copy.length; j++) {
                Point p2 = copy[j];
                for (int k = j + 1; k < copy.length; k++) {
                    Point p3 = copy[k];
                    for (int m = k + 1; m < copy.length; m++) {
                        Point p4 = copy[m];
                        if (p1.slopeTo(p2) == p2.slopeTo(p3) && p2.slopeTo(p3) == p3.slopeTo(p4)) {
                            p1.drawTo(p4);
                            list.add(new LineSegment(p1, p4));
                        }
                    }
                }
            }
        }
        this.lines = list.toArray(new LineSegment[list.size()]);
        this.count = list.size();
    }

    public int numberOfSegments() {
        // the number of line segments
        return count;
    }

    public LineSegment[] segments() {
        // the line segments
        return Arrays.copyOf(lines, count);
    }

}
