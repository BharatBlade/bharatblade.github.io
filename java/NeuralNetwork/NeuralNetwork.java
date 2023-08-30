package NeuralNetwork;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NeuralNetwork {

    public final int inputSize, hiddenSize, outputSize;
    public final List<Neuron> inputLayer, hiddenLayer, outputLayer;

    public double learningRate = 0.01, momentum = 0.5;
    public IActivationFunction activationFunction = new Sigmoid();
    public boolean initialized = false;

    public NeuralNetwork(int inputSize, int hiddenSize, int outputSize) {
        this.inputSize = inputSize; this.hiddenSize = hiddenSize; this.outputSize = outputSize;
        this.inputLayer = new ArrayList<>(); this.hiddenLayer = new ArrayList<>(); this.outputLayer = new ArrayList<>();
    }
    
    public void setAll(double learningRate, double momentum, ActivationFunction activationFunction) {
    	init();
        this.learningRate = learningRate; this.momentum = momentum;
        setActivationFunction(activationFunction);
    }
    
    public void setActivationFunction(ActivationFunction activationFunction) {
        switch (activationFunction) {
            case LEAKY_RELU: 	this.activationFunction = new LeakyReLu(); break;
            case TANH: 			this.activationFunction = new TanH(); break;
            case SIGMOID: 		this.activationFunction = new Sigmoid(); break;
            case SWISH: 		this.activationFunction = new Swish(); break;
        }
    }

    public void init() {
        for (int i = 0; i < inputSize; i++)  { this.inputLayer.add(new Neuron()); }
        for (int i = 0; i < hiddenSize; i++) { this.hiddenLayer.add(new Neuron(this.inputLayer, activationFunction)); }
        for (int i = 0; i < outputSize; i++) { this.outputLayer.add(new Neuron(this.hiddenLayer, activationFunction)); }
        this.initialized = true;
        System.out.println("Network Initialized.");
    }

    public void train(MLDataSet set, int epoch) {
        if (!initialized){ this.init(); }
        System.out.println("Training Started");
        for (int i = 0; i < epoch; i++) {
        	set.shuffle();
            for (MLData datum : set.data) { forward(datum.inputs); backward(datum.targets); }
        }
        System.out.println("Training Finished.");
    }

    public void backward(double[] targets) {
        int i = 0;
        for (Neuron neuron : outputLayer) { neuron.calculateGradient(targets[i++]); }
        for (Neuron neuron : hiddenLayer) { neuron.calculateGradient(); }
        for (Neuron neuron : hiddenLayer) { neuron.updateConnections(learningRate, momentum); }
        for (Neuron neuron : outputLayer) { neuron.updateConnections(learningRate, momentum); }
    }

    public void forward(double[] inputs) {
        int i = 0;
        for (Neuron neuron : inputLayer)  { neuron.output = inputs[i++]; }
        for (Neuron neuron : hiddenLayer) { neuron.calculateOutput(); }
        for (Neuron neuron : outputLayer) { neuron.calculateOutput(); }
    }

    public double[] predict(double... inputs) {
        forward(inputs);
        double[] output = new double[outputLayer.size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = outputLayer.get(i).output;
        }
        System.out.println("Input : " + Arrays.toString(inputs) + " Predicted : " + Arrays.toString(output));
        return output;
    }

}