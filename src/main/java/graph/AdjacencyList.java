package graph;

import java.util.ArrayList;

/**
 * https://www.cnblogs.com/skywang12345/p/3707612.html
 *
 */
public class AdjacencyList {

	
	private ArrayList<VNode> vNodes;
	
	
	public AdjacencyList(int n) {
		super();
		this.vNodes = new ArrayList<VNode>(n);
	}

	//顶点个数
	private int vNum;
	
	//边的个数
	private int eNum;

	public ArrayList<VNode> getvNodes() {
		return vNodes;
	}

	public void setvNodes(ArrayList<VNode> vNodes) {
		this.vNodes = vNodes;
	}

	
	
	public void addVNode(String data) {
		this.vNodes.add(new VNode(data));
	}
	
	public void addENode(int v1,int v2,int weight) {
		VNode vNode1=this.vNodes.get(v1);
		
		//为了保证头节点指针不移动，使用头插法将边节点插入列边
		
		ENode pENode=new ENode(v2, 1);
		
		pENode.next=vNode1.first;
		vNode1.first=pENode;
		
		//无向图得插两次
		VNode vNode2=this.vNodes.get(v2);
		
		ENode qENode=new ENode(v1, 1);
		
		qENode.next=vNode2.first;
		
		vNode2.first=qENode;
	}
	
	
}

//顶点节点
class VNode{
	//顶点的值
	String data;
	//顶点所包含链表的表头指针
	ENode first;
	public VNode(String data) {
		super();
		this.data = data;
	}
	@Override
	public String toString() {
		return "VNode [data=" + data + ", first=" + first + "]";
	}
	
	
}

//边节点
class ENode{
	
	//该节点所对应的顶点在vNodes中的索引
	int index;
	
	//指向下一个节点
	ENode next;
	//边的权
	int weight;
	public ENode(int index, int weight) {
		super();
		this.index = index;
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "ENode [index=" + index + ", next=" + next + ", weight=" + weight + "]";
	}
	
	
	
	
}
