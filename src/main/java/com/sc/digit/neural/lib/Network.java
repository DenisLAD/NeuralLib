/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.digit.neural.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author lucifer
 */
public class Network {

    private final List<Layer> layers = new ArrayList<>();
    private final int inputSize;
    private final List<double[][]> weights = new ArrayList<>();
    private final List<double[]> layerDatas = new ArrayList<>();
    private final List<double[]> errors = new ArrayList<>();

    public Network(int inputSize) {
        this.inputSize = inputSize;
    }

    public void addLayer(Layer layer) {
        layers.add(layer);
    }

    public void build() {
        weights.clear();
        layerDatas.clear();
        layerDatas.clear();

        int lastSize = inputSize;
        double[] l = new double[lastSize + 1];
        l[l.length - 1] = 1.0;
        double[] e = new double[lastSize];
        layerDatas.add(l);
        errors.add(e);

        for (int i = 0; i < layers.size(); i++) {
            Layer layer = layers.get(i);

            double[][] w = new double[layer.getSize()][lastSize + 1];
            initWeights(w);
            weights.add(w);

            if (i + 1 == layers.size()) {
                l = new double[layer.getSize()];
            } else {
                l = new double[layer.getSize() + 1];
                l[l.length - 1] = 1.0;
            }
            layerDatas.add(l);

            e = new double[layer.getSize()];
            errors.add(e);

            lastSize = layer.getSize();
        }
    }

    private void initWeights(double[][] w) {
        for (double[] weight : w) {
            for (int i = 0; i < weight.length; i++) {
                weight[i] = (Math.random() - 0.5) / 1000.0;
            }
        }
    }

    public double[] evaluate(double[] data) {
        System.arraycopy(data, 0, layerDatas.get(0), 0, data.length);

        for (int i = 0; i < layerDatas.size() - 1; i++) {
            double[] input = layerDatas.get(i);
            double[] output = layerDatas.get(i + 1);
            double[][] w = weights.get(i);

            forward(input, output, w, layers.get(i).getFunc());
        }

        int idx = layerDatas.size() - 1;
        return Arrays.copyOf(layerDatas.get(idx), layerDatas.get(idx).length);
    }

    public double train(double[] input, double[] ideal, double learningRate) {
        evaluate(input);

        int idx = layerDatas.size() - 1;
        double[] output = layerDatas.get(idx);
        double[] error = errors.get(idx);
        findOutError(error, output, ideal);

        for (int i = idx; i > 1; i--) {
            double[][] w = weights.get(i - 1);
            double[] oe = errors.get(i);
            double[] ie = errors.get(i - 1);
            double[] in = layerDatas.get(i - 1);

            findError(in, ie, oe, w);

        }

        for (int i = idx; i > 0; i--) {
            double[][] w = weights.get(i - 1);
            double[] oe = errors.get(i);
            double[] in = layerDatas.get(i - 1);

            backward(in, oe, w, learningRate);
        }

        double lr = 0;
        for (int i = 0; i < error.length; i++) {
            double d = (ideal[i] - output[i]);
            lr += d * d * 0.5;
        }

        return lr;

    }

    public double train(TrainingSet set, double learRate, int iterations) {
        double error = 0;
        for (int i = 0; i < iterations; i++) {
            error = 0;
            for (TrainingRow row : set.getTrainingData()) {
                error += train(row.getInput(), row.getOutput(), learRate);
            }
            error /= (double) set.getTrainingData().size();
        }

        return error;
    }

    private void forward(double[] input, double[] output, double[][] weights, ActivationFunction func) {
        int i = input.length;
        int t = weights.length;
        for (int y = 0; y < t; y++) {
            double[] weightRow = weights[y];
            output[y] = 0.0;
            for (int x = 0; x < i; x++) {
                output[y] = output[y] + input[x] * weightRow[x];
            }
//            output[y] = output[y] + weightRow[weightRow.length - 1];
        }

        func.activateCheck(output);
    }

    private void findOutError(double[] error, double[] output, double[] ideal) {
        int s = ideal.length;
        for (int i = 0; i < s; i++) {
            double delta = (ideal[i] - output[i]);
            error[i] = delta * output[i] * (1 - output[i]);
        }
    }

    private void findError(double[] in, double[] ie, double[] oe, double[][] w) {
        int i = in.length - 1;
        int t = w.length;

        for (int x = 0; x < i; x++) {
            ie[x] = 0;
            for (int y = 0; y < t; y++) {
                ie[x] = (ie[x] + w[y][x] * oe[y]);
            }
            ie[x] = ie[x] * in[x] * (1 - in[x]);
        }

    }

    private void backward(double[] in, double[] oe, double[][] w, double learningRate) {
        int i = in.length;
        int t = w.length;
        for (int y = 0; y < t; y++) {
            double[] weightRow = w[y];
            for (int x = 0; x < i; x++) {
                weightRow[x] = weightRow[x] + learningRate * oe[y] * in[x];
            }
        }
    }

}
