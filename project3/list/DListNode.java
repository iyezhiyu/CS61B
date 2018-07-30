/* DListNode.java */

package list;

/**
 *  A DListNode is a mutable node in a DList (doubly-linked list).
 **/

public class DListNode {

  /**
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   **/

  protected DListNode prev;
  protected DListNode next;
  protected Object item;

  /**
   *  DListNode() constructor.
   *  @param i the item to store in the node.
   *  @param p the node previous to this node.
   *  @param n the node following this node.
   */
  DListNode(Object i, DListNode p, DListNode n) {
    item = i;
    prev = p;
    next = n;
  }

  public Object item() {
	  return item;
  }

}
