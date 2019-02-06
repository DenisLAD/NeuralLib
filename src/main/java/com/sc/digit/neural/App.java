/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.digit.neural;

import com.sc.digit.neural.lib.ActivationFunction;
import com.sc.digit.neural.lib.Layer;
import com.sc.digit.neural.lib.Network;

/**
 *
 * @author lucifer
 */
public class App {

    public static void main(String[] args) {
        Network n = new Network(2);
//        n.addLayer(new Layer(8, ActivationFunction.LINEAR));
//        n.addLayer(new Layer(4, ActivationFunction.SIGMOID));
        n.addLayer(new Layer(40, ActivationFunction.SIGMOID));
        n.addLayer(new Layer(1, ActivationFunction.RELU));
        n.build();

        double[][] train = new double[][]{
            {0, 0},
            {1, 0},
            {0, 1},
            {1, 1}
        };
        double[][] ideal = new double[][]{
            {0},
            {1},
            {1},
            {0}
        };

        int lr = 0;

        double err = 1.0;
        while (err > 0.0001) {
            err = 0;
            for (int i = 0; i < train.length; i++) {
                err += n.train(train[i], ideal[i], 0.1);
            }

            if (lr % 1000 == 0) {
                System.out.println(String.format("%.8f", err));
//                for (int i = 0; i < train.length; i++) {
//                    double e = n.evaluate(train[i])[0];
//                    System.out.println(String.format("%.4f %.4f", e, ideal[i][0]));
//                }
            }

            lr++;
        }

        for (int i = 0; i < train.length; i++) {
            err = n.evaluate(train[i])[0];
            System.out.println(String.format("%.4f %.4f", err, ideal[i][0]));
        }
    }
}
