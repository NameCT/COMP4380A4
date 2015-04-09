package tests.bplustree;

import static org.junit.Assert.*;

import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

import bplustree.BPlusTreeInnerNode;
import bplustree.BPlusTreeLeafNode;

public class BPlusTreeInnerNodeTest {

	private BPlusTreeInnerNode<Integer> node = new BPlusTreeInnerNode<Integer>(2);
	
	@Test
	public final void testBPlusTreeInnerNode() {
		assertEquals(5, node.getCapacity());
	}
	
	@Test
	public final void testInsertAndSearch() {
		BPlusTreeLeafNode<Integer, Integer> leaf = new BPlusTreeLeafNode<Integer, Integer>(2);
		for (int i = 0; i < 5; i++) {
			leaf.insert(i, i);
		}
		BPlusTreeInnerNode<Integer> node = (BPlusTreeInnerNode<Integer>) leaf.dealOverflow();
		assertEquals(1, node.getKeys().size());

		BPlusTreeLeafNode<Integer, Integer> leftLeaf = (BPlusTreeLeafNode<Integer, Integer>) node.getChildren().get(0);
		BPlusTreeLeafNode<Integer, Integer> rightLeaf = (BPlusTreeLeafNode<Integer, Integer>) node.getChildren().get(1);
		assertTrue(leftLeaf.getlSibling() == null);
		assertTrue(leftLeaf.getrSibling() == rightLeaf);
		assertTrue(rightLeaf.getlSibling() == leftLeaf);
		assertTrue(rightLeaf.getrSibling() == null);
		
		assertTrue(leftLeaf.getParent() == node);
		assertTrue(rightLeaf.getParent() == node);
		// self's parent null or unchanged?
		assertTrue(node.getParent() == null);
		
	}
	
	@Test
	public final void testSplit() {
		
	}

	@Test
	public final void testDelete() {

	}



}
