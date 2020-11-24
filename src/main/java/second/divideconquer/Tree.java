package second.divideconquer;

import tree.TreeNode;

public class Tree {

	
	/**
	 * 597. 具有最大平均数的子树
	 * 给一棵二叉树，找到有最大平均值的子树。返回子树的根结点。
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode findSubtree2(TreeNode root) {
        // write your code here
		
		//找到左子树的平均值
		
		//右子树的平均值
		
		//加上根之后的平均值
    }
	
	public Result getAvg(TreeNode root) {
		
	}
}

class Result{
	TreeNode node;
	int avg;
	
	public Result(TreeNode node,int avg) {
		// TODO Auto-generated constructor stub
		this.node=node;
		this.avg=avg;
	}
}
