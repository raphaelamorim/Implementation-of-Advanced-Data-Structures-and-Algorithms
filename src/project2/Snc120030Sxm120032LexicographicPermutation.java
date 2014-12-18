package project2;
import java.util.Scanner;


public class Snc120030Sxm120032LexicographicPermutation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the value of n and verbose");
		Scanner in = new Scanner(System.in);
		String val = in.nextLine();
		String[] split = val.split(" ");
		int n = Integer.parseInt(split[0]) + 1;
		int verbose = Integer.parseInt(split[1]);
		System.out.println("Enter the input (separated by spaces)");
		String input = in.nextLine();
		int[] A = new int[n];
		String[] splitInput = input.split(" ");
		A[0] = -1;
		for (int i = 0; i < splitInput.length; i++) {
			A[i + 1] = Integer.parseInt(splitInput[i]);
		}
		
		permutation(A, n, verbose);
		

	}

	private static void permutation(int[] A, int n, int v) {
		// TODO Auto-generated method stub
		
		
		//sort A
		long start = System.currentTimeMillis();
		sort(A);
		if (v > 0) {
			visit(A);
		}
		int count = 1;
		
		while(true) {
			int j = 0;
			for (int i = 0; i < A.length - 1; i++) {
				if (A[i] < A[i+1]) {
					j = i;
				}
			}
			
			if (j == 0) {
				break;
			}
			int l = A.length- 1;
			for (; l > j; l--) {
				if (A[j] < A[l]) {
//					swap(A, j, l);
					int temp = A[j];
					A[j] = A[l];
					A[l] = temp;
					break;
				}
			}
			
			reverse(A, j+1, n);
			
			if (v > 0) {
				visit(A);
			}
			count++;
		}
		long last = System.currentTimeMillis();
		long diff = last - start;
		System.out.println(count + " " + diff);
	}

	private static void visit(int[] A) {
		// TODO Auto-generated method stub
		
		for (int i = 1; i < A.length; i++) {
			System.out.print(A[i]);
		}
		System.out.println();
	}

	private static void reverse(int[] A, int j, int n) {
		// TODO Auto-generated method stub
		int length = n - j;
		int k = n - 1;
		int limit = j + (length/2);
		for (int i = j; i < limit; i++) {
//			swap(A, i, k);
			int temp = A[i];
			A[i] = A[k];
			A[k] = temp;
			k--;
		}
	}

	private static void swap(int[] A, int j, int l) {
		// TODO Auto-generated method stub
		
		int temp = A[j];
		A[j] = A[l];
		A[l] = temp;
	}

	private static void sort(int[] A) {
		// TODO Auto-generated method stub
//		List<Integer> list = new ArrayList<Integer>();
//		for (int i : A) {
//			list.add(i);
//		}
//		
//		Collections.sort(list);
//		for (int i = 0; i < list.size(); i++) {
//			A[i] = list.get(i);
//		}
		
		
		for (int j = 2; j < A.length; j++) {
			int key = A[j];
			int i = j-1;
			while (i > 0 && A[i] > key) {
				A[i + 1] = A[i];
				i = i - 1;
			}
			A[i + 1] = key;
		}
	}
	
	

}
