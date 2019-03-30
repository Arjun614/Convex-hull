/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

/**
 *
 * @author Arjun
 */
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ConvexHullUtil {
    public static class Point {
        double x,y;
        public Point(double x, double y){
            this.x = x;
            this.y = y;
        }
        public boolean equals(Point p){
            if(p.x == x && p.y == y)
                return true;
            return false;
        }
        public String toString(){
            return "("+x+","+y+")";
        }
    }
    public static ArrayList<Point> getConvexHullPoints(ArrayList<Point> points){
        ArrayList<Point> xSortedList = getXSortedList(points);
        ArrayList<Point> ySortedList = getYSortedList(points);
        System.out.println(xSortedList);
        System.out.println(ySortedList);
        ArrayList<Point> hullPoints = new ArrayList();
        Point leftMost = xSortedList.get(0);
        Point rightMost = xSortedList.get(xSortedList.size()-1);
        Point bottomMost = ySortedList.get(0);
        Point topMost = ySortedList.get(ySortedList.size()-1);


        Point currentPoint = bottomMost;
        //first travelling till rightmost point
        while(! currentPoint.equals(rightMost)){
            int pos = getPosInXSorted(xSortedList,currentPoint);
            hullPoints.add(currentPoint);
            double minSlope = 99999999;
            Point minSlopePoint = currentPoint;
            for(int i = pos+1; i<xSortedList.size(); i++){
                double slope = getSlope(currentPoint, xSortedList.get(i));
                
                if(slope <= minSlope){
                    minSlope = slope;
                    minSlopePoint = xSortedList.get(i);
                }
            }
            currentPoint = minSlopePoint;

            System.out.println("1");
        }
        //now current point is rightmost point and is not in hull point list
        while(! currentPoint.equals(topMost)){
            int pos = getPosInYSorted(ySortedList,currentPoint);
            hullPoints.add(currentPoint);
            double minSlope = 99999999;
            Point minSlopePoint = currentPoint;
            for(int i = pos+1; i<ySortedList.size(); i++){
                double slope = getSlope(currentPoint, ySortedList.get(i));
                if(slope <= minSlope){
                    minSlope = slope;
                    minSlopePoint = ySortedList.get(i);
                }
            }
            currentPoint = minSlopePoint;

            System.out.println("2");
        }
        //now current point is topmost point and is not in hull point list
        while(! currentPoint.equals(leftMost)){
            int pos = getPosInXSorted(xSortedList,currentPoint);
            hullPoints.add(currentPoint);
            double minSlope = 999999;
            Point minSlopePoint = currentPoint;
            for(int i = pos-1; i>=0; i--){
                double slope = getSlope(currentPoint, xSortedList.get(i));
                System.out.println(slope);
                if(slope <= minSlope){
                    minSlope = slope;
                    minSlopePoint = xSortedList.get(i);
                }
            }
            currentPoint = minSlopePoint;

            System.out.println("3");
        }
        //now current point is leftmost point and is not in hull point list
        while(! currentPoint.equals(bottomMost)){
            int pos = getPosInYSorted(ySortedList,currentPoint);
            hullPoints.add(currentPoint);
            double minSlope = 999999;
            Point minSlopePoint = currentPoint;
            for(int i = pos-1; i>=0; i--){
                double slope = getSlope(currentPoint, ySortedList.get(i));
                if(slope <= minSlope){
                    minSlope = slope;
                    minSlopePoint = ySortedList.get(i);
                }
            }
            currentPoint = minSlopePoint;

            System.out.println("4");
        }
        System.out.println("\nBottom point "+currentPoint);
        //now current point is bottommost point and is not in hull point list
        hullPoints.add(currentPoint);
        return hullPoints;
    }
    public static ArrayList<Point> getXSortedList(ArrayList<Point> points){
        ArrayList<Point> newList = new ArrayList();
        newList.addAll(points);
        Collections.sort(newList,xComparator);
        return newList;
    }
    public static ArrayList<Point> getYSortedList(ArrayList<Point> points){
        ArrayList<Point> newList = new ArrayList();
        newList.addAll(points);
        Collections.sort(newList,yComparator);
        return newList;
    }
    public static int getPosInXSorted(ArrayList<Point> xSortedList, Point p){
        return Collections.binarySearch(xSortedList,p,xComparator);
    }
    public static int getPosInYSorted(ArrayList<Point> ySortedList, Point p){
        return Collections.binarySearch(ySortedList,p,yComparator);
    }
    private static Comparator<Point> xComparator = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            double d = o1.x - o2.x;
            if (d > 0)
                return 1;
            if (d < 0)
                return -1;
            double d1 = o1.y - o2.y;
            if (d1 > 0)
                return 1;
            if (d1 < 0)
                return -1;
            return 0;
        }
    };
    private static Comparator<Point> yComparator = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            double d = o1.y - o2.y;
            if (d > 0)
                return 1;
            if (d < 0)
                return -1;
            double d1 = o1.x - o2.x;
            if (d1 > 0)
                return 1;
            if (d1 < 0)
                return -1;
            return 0;
        }
    };
    public static double getSlope(Point p1, Point p2){
        double slope = ((double)p2.y - (double)p1.y) / ((double)p2.x - (double)p1.x);
        if(Double.POSITIVE_INFINITY == slope)
            return 999999;
        if(Double.NEGATIVE_INFINITY == slope)
            return 999999;
        return slope;
    }
}
