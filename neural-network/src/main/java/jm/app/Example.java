/*
 * This is an example of the neural network toolbox. The main method trys to train a neural network with three layers to
 * match the XOR operation. The network works successfully. The inputs and the corresponding correct outputs are as
 * follows:
 *   -----------------
 *  | input | output |
 *   -----------------
 *  |  00   |   0    |
 *  |  01   |   1    |
 *  |  10   |   1    |
 *  |  11   |   0    |
 *  ------------------
 */

package jm.app;

public class Example {
    public static void main(String[] args){
        double[][] input = new double[4][];
        input[0] = new double[2];
        input[1] = new double[2];
        input[2] = new double[2];
        input[3] = new double[2];
        input[0][0] = 0;
        input[0][1] = 0;
        input[1][0] = 0;
        input[1][1] = 1;
        input[2][0] = 1;
        input[2][1] = 0;
        input[3][0] = 1;
        input[3][1] = 1;
        double[][] label = new double[4][];
        label[0] = new double[1];
        label[1] = new double[1];
        label[2] = new double[1];
        label[3] = new double[1];
        label[0][0] = 0;
        label[1][0] = 1;
        label[2][0] = 1;
        label[3][0] = 0;

        Double[][][] weights = new Double[2][][];
        weights[0] = new Double[3][2];
        weights[0][0][0] = 6.70482641156543;
        weights[0][0][1] = -4.18128384406268;
        weights[0][1][0] = 5.38360500391982;
        weights[0][1][1] = -7.52967197673607;
        weights[0][2][0] = 4.16230095022815;
        weights[0][2][1] = 5.95317354348635;
        weights[1] = new Double[1][3];
        weights[1][0][0] = -9.46783598265045;
        weights[1][0][1] = 9.57546144367196;
        weights[1][0][2] = 6.14236729717791;

        Double[][] biases = new Double[2][];
        biases[0] = new Double[3];
        biases[0][0] = 1.30745916070485;
        biases[0][1] = -2.19334229747328;
        biases[0][2] = -0.525310872754804;
        biases[1] = new Double[1];
        biases[1][0] = -1.19737302691328;

        NeuralNetwork network = new NeuralNetwork(2, 3, 1);
        network.setActivationFunction(NeuralNetwork.ActivationFunction.SIGMOID);
        // network.setWeitghts(weights);
        // network.setBiases(biases);
        network.train(input, label);
        double[] res1 = network.forward(input[0]);
        double[] res2 = network.forward(input[1]);
        double[] res3 = network.forward(input[2]);
        double[] res4 = network.forward(input[3]);
        printRes(res1);
        printRes(res2);
        printRes(res3);
        printRes(res4);
    }

    private static void printRes(double[] res){
        for(int i = 0; i < res.length; i++){
            System.out.println(res[i]);
        }
    }
}
