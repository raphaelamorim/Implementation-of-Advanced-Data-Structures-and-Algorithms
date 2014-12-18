import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class KnuthPermutation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		System.out.println("Enter the value of n,c,v");
		Scanner in = new Scanner(System.in);
		String val = in.nextLine();
		String[] split = val.split(" ");
		int n = Integer.parseInt(split[0]);
		int c = Integer.parseInt(split[1]);
		int v = Integer.parseInt(split[2]);
		int[][] precedence = new int[c][2];
		System.out.println("Enter the constraints");
		for (int i = 0; i < c; i++) {
			String cons = in.nextLine();
			String[] consSPlit = cons.split(" ");
			precedence[i][0] = Integer.parseInt(consSPlit[0]);
			precedence[i][1] = Integer.parseInt(consSPlit[1]);
			
		}
		
		boolean[][] grid = partialOrderToGrid(precedence, n);
		
		topsort(n, grid, precedence);
	}

	private static void topsort(int n, boolean[][] grid, int[][] precedence) {
		// TODO Auto-generated method stub
		int[] loc = new int[n+1];
		for (int i = 0; i < loc.length; i++) {
			loc[i] = i;
		}
		//int[] p = new int[n+2];
		/*for (int i = 0; i < p.length; i++) {
			p[i] = i;
		}*/
//		p[0] = 0;
//		p[1] = 4;
//		p[2] = 1;
//		p[3] = 5;
//		p[4] = 2;
//		p[5] = 3;
//		p[6] = 6;
		
		DFS dfs = new DFS(n, precedence);
		int[] p = dfs.runTopologicalSort();
		Map<Integer, Integer> topoMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < p.length; i++) {
			topoMap.put(p[i], i);
		}
		
		
		for (int i = 1; i < n+1; i++) {
			System.out.print(topoMap.get(p[i]));
		}
		System.out.println();
		
		int i = 1;
		int k =1;
		while(i < n) {
			k = loc[i];
			int kk = k +1;
			if (grid[i][p[kk]]) {
				int temp = p[k];
				for (int j = k; j > i; j--) {
					p[j] = p[j-1];
					
				}
				p[i] = temp;
				loc[i] = i;
				i++;
			} else {
				int temp = p[k];
				p[k] = p[kk];
				p[kk] = temp;
				loc[i] = kk;
				i = 1;
				for (int l = 1; l < n+1; l++) {
					System.out.print(topoMap.get(p[l]));
				}
				System.out.println();
				
				
			}
		}
	}

	private static boolean[][] partialOrderToGrid(int[][] precedence, int n) {
		// TODO Auto-generated method stub
		boolean[][] grid = new boolean[n+2][n+2];
		for (int i = 0; i < n+2; i++) {
			for (int j = 0; j < n+2; j++) {
				if (j == n+1) {
					grid[i][j] = true;
				} else {
					grid[i][j] = false;
				}
			}
			
		}
		
		for (int i = 0; i < precedence.length; i++) {
			int[] prece = precedence[i];
			grid[prece[0]][prece[1]] = true;
			//grid[prece[1]][prece[0]] = true;
		}
		
		return grid;
	}

}
