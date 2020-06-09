package tree;

import java.util.Arrays;

public class HeapSort {

	public static void main(String[] args) {
		int[]arr={2,5,4,1,10,3};
//		int[]arr={4,6,8,5,9};
		sort(arr);
		System.out.println(Arrays.toString(arr));
//		System.out.println((-1/2));
	}
	
	public static int[] sort(int[]nums) {
		if (nums==null||nums.length==0||nums.length==1) {
			return nums;
		}
		//先构造一个堆，升序，构造大顶堆
		buildHeap(nums);
		
		for (int i = nums.length-1;i>=0;i--) {
			//先交换
			swip(nums, i, 0);
			//再调整，因为只有第二层不满足大顶堆的条件
			//length是i，因为每次都少一个，第一次在buildHeap已经用了，所以循环中就是nums.length-1开始
			adjustHeap(nums, 0, i);
		}
		
		return nums;
	}
	
	public static void buildHeap(int[] arr) {
		// 由于堆是完全二叉树，所以当找到最后的非叶子节点后，
//		依次要调整的就是从这个非叶子节点到根节点，而不是i=(i-1)/2;
		//arr.length-1-1也行
		for (int i = (arr.length - 1) / 2; i >= 0; i--) {

			adjustHeap(arr, i, arr.length);
		}
	}
	
	/**
	 * 
	 * @param arr 要排序的数组
	 * @param i 要调整的index
	 * @param length 数组长度，会变化
	 */
	public static void adjustHeap(int[]arr,int i,int length) {
		//找到最左边的节点
//		while((2*i+1)<length) {
//			i=2*i+1;
//		}
//		while(i>=0) {
//			
//			i=(i-1)/2;
			int k=2*i+1;
			while(k<length) {
				
				if ((k+1)<length&&arr[k]<arr[k+1]) {
					k=k+1;
				}
				if (arr[i]<arr[k]) {
					swip(arr, i, k);
				}
				i=k;
				k=2*k+1;
			}
//			i=(i-1)/2;
//		}
		
	}
	
	public static void swip(int[]arr,int i,int j) {
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
}
