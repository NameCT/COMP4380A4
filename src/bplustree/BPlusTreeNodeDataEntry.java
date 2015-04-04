// probably don't need this anymore since the leaf node contains all the records?

package bplustree;

import java.util.ArrayList;

public class BPlusTreeNodeDataEntry {
	public ArrayList<Integer> data;
	
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