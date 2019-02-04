/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.digit.neural.lib;

/**
 *
 * @author lucifer
 */
public class Layer {

    private final int size;
    private final ActivationFunction func;

    public Layer(int size, ActivationFunction func) {
        this.size = size;
        this.func = func;
    }

    public ActivationFunction getFunc() {
        return func;
    }

    public int getSize() {
        return size;
    }

}
