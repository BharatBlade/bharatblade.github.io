package College;
/***************************************************
 Assignment 4: ANNs Part 2 -- Implementing ML Networks
 CIS 495.2 Bio-Inspired AI, Fall 2017
 L. Grabowski
 Code adapted from J. Feldman and Mikhail Afanasyev
 Net.java
 **************************************************/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.*;

/**
 * Class implementing a basic three-layer neural network.
 */
public class Net {

  // All initial weights in the network range from -1 to 1.
  static double MIN_WEIGHT = -1.0;

  static double MAX_WEIGHT = 1.0;

  // training parameters (with some defaults)
  // Don't change these defaults or the test function won't work
  static double learningRate = 0.1;

  static double momentum = 0.1;

  static double errorCriterion = .1; // error threshold for training to stop

  int numEpochs = 10000; // number of epochs before training stops

  // (unless error drops below threshold)
  int curEpochs;

  int[][] trainingData;

  int[][] trainingTargets;

  /** A bias neuron, with output always equal to 1 */
  Unit Bias;

  /** The first input neuron, should take on first value from trainingData */
  Unit inUnit1;

  /** The second input neuron, should take on second value from trainingData */
  Unit inUnit2;

  /** The output neuron, should compute function of inputs and give Net's output */
  Unit outUnit;

  // other Net attributes ?

  ArrayList<Unit> inUnits = new ArrayList<Unit>();
  ArrayList<ArrayList<Unit>> hiddenUnits = new ArrayList<ArrayList<Unit>>();
  ArrayList<Unit> outUnits = new ArrayList<Unit>();
  
  int numInputs = 0;
  int hidLayers = 0;
  int nhid = 0;
  int numOutputs = 0;
  
  /**
   * Constructor for Net class.
   * 
   * Create Net with the specified architecture and data.
   * 
   * @param patterns
   *          Array of input patterns (each an array of int)
   * @param targets
   *          Array of output patterns (each an array of int)
   */
  public Net(int[][] patterns, int[][] targets) {
	    this.trainingData = patterns;
	    this.trainingTargets = targets;

	    // The Bias is implemented as a unit connected directly to every none input
	    // node.
	    // The bias node will always be active, and have no incomming connections.
	    Bias = new Unit();
	    Bias.activation = 1.0;

	    inUnit1 = new Unit();
	    inUnit2 = new Unit();
	    outUnit = new Unit();
	    inUnit1.setOutgoingUnit(outUnit);
	    inUnit2.setOutgoingUnit(outUnit);
	    outUnit.addIncomingUnit(inUnit1);
	    outUnit.addIncomingUnit(inUnit2);
	    outUnit.addIncomingUnit(Bias);

	    outUnit.initialize();
	  }
  public Net(int in, int hLayers, int nHid, int out, int[][] patterns, int[][] targets) {
    this.trainingData = patterns;
    this.trainingTargets = targets;
    numInputs = in;
    hidLayers = hLayers;
    nhid = nHid;
    numOutputs = out;
    // The Bias is implemented as a unit connected directly to every none input
    // node.
    // The bias node will always be active, and have no incomming connections.
    Bias = new Unit();
    Bias.activation = 1.0;

    for(int i = 0; i < in; i++){
    	inUnits.add(new Unit());
    }
    for(int i = 0; i < hLayers; i++){
    	ArrayList<Unit> hTemp = new ArrayList<Unit>();
    	for(int j = 0; j < nHid; j++){
    		hTemp.add(new Unit());
    	}
    	hiddenUnits.add(hTemp);
    }
    for(int i = 0; i < out; i++){
    	outUnits.add(new Unit());
    }
    for(int i = 0; i < inUnits.size(); i++){
    	for(int j = 0; j < hiddenUnits.get(0).size(); j++){
    		inUnits.get(i).setOutgoingUnit(hiddenUnits.get(0).get(j));
    		hiddenUnits.get(0).get(j).addIncomingUnit(inUnits.get(i));
    	}
    }
    for(int i = 0; i < hiddenUnits.size()-1; i++){
    	for(int j = 0; j < hiddenUnits.get(i).size(); j++){
    		for(int k = 0; k < hiddenUnits.get(i+1).size(); k++){
    			hiddenUnits.get(i).get(j).setOutgoingUnit(hiddenUnits.get(i+1).get(k));
    			hiddenUnits.get(i+1).get(k).addIncomingUnit(hiddenUnits.get(i).get(j));
    		}
    	}
    }
    for(int i = 0; i < hiddenUnits.get(hiddenUnits.size()-1).size(); i++){
    	for(int j = 0; j < outUnits.size(); j++){
    		hiddenUnits.get(hiddenUnits.size()-1).get(i).setOutgoingUnit(outUnits.get(j));
    		outUnits.get(j).addIncomingUnit(hiddenUnits.get(hiddenUnits.size()-1).get(i));
    	}
    }
    for(int i = 0; i < outUnits.size(); i++){
    	outUnits.get(i).addIncomingUnit(Bias);
    }
    for(int i = 0; i < inUnits.size(); i++){
    	inUnits.get(i).initialize();
    }
    for(int i = 0; i < hiddenUnits.size(); i++){
    	for(int j = 0; j < hiddenUnits.get(i).size(); j++){
    		hiddenUnits.get(i).get(j).initialize();
    	}
    }
    for(int i = 0; i < outUnits.size(); i++){
    	outUnits.get(i).initialize();
    }
  }

