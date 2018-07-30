package graph;

import list.*;

public class Vertex {
	
	protected DList adjList;
	protected DListNode vertexNode;
	protected int degree;
	protected Object item;
	
	Vertex(Object item) {
		this.item = item;
		this.adjList = new DList();
		this.vertexNode = null;
		this.degree = 0;
	}
	
}