import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Snc120030_KruskalAlgo {

	Vertex[] nodeList = null;

	public List<Edge> minSpanningTree(Vertex[] nodeList1, List<Edge> edgeList) {
		// TODO Auto-generated method stub

		nodeList = nodeList1;
		for (Vertex u : nodeList) {
			if (u != null) {
				makeSet(u);
			}
		}

		List<Edge> F = new ArrayList<Edge>();

//		Collections.sort(edgeList, new Comparator<Edge>() {
//			public int compare(final Edge e1, final Edge e2) {
//				if (e1.Weight > e2.Weight) {
//					return 1;
//				}
//				return 0;
//			}
//		});
		
		Map<Integer, List<Edge>> edgeMap = new TreeMap<Integer, List<Edge>>();
		for(Edge e : edgeList) {
			if (edgeMap.containsKey(e.Weight)) {
				List<Edge> eList = edgeMap.get(e.Weight);
				eList.add(e);
			} else {
				List<Edge> eList = new ArrayList<Edge>();
				eList.add(e);
				edgeMap.put(e.Weight, eList);
			}
			
		}
		List<Edge> eList = new ArrayList<Edge>();
		Set<Entry<Integer, List<Edge>>> set = edgeMap.entrySet();
		for (Entry<Integer, List<Edge>> entry : set) {
			eList.addAll(entry.getValue());
		}

		for (Edge edge : eList) {

			int ru = find(edge.From);
			int rv = find(edge.To);
			if (ru != rv) {
				F.add(edge);
				union(nodeList[ru], nodeList[rv]);
			}
		}

		return F;

	}

	private void union(Vertex u, Vertex v) {
		// TODO Auto-generated method stub

		if (u.rank > v.rank) {
			v.p = u.node;
		} else if (u.rank < v.rank) {
			u.p = v.node;
		} else {
			v.p = u.node;
			u.rank++;
		}
	}

	private int find(Vertex u) {
		// TODO Auto-generated method stub

		if (u.node != u.p) {
			u.p = find(nodeList[u.p]);
		}
		return u.p;
	}

	private void makeSet(Vertex u) {
		// TODO Auto-generated method stub
		u.p = u.node;
		u.rank = 0;
	}

}
