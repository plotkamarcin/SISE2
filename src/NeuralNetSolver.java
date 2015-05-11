import java.util.Vector;

import javax.swing.JOptionPane;


public class NeuralNetSolver {
	public NeuralNetwork net = new NeuralNetwork(10);
	
	public void train(NeuralNetwork nn) throws Exception
	{
		Integer howManyEpochs;
		String txt1;
		txt1 = JOptionPane.showInputDialog("Wprowadz ilosc epok");
		howManyEpochs= Integer.parseInt(txt1);
		Vector<Double>input = new Vector<Double>();
		Vector<Double>output = new Vector<Double>();
		for (int i = 0; i < 20; i++)
		{
			input.add(Math.random() % 100);
			output.add(Math.sqrt(input.get(i)));
		}
		nn.train(input, output, howManyEpochs);
		System.out.println("trening sieci zakonczony ");
	}	
}
