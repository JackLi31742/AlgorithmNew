package tree;

public class AVL {
	
	public static void main(String[] args) {
		
		TreeNode node1=new TreeNode(10);
		TreeNode node2=new TreeNode(11);
		TreeNode node3=new TreeNode(7);
		TreeNode node4=new TreeNode(6);
		TreeNode node5=new TreeNode(8);
		TreeNode node6=new TreeNode(9);
//		TreeNode node7=new TreeNode(9);
//		TreeNode node8=new TreeNode(2);
		

		add(node1, node2);
		add(node1, node3);
		add(node1, node4);
		add(node1, node5);
		add(node1, node6);
				
		
		System.out.println(getTreeHeight(node1));;
//		int[] arr = { 10, 11, 7, 6, 8, 9 };
//		TreeNode root=null;
//		for(int i=0; i < arr.length; i++) {
//			add(root,new TreeNode(arr[i]));
//		}
		
		System.out.println(node1);
	}
	
	
	public static void add(TreeNode root,TreeNode node) {
		if (root==null) {
			root=node;
			return;
		}
		if (root!=null&&node!=null) {
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
			
			//右旋转
			if (getTreeHeight(root.left)-getTreeHeight(root.right)>1) {
				if (root.left!=null&&getTreeHeight(root.left.right)>getTreeHeight(root.left.left)) {
					leftRotate(root.left);
				}
				rightRotate(root);
				
				return;
			}
			//坐旋转
			if (getTreeHeight(root.right)-getTreeHeight(root.left)>1) {
				if (root.right!=null&&getTreeHeight(root.right.left)>getTreeHeight(root.right.right)) {
					rightRotate(root.right);
				}
				leftRotate(root);
			}
		}
		
	}

	//左旋转
	public static void leftRotate(TreeNode node) {
//		TreeNode newNode=node;
		//必须要新建节点，否则是原来节点的引用，原来节点变化，新节点也会变化
		TreeNode newNode=new TreeNode(node.val);
		newNode.left=node.left;
		newNode.right=node.right.left;
		node.val=node.right.val;
		node.left=newNode;
		node.right=node.right.right;
	}
	
	//右旋转
	public static void rightRotate(TreeNode node) {
//		TreeNode newNode=node;
		TreeNode newNode=new TreeNode(node.val);
		newNode.left=node.left.right;
		newNode.right=node.right;
		node.val=node.left.val;
		node.left=node.left.left;
		node.right=newNode;
	}
	//计算树的高度
	public static int getTreeHeight(TreeNode root) {
		int height=0;
		if (root!=null) {
			int left=getTreeHeight(root.left);
			int right=getTreeHeight(root.right);
			height=left>right?left+1:right+1;
		}
		
		return height;
	}
}
