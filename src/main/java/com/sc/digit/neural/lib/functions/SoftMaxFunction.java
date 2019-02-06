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
public class SoftMaxFunction implements ActivationFunction {

    @Override
    public void activate(double[] output) {
        double sum = 0;
        for (int i = 0; i < output.length; i++) {
            output[i] = Math.exp(output[i]);
            sum += output[i];
        }
        if (Double.isNaN(sum) || sum < 0.000000000001) {
            for (int i = 0; i < output.length; i++) {
                output[i] = 1.0 / output.length;
            }
        } else {
            for (int i = 0; i < output.length; i++) {
                output[i] = output[i] / sum;
            }
        }
    }

}
