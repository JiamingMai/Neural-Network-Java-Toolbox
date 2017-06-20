package jm.app;

import org.junit.Assert;
import org.junit.Test;

public class TestNeuralNetwork {
    NeuralNetwork neuralNetwork;

    public TestNeuralNetwork(){
        init();
    }

    private void init(){
         neuralNetwork = new NeuralNetwork(2, 3, 1);
        Double[][][] weights = new Double[2][][];
        weights[0] = new Double[3][2];
        weights[0][0][0] = 0.1;
        weights[0][0][1] = 0.5;
        weights[0][1][0] = 0.2;
        weights[0][1][1] = 0.7;
        weights[0][2][0] = 0.3;
        weights[0][2][1] = 0.1;
        weights[1] = new Double[1][3];
        weights[1][0][0] = 0.9;
        weights[1][0][1] = 1.2;
        weights[1][0][2] = 2.0;

        Double[][] biases = new Double[2][];
        biases[0] = new Double[3];
        biases[0][0] = 0.4;
        biases[0][1] = 0.8;
        biases[0][2] = 0.6;
        biases[1] = new Double[1];
        biases[1][0] = 0.5;

        neuralNetwork.setWeitghts(weights);
        neuralNetwork.setBiases(biases);
    }

    @Test
    public void testForward(){
        double[] input = {0.9, 0.1};
        double[] output = neuralNetwork.forward(input);
        for(int i = 0; i < output.length; i++){
            System.out.println(output[i]);
        }
        Assert.assertTrue(Math.abs(output[0] - 0.9668) < 0.01);
    }
}
