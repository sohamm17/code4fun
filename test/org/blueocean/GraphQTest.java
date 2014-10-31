package org.blueocean;

import org.blueocean.GraphQ.LinkedListNode;

import junit.framework.TestCase;

public class GraphQTest extends TestCase {
	
	public void testFindMinPath(){
		GraphQ.createTestGraph();
		GraphQ.minPath(GraphQ.findNode(5), GraphQ.findNode(1));
	}
	public void testTopSort(){
		GraphQ.createTestGraph();
		GraphQ.topologicalSort(GraphQ.graph);
	}
	
	public void testisEulerian(){
		String[] words = {"ds","ssdd"};
		LinkedListNode[] n = new GraphQ().createGraph(words);
		
		System.out.println(GraphQ.isEulerian(n));
		System.out.println(GraphQ.isEulerian(new GraphQ().createGraph(new String[]{"ds","asdd"})));
		System.out.println(GraphQ.isEulerian(new GraphQ().createGraph(new String[]{"ds","bsddb"})));
		
		System.out.println(GraphQ.isEulerian(new GraphQ().createGraph(new String[]{"for", "geek", "rig", "kaf"})));
		System.out.println(GraphQ.isEulerian(new GraphQ().createGraph(new String[]{"aaa", "bbb", "baa", "aab"})));
		
	}
	
	public void testisEulerian2(){
		
		
		System.out.println(GraphQ.isEulerian(new String[]{"ds","ssdd"}));
		System.out.println(GraphQ.isEulerian(new String[]{"ds","asdd"}));
		System.out.println(GraphQ.isEulerian(new String[]{"ds","bsddb"}));
		
		System.out.println(GraphQ.isEulerian(new String[]{"for", "geek", "rig", "kaf"}));
		System.out.println(GraphQ.isEulerian(new String[]{"aaa", "bbb", "baa", "aab"}));
		
	}

}
