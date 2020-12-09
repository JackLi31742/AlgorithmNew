package recursion.dfs;

import tree.TreeNode;

public class SubTree {
	
	public static void main(String[] args) {
		TreeNode node1=new TreeNode(1);
		TreeNode node2=new TreeNode(-5);
		TreeNode node3=new TreeNode(2);
		TreeNode node4=new TreeNode(0);
		TreeNode node5=new TreeNode(3);
		TreeNode node6=new TreeNode(-4);
		TreeNode node7=new TreeNode(-5);
		
		node1.left=node2;
		node1.right=node3;
		
		node2.left=node4;
		node2.right=node5;
		
		node3.left=node6;
		node3.right=node7;
		
		
		SubTree avl=new SubTree();
		avl.findSubtree(node1);
	}
	
	/**
	 * 93. 平衡二叉树
	 * 给定一个二叉树,确定它是高度平衡的。对于这个问题,一棵高度平衡的二叉树的定义是：一棵二叉树中每个节点的两个子树的深度相差不会超过1。 
	 * @param root
	 * @return
	 */
	 public static boolean isBalanced(TreeNode root) {
	        // write your code here
		 
		 if (root==null) {
			return true;
		}
		 
		 return getHeight(root).isBalanced;
	 }
	 
	 //由于要同时返回高度和是否平衡，所以定义了额外的类
	 public static Result getHeight(TreeNode root) {
		 
		 if (root==null) {
			return new Result(0, true);
		}
		 
		 Result left = getHeight(root.left);
		 Result right = getHeight(root.right);
		 
		 if (Math.abs(left.height-right.height)<=1&&(left.isBalanced&&right.isBalanced)) {
			return new Result(Math.max(left.height,right.height)+1,true);
		}else {
			return new Result(Math.max(left.height,right.height)+1, false);
		}
		 
	 }

	 
	 /**
	  * 628. 最大子树
	  * 给你一棵二叉树，找二叉树中的一棵子树，他的所有节点之和最大。
	  * @param root
	  * @return
	  */
	 Result result=null;
	 public TreeNode findSubtree(TreeNode root) {
	        // write your code here
		 
		 if (root==null) {
			return root;
		}
		 result=new Result(Integer.MIN_VALUE, root);
		 getSum(root);
		 
		 return result.node;
	 }
	 
	
	 public Result getSum(TreeNode root) {
		 
		 if (root==null) {
			return new Result(0, root);
		}
		 
		 Result left = getSum(root.left);
		 Result right = getSum(root.right);
		 
		 Result parent=new Result(left.sum+right.sum+root.val, root);
		 
		 if (parent.sum>result.sum) {
			result.sum=parent.sum;
			result.node=parent.node;
		}
		 return parent;
	 }
	
}

class Result{
	int height;
	boolean isBalanced;
	int sum;
	TreeNode node;
	
	public Result(int sum, TreeNode node) {
		super();
		this.sum = sum;
		this.node = node;
	}
	
	public Result(int height, boolean isBalanced) {
		super();
		this.height = height;
		this.isBalanced = isBalanced;
	}

	@Override
	public String toString() {
		return "Result [sum=" + sum + ", node=" + node + "]";
	}

	
	
}