  /**
   * feedforward()
   * 
   * @param pattern
   *          An input training pattern
   * 
   * Present pattern and compute activations for rest of net.
   */
  public void feedforward(int[] pattern) {
	for(int i = 0; i < inUnits.size(); i++){
		inUnits.get(i).activation = pattern[i];
	}
	for(int i = 0; i < hiddenUnits.size(); i++){
		for(int j = 0; j < hiddenUnits.get(i).size(); j++){
			hiddenUnits.get(i).get(j).computeActivation();
		}
	}
	for(int i = 0; i < outUnits.size(); i++){
		outUnits.get(i).computeActivation();
	}
  }

  /**
   * computeError()
   * 
   * @return current network error
   * 
   * Present all patterns to network and calculate current error. Current error
   * is measured by sum of squared error on output nodes.
   */
  public double computeError() {
    double sumErr = 0;
    for(int i=0; i<trainingData.length; i++) {
        feedforward(trainingData[i]);
    	for(int j = 0; j < outUnits.size(); j++){
    		outUnits.get(j).computeError(trainingTargets[i][0]);
    		sumErr += 0.5 * Math.pow(outUnits.get(j).error,2);
    	}
    }
    return sumErr;
  }

  /**
   * train()
   * 
   * Train the net according to the current training parameters.
   * 
   * Initialize weights and present patterns in order, updating weights after
   * each pattern. Training continues until error falls below criterion, or when
   * numEpochs epochs have elapsed, whichever comes first.
   * 
   * This should call logNetwork() once, then use logActivationCalculation()
   * and logWeightUpdates() to log the first 10 epochs.  You will be graded
   * in part based on the data logged by this function!
   */
  public void train() {
    logNetwork();
    this.curEpochs = 0;
    double error = computeError();
    while (curEpochs<numEpochs && error>errorCriterion) {
      for (int i=0; i<trainingData.length; i++) {
        feedforward(trainingData[i]);
        if (curEpochs < 10)
          logActivationCalculation();
        for(int j = 0; j < hiddenUnits.size(); j++){
        	for(int k = 0; k < hiddenUnits.get(j).size(); k++){
        		hiddenUnits.get(j).get(k).computeError(trainingTargets[i][0]);
        		hiddenUnits.get(j).get(k).computeWeightChange();
        		hiddenUnits.get(j).get(k).updateWeights();
        	}
        }
        for(int j = 0; j < outUnits.size(); j++){
        	outUnits.get(j).computeError(trainingTargets[i][0]);
        	outUnits.get(j).computeWeightChange();
        	outUnits.get(j).updateWeights();
        }
        if (curEpochs<10)
          logWeightUpdates();
      }
      error = computeError();
      curEpochs += 1;
    }
    System.out.println(error);
  }

  /**
   * toString()
   * 
   * A method to neatly dump the weights of each link
   */
  public String toString() {
    String rStr = "\n";
    rStr += "Error: " + computeError() + " at epoch " + curEpochs + "\n";
    rStr += "Weights: \n";
    rStr += "   inUnit1 -> outUnit = " + outUnit.inWeights[0] + "\n";
    rStr += "   inUnit2 -> outUnit = " + outUnit.inWeights[1] + "\n";
    rStr += "   bias -> outUnit = " + outUnit.inWeights[2] + "\n";
    return rStr;
  }

  /**
   * Set network training parameters.  You need not modify this method.
   */
  public void setTrainingParameters(int n, double l, double m, double e) {
    numEpochs = n;
    learningRate = l;
    momentum = m;
    errorCriterion = e;
  }

  /**
   * Output network information.
   */
  public void logNetwork() {
    System.out.println("NETWORK");
    //** to be filled in **
    //  This is complete for Part 1, but change this method for Part 2
    //  For Part 2, you must also output:
    System.out.println(""+numInputs+" "+hidLayers+" "+nhid+" "+numOutputs);
    System.out.println(""+numEpochs+" "+learningRate+" "+momentum+" "+errorCriterion);
    logWeights();
  }
  
  /**
   * Output network weights.
   */
  public void logWeights() {
    System.out.println("WEIGHTS");
    for(int i = 0; i < this.hiddenUnits.size(); i++){
    	Unit[] hiddenUnits = new Unit[this.hiddenUnits.get(i).size()];
    	for(int j = 0; j < this.hiddenUnits.get(i).size(); j++){
    		hiddenUnits[j] = this.hiddenUnits.get(i).get(j);
    	}
    	logUnitArrayWeights(hiddenUnits);
    }
    
    Unit[] outUnits = new Unit[this.outUnits.size()];
    for(int i = 0; i < outUnits.length; i++){
    	outUnits[i] = this.outUnits.get(i);
    }
    
    logUnitArrayWeights(outUnits);
    //** to be filled in **
    //  This is complete for Part 1, but change this method for Part 2
    //  For Part 2, it should output the weights of all non-input units, starting
    //  at the first hidden layer, and going on down:
    //logUnitArrayWeights(layer);
  }

