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
		int midIndex = this.keys.size()/2;
		
		BPlusTreeLeafNode<K, V> newNode = new BPlusTreeLeafNode<>(this.degree);
		newNode.keys.addAll(this.keys.subList(0, midIndex + 1));
		newNode.values.addAll(this.values.subList(0, midIndex + 1));
		this.keys.retainAll(this.keys.subList(midIndex + 1, size));
		this.values.retainAll(this.values.subList(midIndex + 1, size));
		
		return newNode;
	}
	
	
	@Override
	public int delete(K key) {
		return 0;
	}

	@Override
	protected BPlusTreeNode<K> pushUp(K midKey, BPlusTreeNode<K> leftChild,
			BPlusTreeNode<K> rigthChild) {
		throw new UnsupportedOperationException();
	}
}