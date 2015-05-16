import java.util.Random;
import java.util.Vector;

import javax.swing.JOptionPane;


public class NeuralNetSolver {
	public NeuralNetwork net = new NeuralNetwork(10);
	
	public void train() throws Exception
	{
		Random random = new Random();
		Integer howManyEpochs;
		String txt1;
		txt1 = JOptionPane.showInputDialog("Wprowadz ilosc epok");
		howManyEpochs= Integer.parseInt(txt1);
		Vector<Double>input = new Vector<Double>();
		Vector<Double>output = new Vector<Double>();
		for (int i = 0; i < 20; i++)
		{
			Double temp = random.nextDouble()*100;
			input.add(temp);
			output.add(Math.sqrt(input.get(i)));
		}
		net.train(input, output, howManyEpochs);
		System.out.println("trening sieci zakonczony ");
	}
	public void calculate() throws Exception{
		String txt1;
		txt1 = JOptionPane.showInputDialog("Wprowadz liczbe dla ktorej liczysz pierwiastek");
		Double squareRoot= Double.parseDouble(txt1);
		net.calculate(squareRoot, Math.sqrt(squareRoot));
		net.showWeights();
	}
}
