package org.robusta.macros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Drawer extends JFrame {

    private static Drawer INSTANCE;
	private static volatile DrawerSurface surface;
	private static volatile boolean started = false;
	private static final long serialVersionUID = 654313216000L;

	private Drawer() {
		super("Drawer");
		surface = new DrawerSurface();
		started = false;
	}

    public static synchronized Drawer getInstance() {
        if(INSTANCE == null) {
            return new Drawer();
        } else {
            return INSTANCE;
        }
    }

    public static synchronized void reset(int width, int height) {
        surface.reset(width * 2, height * 2);
        surface.setPreferredSize(new Dimension(width * 2, height * 2));
        started = true;
	}

    public void start() {
	    if(started) {
            add(surface);

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    surface.clear();
                }
            });

            setTitle("Points");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setVisible(true);

            pack();
        }
	}

    public static synchronized void setPixel(int x, int y, int grayValue) {
	    surface.setPixel(x, y, grayValue);
    }

    public static synchronized void setPixel(int x, int y, String color) {
	    surface.setPixel(x, y, color);
    }

    public static synchronized int getPixel(int x, int y) {
	    return surface.getPixel(x, y);
    }

}
