package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Kth {
	
	public static void main(String[] args) {
//		PriorityQueue<Integer> heap =new PriorityQueue<Integer>(2);
//		
//		heap.add(1);
//		heap.add(2);
//		heap.add(3);
//		
//		System.out.println(heap);
		
		
		int[]arr= {1,3,5,7,2,4,6,8};
		
		int[]result=smallestK3(arr, 4);
		
		System.out.println(Arrays.toString(result));
	}
	
	/**
	 * 面试题 17.14. 最小K个数
	 * 找出数组中最小的k个数。以任意顺序返回这k个数均可。
	
		第一种办法：先排序
	 */
	public static int[] smallestK1(int[] arr, int k) {
		if (arr==null||arr.length==0) {
			return new int[k];
		}
		
		if (arr.length<=k) {
			return arr;
		}
		int[] result=new int[k];
		
		Arrays.sort(arr);
		
		for (int i = 0; i < result.length; i++) {
			result[i]=arr[i];
		}
		
		return result;
    }
	
	//第二种，用堆
	public static int[] smallestK2(int[] arr, int k) {
		
		if (arr==null||arr.length==0||k==0) {
			return new int[k];
		}
		
		if (arr.length<=k) {
			return arr;
		}
		Comparator<Integer> comparator=new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
		};
		
		//得用大顶堆
		PriorityQueue<Integer> heap =new PriorityQueue<Integer>(k,comparator);
		
		
		for (int i = 0; i < k; i++) {
			heap.add(arr[i]);
		}
		
		
		int[]result=new int[k];
		
		
		for (int i = k; i < arr.length; i++) {
			if (arr[i]<heap.peek()) {
				heap.poll();
				heap.add(arr[i]);
			}
		}
		
		int i=0;
		while(!heap.isEmpty()) {
			result[i]=heap.poll();
			i++;
		}
		
		return result;
	}
	
	
	/**
	 * 快排思想
	 * @param arr
	 * @param k
	 * @return
	 */
	public static int[] smallestK3(int[] arr, int k) {
		if (arr==null||arr.length==0||k==0) {
			return new int[k];
		}
		
		if (arr.length<=k) {
			return arr;
		}
		
		int left=0;
		int right=arr.length-1;
		
		return smallestK3(arr, k, left, right);
		
		
		
	}
	
	public static int[] smallestK3(int[] arr, int k,int left,int right) {
		if (left>=right) {
			return arr;
		}
		int partition=partition(arr, left, right);
		//当递归调用时，是不需要，也不可以用while循环
//		while((partition+1)!=k) {
			
//			partition是下标，所以需要加1
			if ((partition+1)<k) {
				smallestK3(arr, k, partition+1, right);
			}else if ((partition+1)>k) {
				
				smallestK3(arr, k,left,partition-1);
			}
//		}
		
		int[]result=new int[k];
		for (int i = 0; i < result.length; i++) {
			result[i]=arr[i];
		}
		
		
		return result;
	}
	
	public static int partition(int[] arr, int left,int right) {
		int index=(right-left)/2+left;
		swip(arr, left, index);
		int pivot=arr[left];
		while(left<right) {
			while(left<right&&arr[right]>=pivot) {
				right--;
			}
			swip(arr, left, right);
			while(left<right&&arr[left]<=pivot) {
				left++;
			}
			swip(arr, left, right);;
		}
		return left;
	}
	
	
	public static void swip(int[] arr, int left,int right) {
		int temp=arr[left];
		arr[left]=arr[right];
		arr[right]=temp;
	}
	
}
