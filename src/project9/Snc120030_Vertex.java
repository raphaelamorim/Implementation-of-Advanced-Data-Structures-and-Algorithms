package project9;
import java.util.ArrayList;
import java.util.List;


public class Snc120030_Vertex {
	
	private boolean seen;
	private Snc120030_Vertex mate;
	private int node;
	private List<Snc120030_Vertex> adjList = new ArrayList<Snc120030_Vertex>();
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
	public List<Snc120030_Vertex> getAdjList() {
		return adjList;
	}
	public void setAdjList(List<Snc120030_Vertex> adjList) {
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
	public Snc120030_Vertex getMate() {
		return mate;
	}
	public void setMate(Snc120030_Vertex mate) {
		this.mate = mate;
	}
	
	

}
