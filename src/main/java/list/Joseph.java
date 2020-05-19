package list;
/**
 * 已知n个人（以编号1，2，3...n分别表示）围坐在一张圆桌周围，
 * 从编号为k的人开始报数，数到m的那个人出列；他的下一个人又从1开始报数，
 * 数到m的那个人又出列；依此规律重复下去，直到圆桌周围的人只有一个没有出列
 * @author LANG
 *
 */
public class Joseph {

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
		node5.next=node1;
		
		yuesefu(5, 1, 2, node1);
	}
	
	/**
	 * 建立两个节点，cur指向当前的节点，pre.next指向cur,pre用来做删除
	 * @param n
	 * @param k
	 * @param m
	 */
	public static void yuesefu(int n,int k, int m,ListNode head){
		if (head==null||n<1||k<=0) {
			System.out.println("非法");
			return;
		}
		ListNode cur=head;
		ListNode pre=cur;
		//pre.next指向cur
		while(pre.next!=cur){
			pre=pre.next;
		}
		
		//将pre和cur同时移动k-1，此时cur指向了k，pre指向了k-1
		
		for (int i = 0; i < k-1; i++) {
			pre=pre.next;
			cur=cur.next;
		}
		
		//pre和cur同时开始移动，移动m-1个位置
		//pre==cur说明只剩最后一个节点
		while(pre!=cur){
			for (int i = 0; i < m-1; i++) {
				pre=pre.next;
				cur=cur.next;
			}
			//此时cur指向了从k开始，第m个位置，开始删除
			System.out.println(cur.val);
			pre.next=cur.next;
			cur=cur.next;
		}
		//打印环里的最后一个节点
		System.out.println(cur.val);
		
	}

}
