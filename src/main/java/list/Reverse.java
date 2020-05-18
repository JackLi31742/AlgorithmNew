package list;
/**
 * leetcod 206
 * @author LANG
 *
 */

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
		System.out.println(reverse.reverseList3(node1));;
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
	 * 解法2、新建一个头，作为新链表的头，使用头插法，将原有链表的头不停的插到newHead的后一个位置
	 * 
	 * 
	 * @param head
	 * @return
	 */
	public ListNode reverseList2(ListNode head) {
		if (head==null||head.next==null) {
			return head;
		}
		ListNode newHead=new ListNode(0);
		ListNode first=head;
		while(first!=null){
			ListNode temp=first.next;
			first.next=newHead.next;
			newHead.next=first;
			first=temp;
		}
		
		return newHead.next;
				
	}
	
	/**
	 * 解法3 递归
	 * @param head
	 * @return
	 */
	public ListNode reverseList3(ListNode head) {
		if (head==null||head.next==null) {
			return head;
		}
		//这才是递归的真正用法，遍历到最后一个得到了新的头
		ListNode newHead=reverseList3(head.next);
		
//		newHead.next=head;
		head.next.next=head;
		head.next=null;
		
		return newHead;
	}
	
	
}
