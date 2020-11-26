package second.divideconquer;

import tree.TreeNode;

public class PreOrder {
	
	public static void main(String[] args) {
		
		TreeNode node1=new TreeNode(1);
		TreeNode node2=new TreeNode(2);
		TreeNode node3=new TreeNode(3);
		TreeNode node4=new TreeNode(4);
		TreeNode node5=new TreeNode(5);
		TreeNode node6=new TreeNode(6);
		node1.left=node2;
		
		node2.left=node3;
		node2.right=node4;
		node1.right=node5;
		node5.right=node6;
		
		PreOrder preOrder=new PreOrder();
		preOrder.flatten(node1);
		System.out.println(node1);
	}
	/**
	 * 175. 翻转二叉树
	翻转一棵二叉树。左右子树交换
	 * @param root
	 */
	public void invertBinaryTree(TreeNode root) {
        // write your code here
		
		if (root==null) {
			return;
		}
		swip(root);
		invertBinaryTree(root.left);
		invertBinaryTree(root.right);
		
    }
	
	public void swip(TreeNode root) {
		
		TreeNode tempNode=root.left;
		root.left=root.right;
		root.right=tempNode;
	}

	
	/**
	 * 453. 将二叉树拆成链表
	 * 将一棵二叉树按照前序遍历拆解成为一个 假链表。所谓的假链表是说，用二叉树的 right 指针，来表示链表中的 next 指针。
	 * 不要忘记将左儿子标记为 null，否则你可能会得到空间溢出或是时间溢出。
	 * 
	 * 不能用前序遍历的递归，因为递归之后，所有的变量都会回到上一层，就无法记录那一层节点的右子树了
	 * @param root
	 */
	public void flatten(TreeNode root) {
        // write your code here
		
		flattenAndGetLastNode(root);
    }
	
	/**
	 * 不能返回原来的节点，否则在换左右子树的时候，信息容易丢
	 * 
	 * 分治法：递归的返回值是子树的最后一个节点
	 * @param root
	 * @return
	 */
	public TreeNode flattenAndGetLastNode(TreeNode root) {
		
		if (root==null) {
			return null;
		}
		
		//分
		TreeNode left= flattenAndGetLastNode(root.left);
		
		TreeNode right=flattenAndGetLastNode(root.right);
		
		//合
		if (left!=null&&right!=null) {
			
			left.right=root.right;
			root.right=root.left;
			root.left=null;
			return right;
		}
		
		if (left!=null&&right==null) {
			
			root.right=root.left;
			root.left=null;
			return left;
		}
		
		if (left==null&&right!=null) {
			return right;
		}

		return root;
	}
}
