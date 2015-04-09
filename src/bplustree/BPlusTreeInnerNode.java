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
		return Arrays.binarySearch(keys.toArray(), key);
	}

	public void insert(K key, BPlusTreeNode<K> leftChild,
			BPlusTreeNode<K> rightChild) {
		this.keys.add(key);
		Collections.sort(keys);
		int index = keys.indexOf(key);
		if(!this.children.isEmpty()){
			this.children.remove(index);
		}
		this.children.add(index, leftChild);
		this.children.add(index + 1, rightChild);
	}

	@Override
	public BPlusTreeNode<K> split() {
		int size = this.keys.size();
		int midIndex = size / 2;

		BPlusTreeInnerNode<K> newNode = new BPlusTreeInnerNode<K>(this.degree);
		newNode.keys.addAll(this.keys.subList(midIndex + 1, size));
		newNode.children.addAll(this.children.subList(midIndex + 1, size));
		for (BPlusTreeNode<K> node : newNode.children) {
			node.parent = newNode;
		}
		this.keys.retainAll(this.keys.subList(0, midIndex));
		this.children.retainAll(this.children.subList(0, midIndex));
		return newNode;
	}

	@Override
	public int delete(K key) {
		return 0;
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

}