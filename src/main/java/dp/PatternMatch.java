package dp;

public class PatternMatch {
	
	
	public static void main(String[] args) {
		PatternMatch patternMatch=new PatternMatch();
		patternMatch.isMatch("aa", "*");
	}

	/**
	 * 10. 正则表达式匹配
	 * 192. 通配符匹配
	 * 判断两个可能包含通配符“？”和“*”的字符串是否匹配。匹配规则如下：

	'?' 可以匹配任何单个字符。
	'*' 可以匹配任意字符串（包括空字符串）。
	两个串完全匹配才算匹配成功。
	
	1<=|s|, |p| <= 1000
s仅包含小写英文字母
p包含小写英文字母，？和 *
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch(String s, String p) {
        // write your code here
		
		if (s==null||p==null) {
			return false;
		}
		//memo是记住相同下标计算后的结果，防止重复计算
		//其实memo就是dfs的结果
		boolean[][]memo=new boolean[s.length()][p.length()];
		//visited是记住这俩个下标有没有访问过，和memo的作用不一样
		boolean[][]visited=new boolean[s.length()][p.length()];
		return dfs(s, 0, p, 0,memo,visited);
    }
	
	public boolean isAllStar(String p,int pIndex) {
		
		for (int i = pIndex; i < p.length(); i++) {
			if (p.charAt(i)!='*') {
				return false;
			}
		}
		
		return true;
	}
	
	//从两个字符串的下标0开始搜索
	public boolean dfs(String s,int sIndex, String p,int pIndex) {
		
		//如果s的下标走到头了，那么p从pIndex必须都是*
		if (sIndex==s.length()) {
			
			return isAllStar(p, pIndex);
		}
		
		//如果p的下标走到头了，那么s必须也得走到头
		if (pIndex==p.length()) {
			
			return sIndex==s.length();
		}
		
		if (p.charAt(pIndex)=='?'||s.charAt(sIndex)==p.charAt(pIndex)) {
			sIndex++;
			pIndex++;
			return dfs(s, sIndex, p, pIndex);
		}
		
		if (p.charAt(pIndex)=='*') {
			//这里搜索，必须要用记忆加速
			return dfs(s, sIndex+1, p, pIndex)||dfs(s, sIndex, p, pIndex+1);
		}
		
		return false;
	}
	
	public boolean dfs(String s,int sIndex, String p,int pIndex,boolean[][]memo,boolean[][]visited) {
		
		//如果s的下标走到头了，那么p从pIndex必须都是*
		if (sIndex==s.length()) {
			
			return isAllStar(p, pIndex);
		}
		
		//如果p的下标走到头了，那么s必须也得走到头
		if (pIndex==p.length()) {
			
			return sIndex==s.length();
		}
		if (visited[sIndex][pIndex]) {
			return memo[sIndex][pIndex];
		}
		
		if (p.charAt(pIndex)=='?'||s.charAt(sIndex)==p.charAt(pIndex)) {
			
			visited[sIndex][pIndex]=true;
			//属于向后依赖
			memo[sIndex][pIndex]=dfs(s, sIndex+1, p, pIndex+1,memo,visited);
			return memo[sIndex][pIndex];
		}else if (p.charAt(pIndex)=='*') {
			//这里搜索，必须要用记忆加速
			visited[sIndex][pIndex]=true;
			memo[sIndex][pIndex]=dfs(s, sIndex+1, p, pIndex,memo,visited)||dfs(s, sIndex, p, pIndex+1,memo,visited);
			return memo[sIndex][pIndex];
		}
		
		return false;
	}
	
	
	public boolean dp(String s, int sIndex, String p, int pIndex, boolean[][] dp) {

		// 如果s的下标走到头了，那么p从pIndex必须都是*
		if (sIndex == s.length()) {

			return isAllStar(p, pIndex);
		}

		// 如果p的下标走到头了，那么s必须也得走到头
		if (pIndex == p.length()) {

			return sIndex == s.length();
		}
		
		while(pIndex<p.length()&&sIndex<s.length()) {
			
			if (p.charAt(pIndex) == '?' || s.charAt(sIndex) == p.charAt(pIndex)) {
				
				// 属于向后依赖
				dp[sIndex][pIndex] = dp[sIndex+1][pIndex+1];
				
			} else if (p.charAt(pIndex) == '*') {
				// 这里搜索，必须要用记忆加速
				dp[sIndex][pIndex] = dp[sIndex+1][pIndex] || dp[sIndex][pIndex+1];
			}
			
		}

		return dp[];

	}
}
