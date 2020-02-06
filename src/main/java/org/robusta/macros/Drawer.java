package org.robusta.macros;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

/*
 * ***************************
 * ******** DRAWER CLASS *****
 * ***************************
 */
public class Drawer {

	private static JFrame frame;
	private static DrawerSurface surface;

	public Drawer() {
		surface = new DrawerSurface();
		frame = new JFrame("Robusta - Drawer");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static synchronized boolean setPixel(int x, int y, int r, int g, int b) {
		surface.setPixel(x, y, r, g, b);
		return checkBoundaries(x, y);
	}

	public static synchronized boolean setPixel(int x, int y, int grayValue) {
		surface.setPixel(x, y, grayValue);
		return checkBoundaries(x, y);
	}

	public static synchronized boolean setPixel(int x, int y, String color) {
		surface.setPixel(x, y, color);
		return checkBoundaries(x, y);
	}

	/*
	 * public static synchronized int getPixel(int x, int y) { return
	 * surface.getPixel(x, y); }
	 */
	public static synchronized int getWidth() {
		return frame.getWidth() / 2;
	}

	public static synchronized int getHeight() {
		return frame.getHeight() / 2;
	}

	public static void reset(int w, int h) {
		int width = w * 2;
		int height = h * 2;
		frame.setLocation(780, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(false);
		frame.setSize(width, height);
		surface.setSize(width, height);
		frame.getContentPane().add(surface);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	private static boolean checkBoundaries(int x, int y) {
		boolean isOk = true;
		if (! frame.isVisible()) {
			isOk = false;
		}
		if (x > frame.getWidth() / 2 || x < -frame.getWidth() / 2) {
			isOk = false;
		}
		if (y > frame.getHeight() / 2 || y < -frame.getHeight() / 2) {
			isOk = false;
		}
		return isOk;

	}
}

/*
 * *********************************** ******** DRAWER SURFACE CLASS *****
 * ***********************************
 */
class DrawerSurface extends JComponent {
	private static final long serialVersionUID = -3789133815287680248L;
	private List<Point> points;

	DrawerSurface() {
		this.points = new ArrayList<Point>();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < this.points.size(); i++) {
			Point p = this.points.get(i);
			g.setColor(p.color);
			g.drawLine(p.x, p.y, p.x, p.y);
		}
	}

	protected void setPixel(int x, int y, String colorStr) {
		x = x + (this.getWidth() / 2);
		y = -y + (this.getHeight() / 2);
		this.points.add(new Point(x, y, ColorFactory.getColor(colorStr)));
		this.repaint();
	}

	protected void setPixel(int x, int y, int r, int g, int b) {
		x = x + (this.getWidth() / 2);
		y = -y + (this.getHeight() / 2);
		this.points.add(new Point(x, y, new Color(r, g, b, 0)));
		this.repaint();
	}

	protected void setPixel(int x, int y, int greyValue) {
		x = x + (this.getWidth() / 2);
		y = -y + (this.getHeight() / 2);
		this.points.add(new Point(x, y, new Color(0, 0, 0, greyValue)));
		this.repaint();
	}
}

/*
 * *************************** ******** POINT CLASS ******
 * ***************************
 */
class Point {
	public int x, y;
	public Color color;

	public Point(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	@Override
	public boolean equals(Object obj) {
		Point p = (Point) obj;
		return this.x == p.x && this.y == p.y && this.color.equals(p);
	}
}

/*
 * *************************************** ************ COLOR FACTORY CLASS
 * ****** ***************************************
 */
class ColorFactory {

	private static HashMap<String, Color> colors = createColors();

	private static HashMap<String, Color> createColors() {
		HashMap<String, Color> colors = new HashMap<>(12);

		colors.put("black", Color.BLACK);
		colors.put("blue", Color.BLUE);
		colors.put("cyan", Color.CYAN);
		colors.put("gray", Color.GRAY);
		colors.put("grey", Color.GRAY);
		colors.put("green", Color.GREEN);
		colors.put("magenta", Color.MAGENTA);
		colors.put("orange", Color.ORANGE);
		colors.put("red", Color.RED);
		colors.put("white", Color.WHITE);
		colors.put("yellow", Color.YELLOW);
		colors.put("pink", Color.PINK);

		return colors;
	}

	public static Color getColor(String color) {
		color = color.toLowerCase();
		return colors.getOrDefault(color, Color.BLACK);
	}
}