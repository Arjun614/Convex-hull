package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;



public class SimpleJOGLPrac3{

    public static void main(String[] args) {
        GLCapabilities capabilities=new GLCapabilities();
        final GLCanvas glcanvas=new GLCanvas(capabilities);
        Custom b=new Custom();
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(1280, 720);
        final JFrame frame=new JFrame("Basic frame");
        frame.add(glcanvas);
        frame.setSize(1280,720);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

//Wrapping stars in the boundary using convex hull