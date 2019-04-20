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
public class GaussianFunction extends ActivationFunction {

    @Override
    public void activate(double[] output) {
        int l = output.length;
        for (int i = 0; i < l; i++) {
            output[i] = Math.exp(-Math.pow(2.5 * output[i], 2.0));
        }
    }

    @Override
    public double derivative(double b, double a) {
        return Math.exp(Math.pow(2.5 * b, 2.0) * 12.5 * b);
    }

}
