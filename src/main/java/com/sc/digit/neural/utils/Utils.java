/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.digit.neural.utils;

import java.awt.image.BufferedImage;

/**
 *
 * @author lucifer
 */
public class Utils {

    public static double[] imageToDoubles(BufferedImage i) {
        double[] input = new double[i.getWidth() * i.getHeight()];
        int idx = 0;
        for (int y = 0; y < i.getHeight(); y++) {
            for (int x = 0; x < i.getWidth(); x++) {
                int col = i.getRGB(x, y);
                input[idx++] = (col & 0xff) / 255.0;
            }
        }

        return input;
    }

    public static double[] generateInput(int y) {
        double output[] = new double[10];
        output[y] = 1;
        return output;
    }

    public static double[] generateInput(int y, double min, double max, double v) {
        double output[] = new double[10];
        output[y] = min + (max - min) * v;
        return output;
    }
}
