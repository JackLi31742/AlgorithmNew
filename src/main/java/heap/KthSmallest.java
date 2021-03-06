package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallest {
	
	
	public static void main(String[] args) {
		
		KthSmallest kthSmallest=new KthSmallest();
		int[][]matrix= {{1,5,7},
				{3,7,8},
				{3,7,8}
				};
		
		kthSmallest.kthSmallest3(matrix, 4);
	}
	/**
	 * 
	 * 
	 * 401. 排序矩阵中的从小到大第k个数
		在一个排序矩阵中找从小到大的第 k 个整数。

排序矩阵的定义为：每一行递增，每一列也递增。

	双指针的快排思想，没有利用到矩阵本身递增的性质
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int kthSmallest(int[][] matrix, int k) {
        // write your code here
		
		if (matrix==null||matrix.length<=0||matrix[0].length<=0||k<=0) {
			return -1;
		}
		
		int row = matrix.length;
		
		int col = matrix[0].length;
		
		//由于是用下标，所以要k-1
		int partition=partition(matrix, k-1, row, col, 0, row*col-1);
		
		//要注意递归的返回值到底是什么
		return matrix[partition/col][partition%col];
    }
	
	public int partition(int[][] matrix, int k,int row,int col,int start,int end) {
		
		
		int left=start;
		int right=end;
		if (left>=right) {
			return left;
		}
		int mid=left+(right-left)/2;
		
		int pivot=matrix[mid/col][mid%col];
		
		while(left<=right) {
			
			while(left<=right&&matrix[left/col][left%col]<pivot) {
				left++;
			}
			
			while(left<=right&&matrix[right/col][right%col]>pivot) {
				right--;
			}
			
			if (left<=right) {
				int temp=matrix[left/col][left%col];
				matrix[left/col][left%col]=matrix[right/col][right%col];
				matrix[right/col][right%col]=temp;
				left++;
				right--;
			}
		}
		
		if (left>k) {
			return partition(matrix, k, row, col, start, left-1);
		}else if (left<k) {
			return partition(matrix, k, row, col, left, end);
		}else {
			return left;
		}
	}
	
	
	

	/**
	 * 
	 * 378
	 * 401. 排序矩阵中的从小到大第k个数
		在一个排序矩阵中找从小到大的第 k 个整数。

排序矩阵的定义为：每一行递增，每一列也递增。

	要想利用矩阵本身的性质，这是在答案上的二分
	
	为什么是答案二分呢，意思就是说矩阵的左上角和右下角是范围
	所有的数都在这里，取中点之后，需要找到比这个点小的有多少个，大的也就知道了
	而求有多少个比这个点小，就用searchMatrix的方法
	
	
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int kthSmallest2(int[][] matrix, int k) {
		
		if (matrix==null||matrix.length<=0||matrix[0].length<=0||k<=0) {
			return -1;
		}
		
		int row = matrix.length;
		
		int col = matrix[0].length;
		
		int left=matrix[0][0];
		int right=matrix[row-1][col-1];
		
		while(left <= right) {
			
			int mid=left+(right-left)/2;
			
			Result result=getNumSmallerThanMid(matrix, mid, row, col);
			
			//不能把判断直接放到这，否则会死循环
//			if (result.exist) {
				
				if (result.num<k) {
					left=mid+1;
				}else if (result.num>k) {
					right=mid-1;
				}else {
					if (result.exist) {
						return mid;
					}else {
						//由于是找小的，所以要往左走
						right=mid-1;
					}
				}
		}
		
		
		return left;
	}
	
	/**
	 * target就是每次的mid，
	 * 返回值要把target在不在矩阵中，以及对应比它小的数量带回去
	 * @param matrix
	 * @return
	 */
	public Result getNumSmallerThanMid(int[][] matrix,int target,int row,int col) {
		
		int x=0;int y=col-1;
		
		int num=0;
		boolean exist=false;
		while(x<row&&y>=0) {
			if (matrix[x][y]==target) {
				exist=true;
			}
			if (matrix[x][y]<=target) {
				
				x++;
				//要把所有小于它的都加上，和找等于target的不一样
				num+=y+1;
			}else {
				y--;
			}
		}
		
		return new Result(exist, num);
	}
	
	/**
	 * 可以将本题中的矩阵看做是n个有序列表，从里边找到第k小的
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int kthSmallest3(int[][] matrix, int k) {
		
		if (matrix==null||matrix.length<=0||matrix[0].length<=0||k<=0) {
			return -1;
		}
		
		int row = matrix.length;
		
		int col = matrix[0].length;
		
		PriorityQueue<Integer> heap =new PriorityQueue<Integer>(k,Comparator.reverseOrder());
		
		//把第一列加入到heap中，相当于把链表的头都加入了进去
		
		for (int j = 0; j < row; j++) {
			
			if (heap.size()<k) {
				heap.add(matrix[j][0]);
			}else {
				Integer peek = heap.peek();
				if (peek>matrix[j][0]) {
					heap.poll();
					heap.add(matrix[j][0]);
				}
			}
			
			
		}
		
		for (int i = 0; i < row; i++) {
			
			for (int j = 0; j < col; j++) {
				
				if (heap.size()<k) {
					heap.add(matrix[i][j]);
				}else {
					Integer peek = heap.peek();
					if (peek>matrix[i][j]) {
						heap.poll();
						heap.add(matrix[i][j]);
					}else {
						break;
					}
				}
			}
		}
		
		return heap.peek();
	}
	
}

class Result{
	boolean exist;
	int num;
	public Result(boolean exist, int num) {
		super();
		this.exist = exist;
		this.num = num;
	}
	@Override
	public String toString() {
		return "Result [exist=" + exist + ", num=" + num + "]";
	}
}
