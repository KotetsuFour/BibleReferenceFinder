package io;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Formalize {

	
	public static void convertNASBText(String filename) {
		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		StringBuilder sb = new StringBuilder();
		String book = "";
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			scan.nextLine(); //Every odd line is just "."
			StringBuilder newLine = new StringBuilder();
			Scanner wordScan = new Scanner(line);
			while (wordScan.hasNext()) {
				String word = wordScan.next();
				if (word.equals("--")) {
					break;
				}
				word = word.replace("!", "");
				word = word.replace("*", "");
				word = word.replace("(", "");
				word = word.replace(")", "");
				word = word.replace("\"", "");
				word = word.replace(":", "");
				word = word.replace(";", "");
				word = word.replace("'", "");
				word = word.replace(",", "");
				word = word.replace(".", "");
				word = word.replace("?", "");
				word = word.replace("--", " ");
				newLine.append(" " + word);
			}
			
			StringBuilder title = new StringBuilder(wordScan.next());
			String verse = "";
			while (wordScan.hasNext()) {
				String next = wordScan.next();
				if (wordScan.hasNext()) {
					title.append(next);
				} else {
					verse = next;
				}
			}
			if (!book.equals(title.toString())) {
				try {
					System.out.println(book);
					PrintStream write = new PrintStream(new File("reference/" + book + ".txt"));
					write.println(sb.toString());
					sb = new StringBuilder();
					write.close();
					book = title.toString();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			sb.append(verse);
			sb.append(newLine);
			sb.append("\n");
			
			
			wordScan.close();
		}
		try {
			System.out.println(book);
			PrintStream write = new PrintStream(new File("reference/" + book + ".txt"));
			write.println(sb.toString());
			sb = new StringBuilder();
			write.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		scan.close();
	}
	
	public static void main(String[] args) {
		convertNASBText("reference/nasb.txt");
	}
}
