/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.digit.neural.lib;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucifer
 */
public class TrainingSet {

    private final List<TrainingRow> trainingData = new ArrayList<>();

    public void addTrainingData(double[] input, double[] ideal) {
        trainingData.add(new TrainingRow(input, ideal));
    }

    public List<TrainingRow> getTrainingData() {
        return trainingData;
    }

}
