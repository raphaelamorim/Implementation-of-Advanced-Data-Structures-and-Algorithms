import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Snc120030_P11Basic {
	
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
		int s = Integer.parseInt(lineSplit[2]);
		float alpha = Float.parseFloat(lineSplit[3]);
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
		
		
		KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm();
		List<Edge> minSpanningTree = kruskalAlgorithm.minSpanningTree(nodeList, edgeList);
		
		for (Edge edge : minSpanningTree) {
			System.out.println(edge.From.node + " " + edge.To.node + " " + edge.Weight);
		}
		
		int[] dist = new int[ver+1];
		Vertex[] prev = dijkstra(s, dist);
		
		findLast(minSpanningTree, dist, prev, s, alpha, nodeList);

	}

	private static void findLast(List<Edge> minSpanningTree, int[] dist,
			Vertex[] prev, int s, float alpha, Vertex[] nodeList) {
		// TODO Auto-generated method stub
		
		Vertex[] p = new Vertex[prev.length];
		int[] d = new int[prev.length];
		initialize(s, p, d);
		
	}

	private static void initialize(int s, Vertex[] p, int[] d) {
		// TODO Auto-generated method stub
		
	}

	private static Vertex[] dijkstra(int s, int[] dist) {
		// TODO Auto-generated method stub
		dist[s] = 0;
		Vertex[] previous = new Vertex[dist.length];
		List<Vertex> Q = new ArrayList<Vertex>();
		for (Vertex v : nodeList) {
			if (v != null) {
				if (v.node != s) {
					dist[v.node] = -1;
					previous[v.node] = null;
				}
				Q.add(v);
			}
		}
		
		List<Integer> visitedQ = new ArrayList<Integer>();
		while(Q.size() != visitedQ.size()) {
			List<Integer> notVisitednodes = new ArrayList<Integer>();
			for(Vertex node : Q) {
				if (!visitedQ.contains(node.node)) {
					notVisitednodes.add(node.node);
				}
			}
			
			
			int smallestDistanceNode = getSmallestDistance(notVisitednodes, dist);
			Vertex u = nodeList[smallestDistanceNode];
			for(Edge e : u.edgeList) {
				Vertex v = e.otherEnd(u);
				if((dist[u.node] + e.Weight) < dist[v.node]) {
					dist[v.node] = dist[u.node] + e.Weight;
					previous[v.node] = u;
				}
			    
			}
			
			
			
			
		}
		
		return previous;
	}
	
	
	private static int getSmallestDistance(List<Integer> notVisitedNodeList, int[] dist) {
		
		
		int smallestDistanceNode = 100000;
		//maximum distance same as null
		int smallestDistance = Integer.MAX_VALUE;
		
		for (int nodeNo : notVisitedNodeList) {
			
			if (dist[nodeNo] != -1) {
				int distance = dist[nodeNo];
				if (distance < smallestDistance) {
					smallestDistance = distance;
					smallestDistanceNode = nodeNo;
				}
			}
			
		}
		
		
		return smallestDistanceNode;
	}

}
