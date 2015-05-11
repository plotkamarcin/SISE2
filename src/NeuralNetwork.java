import java.util.Vector;

public class NeuralNetwork {
	Integer howManyHidden = 10;
	Neuron outputNeuron;
	Double input;
	Double expectedOutput;
	Double output;
	Vector<Neuron> hiddenLayer;

	public NeuralNetwork(Integer howManyHidden) {
		hiddenLayer = new Vector<Neuron>(howManyHidden);
		outputNeuron = new Neuron(howManyHidden);
		this.howManyHidden = howManyHidden;
	}

	public void loadWeights() {
		throw new UnsupportedOperationException("not implemented yet!");
	}

	public void showWeights() {
		throw new UnsupportedOperationException("not implemented yet!");
	}

	public void saveWeights() {
		throw new UnsupportedOperationException("not implemented yet!");
	}

	public Double calculate(Double input, Double expectedOutput) throws Exception{
		this.expectedOutput=expectedOutput;
		this.input=input;
		Vector<Double> temp = new Vector<Double>();
		temp.add(1.0);
		temp.add(input);
		for (int i = 0; i < howManyHidden; i++)
			{
			hiddenLayer.get(i).setInputs(temp);//1 na pierwszym wejsciu - bias, dodatkowa waga kontrolna
			}
		Vector<Double>hiddenLayerResult = new Vector<Double>();
		for (Neuron x : hiddenLayer)
			{
			hiddenLayerResult.add(x.activationFunction());
			}
		outputNeuron.setInputs(hiddenLayerResult);
		output = outputNeuron.sum();
		Double networkError = outputNeuron.calculateOutputError(expectedOutput);
		Vector<Double> outputWeightsCorrection = outputNeuron.calculateDecisionError(networkError);
		Vector<Vector<Double>> fixedHiddenWeights = new Vector<Vector<Double>>();
		for (Neuron x : hiddenLayer)
			{
			fixedHiddenWeights.add(x.calculateActivationError(outputWeightsCorrection));
			}
		outputNeuron.correctOutputWeightsError(networkError);
		for (int i = 0; i < howManyHidden; i++)
		{
			hiddenLayer.get(i).correctHiddenWeightsError(outputWeightsCorrection);
		}
		System.out.println(expectedOutput-output);
		return output;		
	}
	void train(Vector<Double> inputs, Vector<Double> outputs, Integer howManyEpochs) throws Exception{
		for (int i = 0; i < howManyEpochs; i++)
		{
			for (int j = 0; j < inputs.size(); j++)
			{
				this.calculate(inputs.get(i), outputs.get(i));
			}
		}
	}
}
