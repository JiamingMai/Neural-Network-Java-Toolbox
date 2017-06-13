package jm.app;

import java.util.Random;

public class NeuralNetwork {
    Double[][][] weights;
    Double[][] biases;
    Double[][] outputMat;
    double lambda = 0.01;
    int epoch = 100000;
    final double RANDOM_COEFFIENCE = 1.0; 

    public void setWeitghts(Double[][][] weights) {
        this.weights = weights;
    }

    public void setBiases(Double[][] biases) {
        this.biases = biases;
    }

    public NeuralNetwork(int... numOfEachLayer) {
        biases = new Double[numOfEachLayer.length - 1][];
        for (int i = 0; i < biases.length; i++) {
            biases[i] = new Double[numOfEachLayer[i + 1]];
        }

        weights = new Double[numOfEachLayer.length - 1][][];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = new Double[numOfEachLayer[i + 1]][numOfEachLayer[i]];
        }

        outputMat = new Double[numOfEachLayer.length][];
        for (int i = 0; i < outputMat.length; i++) {
            outputMat[i] = new Double[numOfEachLayer[i]];
        }

        init();
    }

    private void init() {
        initWeights();
        initBiases();
    }

    private void initWeights() {
        Random random = new Random();
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                for (int k = 0; k < weights[i][j].length; k++) {
                    weights[i][j][k] = RANDOM_COEFFIENCE * random.nextDouble();
                }
            }
        }
    }

    private void initBiases() {
        Random random = new Random();
        for (int i = 0; i < biases.length; i++) {
            for (int j = 0; j < biases[i].length; j++) {
                biases[i][j] = RANDOM_COEFFIENCE * random.nextDouble();
            }
        }
    }

    public void train(double[][] input, double[][] label) {
        train(input, label, input.length);
    }

    public void train(double[][] input, double[][] label, int batchSize) {
        for (int i = 0; i < epoch; i++) {
            double[][] sensitivity = estSensitivityByBatch(input, label, batchSize);
            updateWeights(sensitivity);
            updateBiases(sensitivity);
            printMse(input, label);
        }
    }

    private void printMse(double[][] input, double[][] label) {
        double sum = 0;
        for (int i = 0; i < input.length; i++) {
            double[] res = forward(input[i]);
            for (int j = 0; j < label[i].length; j++) {
                sum += (res[j] - label[i][j]) * (res[j] - label[i][j]);
            }
        }
        double mse = sum / label.length;
        System.out.println("MSE: " + mse);
    }

    private void updateWeights(double[][] sensitivity) {
        for (int layer = 0; layer < weights.length; layer++) {
            for (int i = 0; i < weights[layer].length; i++) {
                for (int j = 0; j < weights[layer][i].length; j++) {
                    weights[layer][i][j] = weights[layer][i][j] - lambda * sensitivity[layer][i] * outputMat[layer][j];
                }
            }
        }
    }

    private void updateBiases(double[][] sensitivity) {
        for (int layer = 0; layer < biases.length; layer++) {
            for (int i = 0; i < biases[layer].length; i++) {
                biases[layer][i] = biases[layer][i] - lambda * sensitivity[layer][i];
            }
        }
    }

    private double[][] estSensitivityByBatch(double[][] input, double[][] label, int batchSize) {
        double[][] sensitivity = new double[biases.length][];
        for (int i = 0; i < biases.length; i++) {
            sensitivity[i] = new double[biases[i].length];
        }

        for (int k = 0; k < batchSize; k++) {
            int sampleNo = generateSampleNo(input, batchSize);
            double[] output = forward(input[sampleNo]);
            for (int i = 0; i < output.length; i++) {
                sensitivity[sensitivity.length - 1][i] += -2 * output[i] * (1 - output[i]) * (label[sampleNo][i] - output[i]);
            }
        }

        for (int layer = sensitivity.length - 2; layer >= 0; layer--) {
            for (int i = 0; i < sensitivity[layer].length; i++) {
                sensitivity[layer][i] = outputMat[layer + 1][i] * (1 - outputMat[layer + 1][i])
                        * innerProduct(selectColumn(weights[layer + 1], i), sensitivity[layer + 1]);
            }
        }
        return sensitivity;
    }

    private int generateSampleNo(double[][] input, int batchSize){
        Random random = new Random();
        int sampleNo = random.nextInt(input.length);
        return sampleNo;
    }

    private double[] selectColumn(Double[][] mat, int col) {
        double[] vec = new double[mat.length];
        for (int i = 0; i < mat.length; i++) {
            vec[i] = mat[i][col];
        }
        return vec;
    }

    public double[] forward(double[] input) {
        outputMat[0] = convertDoubleArray(input);
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                outputMat[i + 1][j] = activationFunction(innerProduct(outputMat[i], weights[i][j]) + biases[i][j]);
            }
        }
        return convertDoubleArray(outputMat[outputMat.length - 1]);
    }

    private double activationFunction(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    private Double innerProduct(Double[] vec1, Double[] vec2) {
        if (vec1.length != vec2.length) {
            return null;
        }
        return innerProduct(convertDoubleArray(vec1), convertDoubleArray(vec2));
    }

    private Double innerProduct(double[] vec1, double[] vec2) {
        if (vec1.length != vec2.length) {
            return null;
        }
        double res = 0;
        for (int i = 0; i < vec1.length; i++) {
            res += vec1[i] * vec2[i];
        }
        return res;
    }

    private Double[] convertDoubleArray(double[] inputArr) {
        Double[] outputArr = new Double[inputArr.length];
        for (int i = 0; i < outputArr.length; i++) {
            outputArr[i] = inputArr[i];
        }
        return outputArr;
    }

    private double[] convertDoubleArray(Double[] inputArr) {
        double[] outputArr = new double[inputArr.length];
        for (int i = 0; i < outputArr.length; i++) {
            outputArr[i] = inputArr[i];
        }
        return outputArr;
    }
}
