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
	public void testSearch() {
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

}
