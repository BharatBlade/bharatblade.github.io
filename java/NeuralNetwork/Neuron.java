package NeuralNetwork;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Neuron {

    public UUID neuronId;
    public List<Connection> incomingConnections,  outgoingConnections;
    public double bias, gradient, output, outputBeforeActivation;
    public IActivationFunction activationFunction;

    public Neuron() {
        this.neuronId = UUID.randomUUID();
        this.incomingConnections = new ArrayList<>();
        this.outgoingConnections = new ArrayList<>();
        this.bias = 1.0;
    }

    public Neuron(List<Neuron> neurons, IActivationFunction activationFunction) {
        this();
        this.activationFunction = activationFunction;
        for (Neuron neuron : neurons) {
            Connection connection = new Connection(neuron, this);
            neuron.outgoingConnections.add(connection);
            this.incomingConnections.add(connection);
        }
    }

    public void calculateOutput() {
        this.outputBeforeActivation = 0.0;
        for (Connection connection : incomingConnections) {
            this.outputBeforeActivation += connection.synapticWeight * connection.from.output;
        }
        this.output = activationFunction.output(this.outputBeforeActivation + bias);
    }

	public void calculateGradient(double target) {
        this.gradient = (target - output) * activationFunction.outputDerivative(output);
    }

    public void calculateGradient() {
        this.gradient = outgoingConnections.stream().mapToDouble(connection -> connection.to.gradient * connection.synapticWeight).sum()
                * activationFunction.outputDerivative(output);
    }

    public void updateConnections(double lr, double mu) {
        for (Connection connection : incomingConnections) {
            double prevDelta = connection.synapticWeightDelta;
            connection.synapticWeightDelta = lr * gradient * connection.from.output;
            connection.synapticWeight += connection.synapticWeightDelta + mu * prevDelta;
        }
    }

}