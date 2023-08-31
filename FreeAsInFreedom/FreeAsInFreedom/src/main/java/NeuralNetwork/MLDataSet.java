package NeuralNetwork;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MLDataSet {

    public double[][] inputs, targets;
    public MLData [] data;
	
    public MLDataSet(double[][] inputs, double[][] targets) {
        this.data = new MLData[inputs.length];
        this.inputs = inputs; this.targets = targets;
        for (int i = 0; i < this.inputs.length; i++) {
            this.data[i] = (new MLData(inputs[i], targets[i]));
        }
    }
    
    public void shuffle() {
    	List<MLData> temp = Arrays.asList(data);		
		Collections.shuffle(temp);
		for(int i = 0; i < temp.size(); i++) {
			data[i] = temp.get(i);
		}
    }
}