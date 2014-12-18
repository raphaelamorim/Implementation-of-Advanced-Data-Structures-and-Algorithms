package project2;
import java.util.Scanner;


public class Snc120030Sxm120032PermComb {
	
	private static long count = 0;
	private static int n = 0;
	private static int verbose = 1;
	private static int k = 0;
	private static int[] B;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the value of n");
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		System.out.println("Enter the value of k");
		k = in.nextInt();
		System.out.println("Enter verbose");
		verbose = in.nextInt();
		int[] A = new int[n];
		B = new int[n];
		
//		permutation(A, n);
		long start = System.currentTimeMillis();
		if (verbose == 0 || verbose == 2) {
			permutation(A, n, k);
		} else {
			combination(A, n, k);
		}
		long last = System.currentTimeMillis();
		long diff = last - start;
		
		System.out.println(count + " " + diff);

	}

	private static void combination(int[] A, int i, int k) {
		// TODO Auto-generated method stub
		if (k == 0) {
			visit(A, n, verbose);
		} else if (i == 0 && k > 0){
			return;
		} else {
			A[i-1] = i;
			combination(A, i-1, k-1);
			A[i-1] = 0;
			combination(A, i-1, k);
			
				
		}
		
	}

	private static void permutation(int[] A, int i) {
		// TODO Auto-generated method stub
		if (i == 0) {
			visit(A, n, verbose);
		}
		else {
			for (int j = 1; j <= n; j++) {
				if (A[j-1] == 0) {
					A[j-1] = i;
					permutation(A, i-1);
					A[j-1] = 0;
				}
			}
		}
	}
	
	private static void permutation(int[] A, int i, int k) {
		// TODO Auto-generated method stub
		if (k == 0) {
			visit(A, n, verbose);
		}
		else {
			for (int j = 1; j <= n; j++) {
				if (B[j-1] == 0) {
					A[i-1] = j;
					B[j-1] = j;
					permutation(A, i-1, k-1);
					B[j-1] = 0;
					//permutation(A, i-1, k);
				}
			}
		}
	}
	


	private static void visit(int[] A, int n, int verbose)
	{
		if (verbose == 2 || verbose == 3) {
			for (int i = 0; i < n; i++) {
				if (A[i] > 0)
					System.out.print(A[i] + " ");
			}
			System.out.println();
		}
	   count++;
	}

}
