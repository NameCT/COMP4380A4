package bplustree;

import java.util.ArrayList;

public abstract class BPlusTreeNode<K extends Comparable<K>>{
	protected ArrayList<K> keys;
	protected int degree;
	protected int capacity;
	protected BPlusTreeNode<K> parent;
	protected BPlusTreeNode<K> lSibling;
	protected BPlusTreeNode<K> rSibling;
	
	public BPlusTreeNode(int degree) {
		this.degree = degree;
		capacity = degree*2 + 1;
		parent = null;
		lSibling = null;
		rSibling = null;
	}
	
	// methods that all nodes should support: search, insert and delete
	
//	public int getKeycount() {
//		return keyCount;
//	}

	public BPlusTreeNode<K> getParent() {
		return parent;
	}

	public void setParent(BPlusTreeNode<K> parent) {
		this.parent = parent;
	}

	public BPlusTreeNode<K> getlSibling() {
		return lSibling;
	}

	public void setlSibling(BPlusTreeNode<K> lSibling) {
		this.lSibling = lSibling;
	}

	public BPlusTreeNode<K> getrSibling() {
		return rSibling;
	}

	public void setrSibling(BPlusTreeNode<K> rSibling) {
		this.rSibling = rSibling;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public boolean isOverflow() {
		return keys.size() == capacity;
	}

	public int sizeOfKeys() {
		return keys.size();
	}
	
	public abstract int search(K key);
	
	//public abstract int insert(K key);
	
	public abstract int delete(K key);
	
}
