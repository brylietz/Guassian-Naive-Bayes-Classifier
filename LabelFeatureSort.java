/*Note for user: You have to save an original file of y. Make a copy of y. Then you sort the copy of y.
 * Once that is done, you then feed the original, the sorted, and the data into dataArrange to 
 * sort the data to correspond with it's original y value.
 */
import java.util.Arrays;
import java.util.ArrayList;

public class LabelFeatureSort {
	
	public static void sortNBC(double[][] feat, String[] labels)
	{
		String[] orig = Arrays.copyOf(labels, labels.length);
		sortLF(labels);
		dataArrange(orig, labels, feat);
		//labels=Arrays.copyOf(sorted, sorted.length);
		
	}
		
	public static void sortLF(String[] y)
	{

		if(y.length <= 1) {return;}
		String[] firsty = new String[y.length / 2];
		String[] secondy = new String[y.length - firsty.length];
		//copy the first half of y into firsty, second into secondy
		for (int i = 0; i < firsty.length; i++)
		{				
			firsty[i]=y[i];
		}
		for (int i = 0; i < secondy.length; i++)
		{
			secondy[i] = y[firsty.length + i];
		}
		sortLF(firsty);
		sortLF(secondy);
		merge(firsty, secondy, y); 
		
	}
	
	private static void merge(String[] firsty, String[] secondy, String[] y) 
	{
		int iFirst = 0; //Next element to consider in first array
		int iSecond = 0; //Next element to consdier in second array
		int j = 0; // Next open position in y
		
		// As long as neither iFirst nor iSecond past the end, move
		// the smaller element into y. Swap corresponding positions in data.
		while (iFirst < firsty.length && iSecond < secondy.length)
		{
			if (firsty[iFirst].compareTo(secondy[iSecond]) < 0)
			{
				y[j] = firsty[iFirst];
				//newX[j] = data[iFirst];
				//data[j] = data[iFirst];
				iFirst++;
			}
			else
			{
				y[j] = secondy[iSecond];
				//newX[j] = data[iSecond];
				//data[j] = data[iSecond];
				iSecond++;
			}
			j++;
		}
		// Copy any remaining entries of the first array
		while(iFirst < firsty.length)
		{
			y[j] = firsty[iFirst];
			//newX[j] = data[iFirst];
			//data[j] = data[iFirst];
			iFirst++;
			j++;
		}
		while(iSecond < secondy.length)
		{
			y[j] = secondy[iSecond];
			//newX[j] = data[iSecond];
			//data[j] = data[iSecond];
			iSecond++;
			j++;
		}
	}
	
	public static void dataArrange(String[] orig, String [] sorted, double[][] data)
	{
		boolean done = false;
		int n = 0;
		//String[] memory = Arrays.copyOf(y, y.length);
		ArrayList<Integer> num = new ArrayList<Integer>();
		double[][] newX = new double[data.length][data[0].length];
		
		for (int k = 0; k < orig.length; k++)
		{
			done = false;
			n = 0;
			while(!done)
			{
				if(orig[k].equals(sorted[n]) && !num.contains(n))
				{
					num.add(n);
					newX[n] = data[k].clone();
					done = true;
				}
				else
				{
					n++;
				}
			}
		}
		for (int f = 0; f < data.length; f++)
		{
			data[f] = newX[f].clone();
		}
	}

}





/*if(memory[k].equals(y[n]) && !num.contains(n))
				{
					num.add(n);
					double[][] temp = new double[1][data[0].length];
					for (int v = 0; v < data[0].length; v++)
					{
						temp[0][v] = data[k][v];
					}
					for (int w = 0; w < data[0].length; w++)
					{
						data[k][w] = data[n][w];
					}
					for (int g = 0; g < data[0].length; g++)
					{
						data[n][g] = temp[0][g];
					}
					n++;
					done = true;
 * 
 * 
 * 
 * 
 * import java.util.Arrays;
import java.util.ArrayList;

public class LabelFeatureSort {
	
	private int tracker = 0;
	
	public void sortLF(String[] y, double[][] data)
	{
		if (tracker == 0)
		{
			String[] memory = Arrays.copyOf(y, y.length);
			tracker++;
		}
		else
		{
			if(y.length <= 1) {return;}
			String[] firsty = new String[y.length / 2];
			String[] secondy = new String[y.length - firsty.length];
			//copy the first half of y into firsty, second into secondy
			for (int i = 0; i < firsty.length; i++)
			{				
				firsty[i]=y[i];
			}
			for (int i = 0; i < secondy.length; i++)
			{
				secondy[i] = y[firsty.length + i];
			}
			sortLF(firsty, data);
			sortLF(secondy, data);
			merge(firsty, secondy, y, data, memory); 
		}
	}
	
	private void merge(String[] firsty, String[] secondy, String[] y, double[][] data, String[] memory) 
	{
		int iFirst = 0; //Next element to consider in first array
		int iSecond = 0; //Next element to consdier in second array
		int j = 0; // Next open position in y
		//String [] memory = Arrays.copyOf(y, y.length);
		
		// As long as neither iFirst nor iSecond past the end, move
		// the smaller element into y. Swap corresponding positions in data.
		while (iFirst < firsty.length && iSecond < secondy.length)
		{
			if (firsty[iFirst].compareTo(secondy[iSecond]) < 0)
			{
				y[j] = firsty[iFirst];
				//newX[j] = data[iFirst];
				//data[j] = data[iFirst];
				iFirst++;
			}
			else
			{
				y[j] = secondy[iSecond];
				//newX[j] = data[iSecond];
				//data[j] = data[iSecond];
				iSecond++;
			}
			j++;
		}
		// Copy any remaining entries of the first array
		while(iFirst < firsty.length)
		{
			y[j] = firsty[iFirst];
			//newX[j] = data[iFirst];
			//data[j] = data[iFirst];
			iFirst++;
			j++;
		}
		while(iSecond < secondy.length)
		{
			y[j] = secondy[iSecond];
			//newX[j] = data[iSecond];
			//data[j] = data[iSecond];
			iSecond++;
			j++;
		}
		
		//Rearrange Data rows to match corresponding label.
		boolean done = false;
		int n = 0;
		ArrayList<Integer> num = new ArrayList<Integer>();
		double[][] newX = new double[data.length][data[0].length];
		
		for (int k = 0; k < memory.length; k++)
		{
			done = false;
			n = 0;
			while(!done)
			{
				if(memory[k].equals(y[n]) && !num.contains(n))
				{
					num.add(n);
					newX[n] = data[k].clone();
					done = true;
				}
				else
				{
					n++;
				}
			}
		}
		for (int f = 0; f < data.length; f++)
		{
			data[f] = newX[f].clone();
		}
		//data = newX;
	}

}





/*if(memory[k].equals(y[n]) && !num.contains(n))
				{
					num.add(n);
					double[][] temp = new double[1][data[0].length];
					for (int v = 0; v < data[0].length; v++)
					{
						temp[0][v] = data[k][v];
					}
					for (int w = 0; w < data[0].length; w++)
					{
						data[k][w] = data[n][w];
					}
					for (int g = 0; g < data[0].length; g++)
					{
						data[n][g] = temp[0][g];
					}
					n++;
					done = true;
 * 
 * 
 * 
 * 
 * 
 */

