package sort2;

import java.util.Arrays;

public class SelectSort {
	
	public static void main(String[] args) {
		int[]arr={3, 9, -1, 1, 20,2,15,4,7};
		sort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println("swipCount:"+swipCount);
		System.out.println("compareCount:"+compareCount);
	}

	static int swipCount=0;
	static int compareCount=0;
	public static void sort(int[]arr){
		for (int i = 0; i < arr.length-1; i++) {
			int min=arr[i];
			int minIndex=i;//标记了下标后，可以在第二层for循环的外边进行交换，减少交换次数
			for (int j = i+1; j < arr.length; j++) {
				compareCount++;
				if (min>arr[j]) {
					min=arr[j];
					minIndex=j;
				}
			}
			
			if (minIndex!=i) {
				swipCount++;
				swip(arr, minIndex, i);
			}
			
		}
	}
	
	public static void swip(int[]arr,int i,int j) {
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
}
