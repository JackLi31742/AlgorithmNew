package tree;

public class HuffmanNode {
	
	Character data;
	int weight;
	HuffmanNode left ;
	HuffmanNode right ;
	public HuffmanNode(Character data, int weight) {
		super();
		this.data = data;
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "HuffmanNode [data=" + data + ", weight=" + weight + ", left=" + left + ", right=" + right + "]";
	}

	
	
}
