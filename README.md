# Convex hull 2D




## What is convex hull
https://en.wikipedia.org/wiki/File:ConvexHull.svg
* Convex hull points are those points which when connected together, wrap all other points inside them. And the distance of the path joining those points should be minimum.
* The above figure displays an elastic band wrapped around random points. All the points touching the elastic band are convex hull points.



## How to use ConvexHullUtil class

The only important class in this program is the ConvexHullUtil class. 

    * Simply pass a list of points to a static method named
      'getConvexHullPoints(ArrayList<Point> points)' and this method will return an 
      arraylist of convexhull points which can then be used in whatever ways. 
    * Point is a static inner class in ConvexHullUtil class.
    * In this project these points were simply drawn on the screen. 
    * Rest of the methods are self explanatory.


## About project
 this project was implemented in  JOGL ( Java Open GL) for visualising convex hull points. 
 IDE used - NET beans 


#### Made by 
      Arjun Doye 
      Roll: 48
      III year
