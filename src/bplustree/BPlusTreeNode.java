package bplustree;

import java.util.ArrayList;

public abstract class BPlusTreeNode<K extends Comparable<K>> {
	protected ArrayList<K> keys;

	protected int degree;
	protected int capacity;
	protected BPlusTreeNode<K> parent;
	protected BPlusTreeNode<K> lSibling;
	protected BPlusTreeNode<K> rSibling;

	public BPlusTreeNode(int degree) {
		this.keys = new ArrayList<K>();
		this.degree = degree;
		capacity = degree * 2 + 1;
		parent = null;
		lSibling = null;
		rSibling = null;
	}

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

	public ArrayList<K> getKeys() {
		return keys;
	}
	
	public abstract int search(K key);

	public abstract int delete(K key);

	protected abstract BPlusTreeNode<K> split();

	public BPlusTreeNode<K> dealOverflow() {
		K upKey = this.keys.get(keys.size() / 2);
		BPlusTreeNode<K> newNode = this.split();
		
		if (this.parent == null) {
			this.parent = new BPlusTreeInnerNode<K>(degree);
		}
		newNode.setParent(this.parent);

		newNode.lSibling = this;
		newNode.rSibling = this.rSibling;
		if (this.rSibling != null) {
			this.rSibling.lSibling = newNode;
		}
		this.rSibling = newNode;

		return this.getParent().pushUp(upKey, this, newNode);

	}

	protected abstract BPlusTreeNode<K> pushUp(K midKey,
			BPlusTreeNode<K> leftChild, BPlusTreeNode<K> rigthChild);

	@Override
	public String toString() {
		return this.keys.toString();
	}
	
	
}
