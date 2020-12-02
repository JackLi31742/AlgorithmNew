package bf;

public class HashFunction {

	int seed;
	int base;
	public HashFunction(int seed, int base) {
		super();
		this.seed = seed;
		this.base = base;
	}
	
	
	public int hash(String word) {
		
		char[] arr = word.toCharArray();
		
		int result = 1;
		
		for (int i = 0; i < arr.length; i++) {
			
			result+=(result+arr[i]*seed)%base;
			result%=result%base;
		}
		
		return result;
	}
	
	
}
