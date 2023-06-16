package functions;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import scripture.Verse;

public class Functionality {

	public static void main(String[] args) {
		ArrayList<String> refFiles = new ArrayList<>();
		ArrayList<Verse> refVerses = new ArrayList<>();
		ArrayList<Verse> qerVerses = new ArrayList<>();
		
		
		//Temporary preset
		refFiles.add("reference/genesis.txt");
		refFiles.add("reference/exodus.txt");
		refFiles.add("reference/leviticus.txt");
		refFiles.add("reference/numbers.txt");
		refFiles.add("reference/deuteronomy.txt");
		String query = "query/example.txt";
		String exclude = "and the of";
		
		//Collect Reference Verses
		for (int q = 0; q < refFiles.size(); q++) {
			try {
				Scanner scan = new Scanner(new File(refFiles.get(q)));
				while (scan.hasNextLine()) {
					refVerses.add(new Verse(refFiles.get(q), scan.nextLine()));
				}
				scan.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		//Collect Query Verses
		try {
			Scanner scan = new Scanner(new File(query));
			while (scan.hasNextLine()) {
				qerVerses.add(new Verse("--", scan.nextLine()));
			}
			scan.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		//Find Matches
		for (int q = 0; q < qerVerses.size(); q++) {
			Verse v = qerVerses.get(q);
			v.initializeSorterArray();
			for (int w = 0; w < refVerses.size(); w++) {
				Verse r = refVerses.get(w);
				int count = 0;
				System.out.println(v.getVerse() + "--" + r.getBook() + r.getVerse());
				for (int idx1 = 0; idx1 < v.getWords().size(); idx1++) {
					String vWord = v.getWords().get(idx1);
					if (exclude.contains(vWord)) {
						continue;
					}
					for (int idx2 = 0; idx2 < r.getWords().size(); idx2++) {
						String rWord = r.getWords().get(idx2);
						if (vWord.equals(rWord)) {
							System.out.println(vWord);
							count++;
						}
					}
				}
				if (count > 0) {
					v.addVerse(count - 1, r);
				}
				System.out.println("\n");
			}
		}
		
		//Temporary Print Functionality
//		for (int q = 0; q < qerVerses.size(); q++) {
//			Verse v = qerVerses.get(q);
//			System.out.println(v.getBook() + v.getVerse());
//			for (int w = 0; w < v.getMatches().size(); w++) {
//				if (!v.getMatches().get(w).isEmpty()) {
//					System.out.println("(" + (w + 1) + ")");
//					for (int e = 0; e < v.getMatches().get(w).size(); e++) {
//						Verse r = v.getMatches().get(w).get(e);
//						System.out.println(r.getBook() + r.getVerse());
//					}
//				}
//			}
//		}
	}
}
