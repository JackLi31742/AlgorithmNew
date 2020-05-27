package sort2;

import java.util.Arrays;
/**
 * 912. 排序数组
 * @author LANG
 *
 */
public class QuickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]arr={5,1,1,2,0,0};
		sort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println("swipCount:"+swipCount);
		System.out.println("compareCount:"+compareCount);
	}

	static int swipCount=0;
	static int compareCount=0;
	
	public static int[] sort(int[]nums) {
		if (nums==null||nums.length==0||nums.length==1) {
			return nums;
		}
		return sort(nums,0,nums.length-1);
		
	}
	
	public static int[] sort(int[]arr,int start,int end){
		if (start>=end) {
			return arr;
		}
		int left=start;
		int right=end;
		int pivot=arr[left];
		//一轮排序
		while(left<right){
			//判断时，必须要有等号，是arr中如果有两个相等的值，必须有等号，才会进入while循环，走指针
			while(left<right&&arr[right]>=pivot){
				right--;
			}
			swip(arr, left, right);
			//出循环的时候，就找到了在pivot左侧的，大于等于pivot的值
			while(left<right&&arr[left]<=pivot){
				left++;
			}
			swip(arr, left, right);
		}
		
		//当退出while循环时，left=right=pivot所在的位置
//		System.out.println("left:"+left+":"+arr[left]+",right:"+right+":"+arr[right]+",pivot:"+pivot);
		//递归的时候，左侧递归，要传入start，右侧传入end
		sort(arr,start,left-1);
		sort(arr,left+1,end);
		return arr;
	}
	
	/**
	 * partition方式，本质和上边的是一样的
	 * @param arr
	 * @param start
	 * @param end
	 * @return
	 */
	public static int[] sort2(int[]arr,int start,int end){
		if (start>=end) {
			return arr;
		}
		
		int partiton=partiton(arr, start, end);
		//当退出while循环时，left=right=pivot所在的位置
//		System.out.println("left:"+left+":"+arr[left]+",right:"+right+":"+arr[right]+",pivot:"+pivot);
		//递归的时候，左侧递归，要传入start，右侧传入end
		sort(arr,start,partiton-1);
		sort(arr,partiton+1,end);
		return arr;
	}
	
	public static int partiton(int[]arr,int start,int end) {
		int left=start;
		int right=end;
		int pivot=arr[left];
		//一轮排序
		while(left<right){
			//判断时，必须要有等号，是arr中如果有两个相等的值，必须有等号，才会进入while循环，走指针
			while(left<right&&arr[right]>=pivot){
				right--;
			}
			swip(arr, left, right);
			//出循环的时候，就找到了在pivot左侧的，大于等于pivot的值
			while(left<right&&arr[left]<=pivot){
				left++;
			}
			swip(arr, left, right);
		}
		return left;
	}
	
	public static void swip(int[]arr,int i,int j) {
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
}
