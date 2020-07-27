package dp;

public class Knapsack {
	
	public static void main(String[] args) {
		//物品的重量
		int[] w={1,4,3};
		//物品的价值
		int[] v={1500,3000,2000};
		//背包的重量
		int C=4;
		//背包的价值
		int[][] value=new int[v.length+1][C+1];
		
		//路径
		int[][] path=new int[v.length+1][C+1];
		//放过第一行
		for (int i = 1; i < value.length; i++) {
			//放过第一列
			for (int j = 1; j < value[i].length; j++) {
				//物品i放不进去
				if (w[i-1]>j) {
					value[i][j]=value[i-1][j];//将上一行的值赋值给现在的行
				}else {
					if (value[i-1][j]<v[i-1]+value[i-1][j-w[i-1]]) {
						value[i][j]=v[i-1]+value[i-1][j-w[i-1]];
						//只有在这个时候是把新的物品放进了背包里，其余都没有放进背包
						//i是物品
						path[i][j]=1;
					}else {
						value[i][j]=value[i-1][j];
					}
				}
				
//				if (w[i-1]<=j&&value[i-1][j]<v[i-1]+value[i-1][j-w[i-1]]) {
//					value[i][j]=v[i-1]+value[i-1][j-w[i-1]];
//				}else {
//					value[i][j]=value[i-1][j];
//				}
			}
		}
		
		for (int i = 0; i < value.length; i++) {
			for (int j = 0; j < value[0].length; j++) {
				System.out.print(value[i][j]+"\t");
			}
			System.out.println();
		}
		
		//打印
		int i=path.length-1;
		int j=path[0].length-1;
		
		while(i>0&&j>0){
			if (path[i][j]==1) {
				System.out.println(i);
				j=j-w[i-1];
			}
			i--;
		}
	}

}
