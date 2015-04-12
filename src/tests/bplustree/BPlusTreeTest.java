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

		for (int i = 0; i < 100; i++) {
			tree.insert(i, i);
		}

		assertTrue(oldRoot != tree.getRoot());
	}

	@Test
	public final void testSearch() {
		BPlusTree<Integer, Integer> tree = new BPlusTree<Integer, Integer>(3);

		for (int i = 0; i < 100; i++) {
			tree.insert(i, i);
		}

		for (int i = 0; i < 100; i++) {
			assertTrue(tree.search(i) == i);
		}
		
	}

	@Test
	public final void testDelete() {
		BPlusTree<Integer, Integer> tree = new BPlusTree<Integer, Integer>(1);

		for (int i = 0; i < 6; i++) {
			tree.insert(i, i);
		}

		tree.delete(2);
		tree.delete(3);
		tree.delete(4);
		assertTrue(tree.search(4) == null);
	}
	
	@Test
	public final void testPrint() {
		BPlusTree<Integer, Integer> tree = new BPlusTree<Integer, Integer>(1);
		for (int i = 0; i < 10; i++) {
			tree.insert(i, i);
		}
		System.out.println(tree);
		
	}
}
