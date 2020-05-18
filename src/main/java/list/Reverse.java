package list;

public class Reverse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ListNode node1=new ListNode(1);
		ListNode node2=new ListNode(2);
		ListNode node3=new ListNode(3);
		ListNode node4=new ListNode(4);
		ListNode node5=new ListNode(5);
		
		node1.next=node2;
		node2.next=node3;
		node3.next=node4;
		node4.next=node5;
		
		Reverse reverse=new Reverse();
		System.out.println(reverse.reverseList(node1));;
	}

	/**
	 * 反转一个单链表。
	 * 解法1、迭代法
	 * @param head
	 * @return
	 */
	public ListNode reverseList1(ListNode head) {
		if (head==null||head.next==null) {
			return head;
		}
		ListNode first=head;
		ListNode second=first.next;
		ListNode third=second.next;
		head.next=null;
		while(second!=null) {
			second.next=first;
			//得先把second赋值给first，否则second指针所指的节点就移动了
			//first节点的位置也就跟着移动了
			first=second;
			second=third;
			if (third!=null) {
				third=third.next;
			}
		}
		return first;
    }
	
	/**
	 * 解法2、递归法
	 * 
	 * 
	 * @param head
	 * @return
	 */
	public ListNode reverseList2(ListNode head) {
		if (head==null||head.next==null) {
			return head;
		}
		ListNode firstListNode=head;
		ListNode secondListNode=head.next;
		head.next=null;
		reverse(secondListNode).next=firstListNode;
	}
	
	public ListNode reverse(ListNode head) {
		
	}
}
