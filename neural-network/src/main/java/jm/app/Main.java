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

        NeuralNetwork network = new NeuralNetwork(2, 10, 1);
        network.train(input, label);
        double[] res = network.forward(input[1]);
        printRes(res);
    }

    private static void printRes(double[] res){
        for(int i = 0; i < res.length; i++){
            System.out.println(res[i]);
        }
    }
}