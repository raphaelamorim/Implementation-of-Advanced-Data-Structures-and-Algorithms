import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;




class VertexP10 {
	
	public int node;
	public List<Edge> edgeList = new ArrayList<Edge>();
	public int p;
	public int rank;
	public int minSpanDegree = 0;
	
	
	
	

}


public class Snc120030_P10 {
	
	//array of nodes
	private static Vertex[] nodeList;

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
		int i = 0;
		nodeList = new Vertex[ver + 1];
		List<Edge> edgeList = new ArrayList<Edge>();
		while (i == 0 || i < e) {
			line = scanner.nextLine();
			lineSplit = line.split(" ");
			int u = Integer.parseInt(lineSplit[0]);
			//vertex u
			Vertex uVertex = nodeList[u];
			if (uVertex == null) {
				uVertex = new Vertex();
				uVertex.node = u;
			}
			int v = Integer.parseInt(lineSplit[1]);
			//vertex v
			Vertex vVertex = nodeList[v];
			if (vVertex == null) {
				vVertex = new Vertex();
				vVertex.node = v;
			}

			if (nodeList[u] == null) {
				nodeList[u] =  uVertex;
			}
			int w = Integer.parseInt(lineSplit[2]);
			//edge u to v
			Edge edge = new Edge(uVertex, vVertex, w);
			uVertex.edgeList.add(edge);
			//edge v to u
			vVertex.edgeList.add(edge);
			edgeList.add(edge);
			
			if (nodeList[v] == null) {
				nodeList[v] = vVertex;
			}
			i++;
		}
		
		
		Snc120030_KruskalAlgo kruskalAlgorithm = new Snc120030_KruskalAlgo();
		List<Edge> minSpanningTree = kruskalAlgorithm.minSpanningTree(nodeList, edgeList);
		
		
		//find nodes in graph that have odd degree
		for (Edge edge : minSpanningTree) {
			Vertex u = edge.From;
//			u.minSpanDegree++;
			
			Vertex v = edge.To;
//			v.minSpanDegree++;
		}
		
		List<Vertex> S = new ArrayList<Vertex>();
		for(Vertex vert : nodeList) {
//			if (vert.minSpanDegree % 2 != 0) {
//				S.add(vert);
//			}
		}
		
		
		
		//all pair shortest path
		int[][] dis = new int[ver][ver];
		int[][] p = new int[ver][ver];
		for(int j = 0; j < ver; j++){
			for(int k = 0; k < ver; k++){
				if (j == k) {
					dis[j][k] = 0;
				} else {
					dis[j][k] = Integer.MAX_VALUE;
				}
			}
		}
		
		for(int j = 0; j < ver; j++){
			for(int k = 0; k < ver; k++){
				
				if (dis[j][k] != 0 && dis[j][k] != Integer.MAX_VALUE) {
					p[j][k] = i;
				} else {
					p[j][k] = -1;
				}
			}
		}
		
		for(Edge ed : edgeList) {
			int x = ed.From.node - 1;
			int y = ed.To.node - 1;
			dis[x][y] = ed.Weight;
		}
		
		for (int k = 0; k < ver; k++) {
			for (int l = 0; l < ver; l++) {
				for (int j = 0; j < ver; j++) {
					if (dis[l][k] == Integer.MAX_VALUE
							|| dis[k][j] == Integer.MAX_VALUE) {
						continue;
					}

					if (dis[l][j] > dis[l][k] + dis[k][j]) {
						dis[i][j] = dis[i][k] + dis[k][j];
						p[l][j] = p[k][j];
					}
				}
			}
		}		
		
		
		

	}

}

