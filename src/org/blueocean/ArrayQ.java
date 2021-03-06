package org.blueocean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class ArrayQ {
	
	/*
	Suppose you have a long flowerbed in which some of the plots are planted and 
	some are not. However, flowers cannot be planted in adjacent plots - 
	they would compete for water and both would die. Given a flowerbed 
	(represented as an array containing booleans), return if a given number of 
	new flowers can be planted in it without violating the no-adjacent-flowers rule.
	*/

	public boolean canPlaceFlowers(List<Boolean> flowerbed, int numberToPlace) {
		this.hashCode();
	    if(flowerbed == null || flowerbed.isEmpty()){
	        throw new IllegalArgumentException("bed is empty");
	    }
	    
	    if(numberToPlace==0)
	        return true;

	    if(flowerbed.size()==1){
	        return !flowerbed.get(0) && numberToPlace<=1;
	    }
	    
	    int counter = 0;
	    
	    for(int i=0; i< flowerbed.size(); i++){
	    	if(!flowerbed.get(i)){
	    		if((i==0 && !flowerbed.get(i+1)) || (i==flowerbed.size()-1 && !flowerbed.get(i-1)) || (!flowerbed.get(i+1) && !flowerbed.get(i-1)) ){
	    			flowerbed.set(i, true);
	    			counter++;
	    			if(counter==numberToPlace)
	    				return true;
	    		}
	    	}
	    }    
	    
	    return false;
	}

	
	/*
	 * http://www.geeksforgeeks.org/print-matrix-diagonally/
	 */
	public static void printMatrixDiagonally(int[][] numbers){
		for(int sum = 0; sum <= numbers.length + numbers[0].length-2; sum++){
			System.out.println();
			for(int row = Math.min(numbers.length-1, sum); row>=0; row--){
				int col = sum - row;
				if(col<=numbers[0].length-1)
					System.out.print(numbers[row][col] + "\t");
			}			
		}		
	}
	
	/*
	 * http://www.geeksforgeeks.org/given-n-x-n-square-matrix-find-sum-sub-squares-size-k-x-k/
	 */
	public static void findSumSubSquare(int[][] numbers, int k){
		for(int i=0; i<=numbers.length-k; i++){
			for(int j=0; j<=numbers[0].length-k; j++){
				
				int sum = 0;
				for(int n = 0; n<k; n++){
					for(int m=0; m<k; m++)
						sum = sum + numbers[i+n][j+m];
				}				
				System.out.println(sum);
			}
		}	
	}
	
	public static void findSumSubSquare2(int[][] numbers, int k){
		int[][] sum2 = new int[numbers.length-k+1][numbers.length-k+1];
		
		for(int n = 0; n<k; n++){
			for(int m=0; m<k; m++)
				sum2[0][0] = sum2[0][0] + numbers[n][m];
		}
		
		for(int row = 1; row<=numbers.length-k; row++){
			sum2[row][0] = sum2[row-1][0];
			for(int m=0; m<k; m++)
				sum2[row][0] = sum2[row][0] + numbers[row+k-1][m] - numbers[row-1][m];			
		}
		
		for(int i=0; i<=numbers.length-k; i++){
			for(int j=1; j<=numbers[0].length-k; j++){			
				sum2[i][j] = sum2[i][j-1];				
				for(int n = 0; n<k; n++){
						sum2[i][j] = sum2[i][j] + numbers[i+n][j+k-1] - numbers[i+n][j-1];
				}				
			}
		}	
		
		for(int i=0; i<=numbers.length-k; i++){
			for(int j=0; j<=numbers[0].length-k; j++){	
				System.out.println(sum2[i][j]);
			}
		}
	}
	
	
	/*
	 * http://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/
	 * 
	 * print matrix in spiral form
	 */
	public static void print2DArraySpiral(int[][] numbers){
		int starti=0, endi=numbers.length-1, startj=0, endj=numbers[0].length-1;
		while(starti<=endi && startj<=endj){
			
			for(int j=startj; j<=endj; j++)
				System.out.println(numbers[starti][j]);
			
			starti++;
			
			for(int i=starti; i<=endi; i++)
				System.out.println(numbers[i][endj]);
			
			endj--;
			
			for(int j=endj; j>=startj; j--)
				System.out.println(numbers[endi][j]);
			
			endi--;
			
			for(int i=endi; i>=starti; i--)
				System.out.println(numbers[i][startj]);
			
			startj++;
		}
	}
	
	/*
	 * find number in rotated sorted array
	 * this is correct
	 * 
	 */
	public static int findInRotatedSorted(int[] num, int k, int start, int end){
		if(start<0 || end >=num.length)
			return -1;
		if(start>end)
			return -1;
		
		int mid = (start + end)/2;
		if(num[mid] == k)
			return mid;
		
		if(num[mid] > num[start]){
			if(num[mid] > k && num[start] <= k){
				return binarySearch(num, k, start, mid);
			}else if(num[mid] < k){
				return findInRotatedSorted(num, k, mid, end);
			}else{
				return findInRotatedSorted(num, k, mid, end);
			}				
		}
		
		if(num[mid] < num[end]){
			if(num[mid] < k && num[end]>=k){
				return binarySearch(num, k, mid, end);
			}else
				return findInRotatedSorted(num, k, start, mid);
		}	
		
		return -1;
	}
	
	public static int binarySearch(int[] num, int k, int start, int end){
		if(start<0 || end >=num.length)
			return -1;
		if(start>end)
			return -1;
		
		int mid = (start + end)/2;
		if(num[mid] == k)
			return mid;
		else if(num[mid] < k){
			return binarySearch(num, k, mid+1, end);
		}else{
			return binarySearch(num, k, start, mid-1);
		}
		
	}
	
	/* 
	 * find max cost path in a matrix, 
	 * you can only move down or right
	 */
	public static int findMaxCostPath(int[][] nums){
		int[][] path = new int[nums.length][nums[0].length];
		System.out.println(nums.length + " - " + nums[0].length);
		for(int i=0; i<nums.length; i++){			
			for(int j=0; j<nums[0].length; j++){
				int max = Math.max((i-1>=0)?path[i-1][j]:Integer.MIN_VALUE , (j-1>=0)?path[i][j-1]:Integer.MIN_VALUE );
				path[i][j] = (max==Integer.MIN_VALUE?0:max) + nums[i][j];	
			}			
		}	
		StringBuilder sb = new StringBuilder();
		int i = nums.length-1; int j = nums[0].length-1;
		while(i>0 || j >0){
				if(i==0){
					sb.append("R");
					j--;
				}else if(j==0){
					sb.append("D");
					i--;
				}
				else if(path[i-1][j] > path[i][j-1]){
					sb.append("D");
					i--;					
				}
				else{
					sb.append("R");
					j--;
				}
		}
	
		System.out.println(sb.reverse().toString());
		
		return path[nums.length-1][nums[0].length-1];
	}
	
	/*
	 * can only move right or down
	 */
	public static Set<String> printPathInAGrid(int x, int y){
		Map<String, Integer> result = new HashMap<String, Integer>();
		result.put("", 0);
		
		//RRDDD => 2*3
		for(int i=1; i<=x+y; i++){
			Map<String, Integer> temp = new HashMap<String, Integer>();
			for(String s : result.keySet()){
				//System.out.println(s);
				Integer numberOfRs = result.get(s);
				if(numberOfRs<x)
					temp.put(s + "R", numberOfRs + 1);
				if(s.length()-numberOfRs<y)
					temp.put(s + "D", numberOfRs);
			}
			
			result = temp;
		}					
		return result.keySet();
	}
	
	/*
	 * can only move right or down
	 */
	public static int numberOfPathInAGrid(int x, int y){
		int[][] result = new int[x+1][y+1];
		
		for(int i=1; i<=x; i++){
			for(int j = 1; j<=y; j++){
				if(i==1 || j == 1)
					result[i][j] = 1;
				else
					result[i][j] = result[i-1][j] + result[i][j-1];				
			}					
		}					
		return result[x][y];
	}
	
	/*
	 * 3sum = 0
	 * n^2
	 */
	public static int[] find3Sum(int[] nums){
		Arrays.sort(nums); 
		for(int k=0; k<nums.length-2; k++){
			int i=k+1;
			int j=nums.length-1;
			int target = 0 - nums[k];
			while(i<j){
				int sum = nums[i] + nums[j];
				if(sum == target)
					return new int[]{k, i, j};
				else if(sum>target)
					j--;
				else
					i++;
			}
		}
		
		return new int[]{0, 0, 0};
		
	}
	
	/**
	 * 2sum solution 1
	 * O(nlogn)
	 */
	public static int[] find2Sum(int[] nums, int target){
		Arrays.sort(nums); 
		int i=0;
		int j=nums.length-1;
		
		while(i<j){
			int sum = nums[i] + nums[j];
			if(sum == target)
				return new int[]{i+1, j+1};
			else if(sum>target)
				j--;
			else
				i++;
		}
			
		return null;
	}
	
	/**
	 * 2sum solution 2
	 * O(n)
	 */
	public static int[] find2Sum2(int[] nums, int target){
		//store the number and the number of times it appears in the list
		HashMap<Integer, Integer> found = new HashMap<Integer, Integer>();
		found.put(nums[0], 1);		
		for(int i=1; i< nums.length; i++){
			int key = target - nums[i];
			 if(found.containsKey(key) && found.get(key)>0){
				 found.put(key, found.get(key)-1);
				 return new int[]{target, nums[i]};
			 }
			 else{
				 if(found.containsKey(nums[i]))
					 found.put(nums[i], found.get(nums[i]+1));
				 else
					 found.put(nums[i], 1);
			 }
		}
			
		return null;
	}
	
	
	/**
	 * find nearest larger element
	 * 
	 * @param input
	 * @return
	 */
	public static Map<Integer, Integer> findNLE(List<Integer> input){		
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		
		if(input==null || input.isEmpty())
			return result;
		
		Stack<Integer> s = new Stack<Integer>();
		s.push(input.get(0));
		for(int sCurr=1; sCurr<input.size(); sCurr++){
			while(!s.isEmpty()){
				if(s.peek()<input.get(sCurr))
					result.put(s.pop(), input.get(sCurr)); 
				else
					break;				
			}			
			s.push(input.get(sCurr));					
		}
		
		while(!s.isEmpty()){
			result.put(s.pop(), Integer.MIN_VALUE);
		}		
		return result;		
	}
}
