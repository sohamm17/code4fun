package org.blueocean;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class SuffixTree {
	STNode root;
	
	class STNode {
		int data ;
		STNode[] edges;
		
		STNode(int d){
			data = d;
		}
		
		STNode(){
			
		}
	}
	//assume small cases: a-z
	STNode addString(String s, int data){
		if(root==null)
			root = new STNode(-1);
		
		STNode current = root;
		int index = 0;
		while(index<s.length()){
			if(current.edges==null)
				current.edges = new STNode[26];
			
			int pos = s.charAt(index) - 'a';
			
			if(current.edges[pos]==null)
				current.edges[pos] = new STNode();
			
			current = current.edges[pos];
			index++;
		}
		
		current.data = data; //leaf store the position
		return root;
	}
	
	public void addAllSubString(String s){
		for(int i=0;i<s.length();i++){
			this.addString(s.substring(i), i);
		}
	}

	public String findLongestRepeatSub(String s){
		this.addAllSubString(s);
		
		Deque<STNode> sk = new ArrayDeque<STNode>();
		sk.add(root);
		
		int maxL = 0;
		int currentL = 0;
		STNode target = root;
		
		while(!sk.isEmpty()){
			Deque<STNode> temp = new ArrayDeque<STNode>();

			while(!sk.isEmpty()){
				STNode n = sk.remove();
				currentL++;
				int counter=0;
				if(n.edges!=null){
					for(STNode nd : n.edges){
						if(nd!=null){
							sk.add(nd);
							counter++;
						}
					}
				}

				if(counter>1 || counter==1 && n.data>0){
					if(currentL>maxL){
						maxL = currentL;
						target = n;
					}
				}	
			}
			sk = temp;
		}
		
		int cn = 0;
		//go down to the leaf if not on leaf yet.
		while(target.data<=0){
			if(target.edges!=null){
				for(STNode nd : target.edges){
					if(nd!=null){
						target = nd;
						cn++;
					}
				}
			}
		}
		if(cn!=0)
			return s.substring(target.data, s.length()-cn+1);
		else
			return s.substring(target.data, s.length());
	}
}
