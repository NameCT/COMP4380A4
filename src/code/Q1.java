package code;

class BPlusTreeEntry {
	int index;
	
	BPlusTreeNode lNode = null;
	BPlusTreeNode rNode = null;
	
	public BPlusTreeEntry(int degree) {
		lNode = new BPlusTreeNode(degree);
		rNode = new BPlusTreeNode(degree);
	}
}

class BPlusTreeNode{
	BPlusTreeEntry[] entries;
	
	public BPlusTreeNode(int degree){
		entries = new BPlusTreeEntry[degree*2];
		for(BPlusTreeEntry entry : entries) {
			entry = new BPlusTreeEntry(degree);
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
