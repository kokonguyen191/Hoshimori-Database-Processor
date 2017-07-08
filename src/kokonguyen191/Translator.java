package kokonguyen191;

import java.util.HashMap;
import java.util.Iterator;
import java.util.HashSet;

public class Translator {

	private HashMap<String, String> dict;
	HashSet<String> untranslated;

	private void generate(String[] raw) {
		for (String entry : raw) {
			String[] splitted = entry.split(",");
			dict.put(splitted[0], splitted[1]);
		}
	}

	String translatePhrase(String str) {
		if (dict.get(str) == null) {
			untranslated.add(str);
			return str;
		} else {
			return dict.get(str);
		}
	}

	boolean canTranslate(String str) {
		return dict.containsKey(str);
	}

	void printUntranslated() {
		if (!untranslated.isEmpty()) {
			System.out.println("These words or sentences haven't been translated:");
			Iterator<String> ite = untranslated.iterator();
			while (ite.hasNext()) {
				System.out.println(ite.next());
			}
		}
	}

	public Translator(String[] raw) {
		untranslated = new HashSet<String>();
		dict = new HashMap<String, String>();
		generate(raw);
	}
}
