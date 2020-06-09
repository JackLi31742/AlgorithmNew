package tree;

public class Delete {
	
	public static void main(String[] args) {
		TreeNode node1=new TreeNode(1);
		TreeNode node2=new TreeNode(2);
		TreeNode node3=new TreeNode(3);
		TreeNode node4=new TreeNode(4);
		
		node1.left=node2;
		node1.right=node3;
		node3.right=node4;
		
		delete(node1, 4);
		System.out.println(node1);
		
	}
	
	/**
	 * 如果删除的节点是叶子节点，则删除该节点
		如果删除的节点是非叶子节点，则删除该子树.
	 * @param root
	 * @param target
	 * @return
	 */
	public static TreeNode delete(TreeNode root,int target) {
		if (root==null||root.val==target) {
			return null;
		}
		if (root.left!=null&&root.left.val==target) {
			root.left=null;
			return root;
		}
		if (root.right!=null&&root.right.val==target) {
			root.right=null;
			return root;
		}
		delete(root.left, target);
		delete(root.right, target);
		return root;
	}

}
