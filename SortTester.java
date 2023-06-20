import java.util.Arrays;

public class SortTester {
	public static void main(String[] args) {
		
		//wikipedia person classfier test
		double[][] X = {{6, 180, 12, 88, 800}, 
						{5.92, 190, 11, 90, 79.478237}, 
						{5.58, 170, 12, 75, 200.58894958}, 
						{5.92, 165, 10, 82, .0009}, 
						{5, 100, 6, 99, .0003}, 
						{5.5, 150, 8, 89, 6666666}, 
						{5.42, 130, 7, 72, .03},
						{5.75, 150, 9, 69, .0005},
						{5.25, 120, 5.5, 90, 50000},
						{5.11, 175, 10, 98, 6666666}};
		//String [] y = {"zach", "male", "male", "female", "female", "male","female"};
		String [] orig = {"z", "z", "a", "b", "f", "f", "f", "f", "t", "t"};
		//String[] sorted = Arrays.copyOf(orig, orig.length);
		//LabelFeatureSort.sortLF(sorted);
		//LabelFeatureSort.dataArrange(orig, sorted, X);
		
		LabelFeatureSort.sortNBC(X, orig);
		
		
		//System.out.println(Arrays.deepToString(orig));
		//System.out.println(Arrays.deepToString(sorted));
		//double[] Y = {5};
		//System.out.println(Arrays.toString(Y));
		
		nbc model = new nbc();
		model.fit(X,orig);
		double[] newX = {6, 130, 8, 88};
		double[] priors = {.2,.2,.2,.2,.2};
		model.predict(newX, priors);


	}
}
