package org.robusta.macros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

/* DRAWER CLASS */
public class Drawer {

	private static JFrame frame;
	private static DrawerSurface surface;

	// this method is just for test purpose
	public static void main(String[] args) throws Exception {
		new Drawer();
		reset(200, 200);
	    for (int i = 4; i < 150; i++) setPixel(i, i, 230, 230, 230);
	}

	public Drawer() {
		surface = new DrawerSurface();
		frame = new JFrame("Robusta - Drawer");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.addMouseWheelListener((e) -> {
			Drawer.surface.zoom(e.getPreciseWheelRotation());
		});
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
		return frame.getWidth() / 2;
	}

	public static synchronized int getHeight() {
		return frame.getHeight() / 2;
	}

	public static void reset(int w, int h) {
		int width = w * 2;
		int height = h * 2;
		TextField c = new TextField("You can zoom using mouse wheel.");
		c.setPreferredSize(new Dimension(width, 20));
		c.setEditable(false);
		c.setEnabled(false);
		frame.getContentPane().setPreferredSize(new Dimension(width, height + 20));
		frame.getContentPane().add(c, BorderLayout.NORTH);
		surface.setPreferredSize(new Dimension(width, height));
		frame.getContentPane().add(surface, BorderLayout.CENTER);
		frame.pack();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setLocation(780, 0);
		frame.setResizable(false);
		frame.setVisible(true);

	}

	private static boolean isOutOfBoundaries(int x, int y) {
		if (x > frame.getWidth() / 2 || x < -frame.getWidth() / 2 || y > frame.getHeight() / 2
				|| y < -frame.getHeight() / 2) {
			return false;
		}
		return true;
	}
}

/* DRAWER SURFACE CLASS */
class DrawerSurface extends JComponent {
	private static final long serialVersionUID = -3789133815287680248L;
	private List<Point> points;
	private double zoom = 1;

	DrawerSurface() {
		this.points = new ArrayList<Point>();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.translate(this.getWidth() / 2, this.getHeight() / 2);
		g.setPaintMode();
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.scale(this.zoom, this.zoom);
		for (int i = 0; i < this.points.size(); i++) {
			Point p = this.points.get(i);
			g.setColor(p.color);
			g.drawLine(p.x, -p.y, p.x, -p.y);

		}
	}

	protected void zoom(double z) {
		this.zoom = this.zoom + z / 6;
		this.repaint();
	}

	protected void setPixel(int x, int y, String colorStr) {
		this.points.add(new Point(x, y, ColorFactory.getColor(colorStr)));
		this.repaint();
	}

	protected void setPixel(int x, int y, int r, int g, int b) {
		this.points.add(new Point(x, y, new Color(r, g, b, 255)));
		this.repaint();
	}

	protected void setPixel(int x, int y, int v) {
		v = v < 0 ? 0 : v > 255 ? 255 : v;
		v = Math.abs(255 - v);
		this.points.add(new Point(x, y, new Color(0, 0, 0, v)));
		this.repaint();
	}
}

/* POINT CLASS */
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