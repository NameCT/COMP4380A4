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
		assertEquals(4, node.values.length);
	}
	
	@Test
	public void testSearch() {
		for (int i = 0; i < node.values.length; i++) {
			node.values[i] = i;
		}
		for (int i = 0; i < node.values.length; i++) {
			assertEquals(i, node.search(i));
		}
		assertFalse(node.search(4) > 0);
		assertFalse(node.search(0) < 0);
	}

}
