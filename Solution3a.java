//Note: FindMinNumOps() does not pass the hidden tests of the challenge due to space complexity requirements (or so I've gathered, Google did not indicate why these fails)
//Given an integer value, find the number of operations it takes to reduce the value to 1, given three operations: a) Add 1, b) Subtract 1, c) Divide by 2
import java.math.BigInteger;
import java.util.HashMap;

public class Solution3a {
	public static HashMap<BigInteger, Integer> lookup = new HashMap<BigInteger, Integer>();
	
	public static boolean IsPowerOfTwo(BigInteger x) {
		//bitwise trick
		return (!x.equals(BigInteger.valueOf(0))) && (x.and(x.subtract(BigInteger.valueOf(1))).equals(BigInteger.valueOf(0)));
	}
	
	public static boolean IsOdd(BigInteger x) {
		//x mod 2 != 0
		return x.mod(BigInteger.valueOf(2)).compareTo(BigInteger.valueOf(0)) != 0;
	}
	
	public static BigInteger ParseBigInt(String in) {
		try {
			return new BigInteger(in);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public static int FindMinNumOps(BigInteger x) {
		//return 0 for 1 or less
		if(x.compareTo(BigInteger.valueOf(1)) <= 0) return 0;
		
		//perform quick lookup on previously calculated inputs
		Integer memo = lookup.get(x);
		if(memo != null) return memo.intValue();
		
		int val = 1;
		
		//if x is odd, prefer a +/- 1 value that is a power of two, or the -1 value
		if(IsOdd(x)) {
			//If +1 is a power of two and -1 is not, run with +1
			if(IsPowerOfTwo(x.add(BigInteger.valueOf(1))) && !IsPowerOfTwo(x.subtract(BigInteger.valueOf(1)))) {
				val += FindMinNumOps(x.add(BigInteger.valueOf(1)));
			}
			//For all other odd cases, prefer the -1
			else {
				val += FindMinNumOps(x.subtract(BigInteger.valueOf(1)));
			}
		}
		//Even numbers always choose the divide by 2 path (or in the case of a power of two, evaluate log2(x)
		else {
			if(IsPowerOfTwo(x)) val = (int)(Math.log10(x.doubleValue()) / Math.log10(2));
			else val += FindMinNumOps(x.divide(BigInteger.valueOf(2)));
		}
		
		//store val into the lookup table, if it doesnt exist already
		if(lookup.get(x) == null) lookup.put(x, Integer.valueOf(val));
		
		return val;
	}
	
	//totally copied from StackOverflow and modified for BigInteger. Not mine! This passes the hidden tests, however.
	public static int FindMinNumOps2(BigInteger x) {
		int ctr = 0;
		
		while(x.compareTo(BigInteger.valueOf(1)) > 0) {
			if(!IsOdd(x)) {
				x = x.divide(BigInteger.valueOf(2));
			}
			else if(x.equals(BigInteger.valueOf(3)) || x.mod(BigInteger.valueOf(4)).compareTo(BigInteger.valueOf(1)) == 0) {
				x = x.subtract(BigInteger.valueOf(1));
			}
			else {
				x = x.add(BigInteger.valueOf(1));
			}
			ctr += 1;
		}
		
		return ctr;
	}
	
	public static int solution(String x) {
		BigInteger input = ParseBigInt(x);
		
		return FindMinNumOps(input);
	}
	
	public static void main(String[] args) {
		System.out.println("0 " + solution("0"));
		System.out.println("1 " + solution("1"));
		System.out.println("2 " + solution("2"));
		System.out.println("3 " + solution("3"));
		System.out.println("4 " + solution("4"));
		System.out.println("5 " + solution("5"));
		System.out.println("6 " + solution("6"));
		System.out.println("7 " + solution("7"));
		System.out.println("8 " + solution("8"));
		System.out.println("9 " + solution("9"));
		System.out.println("10 " + solution("10"));
		System.out.println("11 " + solution("11"));
		System.out.println("12 " + solution("12"));
		System.out.println("13 " + solution("13"));
		System.out.println("14 " + solution("14"));
		System.out.println("15 " + solution("15"));
		System.out.println("16 " + solution("16"));
		System.out.println("17 " + solution("17"));
		System.out.println("18 " + solution("18"));
	}
}
