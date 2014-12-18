import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;




class Vertex {
	
	public int node;
	public List<Edge> edgeList = new ArrayList<Edge>();
	public int p;
	public int rank;
	public boolean isExists;
	public List<Vertex> childList = new ArrayList<Vertex>();
	public int degree;
	public boolean isBad;

}


public class Snc120030_P13 {
	
	//array of nodes
	private static Vertex[] nodeList;
	private static List<Edge> edgeList;

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
		int SCount = 0;
		List<Integer> SNodes = new ArrayList<Integer>();
		while (SCount == 0 || SCount < s) {
			line = scanner.nextLine();
			lineSplit = line.split(" ");
			
			for(String node : lineSplit) {
				SNodes.add(Integer.parseInt(node));
				SCount++;
			}
			
		}
		
		int i = 0;
		nodeList = new Vertex[ver + 1];
		edgeList = new ArrayList<Edge>();
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
		
		
		//hamilton path
		//hamiltonPath();
		
		KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm();
		List<Edge> minSpanningTree = kruskalAlgorithm.minSpanningTree(nodeList, edgeList);
		
		spanDVertices(minSpanningTree, SNodes);
		
		List<Vertex> verList = new ArrayList<Vertex>();
		Vertex root = null;
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.add(root);
		int maxDegree = 0;
		while (!q.isEmpty()) {
			Vertex u = q.poll();
			int degree = u.childList.size();
			if (degree > maxDegree) {
				maxDegree = degree;
			}
			
			u.degree = degree;
			for(Vertex v : u.childList) {
				q.add(v);
			}
		}
		
		

	}
	
	
	private static void spanDVertices(List<Edge> minSpanningTree,
			List<Integer> sNodes) {
		// TODO Auto-generated method stub
		
		for(int i : sNodes) {
			Vertex v = nodeList[i];
			v.isExists = true;
		}
		
		Vertex root = null;
		for (Edge e : minSpanningTree) {
			e.isMinSpanTree = true;
			Vertex u =e.From;
			u.degree++;
			if (u.degree == 1) {
				root = u;
			}
			Vertex v =e.To;
			v.degree++;
			if (v.degree == 1) {
				root = v;
			}
		}
		
		
		
		checkNodeExists(nodeList[sNodes.get(0)]);
		
		
		
	}

	

	private static boolean checkNodeExists(Vertex root) {
		// TODO Auto-generated method stub
		
		
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.add(root);
		while(!q.isEmpty()) {
			
		}
		
		
		
		
		for(Vertex n : nodeList) {
			List<Edge> edList = n.edgeList;
			for (Edge edge : edList) {
				if (edge.isMinSpanTree) {
					if (!edge.isProcessed) {
						Vertex other = edge.otherEnd(n);
						edge.isProcessed = true;
						boolean req = checkNodeExists(other);
						
						if(req) {
							edge.isReq = true;
						}
						
					}
				} else {
					edge.isReq = false;
				}
			}

			
			
		}
		
		
				

		if (root.isExists) {
			return true;
		}
		
		
		return false;
	}


	public static void hamiltonPath(){ 
		//List all possible Hamilton path with fixed starting point
//	    int[] path = new int[nodeList.length];
//	    int l = 0;
//	    int i;
//	    for(i = start-1;i<start;i++){ //Go through row(with given column)
//	        path[0]=i+1;
//	        findHamiltonpath(x,0,i,0, l, path.length);
//	    }
	}

//	private boolean findHamiltonpath(int[] path, int l, int len){
//
//	    int i = 0;
//	    
//
//	            for (Edge e : edgeList){      //2 point connect
//
//	                if (!e.processed) {
//	                	
//	                	if(detect(path,i+1))// if detect a point that already in the path => duplicate 
//		                    continue;
//
//		                l++;            //Increase path length due to 1 new point is connected 
//		                path[l]=i+1;    //correspond to the array that start at 0, graph that start at point 1
//		                if(l==len-1){//Except initial point already count =>success connect all point
//		                	System.out.println("Hamilton path of graph: ");
//		                    display(path);
//		                    l--;
//		                    return true;
//		                }
//
//		                e.processed = true;  //remove the path that has been get and
//		                findHamiltonpath(path,l,len); //recursively start to find new path at new end point
//		                l--;                // reduce path length due to the failure to find new path         
//		                e.processed = false; //and tranform back to the inital form of adjacent matrix(graph)
//	                }
//	            }
//	        path[l+1]=0;    //disconnect two point correspond the failure to find the..   
//			return false;
//	}                     //possible hamilton path at new point(ignore newest point try another one)         

	public void display(int[]x){

	    for(int i:x){
	        System.out.print(i+" ");
	    }
	        System.out.println();   
	}

	
	private boolean detect(int[] x,int target){ //Detect duplicate point in Halmilton path 
	    boolean t=false;                        
	    for(int i:x){
	        if(i==target){
	            t = true;
	            break;
	        }
	    }
	    return t;
	}  

}

