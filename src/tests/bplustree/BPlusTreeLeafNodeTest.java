package tests.bplustree;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bplustree.BPlusTreeLeafNode;

public class BPlusTreeLeafNodeTest {
	BPlusTreeLeafNode<Integer, Integer> node = new BPlusTreeLeafNode<>(2);
	
	@Test
	public void testValuesSize() {
		assertEquals(5, node.getCapacity());
	}
	
	@Test
	public void testInsertAndSearch() {
		for (int i = 0; i < 5; i++) {
			node.insert(i, i);
		}
		assertTrue(node.sizeOfKeys() == node.sizeOfValues());
		assertTrue(node.isOverflow());
		for (int i = 0; i < 5; i++) {
			assertEquals(i, node.search(i));
		}
		assertFalse(node.search(5) > 0);
		assertFalse(node.search(0) < 0);
	}

	@Test
	public void testSplit() {
		for (int i = 0; i < 5; i++) {
			node.insert(i, i);
		}
		BPlusTreeLeafNode<Integer, Integer> newNode = node.split();
		assertEquals(5, newNode.getCapacity());
		assertTrue(newNode.sizeOfKeys() == newNode.sizeOfValues());
		assertFalse(newNode.isOverflow());
		assertTrue(node.sizeOfKeys() == node.sizeOfValues());
		assertFalse(node.isOverflow());
		
		for (int i = 0; i < 3; i++) {
			assertEquals(i, newNode.search(i));
			assertFalse(node.search(i) > 0);
		}
		
		for (int i = 3; i < 5; i++) {
			assertFalse(newNode.search(i) > 0);
			assertEquals(i-3, node.search(i));
		}
	}
}
