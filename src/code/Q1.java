package code;

import java.util.ArrayList;

class BPlusTreeNodeEntry {
	int index;
	
	BPlusTreeNode lNode = null;
	BPlusTreeNode rNode = null;
	
	public BPlusTreeNodeEntry(int degree) {
		lNode = new BPlusTreeNode(degree);
		rNode = new BPlusTreeNode(degree);
	}
}

class BPlusTreeNodeDataEntry {
	ArrayList<Integer> data;
	
	public BPlusTreeNodeDataEntry(int degree) {
		data = new ArrayList<Integer>(degree*2);
	}
	
	public void insert(int data) {
		
	}
	
	public void delete(int data) {
		
	}
	
	public boolean ifContains(int data) {
		
		return false;
	}
}

class BPlusTreeNode{
	ArrayList<BPlusTreeNodeEntry> entries;
	
	public BPlusTreeNode(int degree){
		entries = new ArrayList<BPlusTreeNodeEntry>(degree*2);
		for(BPlusTreeNodeEntry entry : entries) {
			entry = new BPlusTreeNodeEntry(degree);
		}
	}
	
}

class BPlusTree{
	BPlusTreeNode root;
	
	public BPlusTree(int degree) {
		root = new BPlusTreeNode(degree);
	}
	
	public void insert(int index) {
		
	}
	
	public void delete(int index) {
		
	}
	
	public BPlusTreeNode search(int index) {
		
		return null;
	}
}

public class Q1 {

	public static void main(String[] args) {

	}

}
