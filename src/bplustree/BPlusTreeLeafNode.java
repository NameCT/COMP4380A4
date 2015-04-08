package bplustree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BPlusTreeLeafNode<K extends Comparable<K>, V> extends BPlusTreeNode<K> {
	protected ArrayList<V> values; // the "records"
	
	public BPlusTreeLeafNode(int degree) {
		super(degree);
		keys = new ArrayList<K>();
		values = new ArrayList<V>();
	}
	
	public int sizeOfValues() {
		return values.size();
	}

	@Override
	public int search(K key) {
		return Arrays.binarySearch(values.toArray(), key); //nice and simple??
	}

	public void insert(K key, V value) {
		keys.add(key);
		Collections.sort(keys);
		values.add(keys.indexOf(key), value);
		
	}
	
	@Override
	public BPlusTreeLeafNode<K, V> split() {
		int size = keys.size();
		int midIndex = keys.size()/2;
		
		BPlusTreeLeafNode<K, V> newNode = new BPlusTreeLeafNode<>(degree);
		newNode.keys.addAll(keys.subList(0, midIndex + 1));
		newNode.values.addAll(values.subList(0, midIndex + 1));
		keys.retainAll(keys.subList(midIndex + 1, size));
		values.retainAll(values.subList(midIndex + 1, size));
		
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