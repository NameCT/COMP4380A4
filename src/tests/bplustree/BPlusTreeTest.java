package tests.bplustree;

import static org.junit.Assert.*;

import org.junit.Test;

import bplustree.BPlusTree;
import bplustree.BPlusTreeNode;

public class BPlusTreeTest {

	@Test
	public final void testInsert() {
		BPlusTree<Integer, Integer> tree = new BPlusTree<Integer, Integer>(1);
		BPlusTreeNode<Integer> oldRoot = tree.getRoot();
		
		for (int i = 0; i < 3; i++) {
			tree.insert(i, i);
		}
		
		assertTrue(oldRoot != tree.getRoot());
	}

	@Test
	public final void testSearch() {
		BPlusTree<Integer, Integer> tree = new BPlusTree<Integer, Integer>(1);
		
		for (int i = 0; i < 3; i++) {
			tree.insert(i, i);
		}
		
		for (int i = 0; i < 3; i++) {
			assertTrue(tree.search(i) == i);
		}
	}

	@Test
	public final void testDelete() {
		//fail("Not yet implemented");
	}

}
