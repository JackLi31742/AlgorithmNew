package tree;

public class ArrayBinaryTree {

	public static void main(String[] args) {
		int[]arr= {1,2,3,4,5,6,7};
		preorderTraversal(arr);
	}
	
	public static void preorderTraversal(int[]arr) {

		if (arr==null||arr.length==0) {
			return;
		}
		preorderTraversal(0,arr);
		
    }
	
	public static void preorderTraversal(int index,int[]arr) {
		System.out.println(index+1);
		if ((2*index+1)<arr.length) {
			preorderTraversal(2*index+1, arr);
		}
		if ((2*index+2)<arr.length) {
			preorderTraversal(2*index+2, arr);
		}
	}
	
}
