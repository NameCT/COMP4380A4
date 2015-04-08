package bplustree;

public class BPlusTree<K extends Comparable<K>, V> {
	private BPlusTreeNode<K> root;

	public BPlusTree(int degree) {
		root = new BPlusTreeLeafNode<>(degree);
	}

	public void insert(K key, V value) {
		BPlusTreeLeafNode<K, V> leaf = findLeafNode(key);
		leaf.insert(key, value);

		if (leaf.isOverflow()) {
			BPlusTreeNode<K> node = leaf.dealOverflow();
			if (node != null) {
				this.root = node;
			}
		}
	}

	private BPlusTreeLeafNode<K, V> findLeafNode(K key) {
		BPlusTreeNode<K> node = root;
		while (BPlusTreeInnerNode.class == node.getClass()) {
			node = ((BPlusTreeInnerNode<K>) node).getChildren().get(
					node.search(key));
		}
		return (BPlusTreeLeafNode<K, V>) node;
	}

	public V search(K key) {

		return null;
	}

	public void delete(int index) {

	}

}