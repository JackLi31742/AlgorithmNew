package list;

public class Kth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 *       找出单向链表中倒数第 k 个节点,返回该节点的值。
	 *       
	 *  头结点是否有数据，不重要
	 *  解法1、暴力，求出链表的长度n，再移动n-k个节点
	 *  2、双指针，先用first指针正向移动k个节点，记录first位置，second指向头
	 *  	再用second指针和first同时移动，当first第一次为null时，second指向了倒数第k个
	 *  再同时移动时，必须指定计数器count，在first移动时，如果first在等于null时，count<k，说明不合法
	 * @param head
	 * @param k
	 * @return
	 */
	public int kthToLast(ListNode head, int k) {
		if(head==null||k<=0) {
			return Integer.MIN_VALUE;
		}
		ListNode first=head;
		ListNode second =head;
		int count=0;
		for (int i = 0; i < k; i++) {
			if (first!=null) {
				first=first.next;
				count++;
			}
		}
		
		if (count<k) {
			return Integer.MIN_VALUE;
		}
		while(first!=null) {
			first=first.next;
			second=second.next;
		}
		
		return second.val;
	}
}


