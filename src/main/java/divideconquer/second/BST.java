package divideconquer.second;

import java.util.List;
import java.util.Stack;

import tree.TreeNode;

public class BST {
	
	public static void main(String[] args) {
		
		TreeNode node1=new TreeNode(2);
		TreeNode node2=new TreeNode(1);
		TreeNode node3=new TreeNode(3);
		TreeNode node4=new TreeNode(11);
		TreeNode node5=new TreeNode(20);
		TreeNode node6=new TreeNode(6);
		node1.left=node2;
		node1.right=node3;
		
//		node3.left=node4;
//		node3.right=node5;
//		node5.right=node6;
		
		BST bst=new BST();
//		bst.inorderPredecessor(node1, node2);
		
	}
	
	/**
	 * 95. 验证二叉查找树
	 * 给定一个二叉树，判断它是否是合法的二叉查找树(BST)

	一棵BST定义为：
	
	节点的左子树中的值要严格小于该节点的值。
	节点的右子树中的值要严格大于该节点的值。
	左右子树也必须是二叉查找树。
	一个节点的树也是二叉查找树。
	
	
	不能仅仅判断节点和它的左右子节点，因为在左右子树中，有可能左子树的最大值，右子树的最小值会导致非法
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
        // write your code here
		Result3 result3 = isValid(root);
		return result3.isValid;
    }
	
	public Result3 isValid(TreeNode root) {
		
		if (root==null) {
			return new Result3(true);
		}
		
		Result3 left = isValid(root.left);
		
		Result3 right = isValid(root.right);
		
		//先判断非法的，符合合法的条件比较多
		
		if (!left.isValid||!right.isValid) {
			return new Result3(false);
		}
		
		if (left.maxNode!=null&&left.maxNode.val>=root.val) {
			return new Result3(false);
		}
		
		if (right.minNode!=null&&right.minNode.val<=root.val) {
			return new Result3(false);
		}
		
		//以整体看待这三个节点
		Result3 result3=new Result3(true);
		
		
//		if (right.maxNode!=null) {
//			result3.maxNode=right.maxNode;
//		}else if (right.minNode!=null) {
//			result3.maxNode=right.minNode;
//		}else {
//			result3.maxNode=root;
//		}
//		
//		if (left.minNode!=null) {
//			result3.minNode=left.minNode;
//		}else if (left.maxNode!=null) {
//			result3.minNode=left.maxNode;
//		}else {
//			result3.minNode=root;
//		}
		//right.maxNode 和right.minNode是同时存在的，所以不需要上述的多个if else 判断
		result3.minNode = left.minNode != null ? left.minNode : root;
        result3.maxNode = right.maxNode != null ? right.maxNode : root;
		return result3;
	}

	
}



class Result3{
	
	boolean isValid;
	//用是否为null判断，比用isLeft,LeftMax这样的boolean和int要好
	TreeNode maxNode;
	TreeNode minNode;
	
	public Result3(boolean isValid) {
		this(isValid,null,null);
	}
	
	
	public Result3(boolean isValid, TreeNode maxNode, TreeNode minNode) {
		super();
		this.isValid = isValid;
		this.maxNode = maxNode;
		this.minNode = minNode;
	}


	@Override
	public String toString() {
		return "Result3 [isValid=" + isValid + ", maxNode=" + maxNode + ", minNode=" + minNode + "]";
	}
	
}
