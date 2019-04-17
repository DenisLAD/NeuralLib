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

/**
 *
 * @author lucifer
 */
public class App {

    public static void main(String[] args) throws Exception {

        TrainingSet set = new TrainingSet();
        set.addTrainingData(new double[]{0, 0}, new double[]{0});
        set.addTrainingData(new double[]{1, 0}, new double[]{1});
        set.addTrainingData(new double[]{0, 1}, new double[]{1});
        set.addTrainingData(new double[]{1, 1}, new double[]{0});
        Network net = new Network(2);
        net.addLayer(new Layer(32, ActivationFunction.SIGMOID));
        net.addLayer(new Layer(32, ActivationFunction.SIGMOID));
        net.addLayer(new Layer(32, ActivationFunction.SIGMOID));
        net.addLayer(new Layer(1, ActivationFunction.SIGMOID));
        net.build();
        double error = 1;
        double errorOld = 1;
        int errCount = 0;
        int epoch = 0;

        while (error > 0.01f) {
            errorOld = error;
            error = net.train(set, 0.6, 10000);
            System.out.println(String.format("Epoch #%d: Error = %.8f", epoch, error));

//            if (epoch % 1000 == 0) {
                float r = 0;
                for (TrainingRow tr : set.getTrainingData()) {
                    System.out.format("%.5f ", net.evaluate(tr.getInput())[0]);
                }
                System.out.println("");
//            }

            if (error == errorOld) {
                errCount++;
            } else {
                errCount = 0;
            }
            if (errCount > 10 || epoch > 1000) {
                break;
            }
            epoch++;
        }

//        TrainingSet set = new TrainingSet();
//
//        BufferedImage im = ImageIO.read(new File("1.png"));
//
//        int w = 32;
//        int h = 32;
//        for (int y = 5; y < 10; y++) {
//            for (int x = 0; x < 10; x++) {
//                BufferedImage i = im.getSubimage(1 + x * (w + 2), 1 + y * (h + 2), w, h);
//                set.addTrainingData(Utils.imageToDoubles(i), Utils.generateInput(x));
//            }
//        }
//
//        Network net = new Network(2);
//        net.addLayer(new Layer(256, ActivationFunction.RELU));
//        net.addLayer(new Layer(64, ActivationFunction.RELU));
//        net.addLayer(new Layer(10, ActivationFunction.SIGMOID));
//        net.build();
//        double error = 1;
//        double errorOld = 1;
//        int errCount = 0;
//        int epoch = 0;
//        while (error > 0.01f) {
//            errorOld = error;
//            error = net.train(set, 0.001, 100);
//            System.out.println(String.format("Epoch #%d: Error = %.8f", epoch, error));
//
//            if (error == errorOld) {
//                errCount++;
//            } else {
//                errCount = 0;
//            }
//            if (errCount > 10 || epoch > 1000) {
//                break;
//            }
//            epoch++;
//        }
    }

}
