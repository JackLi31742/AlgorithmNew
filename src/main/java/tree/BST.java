package tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class BST {
	
	public static void main(String[] args) {
		TreeNode node1=new TreeNode(7);
		TreeNode node2=new TreeNode(3);
		TreeNode node3=new TreeNode(10);
		TreeNode node4=new TreeNode(12);
		TreeNode node5=new TreeNode(5);
		TreeNode node6=new TreeNode(1);
		TreeNode node7=new TreeNode(9);
		TreeNode node8=new TreeNode(2);
		
		add(node1, node2);
		add(node1, node3);
		add(node1, node4);
		add(node1, node5);
		add(node1, node6);
		add(node1, node7);
		add(node1, node8);
		System.out.println(node1);
		infixOrder(node1);
		
		delete(node1, 7);
		System.out.println(node1);
//		searchNode(node1, 19);
	}
	
	public static void delete(TreeNode root,int value) {
		
		TreeNode node=searchNode(root, value);
		System.out.println("node:"+node);
		if (node==null) {
			return;
		}
		TreeNode parentNode=searchParentNode(root, value);
		System.out.println("parentNode:"+parentNode);
		if (parentNode!=null) {
			
			if (node.left==null&&node.right==null) {//删除的是叶子节点
				
				if (parentNode.left!=null&&parentNode.left.val==value) {
					parentNode.left=null;
				}else {
					parentNode.right=null;
				}
			}else if (node.left!=null&&node.right!=null) {//删除的节点有两颗子树
				int temp=delLeftMax(node);
				node.val=temp;
				
			}else {//删除的节点有一颗子树，但无需判断是左还是右哪个子树
				if (node.left!=null) {
					if (parentNode.left!=null&&parentNode.left.val==value) {
						parentNode.left=node.left;
					}else {
						parentNode.right=node.left;
					}
				}else {
					if (parentNode.left!=null&&parentNode.left.val==value) {
						parentNode.left=node.right;
					}else {
						parentNode.right=node.right;
					}
				}
			}
		}else {
			//节点不为空，说明要找的节点在树里，此时parentNode为空，说明要删除的是根节点root
			if (node.left==null&&node.right==null) {
				root=null;
				return;
			}else if (node.left!=null&&node.right!=null) {
				int temp=delLeftMax(node);
				node.val=temp;
			}else {
				if (node.left!=null) {
					root=root.left;
				}else {
					root=root.right;
				}
			}
		}
		
	}
	
	/**
	 * 删除左子树的最大值节点
	 * @param node
	 * @return 返回这个最大值
	 */
	public static int delLeftMax(TreeNode node) {
		TreeNode pNode=node;
		TreeNode qNode=node.left;
		if (qNode.right!=null) {
			
			while(qNode!=null&&qNode.right!=null) {
				pNode=qNode;
				qNode=qNode.right;
			}
			pNode.right=null;
		}else {
			pNode.left=null;
		}
		return qNode.val;
		
	}
	//查找待删除的节点
	public static TreeNode searchNode(TreeNode root,int value) {
		//没找到会在这return
		//树是空的
		if (root==null) {
			return null;
		}
		if (root.val==value) {
			return root;
		}else if (value<root.val) {
			return searchNode(root.left, value);
		}else {
			return searchNode(root.right, value);
		}
//		return null;
	}
	//找待删除节点的父节点
	public static TreeNode searchParentNode(TreeNode root,int value) {
		if (root==null) {
			return null;
		}
		//说明是根节点是待删除的节点，根节点没有父节点
		if (root.val==value) {
			return null;
		}
		if(root.left!=null&&root.left.val==value){
			return root;
		}
		if (root.right!=null&&root.right.val==value) {
			
			return root;
		}
		
		if (value<root.val) {
			return searchParentNode(root.left, value);
		}else {
			return searchParentNode(root.right, value);
		}
		//没找到
//		return null;
	}
	public static TreeNode add(TreeNode root,TreeNode node) {
		if (root==null) {
			root=node;
			return root;
		}
		if (node.val<root.val) {
			if (root.left==null) {
				root.left=node;
			}else {
				add(root.left, node);
			}
		}else {
			if (root.right==null) {
				root.right=node;
			}else {
				add(root.right, node);
			}
		}
		return root;
	}

	
	public static void infixOrder(TreeNode root) {
		if (root==null) {
			return;
		}
		infixOrder(root.left);
		System.out.println(root.val);
		infixOrder(root.right);
	}
}
