/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.digit.neural.lib;

import com.sc.digit.neural.lib.functions.GaussianFunction;
import com.sc.digit.neural.lib.functions.LinearFunction;
import com.sc.digit.neural.lib.functions.ReLUFunction;
import com.sc.digit.neural.lib.functions.SigmoidFunction;
import com.sc.digit.neural.lib.functions.SinFunction;
import com.sc.digit.neural.lib.functions.SoftMaxFunction;
import com.sc.digit.neural.lib.functions.SteppedSigmoidFunction;
import com.sc.digit.neural.lib.functions.TanHFunction;

/**
 *
 * @author lucifer
 */
public abstract class ActivationFunction {

    public final static ActivationFunction SIGMOID = new SigmoidFunction();
    public final static ActivationFunction STEPPED_SIGMOID = new SteppedSigmoidFunction();
    public final static ActivationFunction RELU = new ReLUFunction();
    public final static ActivationFunction LINEAR = new LinearFunction();
    public final static ActivationFunction TANH = new TanHFunction();
    public final static ActivationFunction SOFTMAX = new SoftMaxFunction();
    public final static ActivationFunction GAUSSIAN = new GaussianFunction();
    public final static ActivationFunction SIN = new SinFunction();

    public abstract void activate(double[] output);

    public abstract double derivative(double b, double a);

}
