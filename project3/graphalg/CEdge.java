package graphalg;

public class CEdge implements Comparable {
	
	protected Object u;
	protected Object v;
	protected int weight;
	
	public CEdge(Object u, Object v, int weight) {
		this.u=u;
		this.v=v;
		this.weight=weight;
	}
	
	public int compareTo(Object o) {
		if (weight < ((CEdge) o).weight) {
			return -1;
		} else if (weight > ((CEdge) o).weight) {
			return 1;
		} else {
			return 0;
		}
	}
}