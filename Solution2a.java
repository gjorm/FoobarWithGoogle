//given a string representing a hallway - where < and > are minions, find the number of times minons salute each other (when they cross paths)
public class Solution {
	public static int solution(String s) {
		int ctr = 0;
		
		for(int i = 0; i < s.length(); i++) {
			
			if(s.charAt(i) == '>') {
				String sub = s.substring(i);
				
				for(int j = 0; j < sub.length(); j++) {
					if(sub.charAt(j) == '<') ctr++;
				}
			}
		}
		
		return ctr * 2;
	}
	
	public static void main(String[] args) {
		System.out.println(solution(">----<"));
		System.out.println(solution("<<>><"));
	}
}
