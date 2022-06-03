//translate a string of ascii text into Braille
public class Solution {

    public static String solution(String s) {
        StringBuilder result = new StringBuilder();
        int[] brailleVals = { 32, 48, 36, 38, 34, 52, 54, 50, 20, 22, 40, 56, 44, 46, 42, 60, 62, 58, 28, 30, 41, 57, 23, 45, 47, 43 };

        for(int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);

            if(c == ' ') {
                result.append("000000");
            }
            else {
                if(Character.isUpperCase(c)) result.append("000001");

                c = Character.toLowerCase(c) - 97;
                
                result.append(String.format("%6s", Integer.toBinaryString(brailleVals[c])).replace(' ', '0'));
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution("code"));
        System.out.println(solution("Braille"));
        System.out.println(solution("The quick brown fox jumps over the lazy dog"));
    }
}