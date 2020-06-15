package tree;

public class HuffmanNode {
	
	String data;
	int weight;
	HuffmanNode left ;
	HuffmanNode right ;
	public HuffmanNode(String data, int weight, HuffmanNode left, HuffmanNode right) {
		super();
		this.data = data;
		this.weight = weight;
		this.left = left;
		this.right = right;
	}
	@Override
	public String toString() {
		return "HuffmanNode [data=" + data + ", weight=" + weight + ", left=" + left + ", right=" + right + "]";
	}

	
	
}
