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
public class SinFunction extends ActivationFunction {

    @Override
    public void activate(double[] output) {
        int l = output.length;
        for (int i = 0; i < l; i++) {
            output[i] = Math.sin(2.0 * output[i]);
        }
    }

    @Override
    public double derivative(double b, double a) {
        return Math.cos(2.0 * b);
    }

}
