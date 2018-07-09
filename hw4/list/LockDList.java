package list;

public class LockDList extends DList {
	
    protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
		return new LockDListNode(item, prev, next);
    }
	
	public void lockNode(DListNode node) {
		((LockDListNode)node).lock=true;
	}
	
    public void remove(DListNode node) {
    	if (node != null) {
    	    if (((LockDListNode)node).lock==false) {
    			node.next.prev=node.prev;
    			node.prev.next=node.next;
    			size--;
    	    }
    	}
	}
    
    public LockDListNode front() {
    	return (LockDListNode) (super.front());
    }
	
    public LockDListNode back() {
    	return (LockDListNode) (super.back());
    }
    
    public LockDListNode next(DListNode node) {
    	return (LockDListNode) (super.next(node));
    }
    
    public LockDListNode prev(DListNode node) {
    	return (LockDListNode) (super.prev(node));
    }
    
    
}