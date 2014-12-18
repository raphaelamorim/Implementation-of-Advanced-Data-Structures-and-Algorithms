import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




class VertexP14 {
	
	public int node;
	public List<VertexP14> vertexList = new ArrayList<VertexP14>();
	public boolean known;
	public int parent;

}

public class Snc120030_P14 {

	
	//array of nodes
	private static VertexP14[] nodeList;
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		String[] lineSplit = line.split(" ");
		//no of vertices
		int ver = Integer.parseInt(lineSplit[0]);
		//no of edges
		int e = Integer.parseInt(lineSplit[1]);
		int s = Integer.parseInt(lineSplit[2]);
		int t = Integer.parseInt(lineSplit[3]);
		int[][] weightMapA = new int[ver][ver];
		int[][] weightMapB = new int[ver][ver];
		int i = 0;
		
		//create array to store weight
		for(int j = 0; j < ver; j++) {
			for(int k = 0; k < ver; k++) {
				if (j == k) {
					weightMapA[j][k] = 0;
					weightMapB[j][k] = 0;
				} else {
					weightMapA[j][k] = 1000000;
					weightMapB[j][k] = 1000000;
				}
			}
		}
		
		while (i == 0 || i < e) {
			line = scanner.nextLine();
			lineSplit = line.split(" ");
			int u = Integer.parseInt(lineSplit[0]);
			int v = Integer.parseInt(lineSplit[1]);
			int w = Integer.parseInt(lineSplit[2]);
			weightMapA[u-1][v-1] = w;
			weightMapB[u-1][v-1] = w;
			
			
			//vertex u
			VertexP14 uVertex = nodeList[u];
			if (uVertex == null) {
				uVertex = new VertexP14();
				uVertex.node = u;
			}
			//vertex v
			VertexP14 vVertex = nodeList[v];
			if (vVertex == null) {
				vVertex = new VertexP14();
				vVertex.node = v;
			}

			if (nodeList[u] == null) {
				nodeList[u] =  uVertex;
			}
			
			if (nodeList[v] == null) {
				nodeList[v] = vVertex;
			}

			uVertex.vertexList.add(vVertex);
			
			i++;
		}
		
		for (VertexP14 v : nodeList) {
			if(!v.known) {
				dfsVisit(v);
			}
		}
		
		
		
		System.out.println("0 INF");
		long start=System.currentTimeMillis();
		
		int out = 1;
		int[][] dup = new int[ver][ver];
		
		//run matrix min addition algorithm to find shortest path with i-1 edges
		for(int l = 0; l < ver; l++) {
			System.out.println(out+" "+weightMapA[s - 1][t - 1]);
			
			for(int m = 0; m < ver; m++) {
				for(int j = 0; j < ver; j++) {
					dup[m][j]=100000;
					for(int k = 0; k < ver; k++)
					{
						int sum = weightMapA[m][k]+weightMapB[k][j];
						if(dup[m][j] > sum) {
							dup[m][j]=sum;
						}
					}
					
				}
			}
			weightMapA = dup;
			//increment the no of edges value
			out = out * 2;
		}
		
		long end=System.currentTimeMillis();
		System.out.println(end-start);

	}

	private static boolean dfsVisit(VertexP14 v) {
		// TODO Auto-generated method stub
		v.known = true;
		List<VertexP14> vList = v.vertexList;
		for (VertexP14 vertexP14 : vList) {
			if(!vertexP14.known) {
				dfsVisit(vertexP14);
				vertexP14.parent = v.node; 
			} else {
				System.out.println("Not a DAG");
				System.exit(0);
				return true;
			}
		}
		
		return false;
	}
	
	

}
