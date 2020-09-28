package sort;

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
		return sort2(nums,0,nums.length-1);
		
	}
	
	public static int[] sort(int[]arr,int start,int end){
		if (start>=end) {
			return arr;
		}
		int left=start;
		int right=end;
		//pivot的选取，和while循环中swip的位置很重要
		int pivot=arr[left];
		//一轮排序
		while(left<right){
			//判断时，必须要有等号，是arr中如果有两个相等的值，必须有等号，才会进入while循环，走指针
			//最后才有可能退出最外边的while循环
			//同时也避免了如果有很多等于pivot的数，这些数由于没有等号，被划在了一边，导致partition不均匀
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
	 * 选取任意的元素，可以将这个元素与left交换，
	 * 依然使用left进行while循环中的交换，这样，用一次交换的代价，省却了思考用这个
	 * 元素下标进行交换的算法思考
	 * @param arr
	 * @param start
	 * @param end
	 * @return
	 */
	public static int[] sort4(int[]arr,int start,int end){
		if (start>=end) {
			return arr;
		}
		int left=start;
		int right=end;
		//pivot的选取，和while循环中swip的位置很重要
		int index=(right-left)/2+left;
		swip(arr, left, index);
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
		sort4(arr,start,left-1);
		sort4(arr,left+1,end);
		return arr;
	}
	
	public static int[] sort3(int[]arr,int start,int end){
		if (start>=end) {
			return arr;
		}
		int left=start;
		int right=end;
		//pivot的选取，和while循环中swip的位置很重要
		int pivot=arr[right];
		//一轮排序
		while(left<right){
			//出循环的时候，就找到了在pivot左侧的，大于等于pivot的值
			while(left<right&&arr[left]<=pivot){
				left++;
			}
			swip(arr, left, right);
			//判断时，必须要有等号，是arr中如果有两个相等的值，必须有等号，才会进入while循环，走指针
			while(left<right&&arr[right]>=pivot){
				right--;
			}
			swip(arr, left, right);
		}
		
		//当退出while循环时，left=right=pivot所在的位置
//		System.out.println("left:"+left+":"+arr[left]+",right:"+right+":"+arr[right]+",pivot:"+pivot);
		//递归的时候，左侧递归，要传入start，右侧传入end
		sort3(arr,start,left-1);
		sort3(arr,left+1,end);
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
		sort2(arr,start,partiton-1);
		sort2(arr,partiton+1,end);
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
