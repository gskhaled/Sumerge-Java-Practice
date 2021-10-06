package com.sumerge;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagContentExtSolution {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int testCases = Integer.parseInt(in.nextLine());
		while (testCases > 0) {
			String line = in.nextLine();

			boolean foundMatches = false;
			// <(.+)> matches with the opening tags
			// ([^<]+) matches with the content but avoids having another opening tag inside
			// </\\1> matches with the closing tags and 1 ensures similar to the first group
			// captured
			Pattern pattern = Pattern.compile("<(.+)>([^<]+)</\\1>");
			Matcher matcher = pattern.matcher(line);

			while (matcher.find()) {
				System.out.println(matcher.group(2));
				foundMatches = true;
			}

			if (!foundMatches) {
				System.out.println("None");
			}
			testCases--;
		}
	}
}
