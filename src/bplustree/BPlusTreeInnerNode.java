package bplustree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BPlusTreeInnerNode<K extends Comparable<K>> extends BPlusTreeNode<K>{
	protected ArrayList<BPlusTreeNode<K>> children;
	
	public BPlusTreeInnerNode(int degree){
		super(degree);
		children = new ArrayList<BPlusTreeNode<K>>();
	}

	@Override
	public int search(K key) {
		return Arrays.binarySearch(keys.toArray(), key);
	}

	public void insert(K key, BPlusTreeNode<K> leftChild, BPlusTreeNode<K> rightChild) {
		keys.add(key);
		Collections.sort(keys);
		int index = keys.indexOf(key);
		children.set(index, leftChild);
		children.set(index+1, rightChild);
		
	}
	
	@Override
	public BPlusTreeNode<K> split() {
		return null;
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
		if(this.isOverflow()) {
			return this.dealOverflow();
		} else {
			return this.parent == null ? this : null;
		}
	}

	
}