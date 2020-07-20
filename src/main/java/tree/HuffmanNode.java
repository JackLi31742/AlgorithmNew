package tree;

/**
 * 节点
 *
 */
public class HuffmanNode {
	
	Byte data;//值
	int weight;//出现的次数
	HuffmanNode left ;
	HuffmanNode right ;
	public HuffmanNode(Byte data, int weight) {
		super();
		this.data = data;
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "HuffmanNode [data=" + data + ", weight=" + weight + ", left=" + left + ", right=" + right + "]";
	}

	
	
}
