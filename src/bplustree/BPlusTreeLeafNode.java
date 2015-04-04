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
;
	}

	@Override
	public int delete(K key) {
		return 0;
	}
}