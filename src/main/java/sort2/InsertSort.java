package sort2;

import java.util.Arrays;

public class InsertSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]arr={3, 9, -1, 1, 20,2,15,4,7};
		sort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println("swipCount:"+swipCount);
		System.out.println("compareCount:"+compareCount);
	}

	static int swipCount=0;
	static int compareCount=0;
	public static void sort(int[] arr){
		
		for (int i = 1; i < arr.length; i++) {
			int insertValue=arr[i];
			int index=i-1;
			//涉及到后移，必须要从后往前
//			不能用for,因为index一直在减少
//			for (; index >=0; index--) {
//				if (arr[index]>insertValue) {
//					arr[index+1]=arr[index];
//				}
//			}
			//只有满足了arr[index]>insertValue，index才往前，不然就已经找到了插入位置
			while(index>=0&&arr[index]>insertValue){
				swipCount++;
				arr[index+1]=arr[index];
				index--;
			}
			if ((index+1)!=i) {
				arr[index+1]=insertValue;
			}
		}
	}
}
