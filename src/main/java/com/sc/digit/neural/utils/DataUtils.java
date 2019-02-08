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
public class DataUtils {

    public static double[] imageBWToDoubles(BufferedImage i) {
        double[] input = new double[i.getWidth() * i.getHeight()];
        int idx = 0;
        for (int y = 0; y < i.getHeight(); y++) {
            for (int x = 0; x < i.getWidth(); x++) {
                int col = i.getRGB(x, y);
                int r = (col >> 16) & 0xff;
                int g = (col >> 16) & 0xff;
                int b = col & 0xff;

                input[idx++] = (r + g + b) / 765.0;
            }
        }

        return input;
    }

    public static double[] imageToDoubles(BufferedImage i) {
        int step = i.getWidth() * i.getHeight();
        double[] input = new double[step * 3];
        int idx = 0;
        for (int y = 0; y < i.getHeight(); y++) {
            for (int x = 0; x < i.getWidth(); x++) {
                int col = i.getRGB(x, y);
                int r = (col >> 16) & 0xff;
                int g = (col >> 16) & 0xff;
                int b = col & 0xff;
                
                r = r < 64 ? 0 : r;
                g = g < 64 ? 0 : g;
                b = b < 64 ? 0 : b;
                
                r = r > 200 ? 255 : r;
                g = g > 200 ? 255 : g;
                b = b > 200 ? 255 : b;

                input[idx] = r / 255.0;
                input[idx + step] = g / 255.0;
                input[idx + step * 2] = b / 255.0;
                idx++;
            }
        }

        return input;
    }

    public static double[] generateInput(int size, int idx, double value) {
        double[] ret = new double[size];
        ret[idx] = value;
        return ret;
    }

    public static double[] generateInput(int size, int idx) {
        return generateInput(size, idx, 1.0);
    }

    public static double[] findMax(double[] row) {
        int idx = 0;
        double max = Double.MIN_VALUE;
        for (int i = 0; i < row.length; i++) {
            if (max < row[i]) {
                idx = i;
                max = row[i];
            }
        }
        return new double[]{idx, max};
    }

}
