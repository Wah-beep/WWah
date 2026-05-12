import java.util.*;
import java.lang.reflect.Array;
import java.util.function.Function;

/** NOT the classic implementation of Merge Sort -- this is iterative */
class MergeIterative {

	/** Sorts the array based on the key extracted with keyGetter.
	@param array Array of any type to be sorted.
	@param keyGetter Function to extract sorting key from each array element.
	@return None. The passed array will be sorted upon completion.
	*/
	public static <T,K extends Comparable<K>> void sort(T[] array, Function<T,K> keyGetter) {
		
		// Iterate & increasing subarray size 
		for (int currSize = 1; currSize <= array.length - 1; currSize = 2 * currSize) {
			
			// Starting points of subarray
			for (int leftStart = 0; leftStart < array.length - 1; leftStart += 2 * currSize) {
				
				// Find endpoints
				int mid = Math.min(leftStart + currSize - 1, array.length - 1);
				int rightEnd = Math.min(leftStart + 2 * currSize - 1, array.length - 1);
				
				// Helper
				merge(array, leftStart, mid, rightEnd, keyGetter);
			}
		}
	} // end MergeIterative()
	
	/** Merge two sorted subarrays into one sorted subarray (in original array).
	@param array Array to be sorted by key 
	@param start Leftmost index of left subarray
	@param middle Last index of left subarray
	@param end Last index of right subarray
	@param keyGetter Function to extract key
	*/
	public static <T,K extends Comparable<K>> void merge(T[] array, int start, int middle, int end, Function<T,K> keyGetter) {
		
		/* 
		1) create two new arrays to hold sorted subarrays that will be merged
		*/
		
		int size_left = middle - start + 1;
		int size_right = end - middle;
		
		// claude recommendation (you cannot directly declare a T[] array)
    	@SuppressWarnings("unchecked")
    	T[] left = (T[]) Array.newInstance(
        	array.getClass().getComponentType(), size_left);
        	
        @SuppressWarnings("unchecked")
    	T[] right = (T[]) Array.newInstance(
        	array.getClass().getComponentType(), size_right);
		
		// copy array elements into temp left and right arrays
		int left_idx = 0;
		for (int i=start; i<=middle; i++) {
			left[left_idx] = array[i];
			left_idx++;
		}
		int right_idx = 0;
		for (int i=middle+1; i<=end; i++) {
			right[right_idx] = array[i];
			right_idx++;
		}
		
		/*
		2) Repeatedly check next element in each subarray to place back into
		original array in sorted order.
		*/
		
		// set pointers into first element in left and right
		left_idx = 0;
		right_idx = 0;
		
		// place elements of left and right back into original array [start:end]
		for (int i=start; i<=end; i++) {
			
			// are either index off the end? no need to compare
			if (right_idx >= size_right) {
				array[i] = left[left_idx];
				left_idx++;
			} else if (left_idx >= size_left) {
				array[i] = right[right_idx];
				right_idx++;
				
			} else {
				// whichever is less, place in array and move to next
				K left_value = keyGetter.apply(left[left_idx]);
				K right_value = keyGetter.apply(right[right_idx]);
				if ( left_value.compareTo(right_value) <= 0) {
					// left is less
					array[i] = left[left_idx];
					left_idx++;
				} else {
					// right is less
					array[i] = right[right_idx];
					right_idx++;
				}
			} // end else find minimum of the values
		} // end for int i
	} // end merge
} // end class MergeIterative
	
/*
Recursive Merge Sort divides the array in half, in half, in half, ...
until the subarray is of size 1, then it merges 2 elements.
Then it merges subarrays of size 2 (4 elements).
Then it merges subarrays of size 4 (8 elements).

In this iterative version, we follow the same pattern.
In your first pass through the array, 
order all pairs array[0][1],array[2][3],[4][5], ... [n-1]
Then order those pairs array[0]..[3], array[4]..[7], ... [n-1]
Keep making passes through the array increasing subarray size each pass,
until you have merged the two n/2 sized subarrays (if n=2^x).

You will need additional space to copy the left & right subarrays
to then place them back into original array. BE EFFICIENT with space.
Creating generic type arrays looks like this:
@SuppressWarnings("unchecked")
T[] arrayCopy = (T[]) Array.newInstance(
	array.getClass().getComponentType(), n);

Ordering array elements is based on the key. 
Like this: keyGetter.apply(left[left_idx])).compareTo(keyGetter.apply(right[right_idx])) <= 0)

The size of the subarrays to be merged are powers of 2, which are the step size for a pass through the array (except when n is not a power of 2).
BEWARE arrays that are not powers of 2. Think through an array of size 10 -- how will you handle those extra 2 elements at the end? 
I recommend first writing code for an array of size 8 (or some 2^x), then fix it to handle any size.

The number of times you have to pass through the array to merge subarrays is based on log2(n), 
like this: int passes = (int) (Math.log(n) / Math.log(2));

It is useful to be explicit about your indices. There are a lot of them in this code. 
It is probably best to name them (for example, start or left_idx, as opposed to p or i) 
and double check their value -- super easy to be off by 1.

There are elements in the iterative version that also appear in the recursive version (which has been completed for you). 
Feel free to copy those elements into this algorithm.
*/

