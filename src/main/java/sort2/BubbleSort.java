package sort2;

import java.util.Arrays;
/**
 * 性能极差，会超时
 * @author LANG
 *
 */
public class BubbleSort {

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
	public static void sort(int[]arr){
		
		for (int i = 0; i < arr.length; i++) {
			
			//比较的是相邻的两个元素，一趟排序后，最后一个元素就不参与下一趟的比较
			for (int j = 0; j < arr.length-1-i; j++) {
				compareCount++;
				if (arr[j]>arr[j+1]) {
					swipCount++;
					swip(arr, j, j+1);
				}
			}
		}
	}
	
	/**
	 * 2和3本质是一样的
	 * @param arr
	 */
	public static void sort2(int[]arr){
		
		for (int i = 0; i < arr.length; i++) {
			boolean flag = false; // 标识变量，表示是否进行过交换
			
			//比较的是相邻的两个元素，一趟排序后，最后一个元素就不参与下一趟的比较
			for (int j = 0; j < arr.length-1-i; j++) {
				compareCount++;
				if (arr[j]>arr[j+1]) {
					flag = true;
					swipCount++;
					swip(arr, j, j+1);
				}
			}
			if (!flag) {
				break;
			}
		}
	}
	
	public static void sort3(int[]arr){
		
		boolean flag = false; // 标识变量，表示是否进行过交换
		for (int i = 0; i < arr.length; i++) {
			
			//比较的是相邻的两个元素，一趟排序后，最后一个元素就不参与下一趟的比较
			for (int j = 0; j < arr.length-1-i; j++) {
				compareCount++;
				if (arr[j]>arr[j+1]) {
					flag = true;
					swipCount++;
					swip(arr, j, j+1);
				}
			}
			if (!flag) {
				break;
			}else {
				flag = false; // 重置flag!!!, 进行下次判断
			}
		}
	}
	
	/**
	 * 这不是冒泡排序
	 * @param arr
	 */
	public static void sort4(int[]arr){
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++) {
				compareCount++;
				if (arr[i]>arr[j]) {
					swipCount++;
					swip(arr, i, j);
				}
			}
		}
	}
	public static void swip(int[]arr,int i,int j) {
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}

}