/*
 * import java.util.Arrays;
import java.util.ArrayList;

public class LabelFeatureSort {
	
	private int tracker = 0;
	
	public void sortLF(String[] y, double[][] data)
	{
		if (tracker == 0)
		{
			String[] memory = Arrays.copyOf(y, y.length);
			tracker++;
		}
		else
		{
			if(y.length <= 1) {return;}
			String[] firsty = new String[y.length / 2];
			String[] secondy = new String[y.length - firsty.length];
			//copy the first half of y into firsty, second into secondy
			for (int i = 0; i < firsty.length; i++)
			{				
				firsty[i]=y[i];
			}
			for (int i = 0; i < secondy.length; i++)
			{
				secondy[i] = y[firsty.length + i];
			}
			sortLF(firsty, data);
			sortLF(secondy, data);
			merge(firsty, secondy, y, data, memory); 
		}
	}
	
	private void merge(String[] firsty, String[] secondy, String[] y, double[][] data, String[] memory) 
	{
		int iFirst = 0; //Next element to consider in first array
		int iSecond = 0; //Next element to consdier in second array
		int j = 0; // Next open position in y
		//String [] memory = Arrays.copyOf(y, y.length);
		
		// As long as neither iFirst nor iSecond past the end, move
		// the smaller element into y. Swap corresponding positions in data.
		while (iFirst < firsty.length && iSecond < secondy.length)
		{
			if (firsty[iFirst].compareTo(secondy[iSecond]) < 0)
			{
				y[j] = firsty[iFirst];
				//newX[j] = data[iFirst];
				//data[j] = data[iFirst];
				iFirst++;
			}
			else
			{
				y[j] = secondy[iSecond];
				//newX[j] = data[iSecond];
				//data[j] = data[iSecond];
				iSecond++;
			}
			j++;
		}
		// Copy any remaining entries of the first array
		while(iFirst < firsty.length)
		{
			y[j] = firsty[iFirst];
			//newX[j] = data[iFirst];
			//data[j] = data[iFirst];
			iFirst++;
			j++;
		}
		while(iSecond < secondy.length)
		{
			y[j] = secondy[iSecond];
			//newX[j] = data[iSecond];
			//data[j] = data[iSecond];
			iSecond++;
			j++;
		}
		
		//Rearrange Data rows to match corresponding label.
		boolean done = false;
		int n = 0;
		ArrayList<Integer> num = new ArrayList<Integer>();
		double[][] newX = new double[data.length][data[0].length];
		
		for (int k = 0; k < memory.length; k++)
		{
			done = false;
			n = 0;
			while(!done)
			{
				if(memory[k].equals(y[n]) && !num.contains(n))
				{
					num.add(n);
					newX[n] = data[k].clone();
					done = true;
				}
				else
				{
					n++;
				}
			}
		}
		for (int f = 0; f < data.length; f++)
		{
			data[f] = newX[f].clone();
		}
		//data = newX;
	}

}





/*if(memory[k].equals(y[n]) && !num.contains(n))
				{
					num.add(n);
					double[][] temp = new double[1][data[0].length];
					for (int v = 0; v < data[0].length; v++)
					{
						temp[0][v] = data[k][v];
					}
					for (int w = 0; w < data[0].length; w++)
					{
						data[k][w] = data[n][w];
					}
					for (int g = 0; g < data[0].length; g++)
					{
						data[n][g] = temp[0][g];
					}
					n++;
					done = true;
 * 
 * 
 * 
 * 
 * 
 */

