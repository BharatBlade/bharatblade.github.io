package NeuralNetwork;
public class NN {

    public static void main(String[] args) {
    	double [][] XOR_INPUT = new double[50][2];
    	double [][] XOR_IDEAL = new double[50][1];
    	
    	for(int i = 0; i < XOR_INPUT.length; i++) {
    		XOR_INPUT[i][0] = (0.4/XOR_INPUT.length)*i;
    		XOR_INPUT[i][1] = (0.4/XOR_INPUT.length)*i;
    		XOR_IDEAL[i][0] = XOR_INPUT[i][0] + XOR_INPUT[i][1];
    	}
    	
    	NeuralNetwork neuralNetwork = new NeuralNetwork(2, 100, 1);
        neuralNetwork.setAll(0.01, 0.3, ActivationFunction.LEAKY_RELU);

        MLDataSet dataSet = new MLDataSet(XOR_INPUT, XOR_IDEAL);
        neuralNetwork.train(dataSet, 100000000);

        for(int i = 0; i < XOR_INPUT.length; i++) {
            neuralNetwork.predict(XOR_INPUT[i][0], XOR_INPUT[i][1]);        	
        }
    }
}