  /**
   * Output logging data about activation calculations.
   */
  public void logActivationCalculation() {
    logWeights();
    System.out.println("ACTIVATION");
    Unit[] inUnits = new Unit[this.inUnits.size()];
    for(int i = 0; i < this.inUnits.size(); i++){
    	inUnits[i] = this.inUnits.get(i);
    }
    logUnitArrayActivationCalculation(inUnits);
    
    for(int i = 0; i < this.hiddenUnits.size(); i++){
    	Unit[] hiddenUnits = new Unit[this.hiddenUnits.get(i).size()];
    	for(int j = 0; j < hiddenUnits.length; j++){
        	hiddenUnits[j] = this.hiddenUnits.get(i).get(j);
        }
    	logUnitArrayActivationCalculation(hiddenUnits);
    }
    
    Unit[] outUnits = new Unit[this.outUnits.size()];
    for(int i = 0; i < this.outUnits.size(); i++){
    	outUnits[i] = this.outUnits.get(i);
    }
    logUnitArrayActivationCalculation(outUnits);
    //** to be filled in **
    //  This is complete for Part 1, but should be altered for Part 2
    //  For Part 2, it should output the activations of all units, starting
    //  at the input layer, and going on down:
    //logUnitArrayActivationCalculation(layer);
  }

  /**
   * Output logging data about weight updates.  You need not modify this method.
   */
  public void logWeightUpdates() {
	System.out.println();
    System.out.println("MOMENTUM");
    for(int i = 0; i < outUnits.size(); i++){
    	System.out.print(arrayToString(outUnits.get(i).weightChangeMomentum) + " ");
    }
    System.out.println();
    System.out.println("WEIGHT CHANGE");
    
    for(int i = 0; i < hiddenUnits.size(); i++){
    	Unit[] hiddenUnits = new Unit[this.hiddenUnits.get(i).size()];
        for(int j = 0; j < this.hiddenUnits.get(i).size(); j++){
        	hiddenUnits[j] = this.hiddenUnits.get(i).get(j);
        }
        logUnitArrayWeightUpdates(hiddenUnits);
    }
    
    Unit[] outUnits = new Unit[this.outUnits.size()];
    for(int i = 0; i < outUnits.length; i++){
    	outUnits[i] = this.outUnits.get(i);
    }
    logUnitArrayWeightUpdates(outUnits);
    //** to be filled in **
    //  This is complete for Part 1, but should be altered for Part 2
    //  For Part 2, it should output the weight updates of all non-input units, starting
    //  at the first hidden layer, and going on down:
    //logUnitArrayWeightUpdates(layer);
  }
  
  /**
   * Log the weights of a single unit.  You need not modify this method.
   */
  private void logUnitWeights(Unit u) {
    for (int i=0; i<u.inWeights.length; i++) {
      System.out.print(""+u.inWeights[i]+" ");
    }
  }
  
  /**
   * Log the weights of an array of units.  You need not modify this method.
   * 
   * @param units
   */
  private void logUnitArrayWeights(Unit[] units) {
	  for (int i=0; i<units.length; i++) {
      logUnitWeights(units[i]);
      if (i!=units.length-1)
        System.out.print("; ");
    }
    System.out.println();
  }

  /**
   * Log the weight updates of an array of units.  You need not modify this method
   * 
   * @param units
   */
  public void logUnitArrayWeightUpdates(Unit[] units) {
    for (int i=0; i<units.length; i++) {
      Unit u = units[i];
      System.out.print(""+arrayToString(u.weightChange));
      if (i!=units.length-1)
        System.out.print("; ");
    }
    System.out.println();
  }

  /**
   * Log the activations of an array of units.  You need not modify this method.
   * 
   * @param units
   */
  public void logUnitArrayActivationCalculation(Unit[] units) {
    for (Unit u : units) {
      System.out.print(""+u.activation+" ");
    }
    //System.out.println();
  }
  
  /** 
   * Convert an array to a string with spaces.  You need not modify this method.
   */
  public static String arrayToString(double[] arr) {
    StringBuffer s = new StringBuffer();
    for (int i=0; i<arr.length; i++) {
      if (i!=0)
        s.append(" ");
      s.append(""+arr[i]);
    }
    return s.toString();
  }

  /**
   *  Parse an array of strings into an array of doubles.
   * 
   * @param arr
   * @return
   */
  public static double[] parseDoubleArray(String[] arr) {
    double[] d = new double[arr.length];
    for (int i=0; i<arr.length; i++) {
      d[i] = Double.parseDouble(arr[i]);
    }
    return d;
  }
}
