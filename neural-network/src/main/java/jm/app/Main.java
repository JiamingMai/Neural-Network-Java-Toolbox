package jm.app;

public class Main {
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
        label[3][0] = 1;

        /*
        Double[][][] weights = new Double[1][][];
        weights[0] = new Double[1][2];
        weights[0][0][0] = 1.209879;
        weights[0][0][1] = 1.275985;

        Double[][] biases = new Double[1][];
        biases[0] = new Double[1];
        biases[0][0] = -0.010177;
        */

        NeuralNetwork network = new NeuralNetwork(2, 10, 1);
        network.train(input, label);
        //network.setWeitghts(weights);
        //network.setBiases(biases);
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