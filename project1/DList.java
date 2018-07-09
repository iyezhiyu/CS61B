/* DList.java */

public class DList {

  protected DListNode head;
  protected int size;

  public DList() {
	head=new DListNode(-1,-1,-1,-1);
	head.prev=head;
	head.next=head;
	size=0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int length() {
    return size;
  }

  public void insertFront(int r, int g, int b, int num) {
	DListNode theNewNode=new DListNode(r, g, b, num);
	theNewNode.prev=head;
	theNewNode.next=head.next;
	head.next.prev=theNewNode;
	head.next=theNewNode;
	size++;
  }

  public void insertBack(int r, int g, int b, int num) {
	DListNode theNewNode=new DListNode(r, g, b, num);
	theNewNode.prev=head.prev;
	theNewNode.next=head;
	head.prev.next=theNewNode;
	head.prev=theNewNode;
	size++;
  }

  public DListNode front() {
	if (size==0) {
		return null;
	} else {
		return head.next;
	}
  }

  public DListNode back() {
	if (size==0) {
		return null;
	} else {
		return head.prev;
	}
  }

  public DListNode next(DListNode node) {
	if (node == null) {
		return null;
	} else if (node==head.prev) {
		return null;
	} else {
		return node.next;
	}
  }

  public DListNode prev(DListNode node) {
	if (node == null) {
		return null;
	} else if (node==head.next) {
		return null;
	} else {
		return node.prev;
	}
  }

  public void insertAfter(int r, int g, int b, int num, DListNode node) {
	if (node != null) {
		DListNode theNewNode=new DListNode(r, g, b, num);
		theNewNode.prev=node;
		theNewNode.next=node.next;
		node.next.prev=theNewNode;
		node.next=theNewNode;
		size++;
	}
  }

  public void insertBefore(int r, int g, int b, int num, DListNode node) {
	if (node != null) {
		DListNode theNewNode=new DListNode(r, g, b, num);
		theNewNode.prev=node.prev;
		theNewNode.next=node;
		node.prev.next=theNewNode;
		node.prev=theNewNode;
		size++;
	}
  }

  public void remove(DListNode node) {
	if (node!=null) {
		node.next.prev=node.prev;
		node.prev.next=node.next;
		size--;
	}
  }

}
