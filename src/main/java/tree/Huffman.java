package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Huffman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void huffman(int[] arr){
		
		List<TreeNode> list=new ArrayList<>();
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
		
		Collections.sort(list,comparator);
		
		
		
	}

}
