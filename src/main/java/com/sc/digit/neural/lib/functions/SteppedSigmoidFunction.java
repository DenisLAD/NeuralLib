/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.digit.neural.lib.functions;

import com.sc.digit.neural.lib.ActivationFunction;

/**
 *
 * @author lucifer
 */
public class SteppedSigmoidFunction extends ActivationFunction {

    @Override
    public void activate(double[] output) {
        int l = output.length;
        for (int i = 0; i < l; i++) {
            output[i] = 1.0 / (1.0 + Math.exp(-4.9 * output[i]));
        }
    }

    @Override
    public double derivative(double a, double b) {
        double s = Math.exp(-4.9 * a);
        return Math.pow(s * 4.9 / (1 + s), 2);
    }

}
