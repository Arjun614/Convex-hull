/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import org.yourorghere.ConvexHullUtil.Point;

/**
 *
 * @author srcoem
 */
public class Custom implements GLEventListener{
    private GLU glu;
    public void init(GLAutoDrawable drawable) {
        log("Init");
        
        glu = new GLU();
        GL gl = drawable.getGL();
        gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        gl.glViewport(0,0,1280,720);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(0,1280,0,720);
    }
    public static void log(String s){
        System.out.println(s);
    }

    public void display(GLAutoDrawable drawable) {
        log("Display");
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        Random random = new Random();
        int totalPoints = 45;
        
        ArrayList<Point> points = new ArrayList();
        for(int i=0; i< totalPoints; i++){
            points.add(new Point(getRandom(random, 50, 1200),getRandom(random,50, 700)));
            
        }
        System.out.println(points);
        ArrayList<Point> convexHullPoint = ConvexHullUtil.getConvexHullPoints(points);
        gl.glPointSize(4);
        drawPoints(points,.5f,.5f,.5f,gl);
        drawLoop(convexHullPoint,.3f,.3f,0.3f,gl);
        drawPoints(convexHullPoint,1,0,.2f,gl);
        System.out.println("Total distance : "+getTotalDistance(convexHullPoint));
    }
    public static int getRandom(Random random, int lower, int upper){
        return random.nextInt(upper - lower + 1) + lower;
    }
    public static void drawPoints(ArrayList<Point> points, float r, float g, float b, GL gl){
        gl.glColor3f(r, g, b);
        gl.glBegin(GL.GL_POINTS);
        for(Point p:points){
            gl.glVertex2d(p.x, p.y);
        }
        gl.glEnd();
    }
    public static void drawLoop(ArrayList<Point> points, float r, float g, float b, GL gl){
        gl.glColor3f(r, g, b);
        gl.glBegin(GL.GL_LINE_STRIP);
        for(Point p:points){
            gl.glVertex2d(p.x, p.y);
        }
        gl.glEnd();
    }
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        
    }
    public double getTotalDistance(ArrayList<Point> points){
        double total = 0;
        Point p1, p2;
        for(int i=0; i<points.size()-1; i++){
            p1 = points.get(i);
            p2 = points.get(i+1);
            total += Math.sqrt(Math.pow(p2.x - p1.x,2)   + Math.pow(p1.y - p2.y,2));
        }
        return total;
    }
    
}
