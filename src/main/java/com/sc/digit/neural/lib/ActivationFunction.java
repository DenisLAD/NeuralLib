/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.digit.neural.lib;

import com.sc.digit.neural.lib.functions.LinearFunction;
import com.sc.digit.neural.lib.functions.ReLUFunction;
import com.sc.digit.neural.lib.functions.SigmoidFunction;
import com.sc.digit.neural.lib.functions.SoftMaxFunction;
import com.sc.digit.neural.lib.functions.StepFunction;
import com.sc.digit.neural.lib.functions.TanHFunction;

/**
 *
 * @author lucifer
 */
public interface ActivationFunction {

    public final static ActivationFunction SIGMOID = new SigmoidFunction();
    public final static ActivationFunction RELU = new ReLUFunction();
    public final static ActivationFunction LINEAR = new LinearFunction();
    public final static ActivationFunction TANH = new TanHFunction();
    public final static ActivationFunction STEP = new StepFunction();
    public final static ActivationFunction SOFTMAX = new SoftMaxFunction();

    public void activate(double[] output);

}
