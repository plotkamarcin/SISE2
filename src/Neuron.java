import java.util.Random;
import java.util.Vector;

public class Neuron {

	private Integer howManyInputs = 2;

	public Vector<Double> inputs = new Vector<Double>(2);
	public Vector<Double> weights= new Vector<Double>(2);
	Double momentum = 0.00;// modyfikuje wagi aby nie wpaœæ w nieprawid³owe
							// minimum
	Double learningFactor = 0.001;
	Random random = new Random();

	public Neuron(Integer howManyInputs) {
		inputs = new Vector<Double>(howManyInputs);
		weights = new Vector<Double>(howManyInputs);
		this.howManyInputs = howManyInputs;
		this.generateWeights();
	}

	public Double sum() {
		Double result = 0.0;
		for (int i = 0; i < howManyInputs; i++) {
			result += inputs.get(i) * weights.get(i);
		}
		return result;
	}

	public Double activationFunction() {
		Double s = sum();
		return 2.0 / (1 + Math.exp(-s) - 1);
	}

	public void setInputs(Vector<Double> inputs) throws Exception {

		this.inputs = inputs;
	}
	public void setWeights(Vector<Double>weights) throws Exception{

		this.weights = weights;
	}
	public Double derivative(){
		return (1 - activationFunction()*activationFunction());
	}
	public void generateWeights(){
		Double max = 32767.0;
		for (int i = 0; i < howManyInputs; i++)
		{	
			weights.add((random.nextDouble()/((max-0.5))/4.0));
		}
	}
	public Double calculateOutputError(Double value){
		return (value-sum());
	}
	public Vector<Double> calculateDecisionError(Double outputError){
		Vector<Double> result = new Vector<Double>();
		for (double x : weights) 
		{
			result.add(x*outputError);
					}
		return result;
	}
	public Vector<Double> calculateActivationError(Vector<Double> decisionError){
		Vector<Double> result=new Vector<Double>();
		for (double x : decisionError) 
			{
			result.add(x*derivative());
			}
		return result;
	}
	public void correctHiddenWeightsError(Vector<Double> activationError){
		for (int i = 0; i < howManyInputs; i++)
		{
			double fix= momentum*weights.get(i) + learningFactor*inputs.get(i) * activationError.get(i);
			weights.set(i, fix);
		}
	}
	public void correctOutputWeightsError(Double outputError){
		for (int i = 0; i < howManyInputs; i++)
		{
			double fix = momentum*weights.get(i) + learningFactor*outputError*sum();
			weights.set(i,fix);
		}
	}
}
