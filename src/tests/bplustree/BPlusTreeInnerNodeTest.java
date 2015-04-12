package tests.bplustree;

import static org.junit.Assert.*;

import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

import bplustree.BPlusTreeInnerNode;
import bplustree.BPlusTreeLeafNode;

public class BPlusTreeInnerNodeTest {
	
	@Test
	public final void testBPlusTreeInnerNode() {
		BPlusTreeInnerNode<Integer> node = new BPlusTreeInnerNode<Integer>(2);
		assertEquals(5, node.getCapacity());
	}
	
	@Test
	public final void testInsertAndSearch() {
		BPlusTreeLeafNode<Integer, Integer> leaf = new BPlusTreeLeafNode<Integer, Integer>(2);
		for (int i = 0; i < 5; i++) {
			leaf.insert(i, i);
		}
		BPlusTreeInnerNode<Integer> node = (BPlusTreeInnerNode<Integer>) leaf.dealOverflow();
		assertEquals(1, node.sizeOfKeys());

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
		BPlusTreeLeafNode<Integer, Integer> leaf = new BPlusTreeLeafNode<Integer, Integer>(1);
		leaf.insert(0, 0);
		leaf.insert(10, 10);
		leaf.insert(20, 20);
		BPlusTreeInnerNode<Integer> node1 = (BPlusTreeInnerNode<Integer>) leaf.dealOverflow();
		leaf.insert(1, 1);
		leaf.insert(2, 2);
		assertEquals(node1, (BPlusTreeInnerNode<Integer>) leaf.dealOverflow());
		leaf.insert(3, 3);
		leaf.insert(4, 4);
		
		BPlusTreeInnerNode<Integer> node3 = (BPlusTreeInnerNode<Integer>) leaf.dealOverflow();
		BPlusTreeInnerNode<Integer> node2 = (BPlusTreeInnerNode<Integer>) node1.getrSibling();
		
		assertTrue(node1.search(1) >= 0);
		//assertTrue(node1.search(3) < 0);
		//assertTrue(node1.search(10) < 0);
		assertTrue(node2.search(10) >= 0);
		//assertTrue(node2.search(3) < 0);
	
		assertTrue(node3.search(3) >= 0);
		
		assertEquals(node1.getParent(), node3);
		assertEquals(node2.getParent(), node3);
		assertTrue(node3.getChildren().contains(node1));
		assertTrue(node3.getChildren().contains(node2));
		assertTrue(node3.getParent() == null);
		
		assertTrue(node1.getlSibling() == null);
		assertTrue(node1.getrSibling() == node2);
		assertTrue(node2.getlSibling() == node1);
		assertTrue(node2.getrSibling() == null);
		
	}

	@Test
	public final void testDelete() {

	}



}
