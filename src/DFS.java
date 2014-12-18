import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Graph {
	int data;
	String color;
	int d;
	int f;
}
public class DFS {
	
	static int time = 0;
	static List<Graph> topologicalSort = new ArrayList<Graph>();
	static int n = 0;
	static int[][] pre;
	
	public DFS(int n, int[][] precedence) {
		this.n= n;
		this.pre  = precedence; 
				
	}

	/**
	 * @param args
	 */
	public static int[] runTopologicalSort() {
		// TODO Auto-generated method stub

		Map<Integer, Graph> graphMap = new HashMap<Integer, Graph>();
		for (int i = 1; i <= n; i++) {
			Graph graph = new Graph();
			graph.data = i; 
			graph.color = "White";
			graphMap.put(i, graph);
		}
		Map<Integer, List<Graph>> adjList = new HashMap<Integer, List<Graph>>();
		//int[][] pre = {{1,2}, {2,3}, {1,5}};
		int[] topoOrder = new int[n+2];
		topoOrder[0] = 0;
		topoOrder[n+1] = n+1;
		/*Set<Entry<Integer, Graph>> set = graphMap.entrySet();
		for (Entry<Integer, Graph> entry : set) {
			Graph src = graphMap.get(entry.getKey());
			Graph dest = graphMap.get(entry.getValue());
			
			if (adjList.get(entry.getKey()) == null) {
				List<Graph> list = new ArrayList<Graph>();
				list.add(dest);
				adjList.put(entry.getKey(), list);
			} else {
				List<Graph> list = adjList.get(entry.getKey());
				list.add(dest);
				//adjList.put(entry.getKey(), list);
			}
		}*/
		
		for (int i = 0; i < pre.length; i++) {
			int[] val = pre[i];
			Graph src = graphMap.get(val[0]);
			Graph dest = graphMap.get(val[1]);
			
			if (adjList.get(val[0]) == null) {
				List<Graph> list = new ArrayList<Graph>();
				list.add(dest);
				adjList.put(val[0], list);
			} else {
				List<Graph> list = adjList.get(val[0]);
				list.add(dest);
				//adjList.put(entry.getKey(), list);
			}
		}
		
		for (int i = 1; i <= n; i++) {
			Graph vertex = graphMap.get(i);
			if (vertex.color.equals("White")) {
				dfsVisit(graphMap, adjList, vertex);
			}
		}
		int m = 1;
		for (int i = topologicalSort.size() - 1; i >= 0; i--) {
			System.out.println(topologicalSort.get(i).data);
			topoOrder[m] = topologicalSort.get(i).data;
			m++;
		}
		
		
		return topoOrder;
	}

	private static void dfsVisit(Map<Integer, Graph> graphMap,
			Map<Integer, List<Graph>> adjList, Graph vertex) {
		// TODO Auto-generated method stub
		time++;
		vertex.d = time;
		vertex.color = "Gray";
		List<Graph> list = adjList.get(vertex.data);
		if (list != null) {
			for (Graph graph : list) {
				if (graph.color.equals("White")) {
					dfsVisit(graphMap, adjList, graph);
				}
			}
		}
		vertex.color = "Black";
		time++;
		vertex.f = time;
		topologicalSort.add(vertex);
	}

	

}
