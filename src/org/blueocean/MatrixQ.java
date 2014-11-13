package org.blueocean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MatrixQ {
	
	/*Given a matrix with 1's and 0's, a rectangle can be made with 1's. 
	What is the maximum area of the rectangle. 

	00010 
	11100 
	11110 
	11000 
	11010 In this test case the result needs to be 8. */
	public int max(int[][] matrix){
	   int max = 0;
	   
	   for(int r=0; r<matrix.length; r++){
	       for(int c=0; c<matrix[0].length; c++){
	           int[] result = growRec(r, r, c, c, matrix);
	           int area = (result[1]-result[0]+1)*(result[4]-result[3]+1);
	           max = max>area?max:area;
	       }
	   }
	   
	   return max;
	}

	public int[] growRec(int rowT, int rowB, int colL, int colR, int[][] matrix){
	    //grow up
	    boolean canGrowT = false;
	    if(rowT-1>=0){
	        canGrowT = true;
	        for(int c = colL; c<=colR; c++){
	            if(matrix[rowT-1][c]!=1){
	                canGrowT = false;
	                break;
	            }
	                
	        }
	     }   
	     
	     boolean canGrowL = false;  
	     if(colL-1>=0){  
	        canGrowL = true;
	        for(int r = rowT; r<=rowB; r++){
	            if(matrix[r][colL-1]!=1){
	                canGrowL = false;
	                break;
	            }        
	        }
	     }
	        
	     boolean canGrowR = false;  
	     if(colR+1<matrix[0].length){  
	        canGrowR = true;
	        for(int r = rowT; r<=rowB; r++){
	            if(matrix[r][colR+1]!=1){
	                canGrowR = false;
	                break;
	            }        
	        }
	      }
	      
	     boolean canGrowB = false;  
	     if(rowB+1<matrix.length){  
	        canGrowB = true;
	        for(int c = colL; c<=colR; c++){
	            if(matrix[rowB+1][c]!=1){
	                canGrowB = false;
	                break;
	            }        
	        }
	      }        
	     
	     if(!canGrowT && !canGrowB && !canGrowL && !canGrowR)
	         return new int[]{rowT, rowB, colL, colR};
	         
	     int increaseBT = 0;      
	     if(canGrowB)         
	        increaseBT += colR - colL + 1;
	     if(canGrowT)         
	        increaseBT += colR - colL + 1;   
	        
	     int increaseLR = 0;      
	     if(canGrowL)         
	        increaseLR += rowB - rowT + 1;
	     if(canGrowR)         
	        increaseLR += rowB - rowT + 1;   
	        
	     if(increaseBT > increaseLR){
	         //grow by row
	         if(canGrowT){
	             rowT--;
	         }
	         
	         if(canGrowB)
	             rowB++;
	         
	     }else{
	         //grow by col
	         if(canGrowL)
	             colL--;
	         if(canGrowR)
	             colR++;
	     }   
	    
	     return growRec(rowT, rowB, colL, colR, matrix);   
	}
	
	/*
	 * Q1. Given matrix of 1s and 0s where 0 is water and 1 is land. Find number of islands.
	 * http://www.geeksforgeeks.org/amazon-interview-experience-set-142-campus-sde-1/
	 */
	public static int findNumberOfIslands(int[][] matrix){
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		int num = 0;
		for(int row=0; row<matrix.length; row++){
			for(int col=0; col<matrix[0].length; col++){
				if(!visited[row][col] && matrix[row][col]==1){	
					int[] counter = new int[1];
					counter[0] = 0;
					visitRec(row, col, visited, matrix, counter);
					num++;
					System.out.println(counter[0]);
				}
			}
		}
		
		return num;
	}
	
	public static void visitRec(int row, int col, boolean[][] visited, int[][] matrix, int[] counter){
		if(row<0 || row>=matrix.length || col<0 || col>=matrix[0].length || visited[row][col] || matrix[row][col] ==0 )
			return;
		else{
			visited[row][col]=true;
			counter[0]++;
			visitRec(row-1, col, visited, matrix, counter);
			visitRec(row+1, col, visited, matrix, counter);
			visitRec(row, col-1, visited, matrix, counter);
			visitRec(row, col+1, visited, matrix, counter);
		}
	}
	
	/*
	 * http://www.geeksforgeeks.org/find-the-row-with-maximum-number-1s/
	 * row is sorted array with 0s or 1s
	 */
	public static int findFirst1(int[] row, int start, int end){
		if(start==end)
			return row[start]==1?start:-1;
		else if(start-end==1)
			return row[start]==1?start:end;
		else{
			int mid = (start+end)/2;
			if(row[mid]==0){
				return findFirst1(row, mid+1, end);
			}else{
				if(row[mid-1]==0)
					return mid;
				else
					return findFirst1(row, start, mid-1);
			}
		}
	}
	
	/*
	 * Q3. Given a matrix of 0s and 1s find the row that contains maximum number of 1s.
	 */
	//may have stack overflow issue, we can use stack
	int doFill(int x, int y, boolean[][] fill) {
		// Check to ensure that we are within the bounds of the grid, if not, return 0
		 if (x < 0 || x >= 600) return 0;
		// Similar check for y
		 if (y < 0 || y >= 400) return 0;
		// Check that we haven't already visited this position, as we don't want to count it twice
		 if (fill[x][y]) return 0;

		// Record that we have visited this node
		 fill[x][y] = true;

		 // Now we know that we have at least one empty square, then we will recursively attempt to
		 // visit every node adjacent to this node, and add those results together to return.
		 return 1 + doFill(x - 1, y, fill) + doFill(x + 1, y, fill) + doFill(x, y + 1, fill) + doFill(x, y - 1, fill);
		}
	
	public int findLargestIsland(int[][] mat, int r, int c, boolean[][] visited){
	    Stack<Node> s = new Stack<Node>();
	    s.push(new Node(r, c));
	    
	    int counter = 0;
	    while(!s.isEmpty()){
	        Node n = s.pop();
	        if(n.row<0 || n.row>=mat.length || n.col<0 || n.col>=mat.length)
	            continue;
	            
	        if(!visited[n.row][n.col]){       
	            visited[n.row][n.col] = true;        
	            counter++;   
	             s.push(new Node(n.row-1, n.col));	             
	             s.push(new Node(n.row+1, n.col));	             
	             s.push(new Node(n.row, n.col-1));	           
	             s.push(new Node(n.row, n.col+1));
	        }
	    }
	    return counter;
	}
	
	public static int findMax1Row(int[][] matrix){
		int row = 0;
		int col = matrix[0].length-1;
		int max = row;
		while(row<matrix.length && col >=0){
			if(matrix[row][col]==1){
				col--;
				max = row;
			}else
				row++;
		}
		
		return max;
	}
	
	/*
	 * http://www.careercup.com/question?id=5998719358992384
	 * Given a matrix consisting of 0's and 1's,
	 *  find the largest connected component consisting of 1's.
	 */
	public static class Node{
		int row;
		int col;
		public Node(int r, int c){
			this.row = r;
			this.col = c;
		}
		public String toString(){
			return row + " - " + col;
		}
	}
	public static List<Node> findMaxComponent(int[][] matrix){
		List<Node> result = new ArrayList<Node>();
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		for(int r=0; r<matrix.length; r++){
			for(int c=0; c<matrix[0].length; c++){		
				List<Node> current = new ArrayList<Node>();
				findMaxComponentRecursive(matrix, r-1, c, visited, current);
				if(current.size() > result.size())
					result = current;
			}
		}
		return result;	
	}
	
	public static void findMaxComponentRecursive(int[][] matrix, int r, int c, boolean[][] visited, List<Node> current){
		if(r>=0&&r<matrix.length&&c>=0&&c<matrix[0].length&& !visited[r][c] && matrix[r][c]==1){
			visited[r][c] = true;
			current.add(new Node(r, c));
			//continue
			findMaxComponentRecursive(matrix, r-1, c, visited, current);
			findMaxComponentRecursive(matrix, r+1, c, visited, current);
			findMaxComponentRecursive(matrix, r, c-1, visited, current);
			findMaxComponentRecursive(matrix, r, c+1, visited, current);
		}	
	}

	/*
	 * http://www.careercup.com/question?id=5890898499993600
	 * Given a matrix of letters and a word, check if the word is present in the matrix. 
	 * E,g., suppose matrix is: 
		a b c d e f 
		z n a b c f 
		f g f a b c 
		and given word is fnz, it is present. However, gng is not since you would be repeating g twice. 
		You can move in all the 8 directions around an element.
	 */
	public static boolean isPresent(char[][] matrix, char[] word){
	
		for(int row=0; row<matrix.length; row++){
			for(int col=0; col<matrix[0].length; col++){
				boolean[][] visited = new boolean[matrix.length][matrix[0].length];
				if(isPresentRecursive(matrix, word, 0, row, col, visited)){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean  isPresentRecursive(char[][] matrix, char[] word, int pos, int row, int col, boolean[][] visited){
		//pass the end of word
		if(pos==word.length)
			return true;
		
		if(row<0 || row>=matrix.length || col<0 || col>=matrix[0].length)
			return false;
		
		if(visited[row][col])
			return false;
		
		if(matrix[row][col]==word[pos]){	
			visited[row][col] = true;
			return 
			 isPresentRecursive(matrix, word, pos+1, row-1, col, visited) || 			
			 isPresentRecursive(matrix, word, pos+1, row+1, col, visited) || 			
			 isPresentRecursive(matrix, word, pos+1, row, col-1, visited) ||			
			 isPresentRecursive(matrix, word, pos+1, row, col+1, visited) ||			
			 isPresentRecursive(matrix, word, pos+1, row-1, col-1, visited) ||			
			 isPresentRecursive(matrix, word, pos+1, row-1, col+1, visited) ||
			 isPresentRecursive(matrix, word, pos+1, row+1, col-1, visited) ||
			 isPresentRecursive(matrix, word, pos+1, row+1, col+1, visited) ;
		}
		return false;

	}

}
