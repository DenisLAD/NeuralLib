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
public class ReLUFunction implements ActivationFunction {

    @Override
    public void activate(double[] output) {
        int l = output.length;
        for (int i = 0; i < l; i++) {
            output[i] = output[i] <= 0 ? 0 : output[i];
        }
    }

}
