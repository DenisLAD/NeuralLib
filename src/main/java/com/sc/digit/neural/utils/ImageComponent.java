/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.digit.neural.utils;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 *
 * @author lucifer
 */
public class ImageComponent extends JComponent {

    private BufferedImage image;
    private BufferedImage set;

    public ImageComponent(int w, int h) {
        image = new BufferedImage(w * 4, h * 4, BufferedImage.TYPE_INT_RGB);
        set = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
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

    public BufferedImage getSet() {
        return set;
    }

    public void cobine() {
        image.getGraphics().drawImage(set, 0, 0, set.getWidth() * 4, set.getHeight() * 4, 0, 0, set.getWidth(), set.getHeight(), null);
    }

}
