package bplustree;

import java.util.Arrays;

public class BPlusTreeLeafNode<K extends Comparable<K>, V> extends BPlusTreeNode<K> {
	public Object[] values; // the "records"
	
	public BPlusTreeLeafNode(int degree) {
		keys = new Object[degree*2];
		values = new Object[degree*2];
	}

	@Override
	public int search(K key) {
		return Arrays.binarySearch(values, key); //nice and simple??
	}

	@Override
	public int insert(K key) {
		return 0;
	}

	@Override
	public int delete(K key) {
		return 0;
	}
}