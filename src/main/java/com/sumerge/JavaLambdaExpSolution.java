package com.sumerge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

interface PerformOperation {
	boolean check(int a);
}

class MyMath {
	public static boolean checker(PerformOperation p, int num) {
		return p.check(num);
	}

	public static PerformOperation isOdd() {
		return num -> {
			if (num % 2 == 0)
				return false;
			else
				return true;
		};
	}

	public static PerformOperation isPrime() {
		return num -> {
			for (int i = 2; i < num; i++) {
				if (num % i == 0)
					return false;
			}
			return true;
		};
	}

	public static PerformOperation isPalindrome() {
		return num -> {
			String numString = "" + num;
			int i = 0;
			int j = numString.length() - 1;
			while (i < j) {
				if (numString.charAt(i) != numString.charAt(j))
					return false;
				i++;
				j--;
			}
			return true;
		};

	}
}

public class JavaLambdaExpSolution {

	public static void main(String[] args) throws IOException {
		MyMath ob = new MyMath();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		PerformOperation op;
		boolean ret = false;
		String ans = null;
		while (T-- > 0) {
			String s = br.readLine().trim();
			StringTokenizer st = new StringTokenizer(s);
			int ch = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if (ch == 1) {
				op = ob.isOdd();
				ret = ob.checker(op, num);
				ans = (ret) ? "ODD" : "EVEN";
			} else if (ch == 2) {
				op = ob.isPrime();
				ret = ob.checker(op, num);
				ans = (ret) ? "PRIME" : "COMPOSITE";
			} else if (ch == 3) {
				op = ob.isPalindrome();
				ret = ob.checker(op, num);
				ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

			}
			System.out.println(ans);
		}
	}
}