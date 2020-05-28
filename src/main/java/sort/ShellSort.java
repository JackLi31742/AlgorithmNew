package sort;

import java.util.Arrays;

public class ShellSort {
	
	public static void main(String[] args) {
		int[]arr={3, 9, -1, 1, 20,2,15,4,7,0};
		sort2(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println("swipCount:"+swipCount);
		System.out.println("compareCount:"+compareCount);
	}
	static int swipCount=0;
	static int compareCount=0;
	
	/**
	 * 对每一个gap内的组进行排序时，交换，冒泡，速度极慢
	 * @param arr
	 */
	public static void sort(int[]arr) {
		//在gap=1/2的时候停止
		for (int gap = arr.length/2; gap >0; gap=gap/2) {
			for (int i = gap; i < arr.length; i++) {
				for (int j = i-gap; j >=0; j=j-gap) {
					compareCount++;
					if (arr[j]>arr[j+gap]) {
						swipCount++;
						swip(arr, j, j+gap);
					}
				}
			}
		}
	}
	
	public static void swip(int[]arr,int i,int j) {
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
	
	/**
	 * 对每一个gap内的组进行排序时，移位，插入，速度没有快排快
	 * @param arr
	 */
	public static void sort2(int[]arr) {
		for (int gap = arr.length/2; gap >0; gap=gap/2) {
			for (int i = gap; i < arr.length; i++) {
				
				int inserValue=arr[i];
				int index=i-gap;
				while(index>=0&&arr[index]>inserValue){
					swipCount++;
					compareCount++;
					arr[index+gap]=arr[index];
					index=index-gap;
				}
				
				if ((index+gap)!=i) {
					arr[index+gap]=inserValue;
				}
			}
		}
	}
}
