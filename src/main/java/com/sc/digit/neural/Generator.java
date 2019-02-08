/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.digit.neural;

import com.sc.digit.neural.lib.ActivationFunction;
import com.sc.digit.neural.lib.Layer;
import com.sc.digit.neural.lib.Network;
import com.sc.digit.neural.lib.TrainingSet;
import com.sc.digit.neural.utils.DataUtils;
import com.sc.digit.neural.utils.ImageComponent;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author lucifer
 */
public class Generator {

    public static void main(String[] args) throws Exception {

        BufferedImage im = ImageIO.read(new File("1.png"));

        int w = 32;
        int h = 32;

        TrainingSet set = new TrainingSet();
        for (int y = 0; y < 10; y += 3) {
            for (int x = 0; x < 10; x++) {
                BufferedImage i = im.getSubimage(1 + x * (w + 2), 1 + y * (h + 2), w, h);
                double[] imgs = DataUtils.imageToDoubles(i);
                set.addTrainingData(DataUtils.generateInput(10, x), imgs);
            }
        }

        Network net = new Network(10);
        net.addLayer(new Layer(32, ActivationFunction.SIGMOID));
        net.addLayer(new Layer(64, ActivationFunction.SIGMOID));
//        net.addLayer(new Layer(256, ActivationFunction.SIGMOID));
        net.addLayer(new Layer(1024 * 3, ActivationFunction.SIGMOID));
//        net.addLayer(new Layer(1024, ActivationFunction.STEP));
        net.build();

        JFrame frame = new JFrame();
        frame.setSize(1308, 170);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(100, 100);

        frame.setVisible(true);

        FlowLayout fl = new FlowLayout(FlowLayout.LEFT, 1, 1);

        ImageComponent[] images = new ImageComponent[10];
        for (int i = 0; i < 10; i++) {
            images[i] = new ImageComponent(w, h);
            images[i].setLocation(i * 32, 0);
            frame.getContentPane().add(images[i]);
        }
        frame.getContentPane().setLayout(fl);

        while (true) {
            for (int i = 0; i < 10; i++) {
                drawNet(i, net, images[i]);
            }
            net.train(set, 0.01, 1);
        }

    }

    private static int col(double iv) {
        int p = (int) (Math.abs(iv) * 255.0);
        p = p > 255 ? 255 : p < 0 ? 0 : p;

        return (p << 16) | (p << 8) | p;
    }

    private static void drawNet(int i, Network net, ImageComponent image) {
        double[] iv = net.evaluate(DataUtils.generateInput(10, i));

        int idx = 0;
        BufferedImage bi = image.getSet();
        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                int r = (int) (iv[idx] * 255.0);
                int g = (int) (iv[idx + 1024] * 255.0);
                int b = (int) (iv[idx + 2048] * 255.0);
                bi.setRGB(x, y, ((r & 255) << 16) | ((g & 255) << 8) | (b & 255));
                idx++;
            }
        }

        image.cobine();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                image.repaint();
            }
        });
    }
}
