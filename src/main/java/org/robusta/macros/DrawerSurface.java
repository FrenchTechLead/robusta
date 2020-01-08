package org.robusta.macros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;


class DrawerSurface extends JPanel implements ActionListener {

    private int width;
    private int height;
    private Set<Point> points;

    public DrawerSurface() {
        this.width = 0;
        this.height = 0;
        this.points = new TreeSet<>();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setPixel(int x, int y, int grayValue) {
        checkBoundaries(x, y);

        int x_translation = Math.abs(width / 2);
        int y_translation = Math.abs(height / 2);

        Point p = new Point(x  +  x_translation, y + y_translation, new Color(grayValue, grayValue, grayValue));
        this.points.add(p);
    }

    public void setPixel(int x, int y, String color) {
        checkBoundaries(x, y);

        int x_translation = Math.abs(width / 2);
        int y_translation = Math.abs(height / 2);

        Point p = new Point(x  +  x_translation, y + y_translation, ColorFactory.getColor(color.toLowerCase()));
        this.points.add(p);
    }

    public int getPixel(int x, int y) {
        checkBoundaries(x, y);

        int x_translation = Math.abs(width / 2);
        int y_translation = Math.abs(height / 2);

        for(Point p : points) {
            if(p.x == x + x_translation && p.y == y + y_translation)
                return p.getColor().getRed();
        }

        // default value
        return 0;
    }

    public void reset(int width, int height) {
        this.width = width;
        this.height = height;
        this.points.clear();
    }

    public void clear() {
        this.width = 0;
        this.height = 0;
        this.points.clear();
    }

    private void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        BasicStroke bs1 = new BasicStroke(2f);
        g2d.setStroke(bs1);

        for(Point p : this.points) {
            g2d.setColor(p.getColor());
            g2d.drawLine(p.x, p.y, p.x, p.y);
        }

    }

    private void checkBoundaries(int x, int y) throws IllegalArgumentException {
       if (x > width/2 || x < - width/2) {
           System.out.println("Width out of bound");
           throw new IllegalArgumentException("x value is out of window width boundary");

       }

        if (y > height/2 || y < - height/2) {
            throw new IllegalArgumentException("y value is out of window height boundary");
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}

class Point implements Comparable {
    int x;
    int y;
    private Color color;

    Point(int _x, int _y, Color c) {
        x = _x;
        y = _y;
        color = c;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public Color getColor() {
        return color;
    }

    public int compareTo(Object o) {
        Point p = (Point) o;
        int xEquality = p.x - x;

        if (xEquality != 0) return xEquality;

        return p.y - y;
    }

}

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
        return colors.getOrDefault(color, Color.BLACK);
    }
}
