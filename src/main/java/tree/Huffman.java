package tree;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Huffman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[]arr= {13,7,8,3,29,6,1};
		System.out.println(huffman(arr));;
	}
	
	public static TreeNode huffman(int[] arr){
		
		LinkedList<TreeNode> list=new LinkedList<>();
		for (int i = 0; i < arr.length; i++) {
			TreeNode node=new TreeNode(arr[i]);
			list.add(node);
		}
		
		Comparator<TreeNode> comparator=new Comparator<TreeNode>() {

			@Override
			public int compare(TreeNode o1, TreeNode o2) {
				// TODO Auto-generated method stub
				return o1.val-o2.val;
			}
			
		};
		
		while(list.size()>1) {
			
			Collections.sort(list,comparator);
			
			TreeNode leftNode=list.get(0);
			TreeNode rightNode=list.get(1);
			
			TreeNode parentNode=new TreeNode(leftNode.val+rightNode.val);
			
			parentNode.left=leftNode;
			parentNode.right=rightNode;
			list.removeFirst();
			list.removeFirst();
			
			list.add(parentNode);
		}
		
		return list.getFirst();
		
	}

}

