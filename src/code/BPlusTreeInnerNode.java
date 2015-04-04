package code;

import java.util.ArrayList;

class BPlusTreeInnerNode{
	ArrayList<BPlusTreeLeafNode> entries;
	
	public BPlusTreeInnerNode(int degree){
		entries = new ArrayList<BPlusTreeLeafNode>(degree*2);
		for(BPlusTreeLeafNode entry : entries) {
			entry = new BPlusTreeLeafNode(degree);
		}
	}
	
}