import java.util.Arrays;
import java.util.ArrayList;

public class Dynamic {
	
	private Integer[] values;
	private Integer target;
	private Integer[][] memo;
	private int[][] s;
	
	// Constructor
	public Dynamic(Integer[] values, Integer target) {
		this.values = values;
		this.target = target;
	}
	
	/**
	* Return minimum number of integers, recursive version
	* Calls helper function
	* @return an integer
	*/
	public int solveRecursive() {
		// Create new table
		memo = new Integer[values.length + 1][target + 1];
		s = new int[values.length + 1][target + 1];
		return findMinInteger(values.length, target);
	}
	
	/**
	* Helper function
	* @param i the list
	* @param j the target
	* @return an integer
	*/
	public int findMinInteger(int i, int j) {
		
		// Check the table
		if (memo[i][j] != null) {
			return memo[i][j];
		}
		
		// Base case
		if (j == 0) {
			memo[i][j] = 0;
			return 0;
		}
		
		// Inf
		if (i == 0) {
			memo[i][j] = Integer.MAX_VALUE;// java max value, basically infinite
			return memo[i][j];
		}
		
		// Can't take
		if (values[i - 1] > j) {
			memo[i][j] = findMinInteger(i - 1, j);
			s[i][j] = 0;
			return memo[i][j];
		}
		
		// No Take
		int noTake = findMinInteger(i - 1, j);
		
		// Take
		int take = findMinInteger(i - 1, j - values[i - 1]);
		
		// To prevent integer overflow
		if (take != Integer.MAX_VALUE) {
			take = take + 1;
		}
		
		// Take or noTake
		if (take < noTake) {
			memo[i][j] = take;
			s[i][j] = 1;
		} else {
			memo[i][j] = noTake;
			s[i][j] = 0;
		}
		
		return memo[i][j];
	}
	
	/**
	* Return minimum number of integers, iterative version
	* @return an integer
	*/
	public int solveIterative() {
		// Tables
		memo = new Integer[values.length + 1][target + 1];
		s = new int[values.length + 1][target + 1];
		
		for (int i = 0; i <= values.length; i++) {
			for (int j = 0; j <= target; j++) {
				
				if (j == 0) {
					memo[i][j] = 0;// Base case
				} else if (i == 0) {
					memo[i][j] = Integer.MAX_VALUE;// Infinite
				} else if (values[i - 1] > j) {
					memo[i][j] = memo[i - 1][j];// Can't take
				} else {
					int noTake = memo[i - 1][j];
					int take = memo[i -1][j - values[i -1]];
					
					if (take != Integer.MAX_VALUE) {
						take = take + 1;
					}
					
					if (take < noTake) {
						memo[i][j] = take;// Take
						s[i][j] = 1;
					} else {
						memo[i][j] = noTake;// No Take
						s[i][j] = 0;
					}
				}
			}
		}
		
		return memo[values.length][target];
	}
	
	/**
	* Return a list of choices.
	* @return a list
	*/
	public ArrayList<Integer> listChoices() {
		
		ArrayList<Integer> solutionList = new ArrayList<>();// To track the choices
		int i = values.length;
		int j = target;
		
		while (i != 0 && j != 0) {
			if (s[i][j] == 1) {
				solutionList.add(values[i - 1]);// Add to the solutionList
				j = j - values[i -1];
			}
			i = i - 1;
		}
		
		return solutionList;
	}
}