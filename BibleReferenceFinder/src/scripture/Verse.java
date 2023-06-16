package scripture;

import java.util.ArrayList;
import java.util.Scanner;

public class Verse {

	private ArrayList<String> words;
	
	private String verse;
	
	private String book;
	
	private ArrayList<ArrayList<Verse>> matches;
	
	private int greatestMatches;
	
	public Verse(String book, String line) {
		this.book = book;
		words = new ArrayList<>();
		Scanner scan = new Scanner(line);
		verse = scan.next();
		while (scan.hasNext()) {
			String next = scan.next(); //We won't allow repeat words, so that the
			boolean add = true;           //array system will work
			for (int q = 0; q < words.size(); q++) {
				if (next.toLowerCase().equals(words.get(q).toLowerCase())) {
					add = false;
				}
			}
			if (add) {
				words.add(next.toLowerCase());
			}
		}
		scan.close();
	}
	
	public ArrayList<String> getWords() {
		return words;
	}
	public String getBook() {
		return book;
	}
	public String getVerse() {
		return verse;
	}
	
	public void initializeSorterArray() {
		this.matches = new ArrayList<>();
		for (int q = 0; q < words.size(); q++) {
			matches.add(new ArrayList<Verse>());
		}
	}
	public void addVerse(int idx, Verse v) {
		matches.get(idx).add(v);
		if (idx > greatestMatches) {
			greatestMatches = idx;
		}
	}
	public ArrayList<ArrayList<Verse>> getMatches() {
		return matches;
	}
}
