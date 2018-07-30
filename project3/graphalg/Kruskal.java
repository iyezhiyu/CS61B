/* Kruskal.java */

package graphalg;

import graph.*;
import set.*;
import list.*;
import dict.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   *
   * @param g The weighted, undirected graph whose MST we want to compute.
   * @return A newly constructed WUGraph representing the MST of g.
   */
  public static WUGraph minSpanTree(WUGraph g) {
	  LinkedQueue que = new LinkedQueue();
	  WUGraph newGraph = new WUGraph();
	  Object[] vertices = g.getVertices();
	  for (int i = 0; i<vertices.length; i++) {
		  newGraph.addVertex(vertices[i]);
		  Neighbors neighbor = g.getNeighbors(vertices[i]);
		  for (int j = 0; j < neighbor.neighborList.length; j++) {
			  CEdge edge = new CEdge(vertices[i], neighbor.neighborList[j], neighbor.weightList[j]);
			  que.enqueue(edge);
		  }
	  }
	  
	  ListSorts.quickSort(que);
	  
	  DisjointSets set = new DisjointSets(g.vertexCount());
	  HashTableChained vertexHash = new HashTableChained();
	  for (int i = 0; i<vertices.length; i++) {
		  vertexHash.insert(vertices[i], i);
	  }
	  
	  while (! que.isEmpty()) {
		  CEdge edge = null;
		  try {
			  edge = (CEdge) que.dequeue();
		  } catch (QueueEmptyException e) {
			  System.err.println(e);
		  }
		  int temp1 = set.find((Integer)(vertexHash.find(edge.u)).value());
		  int temp2 = set.find((Integer)(vertexHash.find(edge.v)).value());
		  if (temp1 != temp2) {
			  set.union(temp1, temp2);
			  newGraph.addEdge(edge.u, edge.v, edge.weight);
		  }
	  }
	  return newGraph;
	  
  }

}
