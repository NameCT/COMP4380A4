package code;

public abstract class BPlusTreeNode<K extends Comparable<K>>{
	protected Object[] keys;
	//keycount?
	protected BPlusTreeNode<K> parent;
	protected BPlusTreeNode<K> lSibling;
	protected BPlusTreeNode<K> rSibling;
	
	public BPlusTreeNode() {
		parent = null;
		lSibling = null;
		rSibling = null;
	}
	
	// methods that all nodes should support: search, insert and delete
	
	public abstract int search(K key);
	
	public abstract int insert(K key);
	
	public abstract int delete(K key);
	
}
