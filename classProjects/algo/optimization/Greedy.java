import java.util.ArrayList;
import java.util.Arrays;

public class Greedy {
	
	/**
	* Return a set of minimum number of integers
	* @param value, a list of values
	* @param target, the total sum
	* @return a list of choices
	*/
	public static ArrayList<Integer> solve(Integer[] value, Integer target) {
		// Sorting
		Arrays.sort(value);
		Integer[] sortedValue = value;
		
		// To track choices
		ArrayList<Integer> choices = new ArrayList<>();
		
		int i = value.length - 1;
		int j = target;
		
		// Iteration
		while (i != 0 && j != 0) {
			if (sortedValue[i] <= j) {// Take the value
				choices.add(sortedValue[i]);// Add to the choices
				j = j - sortedValue[i];
			}
			i = i - 1;
		}
		
		return choices;
	}
}