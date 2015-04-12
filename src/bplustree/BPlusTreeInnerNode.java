package bplustree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BPlusTreeInnerNode<K extends Comparable<K>> extends
		BPlusTreeNode<K> {
	protected ArrayList<BPlusTreeNode<K>> children;

	public BPlusTreeInnerNode(int degree) {
		super(degree);
		this.children = new ArrayList<BPlusTreeNode<K>>();
	}

	@Override
	public int search(K key) {
		int index = Arrays.binarySearch(keys.toArray(), key);
		return index >= 0 ? index + 1 : -index - 1;
	}

	public void insert(K key, BPlusTreeNode<K> leftChild,
			BPlusTreeNode<K> rightChild) {
		this.keys.add(key);
		Collections.sort(keys);
		int index = keys.indexOf(key);
		if (!this.children.isEmpty()) {
			this.children.remove(index);
		}
		this.children.add(index, leftChild);
		this.children.add(index + 1, rightChild);
	}

	@Override
	public BPlusTreeNode<K> split() {
		int keySize = this.keys.size();
		int childrenSize = this.children.size();
		int midIndex = keySize / 2;

		BPlusTreeInnerNode<K> newNode = new BPlusTreeInnerNode<K>(this.degree);
		newNode.keys.addAll(this.keys.subList(midIndex + 1, keySize));
		newNode.children.addAll(this.children.subList(midIndex + 1,
				childrenSize));
		for (BPlusTreeNode<K> node : newNode.children) {
			node.parent = newNode;
		}
		//newNode.children.get(0).lSibling = null;
		
		this.keys.retainAll(this.keys.subList(0, midIndex));
		this.children.retainAll(this.children.subList(0, midIndex + 1));
		//this.children.get(0).rSibling = null;
		
		return newNode;
	}

	private void delete(int index) {
		this.keys.remove(index);
		this.children.remove(index + 1);
	}

	public ArrayList<BPlusTreeNode<K>> getChildren() {
		return children;
	}

	@Override
	protected BPlusTreeNode<K> pushUp(K midKey, BPlusTreeNode<K> leftChild,
			BPlusTreeNode<K> rigthChild) {
		this.insert(midKey, leftChild, rigthChild);
		if (this.isOverflow()) {
			return this.dealOverflow();
		} else {
			return this.parent == null ? this : null;
		}
	}

	@Override
	protected void processChildrenTransfer(BPlusTreeNode<K> borrower,
			BPlusTreeNode<K> lender, int borrowIndex) {
		int borrowerChildIndex = this.children.indexOf(borrower);

		if (borrowIndex == 0) {
			// borrow a key from right sibling
			K upKey = borrower.transferFromSibling(
					this.keys.get(borrowerChildIndex), lender, borrowIndex);
			this.keys.set(borrowerChildIndex, upKey);
		} else {
			// borrow a key from left sibling
			K upKey = borrower.transferFromSibling(
					this.keys.get(borrowerChildIndex - 1), lender, borrowIndex);
			this.keys.set(borrowerChildIndex - 1, upKey);
		}
	}

	@Override
	protected BPlusTreeNode<K> processChildrenFusion(
			BPlusTreeNode<K> leftChild, BPlusTreeNode<K> rightChild) {
		int index = this.children.indexOf(leftChild);

		K sinkKey = this.keys.get(index);

		// merge two children and the sink key into the left child node
		leftChild.fusionWithSibling(sinkKey, rightChild);

		// remove the sink key, keep the left child and abandon the right child
		this.delete(index);

		// check whether need to propagate borrow or fusion to parent
		if (this.isUnderflow()) {
			if (this.getParent() == null) {
				// current node is root, only remove keys or delete the whole
				// root node
				if (this.keys.size() == 0) {
					leftChild.setParent(null);
					return leftChild;
				} else {
					return null;
				}
			}

			return this.dealUnderflow();
		}

		return null;
	}

	@Override
	protected void fusionWithSibling(K sinkKey, BPlusTreeNode<K> rightSibling) {
		BPlusTreeInnerNode<K> rightSiblingNode = (BPlusTreeInnerNode<K>) rightSibling;

		this.keys.add(sinkKey);

		this.keys.addAll(rightSiblingNode.keys);
		this.children.addAll(rightSiblingNode.children);

		this.rSibling = (rightSiblingNode.rSibling);
		if (rightSiblingNode.rSibling != null)
			rightSiblingNode.rSibling.lSibling = this;
	}

	@Override
	protected K transferFromSibling(K sinkKey, BPlusTreeNode<K> sibling,
			int borrowIndex) {
		BPlusTreeInnerNode<K> siblingNode = (BPlusTreeInnerNode<K>)sibling;
		
		K upKey = null;
		if (borrowIndex == 0) {
			// borrow the first key from right sibling, append it to tail
			int index = this.keys.size();
			this.keys.set(index, sinkKey);
			this.children.set(index + 1, siblingNode.children.get(borrowIndex));			
			
			upKey = siblingNode.keys.get(0);
			siblingNode.delete(borrowIndex);
		}
		else {
			// borrow the last key from left sibling, insert it to head
			this.insert(sinkKey, siblingNode.children.get(borrowIndex + 1), this.children.get(0));
			upKey = siblingNode.keys.get(borrowIndex);
			siblingNode.delete(borrowIndex);
		}
		
		return upKey;
	}
	
	@Override
	public String toString() {
		return this.keys.toString();
	}

}