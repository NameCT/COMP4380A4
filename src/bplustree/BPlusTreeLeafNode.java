package bplustree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BPlusTreeLeafNode<K extends Comparable<K>, V> extends BPlusTreeNode<K> {
	protected ArrayList<V> values; // the "records"
	
	public BPlusTreeLeafNode(int degree) {
		super(degree);
		this.values = new ArrayList<V>();
	}
	
	public int sizeOfValues() {
		return this.values.size();
	}

	@Override
	public int search(K key) {
		return Arrays.binarySearch(values.toArray(), key); //nice and simple??
	}

	public void insert(K key, V value) {
		this.keys.add(key);
		Collections.sort(keys);
		this.values.add(keys.indexOf(key), value);
		
	}
	
	@Override
	public BPlusTreeLeafNode<K, V> split() {
		int size = this.keys.size();
		int midIndex = size / 2;
		
		BPlusTreeLeafNode<K, V> newNode = new BPlusTreeLeafNode<>(this.degree);
		newNode.keys.addAll(this.keys.subList(midIndex, size));
		newNode.values.addAll(this.values.subList(midIndex, size));
		this.keys.retainAll(this.keys.subList(0, midIndex));
		this.values.retainAll(this.values.subList(0, midIndex));
		
		return newNode;
	}
	
	public boolean delete(K key) {
		int index = this.search(key);
		if (index < 0)
			return false;
		
		this.keys.remove(index);
		this.values.remove(index);
		return true;
	}

	@Override
	protected BPlusTreeNode<K> pushUp(K midKey, BPlusTreeNode<K> leftChild,
			BPlusTreeNode<K> rigthChild) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void processChildrenTransfer(BPlusTreeNode<K> borrower,
			BPlusTreeNode<K> lender, int borrowIndex) {
	}

	@Override
	protected BPlusTreeNode<K> processChildrenFusion(
			BPlusTreeNode<K> leftChild, BPlusTreeNode<K> rightChild) {
		return null;
	}

	@Override
	protected void fusionWithSibling(K sinkKey, BPlusTreeNode<K> rightSibling) {
		BPlusTreeLeafNode<K, V> siblingLeaf = (BPlusTreeLeafNode<K, V>)rightSibling;
		
		this.keys.addAll(siblingLeaf.keys);
		this.values.addAll(siblingLeaf.values);
		
		this.setrSibling(siblingLeaf.rSibling);
		if (siblingLeaf.rSibling != null)
			siblingLeaf.rSibling.setlSibling(this);
	}

	@Override
	protected K transferFromSibling(K sinkKey, BPlusTreeNode<K> sibling,
			int borrowIndex) {
		BPlusTreeLeafNode<K, V> siblingNode = (BPlusTreeLeafNode<K, V>)sibling;
		
		this.insert(siblingNode.keys.get(borrowIndex), siblingNode.values.get(borrowIndex));
		siblingNode.keys.remove(borrowIndex);
		siblingNode.values.remove(borrowIndex);
		
		return borrowIndex == 0 ? sibling.keys.get(0) : this.keys.get(0);
	}
	
	@Override
	public String toString() {
		return this.values.toString();
	}
}