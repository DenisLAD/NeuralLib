/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.digit.neural;

import com.sc.digit.neural.lib.ActivationFunction;
import com.sc.digit.neural.lib.Layer;
import com.sc.digit.neural.lib.Network;
import com.sc.digit.neural.lib.TrainingRow;
import com.sc.digit.neural.lib.TrainingSet;
import com.sc.digit.neural.utils.ImageComponent;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author lucifer
 */
public class Segmentation {

    public static void main(String[] args) throws InterruptedException {
        Network net = new Network(2);
//        net.addLayer(new Layer(10, ActivationFunction.SIGMOID));
        net.addLayer(new Layer(10, ActivationFunction.SIGMOID));
//        net.addLayer(new Layer(10, ActivationFunction.RELU));
//        net.addLayer(new Layer(8, ActivationFunction.SIGMOID));
//        net.addLayer(new Layer(8, ActivationFunction.SIGMOID));
        net.addLayer(new Layer(1, ActivationFunction.SIGMOID));
        net.build();

        JFrame frame = new JFrame("Hyperplane");
        frame.setSize(128, 128);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(100, 100);

        ImageComponent ic = new ImageComponent(128, 128);
        frame.getContentPane().add(ic);
        frame.pack();

        TrainingSet set = new TrainingSet();

        for (int i = 0; i < 10; i++) {
            set.addTrainingData(new double[]{Math.random(), Math.random()}, new double[]{1.0});
//            set.addTrainingData(new double[]{Math.random(), Math.random()}, new double[]{0.5});
            set.addTrainingData(new double[]{Math.random(), Math.random()}, new double[]{0.0});
        }

        int idx = 0;
//        Thread.sleep(10000);
        while (true) {
            double err = net.train(set, 0.01, 1000);
            System.out.format("%.9f\n", err);
            idx++;
//            if (idx % 10 == 0) {
                drawNet(ic, net, set);
//            }
        }

    }

    private static void drawNet(ImageComponent ic, Network net, TrainingSet set) {
        BufferedImage img = ic.getSet();
        for (int x = 0; x < 128; x++) {
            for (int y = 0; y < 128; y++) {
                double c = net.evaluate(new double[]{(double) x / 128.0, (double) y / 128.0})[0];
                int color = Color.getHSBColor((float) (c / 1.7), 0.9f, 0.9f).getRGB();
                img.setRGB(x, y, color);
            }
        }
        for (TrainingRow row : set.getTrainingData()) {
            double pos[] = row.getInput();
            double type = row.getOutput()[0];

            int color = type < 0.5 ? 0 : 0xffffff;
            img.setRGB((int) (pos[0] * 128), (int) (pos[1] * 128), color);
        }
        ic.cobine();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ic.repaint();
            }
        });
    }
}

