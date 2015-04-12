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

	public ArrayList<K> geKs() {
		return keys;
	}

	public abstract int search(K key);

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

	public boolean isUnderflow() {
		return this.keys.size() < (this.capacity / 2);
	}

	public boolean canLendAKey() {
		return this.keys.size() > (this.capacity / 2);
	}

	public BPlusTreeNode<K> dealUnderflow() {
		if (this.getParent() == null)
			return null;

		// try to borrow a key from sibling
		BPlusTreeNode<K> leftSibling = this.lSibling;
		if (leftSibling != null && leftSibling.parent == this.parent
				&& leftSibling.canLendAKey()) {
			this.getParent().processChildrenTransfer(this, leftSibling,
					leftSibling.keys.size() - 1);
			return null;
		}

		BPlusTreeNode<K> rightSibling = this.rSibling;
		if (rightSibling != null && rightSibling.parent == this.parent
				&& rightSibling.canLendAKey()) {
			this.getParent().processChildrenTransfer(this, rightSibling, 0);
			return null;
		}

		// Can not borrow a key from any sibling, then do fusion with sibling
		if (leftSibling != null && leftSibling.parent == this.parent) {
			return this.getParent().processChildrenFusion(leftSibling, this);
		} else if (rightSibling.parent == this.parent) {
			return this.getParent().processChildrenFusion(this, rightSibling);
		} 
		
		return null;
	}

	protected abstract BPlusTreeNode<K> pushUp(K midKey,
			BPlusTreeNode<K> leftChild, BPlusTreeNode<K> rigthChild);

	protected abstract void processChildrenTransfer(BPlusTreeNode<K> borrower,
			BPlusTreeNode<K> lender, int borrowIndex);

	protected abstract BPlusTreeNode<K> processChildrenFusion(
			BPlusTreeNode<K> leftChild, BPlusTreeNode<K> rightChild);

	protected abstract void fusionWithSibling(K sinkKey,
			BPlusTreeNode<K> rightSibling);

	protected abstract K transferFromSibling(K sinkKey,
			BPlusTreeNode<K> sibling, int borrowIndex);

	@Override
	public String toString() {
		return this.toString();
	}

}
