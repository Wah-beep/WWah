import java.util.Random;
import java.util.ArrayList;

public class Main {
	
	public static Integer[] getRandomList(int size) {
		Random rand = new Random();
		Integer[] arr = new Integer[size];
		
		for (int i = 0; i < size; i++) {
			arr[i] = rand.nextInt(99) + 1;// values 1-99 
		}
		
		return arr;
	}
	
	public static void main(String[] args) {
		Integer[] testValues = {0,5,2,3,1,1,7,1,2};// For testing only
		int testTarget = 10;
		Integer[] values = getRandomList(50);
		Random rand = new Random();
		Integer target = rand.nextInt(1000);	
		
		System.out.println("Target: " + target);
		
		// Iterative
		Dynamic sumDP = new Dynamic(values, target);
		int count = sumDP.solveIterative();
		ArrayList<Integer> resultI = sumDP.listChoices();
		System.out.println(resultI + " Iterative");
		
		// Recursive
		count = sumDP.solveRecursive();
		ArrayList<Integer> resultR = sumDP.listChoices();
		System.out.println(resultR + " Recursive");
		
		// Greedy
		ArrayList<Integer> resultG = Greedy.solve(values, target);
		System.out.println(resultG + " Greedy");
	}
}