package tests.bplustree;

import static org.junit.Assert.*;

import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

import bplustree.BPlusTreeInnerNode;

public class BPlusTreeInnerNodeTest {

	private BPlusTreeInnerNode<Integer> node = new BPlusTreeInnerNode<>(2);
	
	@Test
	public final void testBPlusTreeInnerNode() {
		assertEquals(5, node.getCapacity());
	}
	
	@Test
	public final void testInsert() {
		// invoke by the leaf nodes?? the tree?
	}
	
	@Test
	public final void testSearch() {
		
	}

	@Test
	public final void testDelete() {

	}



}
