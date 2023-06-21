//Going to read in email spam dataset and apply naive bayes to it
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadingInDataSets {
	public static void main(String[] args) throws FileNotFoundException {
		
		//read in spam dataset
		File spam = new File("C:\\Users\\bliet\\eclipse-workspace\\NaiveBayesClassfier\\src\\SpamFilteringDataSet.txt");
		Scanner reader = new Scanner(spam);
		
		
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		ArrayList<String> row = new ArrayList<String>();
		ArrayList<String> labels = new ArrayList<String>();
		
		
		while (reader.hasNextLine())
		{
			String line = new String();
			line = reader.nextLine();
			String[] cut = line.split(",", -2);
			//System.out.println(cut[2]);
			for (int i = 0; i < cut.length - 1; i++)
			{
				row.add(cut[i]);
			}
			labels.add(cut[cut.length - 1]);
			data.add(row);
			row = new ArrayList<String>();
		}
		
		//remove the y data for each 
		
		//System.out.println(data);
		//System.out.println(labels);
		//System.out.println(labels);
		
		reader.close();
		
		//convert each string to  a number
		ArrayList<ArrayList<Double>> parsedData = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> parsedRow = new ArrayList<Double>();
		for (int k = 0; k < data.size(); k++)
		{
			for (int j = 0; j < data.get(k).size(); j++)
			{
				double parsedVal = Double.parseDouble(data.get(k).get(j));
				parsedRow.add(parsedVal);
			}
			parsedData.add(parsedRow);
			parsedRow = new ArrayList<Double>();
		}
		//System.out.println(parsedData);
		
		//convert parsedData into a 2d array double[][]
		double[][] spamData = new double[parsedData.size()][parsedData.get(0).size()];
		
		for (int r = 0; r < parsedData.size(); r++)
		{
			for (int c = 0; c < parsedData.get(0).size(); c++)
			{
				spamData[r][c] = parsedData.get(r).get(c);
			}
		}
		
		//convert labels into a string object
		String[] slabels = new String[labels.size()];
		
		for (int u = 0; u < labels.size(); u++)
		{
			slabels[u] = labels.get(u);
		}
		
		//System.out.println(Arrays.deepToString(spamData));
		//trial run
		nbc model = new nbc();
		model.fit(spamData, slabels);//so far, fit function looks like its working correctly.
		
		// to do a test, going to use last row from spamdata and slabels
		double[] newX = spamData[spamData.length - 1];
		double[] priors = {.5,.5};
		model.predict(newX, priors);
	}
}

/*row.add(reader.nextLine());
			//remove the y data and put it in label data
			labels.add(row.get(row.size() -1));
			row.remove(row.size() -1);
			//then add row to our data---the Features 
			data.add(row);
			row = new ArrayList<String>();
 * 
 * 
 */
