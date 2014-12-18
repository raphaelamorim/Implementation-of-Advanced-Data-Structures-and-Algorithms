import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;



public class MaximumMatching {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		String[] lineSplit = line.split(" ");
		int ver = Integer.parseInt(lineSplit[0]);
		int e = Integer.parseInt(lineSplit[1]);
		int i = 0;
		Snc120030_Vertex[] nodeList = new Snc120030_Vertex[ver + 1];
		while (i == 0 || i < e) {
			line = scanner.nextLine();
			lineSplit = line.split(" ");
			int u = Integer.parseInt(lineSplit[0]);
			Snc120030_Vertex uVertex = nodeList[u];
			if (uVertex == null) {
				uVertex = new Snc120030_Vertex();
				uVertex.setNode(u);
			}
			int v = Integer.parseInt(lineSplit[1]);
			Snc120030_Vertex vVertex = nodeList[v];
			if (vVertex == null) {
				vVertex = new Snc120030_Vertex();
				vVertex.setNode(v);
			}

			if (nodeList[u] == null) {
				nodeList[u] =  uVertex;
			}
			uVertex.getAdjList().add(vVertex);
			vVertex.getAdjList().add(uVertex);
			
			if (nodeList[v] == null) {
				nodeList[v] = vVertex;
			}
			i++;
		}
		
		int mSize = 0;
		//maximal matching
		for (Snc120030_Vertex v : nodeList) {
			if (v != null && v.getMate() == null) {
				List<Snc120030_Vertex> adjList = v.getAdjList();
				for (Snc120030_Vertex adj : adjList) {
					if (adj.getMate() == null) {
						v.setMate(adj);
						adj.setMate(v);
						mSize++;
					}
				}
			}
		}
		
		
		
//		for (Snc120030_Vertex v : nodeList) {
//			if (!v.isSeen()) {
//			}
//		}
		
		
		
		//augmenting process
		Snc120030_Vertex start = null;
		for (Snc120030_Vertex v : nodeList) {
			if (v != null && v.getMate() == null) {
				start = v;
				break;
			}
		}
		
		Queue<Snc120030_Vertex> q = new LinkedList<Snc120030_Vertex>();
		q.add(start);
		start.setSeen(true);
		start.setType("outer");
		
		while(!q.isEmpty()) {
			Snc120030_Vertex u = q.poll();
			System.out.println("node : " + u.getNode() + " - " + u.getType());
			int mateNode = -1;
			if (u.getMate() != null) {
				mateNode = u.getMate().getNode();
			}
			for(Snc120030_Vertex adj : u.getAdjList()) {
				if (!adj.isSeen() && mateNode != adj.getNode()) {
					adj.setSeen(true);
					adj.setParent(u.getNode());
					adj.setType("inner");
					System.out.println("node : " + adj.getNode() + " - " + adj.getType());
					if (adj.getMate() != null) {
						Snc120030_Vertex x = adj.getMate();
						x.setSeen(true);
						x.setParent(adj.getNode());
						x.setType("outer");
						q.add(x);
					} else {
						
					}
				} else {
					if (u.getType().equalsIgnoreCase(adj.getType())
							&& u.getType().equals("outer")) {
						System.out.println("Blossom exists");
					}
				}
				
				
			}
		}
		
		Queue<Snc120030_Vertex> outerNodes = new LinkedList<Snc120030_Vertex>();
		for(Snc120030_Vertex n : nodeList) {
			if (n.getType().equals("outer")) {
				outerNodes.add(n);
			}
		}
		
		while (!outerNodes.isEmpty()) {
			Snc120030_Vertex u = outerNodes.poll();
			int mateNode = -1;
			if (u.getMate() != null) {
				mateNode = u.getMate().getNode();
			}
			List<Snc120030_Vertex> adjList = u.getAdjList();
			for (Snc120030_Vertex v : adjList) {
				if(mateNode == v.getNode()) {
					continue;
				} else {
					if(v.isSeen()) {
						
					} else if (v.getType().equals("inner") && v.isSeen()) {
						continue;
					} else if (!v.isSeen()) {
						v.setType("inner");
						v.setParent(u.getNode());
						Snc120030_Vertex x = v.getMate();
						x.setType("outer");
						x.setParent(v.getNode());
						q.add(v);
						v.setSeen(true);
					}  else if (v.getType().equals("inner") && v.isSeen()) {
						continue;
					} else if (true) {
						
					}
				}
			}
		}
	}

}
