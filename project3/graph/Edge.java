package graph;

import list.*;

public class Edge {
	
	protected Vertex u;
	protected Vertex v;
	protected int weight;
	protected DListNode uNode;
	protected DListNode vNode;
	
	Edge(Vertex u, Vertex v, int weight) {
		this.u = u;
		this.v = v;
		this.weight = weight;
		this.uNode = null;
		this.vNode = null;
	}
	
	public int weight() {
		return weight;
	}
}