/* HashTableChained.java */

package dict;

import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  protected DList[] hashTable;
  protected int size;



  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
	while (!isPrime(sizeEstimate)) {
		sizeEstimate++;
	}
	hashTable=new DList[sizeEstimate];
	size=0;
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
	hashTable=new DList[101];
	size=0;
  }
  
  private boolean isPrime(int n) {
	  if (n % 2 ==0 && n!=2 ) {
		  return false;
	  }
	  for (int i=3; i*i<=n; i+=2) {
		  if (n%i == 0) {
			  return false;
		  }
	  }
	  return true;
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    int a=3;
    int b=5;
    int p=131;
    int compressed_value = ((a*code + b) % p) % hashTable.length;
    
    if (compressed_value < 0) {
    	compressed_value = compressed_value +hashTable.length;
    }
    return compressed_value;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    if (size == 0) {
    	return true;
    }
    return false;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    Entry entry=new Entry();
    entry.key=key;
    entry.value=value;
    
    int code=key.hashCode();
    int compressed_value=compFunction(code);
    
    if (hashTable[compressed_value] == null) {
    	hashTable[compressed_value] = new DList();
    }
    hashTable[compressed_value].insertFront(entry);
    size++;
    return entry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    int compressed_value=compFunction(key.hashCode());
    if (hashTable[compressed_value] == null) {
    	return null;
    }
    DListNode node=hashTable[compressed_value].front();
    while (node != null) {
    	if (((Entry) node.item()).key().equals(key)) {
    		return ((Entry) node.item());
    	} else {
    		node = hashTable[compressed_value].next(node);
    	}
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
	    int compressed_value=compFunction(key.hashCode());
	    if (hashTable[compressed_value] == null) {
	    	return null;
	    }
	    DListNode node=hashTable[compressed_value].front();
	    while (node != null) {
	    	if (((Entry) node.item()).key().equals(key)) {
	    		Entry foundEntry=(Entry) node.item();
	    		hashTable[compressed_value].remove(node);
	    		size--;
	    		return foundEntry;
	    	} else {
	    		hashTable[compressed_value].next(node);
	    	}
	    }
	    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
	hashTable=new DList[hashTable.length];
	size=0;
  }

  public void printHistogram() {
	  int[] count= new int[hashTable.length];
	  for (int i=0; i<hashTable.length; i++) {
		  if (hashTable[i] == null) {
			  count[0]++;
			  System.out.println("Bucket "+i+": "+0);
		  } else {
		      count[hashTable[i].length()]++;
		      System.out.println("Bucket "+i+": "+hashTable[i].length());
		  }
	  }
	  System.out.println();
	  for (int j=0; j<hashTable.length; j++) {
		  System.out.println("Number of buckets with " + j + " entries: " +
		          count[j]);
	  }
  }
  
  public int expectedCollisions() {
	    int n = size;           // number of keys
	    int N = hashTable.length;   // number of buckets
	    return (int) (n - N + N * Math.pow(1 - 1.0 / N, n));
	  }
  
  public int numCollisions() {
	    int count = 0;
	    for (int i = 0; i < hashTable.length; i++) {
	    	if (hashTable[i] != null) {
	          if (hashTable[i].length() > 1) {
	            count+= hashTable[i].length() - 1;
	          }
	    	}
	    }
	    return count;
	  }
  
}
