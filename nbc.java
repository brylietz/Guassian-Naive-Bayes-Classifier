import java.util.ArrayList;
import java.util.Arrays;


public class nbc {
	
	//list of means for data points in a certain label
	int dim1 = 0;
	int dim2 = 0;
	private double[][] Means = new double[dim1][dim2];
	private double[][] Var = new double[dim1][dim2];
	private ArrayList<String> Labels = new ArrayList<String>();
	
	//This algorithm assumes the data has been sorted. This means that the labels are in 
	//alphabetical order and the features correspond to the right labels. 
	public void fit(double[][] feat, String[] labels)
	{
		double sum = 0;
		double mean = 0;
		double variance = 0;
		int n = 0;
		LabelFeatureSort.sortNBC(feat, labels);
		boolean done = false;
		int counter = 0;
		int r = 0;
		
		//get Labels
		for (int q = 0; q < labels.length -1; q++)
		{
			if (!labels[q].equals(labels[q+1]))
			{
				if (!Labels.contains(labels[q]))
				{
					Labels.add(labels[q]);
				}
				if (!Labels.contains(labels[q+1]))
				{
					Labels.add(labels[q+1]);
				}
			}
		}
		System.out.println(Labels);
		double[][] means = new double[Labels.size()][feat[0].length];
		double[][] var = new double[Labels.size()][feat[0].length];
		
		//calculate means for each label
		for(int k = 0; k < feat[0].length; k++)
		{
			n = 0;
			r = 0;
			while(n<labels.length)
			{
				if (n+1==labels.length)
				{
					sum = sum + feat[n][k];
					counter++;
					mean = sum /counter;
					means[r][k] = mean;
					sum=0;
					mean=0;
					counter=0;
					n++;
				}
				else
				{
					if(labels[n].equals(labels[n+1]))
					{
						sum = sum + feat[n][k];
						counter++;
						n++;
					}
					else 
					{
						sum = sum + feat[n][k];
						counter++;
						mean = sum /counter;
						means[r][k] = mean;
						sum=0;
						mean=0;
						counter=0;
						r++;
						n++;
					}
				}
			}
		}
			
			//calculate variance for each label
			double varsum = 0;
			for(int col = 0; col < feat[0].length; col++)
			{
				n = 0;
				r = 0;
				while(n<labels.length)
				{
					if (n+1==labels.length)
					{
						varsum = varsum +Math.pow((feat[n][col]-means[r][col]), 2);
						counter++;
						variance = varsum / (counter - 1);
						var[r][col] = variance;
						varsum=0;
						variance=0;
						counter=0;
						n++;
					}
					else
					{
						if(labels[n].equals(labels[n+1]))
						{
							varsum = varsum +Math.pow((feat[n][col]-means[r][col]), 2);
							counter++;
							n++;
						}
						else 
						{
							varsum = varsum +Math.pow((feat[n][col]-means[r][col]), 2);
							counter++;
							if (counter - 1 == 0)
							{
								variance = varsum;
							}
							else
							{
								variance = varsum /(counter - 1);
							}
							var[r][col] = variance;
							varsum=0;
							variance=0;
							counter=0;
							r++;
							n++;
						}
					}
					
				}
			}
			
		Means = means;
		Var = var;
		System.out.println("Rows correspond to labels. Columns denote num of features. Means are ");
		System.out.println(Arrays.deepToString(Means));
		System.out.println("Variances are ");
		System.out.println(Arrays.deepToString(Var));
	}
	
	//Normal distribution pdf
	//need to watch out division by 0 with variance
	public double gaussian(double x, double mean, double variance)
	{
		if (variance == 0)
		{
			return 0;
		}
		else
		{
			return ((1/(Math.sqrt(2 * Math.PI * variance))) * (1 / (Math.exp(Math.pow((x-mean), 2)/ (2 * variance)))));
		}
	}
	
	//pick up for next time. Calc denom of conditional probability. 
	//Find maximum value in cond prob. 
	//Then find what label is associated with it, something like this.
	//Note you don't need denominator of conditional probability because choosing maximum value.
	public void predict(double[] feat, double[] prior)
	{
		double[] condprob = new double[prior.length];
		double value = 0;
		
		for(int row = 0; row < Means.length; row++)
		{
			value = prior[row];
			for (int col = 0; col < feat.length; col++)
			{
				value = value * gaussian(feat[col], Means[row][col], Var[row][col]);
			}
			condprob[row] = value;
			value=0;
		}
		System.out.println("Proportional conditional probabilities are ");
		System.out.println(Arrays.toString(condprob));
		
		//extract maximum value from condprob
		double largest = condprob[0];
		int pred = 0;
		for (int z =0; z < condprob.length; z++)
		{
			if(condprob[z] > largest)
			{
				largest = condprob[z];
				pred = z;
			}
		}
		System.out.println("Largest is ");
		System.out.println(largest);
		System.out.println("The predicted label is ");
		System.out.println(Labels);
		System.out.print(Labels.get(pred));
		
	}
}

/*while(n<labels.length)
		{
			for (int k = 0; k < feat[0].length; k++)
			{
				if (n+1==labels.length)
				{
					sum = sum + feat[n][k];
					mean = sum /feat.length;
					means.add(mean);
					sum=0;
					mean=0;
					n++;
				}
				else
				{
					if(labels[n].equals(labels[n+1]))
					{
						sum = sum + feat[n][k];
						n++;
					}
					else 
					{
						sum = sum + feat[n][k];
						mean = sum /feat.length;
						means.add(mean);
						sum=0;
						mean=0;
						n++;
					}
				}
			}
		}*/
