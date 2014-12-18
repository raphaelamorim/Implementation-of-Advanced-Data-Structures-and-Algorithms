package project9;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;




class Vertex {
	
	private boolean seen;
	private Vertex mate;
	private int node;
	private List<Vertex> adjList = new ArrayList<Vertex>();
	private String type;
	private int parent;
	private int distance = Integer.MAX_VALUE;
	
	
	
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Vertex> getAdjList() {
		return adjList;
	}
	public void setAdjList(List<Vertex> adjList) {
		this.adjList = adjList;
	}
	public int getNode() {
		return node;
	}
	public void setNode(int node) {
		this.node = node;
	}
	public boolean isSeen() {
		return seen;
	}
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	public Vertex getMate() {
		return mate;
	}
	public void setMate(Vertex mate) {
		this.mate = mate;
	}
	
	

}

public class Snc120030_P9Basic {
	
	//queue of vertices
	private static Queue<Vertex> Q = new LinkedList<Vertex>();
	//cardinality size
	private static int mSize = 0;
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
		while (i == 0 || i < e) {
			line = scanner.nextLine();
			lineSplit = line.split(" ");
			int u = Integer.parseInt(lineSplit[0]);
			//vertex u
			Vertex uVertex = nodeList[u];
			if (uVertex == null) {
				uVertex = new Vertex();
				uVertex.setNode(u);
			}
			int v = Integer.parseInt(lineSplit[1]);
			//vertex v
			Vertex vVertex = nodeList[v];
			if (vVertex == null) {
				vVertex = new Vertex();
				vVertex.setNode(v);
			}

			if (nodeList[u] == null) {
				nodeList[u] =  uVertex;
			}
			//edge u to v
			uVertex.getAdjList().add(vVertex);
			//edge v to u
			vVertex.getAdjList().add(uVertex);
			
			if (nodeList[v] == null) {
				nodeList[v] = vVertex;
			}
			i++;
		}
		
		long start = System.currentTimeMillis();
		boolean isBipartite = true;
		for (Vertex v : nodeList) {
			if (v!= null && !v.isSeen()) {
				//distance to 0
				v.setDistance(0);
				//checking for bipartite 
				isBipartite &= isBipartite(v);
			}
		}
		
		
		if (!isBipartite) {
			//if the graph is Not bipartite then exit
			System.out.println("Not bipartite");
			System.exit(0);
		} else {

			for (Vertex v : nodeList) {
				if (v != null) {
					if (v.getDistance() % 2 == 0) {
						//set the type outer
						v.setType("outer");
					} else {
						//set the type inner
						v.setType("inner");
					}
				}
			}
		}
		
//		for (Vertex v : nodeList) {
//			if (v != null) {
//				System.out.println(v.getNode() + " - " + v.getType());
//			}
//		}
		
		
		for (Vertex v : nodeList) {
			if (v != null) {
				//adjacent list of vertex
				List<Vertex> adjList = v.getAdjList();
				for (Vertex adj : adjList) {
					if (v.getMate() == null && adj.getMate() == null) {
						//set mate v to adj
						v.setMate(adj);
						//set mate adj to v
						adj.setMate(v);
						//increase cardinality size by 1
						mSize++;
					}
				}
			}
		}
		
		
		//find augmenting path
		while(true) {
			Queue<Vertex> queue = new LinkedList<Vertex>();
			for (Vertex v : nodeList) {
				if (v != null) {
					//set seen as false
					v.setSeen(false);
					//set parent as null
					v.setParent(-1);
					if (v.getMate() == null && v.getType().equals("outer")) {
						//set seen as true
						v.setSeen(true);
						//add to queue
						queue.add(v);
					}
				}
			}
			
			boolean pathFound = false;
			while(!queue.isEmpty()) {
				Vertex u = queue.poll();
				boolean breakFlag = false;
				for(Vertex v : u.getAdjList()) {
					if(!v.isSeen()) {
						//set parent as u
						v.setParent(u.getNode());
						//set seen as true
						v.setSeen(true);
						if (v.getMate() == null) {
							//process augmenting path
							processAugPath(v);
							pathFound = true;
							breakFlag = true;
							break;
							//got to start
						} else {
							Vertex x = v.getMate();
							//set seen as true to the mate vertex
							x.setSeen(true);
							//set parent as v
							x.setParent(v.getNode());
							//add to queue
							queue.add(x);
						}
					}
				}
				if(breakFlag) {
					break;
				}
			}
			
			if(!pathFound) {
				break;
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println(mSize + " " + (end - start));
		//System.out.println(mSize);
		
		for (Vertex v : nodeList) {
			if (v != null) {
				if (v.getMate() != null) {
					System.out.println(v.getNode() + " " + v.getMate().getNode());
				} else {
					System.out.println(v.getNode() + " - ");
				}
			}
		}
	}
	
	
	private static void processAugPath(Vertex u) {
		// TODO Auto-generated method stub
		//u parent
		Vertex p = nodeList[u.getParent()];
		//p parent
		Vertex x = nodeList[p.getParent()];
		//set mate u to p
		u.setMate(p);
		//set mate p to u
		p.setMate(u);
		while(x != null) {
			//parent of x
			Vertex nmx = nodeList[x.getParent()];
			Vertex y = null;
			if (nmx.getParent() >= 0) {
				//parent of y
				y = nodeList[nmx.getParent()];
			}
			//mate x to nmx
			x.setMate(nmx);
			//set mate nmx to x
			nmx.setMate(x);
			x = y;
		}
		//increase cardinality size by 1
		mSize++;
		
	}


	/**
	 * Method to perform BFS on the graph to check if the graph is bipartite or
	 * not
	 * 
	 * @param g
	 *            : Graph - The reference to the graph
	 * @param src
	 *            : Vertex - The reference to the source vertex of the component
	 * @return true if the graph is bipartite
	 */
	static boolean isBipartite(Vertex src) {
		boolean isBipartite = true;

		Q.clear();
		// add the source vertex to the queue
		Q.add(src);
		// mark the source as visited
		src.setSeen(true);

		// Perform BFS
		while (!Q.isEmpty()) {
			// remove a vertex from the head of the queue
			Vertex u = Q.poll();
			// iterate through the u's adjacency list
			for (Vertex v : u.getAdjList()) {
				/*
				 * if the vertex v is not visited then mark v as visited and
				 * update v's distance and parent and then add v to the queue
				 */
				if (!v.isSeen()) {
					v.setSeen(true);
					v.setParent(u.getNode());
					v.setDistance(u.getDistance() + 1);
					Q.add(v);
				} else {
					/*
					 * if the ends of edge (u,v), vertices u and v, are at the 
					 * same distance from the source, the graph is not bipartite
					 */
					if (u.getDistance() == v.getDistance())
						isBipartite = false;
				}
			}
		}
		return isBipartite;
	}

}
