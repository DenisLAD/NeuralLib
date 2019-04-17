/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.digit.neural.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 *
 * @author lucifer
 */
public class GraphComponent extends JComponent {

    private BufferedImage image;
    private static final int LEN = 1200;
    private List<Double> data = new ArrayList<>();

    public GraphComponent() {
        image = new BufferedImage(LEN, 200, BufferedImage.TYPE_INT_RGB);

    }

    public void addData(double val) {
        data.add(val);
        if (data.size() > LEN) {
            data.remove(0);
        }

        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());

        int step = LEN / data.size();
        double max = 0;
        for (int i = 0; i < data.size(); i++) {
            if (max < data.get(i)) {
                max = data.get(i);
            }
        }
        g.setColor(Color.white);
        for (int i = 0; i < data.size() - 1; i++) {
            double h1 = 200.0 - data.get(i) / max * 200.0;
            double h2 = 200.0 - data.get(i + 1) / max * 200.0;
            g.drawLine(i * step, (int) h1, (i + 1) * step, (int) h2);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(image, 0, 0, null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(), image.getHeight());
    }

    @Override
    public Dimension getSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public BufferedImage getImage() {
        return image;
    }

}
