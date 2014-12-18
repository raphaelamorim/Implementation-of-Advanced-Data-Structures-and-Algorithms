package project1;
import java.io.*;

public class Snc120030EvenBetterMergeSort {

	static int MergeSort(int[] A, int[] B, int p, int r) {
		try {
			if (p < r) {
				if (true) {
					int q = (p + r) / 2;
					int h1 = MergeSort(A, B, p, q);
					int h2 = MergeSort(A, B, q + 1, r);
					if (h1 != h2) {
						// throw new Exception("Not Power of 2");
						if (h1 % 2 == 0) {
							for (int x = p; x <= q; x++)
								A[x] = B[x];
						} else {
							for (int x = q + 1; x <= r; x++)
								B[x] = A[x];
						}
					}
					if (h1 % 2 != 0) {
						Merge(B, A, p, q, r);
					} else {
						Merge(A, B, p, q, r);
					}
					return h1 + 1;
				} else { // Insertion sort
					for (int i = p, j = i; i < r; j = ++i) {
						int ai = A[i + 1];
						while (ai < A[j]) {
							A[j + 1] = A[j];
							if (j-- == p) {
								break;
							}
						}
						A[j + 1] = ai;
					}
					return 0;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	static void Merge(int[] src, int[] dest, int p, int q, int r) {

		int i = p;
		int j = q + 1;
		for (int k = p; k <= r; k++) {
			if ((j > r) || ((i <= q) && (src[i] <= src[j])))
				dest[k] = src[i++];
			else
				dest[k] = src[j++];
		}
		return;
	}

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt("8");
		int[] A = new int[n];
		int[] B = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = n - i;
		}
		long start = System.currentTimeMillis();
		int val = MergeSort(A, B, 0, n - 1);
		long last = System.currentTimeMillis();

		if (val % 2 == 0) {
			for (int j = 0; j < A.length - 1; j++) {
				//System.out.println(A[j]);
				if (A[j] > A[j + 1]) {
					System.out.println("Sorting failed :-(");
					return;
				}
			}
		} else {
			for (int j = 0; j < B.length - 1; j++) {
				//System.out.println(B[j]);
				if (B[j] > B[j + 1]) {
					System.out.println("Sorting failed :-(");
					return;
				}
			}
		}
		System.out.println("Success!");
		System.out.println(last - start);

	}
}