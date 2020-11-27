package org.robusta.macros;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

/* DRAWER CLASS */
public class Drawer {

	private static JFrame frame;
	private static DrawerSurface surface;

	private static int WIDTH;
	private static int HEIGTH;
	private final static int MAX_W = 600;
	private final static int MAX_H = 600;
	protected static int ZOOM_FACTOR = 1;

	// this method is just for test purpose
	public static void main(String[] args) throws Exception {
		new Drawer();
		reset(50, 50);
		for (int i = 0; i < 50; i++) {
			setPixel(i, i, 0, 0, 0);
			setPixel(-i, -i, 0, 0, 0);
			setPixel(i, -i, 0, 0, 0);
			setPixel(-i, i, 0, 0, 0);
			 //Utils.sleep(100);
		}

	}

	public Drawer() {
		surface = new DrawerSurface();
		frame = new JFrame("Robusta - Drawer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(MAX_W, MAX_H));

	}

	public static synchronized boolean setPixel(int x, int y, int r, int g, int b) {
		surface.setPixel(x, y, r, g, b);
		return isOutOfBoundaries(x, y);
	}

	public static synchronized boolean setPixel(int x, int y, int grayValue) {
		surface.setPixel(x, y, grayValue);
		return isOutOfBoundaries(x, y);
	}

	public static synchronized boolean setPixel(int x, int y, String color) {
		surface.setPixel(x, y, color);
		return isOutOfBoundaries(x, y);
	}

	public static synchronized int getWidth() {
		return Drawer.WIDTH;
	}

	public static synchronized int getHeight() {
		return Drawer.HEIGTH;
	}

	public static void reset(int w, int h) {
		Drawer.WIDTH = w;
		Drawer.HEIGTH = h;
		
		int wX2 = w * 2;
		int hX2 = h * 2;
		ZOOM_FACTOR = MAX_H / Math.max(wX2, hX2) ;
		Dimension dim = new Dimension(wX2 * ZOOM_FACTOR, hX2 * ZOOM_FACTOR);
		surface.zoom(ZOOM_FACTOR);
		
		surface.setSize(wX2 * ZOOM_FACTOR, hX2 * ZOOM_FACTOR);
		surface.setPreferredSize(dim);
		surface.setBackground(Color.LIGHT_GRAY);
		JPanel p1 = new JPanel();
		p1.setBackground(Color.LIGHT_GRAY);
		p1.add(surface);
		p1.setPreferredSize(dim);
		JPanel p2 = new JPanel();
		p2.setBackground(Color.DARK_GRAY);
		p2.add(p1);
		p2.setPreferredSize(new Dimension (wX2, hX2));
		frame.getContentPane().add(p2);
		frame.setLocation(780, 0);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

	}

	private static boolean isOutOfBoundaries(int x, int y) {
		if (x > Drawer.WIDTH || x < -Drawer.WIDTH || y > Drawer.HEIGTH || y < -Drawer.HEIGTH) {
			return false;
		}
		return true;
	}
}

/* DRAWER SURFACE CLASS */
class DrawerSurface extends JPanel {
	private static final long serialVersionUID = -3789133815287680248L;
	private Map<Point, Color> points;
	private double zoom = 1;

	DrawerSurface() {
		this.points = new ConcurrentHashMap<Point, Color>();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.translate(Drawer.getWidth() * Drawer.ZOOM_FACTOR, 
				Drawer.getHeight() * Drawer.ZOOM_FACTOR);
		g.setPaintMode();
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.scale(this.zoom, this.zoom);
		this.points.forEach((p, c) -> {
			g.setColor(c);
			g.drawLine(p.x, -p.y, p.x, -p.y);
		});
	}

	protected void zoom(double z) {
		this.zoom = z;
		this.repaint();
	}

	protected void setPixel(int x, int y, String colorStr) {
		this.points.put(new Point(x, y), ColorFactory.getColor(colorStr));
		this.repaint();
	}

	protected void setPixel(int x, int y, int r, int g, int b) {
		this.points.put(new Point(x, y), new Color(r, g, b, 255));
		this.repaint();
	}

	protected void setPixel(int x, int y, int v) {
		v = v < 0 ? 0 : v > 255 ? 255 : v;
		v = Math.abs(255 - v);
		this.points.put(new Point(x, y), new Color(0, 0, 0, v));
		this.repaint();
	}
}

/* POINT CLASS */
class Point {
	public int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		Point p = (Point) obj;
		return this.x == p.x && this.y == p.y;
	}

	@Override
	public int hashCode() {
		return x * 31 + y;
	}

}

/* COLOR FACTORY CLASS */
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