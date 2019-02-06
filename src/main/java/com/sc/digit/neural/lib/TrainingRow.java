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
public class TrainingRow {

    private double[] input;
    private double[] output;

    public TrainingRow(double[] input, double[] output) {
        this.input = input;
        this.output = output;
    }

    public double[] getInput() {
        return input;
    }

    public double[] getOutput() {
        return output;
    }

    public void setInput(double[] input) {
        this.input = input;
    }

    public void setOutput(double[] output) {
        this.output = output;
    }

}
