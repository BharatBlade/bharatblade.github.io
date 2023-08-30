package NeuralNetwork;
import java.util.UUID;

public class Connection {
    public UUID connectionId;
	public Neuron from, to;
    public double synapticWeight, synapticWeightDelta;

    public Connection(Neuron from, Neuron to) {
        this.connectionId = UUID.randomUUID();
        this.from = from; this.to = to;
        this.synapticWeight = RandomGenerator.randomValue(-2, 2);
    }
}