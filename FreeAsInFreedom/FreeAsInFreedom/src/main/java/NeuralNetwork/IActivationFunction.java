package NeuralNetwork;
public interface IActivationFunction {
    double output(double x);
    double outputDerivative(double x);
}