package kokonguyen191;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CrawledDataCsvParser {

	private static final String[] FIELDS = { "id", "owner_id", "card_type", "i_card_type", "name", "japanese_name",
			"character", "i_character", "rarity", "i_rarity", "weapon", "i_weapon", "obtain_method", "image",
			"special_icon", "art", "special_front", "front_top", "front_bottom", "front_name", "front_rarity",
			"front_weapon", "transparent", "subcard_effect", "hp_1", "sp_1", "atk_1", "def_1", "hp_50", "sp_50",
			"atk_50", "def_50", "hp_70", "sp_70", "atk_70", "def_70", "skill_name", "japanese_skill_name", "skill_SP",
			"skill_range", "skill_affinity", "i_skill_affinity", "action_skill_effects", "skill_comment",
			"skill_preview", "action_skill_combo", "evolved_action_skill_combo", "action_skill_damage",
			"evolved_action_skill_damage", "nakayoshi_title", "japanese_nakayoshi_title", "nakayoshi_skill_effect",
			"nakayoshi_skill_target", "evolved_nakayoshi_skill_effect", "evolved_nakayoshi_skill_target",
			"charge_comment", "charge_damage", "charge_hit", "charge_name", "charge_range" };

	private Translator translator = new Translator(new Dictionary().getTranslationDict());
	private Translator converter = new Translator(new Dictionary().getConversionDict());

	private String dengekiDatabase;
	private String zhDatabase;

	private HashMap<String, Card> dengeki;
	public LinkedList<Card> zh;
	private HashMap<String, Card> database;

	/**
	 * Constructor
	 */
	public CrawledDataCsvParser() {
		// Initialization
		dengeki = new HashMap<String, Card>();
		zh = new LinkedList<Card>();
		database = new HashMap<String, Card>();

		String[] line;
		try {
			// Create dengekionline database
			dengekiDatabase = "C:/Users/kokon/PycharmProjects/hoshimori_scrapy/hoshimori/results/card_database.csv";
			CSVReader dengekiReader = new CSVReader(new FileReader(dengekiDatabase));
			line = dengekiReader.readNext();
			while ((line = dengekiReader.readNext()) != null) {
				Card toAdd = new Card();
				// Add them all
				toAdd.card_type = line[0];
				StringBuilder name_sb = new StringBuilder(line[3].replace("（ハート）", "♡"));
				for (int i = 0; i < name_sb.length(); i++) {
					if (name_sb.charAt(i) == '（' || name_sb.charAt(i) == '（') {
						name_sb.setCharAt(i, '(');
					} else if (name_sb.charAt(i) == '）' || name_sb.charAt(i) == '）') {
						name_sb.setCharAt(i, ')');
					}
				}
				toAdd.japanese_name = name_sb.toString();
				toAdd.action_skill_effects = line[26];
				toAdd.skill_comment = line[27];
				toAdd.skill_preview = line[28];
				toAdd.charge_name = line[29];
				toAdd.charge_hit = line[30];
				toAdd.charge_damage = line[31];
				toAdd.charge_range = line[32];
				toAdd.charge_comment = line[33];
				dengeki.put(toAdd.japanese_name, toAdd);
			}
			// Create zh database
			zhDatabase = "C:/Users/kokon/PycharmProjects/hoshimori_scrapy/hoshimori/results/zh_card_database.csv";
			CSVReader zhReader = new CSVReader(new FileReader(zhDatabase));
			line = zhReader.readNext();
			while ((line = zhReader.readNext()) != null) {
				Card toAdd = new Card();
				toAdd.id = Integer.parseInt(line[0]);
				toAdd.japanese_name = line[1];
				toAdd.character = line[2];
				toAdd.rarity = line[3];
				toAdd.weapon = line[4];
				toAdd.obtain_method = line[5];
				toAdd.image = line[6];
				toAdd.special_icon = line[7];
				toAdd.art = line[8];
				toAdd.special_front = line[9];
				toAdd.front_top = line[10];
				toAdd.front_bottom = line[11];
				toAdd.front_name = line[12];
				toAdd.front_rarity = line[13];
				toAdd.front_weapon = line[14];
				toAdd.subcard_effect = Boolean.valueOf(line[15]);
				toAdd.hp_1 = Integer.parseInt(line[16]);
				toAdd.sp_1 = Integer.parseInt(line[17]);
				toAdd.atk_1 = Integer.parseInt(line[18]);
				toAdd.def_1 = Integer.parseInt(line[19]);
				toAdd.hp_50 = Integer.parseInt(line[20]);
				toAdd.sp_50 = Integer.parseInt(line[21]);
				toAdd.atk_50 = Integer.parseInt(line[22]);
				toAdd.def_50 = Integer.parseInt(line[23]);
				toAdd.hp_70 = returnIntValue(line[24], "-");
				toAdd.sp_70 = returnIntValue(line[25], "-");
				toAdd.atk_70 = returnIntValue(line[26], "-");
				toAdd.def_70 = returnIntValue(line[27], "-");
				toAdd.japanese_skill_name = line[28];
				toAdd.skill_SP = returnIntValue(line[29], "");
				toAdd.skill_range = line[30];
				toAdd.skill_affinity = line[31];
				toAdd.action_skill_effects = line[32];
				toAdd.action_skill_combo = returnIntValue(line[33], "");
				toAdd.action_skill_damage = line[34];
				toAdd.evolved_action_skill_combo = returnIntValue(line[35], "");
				toAdd.evolved_action_skill_damage = line[36];
				toAdd.japanese_nakayoshi_title = line[37];
				toAdd.nakayoshi_skill_effect = line[38];
				toAdd.nakayoshi_skill_target = line[39];
				toAdd.evolved_nakayoshi_skill_effect = line[40];
				toAdd.evolved_nakayoshi_skill_target = line[41];
				zh.add(toAdd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check if a string is equal to another string. If not, return its int
	 * value, else, return null
	 * 
	 * @param str
	 *            string to test
	 * @param test
	 *            test condition
	 * @return
	 */
	static Integer returnIntValue(String str, String test) {
		if (str.equals(test)) {
			return null;
		} else {
			return Integer.parseInt(str);
		}
	}

	/**
	 * Get filename from url
	 * 
	 * @param str
	 *            url
	 * @return
	 */
	static String returnFileName(String prefix, String str) {
		if (str == null || str.equals("")) {
			return "";
		} else {
			return String.format("%s/%s", prefix, str.split("/")[7]);
		}
	}

	static String skillDamageTranslate(String str) {
		if (str == null || str.equals("－")) {
			return "";
		} else {
			StringBuilder sb = new StringBuilder(str.trim());
			int i = 0;
			int max = sb.length();
			while (i < max) {
				char c = sb.charAt(i);
				if (c == '約') {
					if (i == 0) {
						sb.replace(i, i + 1, "Approx. ");
					} else {
						sb.replace(i, i + 1, "approx. ");
					}
					i += 7;
					max += 7;
				} else if (c == '倍') {
					sb.replace(i, i + 1, " times");
					i += 5;
					max += 5;
				} else if (c == '：') {
					sb.replace(i, i + 1, ": ");
					i += 1;
					max += 1;
				} else if (c == '+') {
					sb.replace(i, i + 1, " + ");
					i += 2;
					max += 2;
				} else if (c == '×') {
					sb.replace(i, i+1, " ×");
					i += 1;
					max += 1;
				} else if (c == '—') {
					sb.deleteCharAt(i);
				}
				i++;
			}
			return sb.toString().replaceAll("親密度", " Affection Level");
		}
	}

	void merge() throws IllegalArgumentException {
		HashSet<Card> notInDatabase = new HashSet<Card>();
		for (Card card_zh : zh) {
			Card toAdd = new Card();
			toAdd = card_zh;
			toAdd.owner_id = 1;

			String card_name = card_zh.getOriginal();
			Card dengekiEquivalence = dengeki.get(card_name);
			if (dengekiEquivalence == null) {
				dengekiEquivalence = dengeki.get(translator.translatePhrase(card_name));
				if (dengekiEquivalence == null) {
					notInDatabase.add(card_zh);
				} else {
					toAdd.card_type = dengekiEquivalence.card_type;
					toAdd.action_skill_effects = dengekiEquivalence.action_skill_effects;
					toAdd.skill_comment = dengekiEquivalence.skill_comment;
					toAdd.skill_preview = returnFileName("hoshimori/static/uploaded/c/skill",
							dengekiEquivalence.skill_preview);
					toAdd.charge_name = dengekiEquivalence.charge_name;
					toAdd.charge_hit = dengekiEquivalence.charge_hit;
					toAdd.charge_damage = dengekiEquivalence.charge_damage;
					toAdd.charge_range = dengekiEquivalence.charge_range;
					toAdd.charge_comment = dengekiEquivalence.charge_comment;
				}
			}

			// Get images url
			toAdd.image = returnFileName("hoshimori/static/uploaded/c/icon", toAdd.image);
			toAdd.special_icon = returnFileName("hoshimori/static/uploaded/c/icon/special", toAdd.special_icon);
			toAdd.art = returnFileName("hoshimori/static/uploaded/c/art", toAdd.art);
			toAdd.special_front = returnFileName("hoshimori/static/uploaded/c/art/special", toAdd.special_front);
			toAdd.front_top = returnFileName("hoshimori/static/uploaded/c/art/front_top", toAdd.front_top);
			toAdd.front_bottom = returnFileName("hoshimori/static/uploaded/c/art/front_bottom", toAdd.front_bottom);
			toAdd.front_name = returnFileName("hoshimori/static/uploaded/c/art/front_name", toAdd.front_name);
			toAdd.front_rarity = returnFileName("hoshimori/static/uploaded/c/art/front_rarity", toAdd.front_rarity);
			toAdd.front_weapon = returnFileName("hoshimori/static/uploaded/c/art/front_weapon", toAdd.front_weapon);

			// Quick convert to int
			toAdd.i_card_type = Integer.parseInt(converter.translatePhrase(dengekiEquivalence.card_type));
			toAdd.i_character = Integer.parseInt(converter.translatePhrase(card_zh.character));
			toAdd.i_rarity = Integer.parseInt(converter.translatePhrase(card_zh.rarity));
			toAdd.i_weapon = Integer.parseInt(converter.translatePhrase(card_zh.weapon));
			if (card_zh.skill_affinity.equals("")) {
				toAdd.i_skill_affinity = 0;
			} else {
				toAdd.i_skill_affinity = Integer.parseInt(converter.translatePhrase(card_zh.skill_affinity));
			}

			// Translate name
			String[] splitted = card_zh.japanese_name.substring(1).split("】");
			String batch = splitted[0];
			if (translator.canTranslate(splitted[1])) {
				toAdd.name = String.format("【%s】 %s", translator.translatePhrase(batch),
						translator.translatePhrase(splitted[1]));
			} else if (splitted[1].endsWith(")")) {
				String[] splitted_twice = splitted[1].split("\\(");
				if (splitted_twice.length == 2) {
					toAdd.name = String.format("【%s】 %s (%s)", translator.translatePhrase(batch),
							translator.translatePhrase(splitted_twice[0]),
							translator.translatePhrase(splitted_twice[1].substring(0, splitted_twice[1].length() - 1)));
				} else {
					throw new IllegalArgumentException(String.format("Funny card format: %s", card_zh.japanese_name));
				}
			} else {
				throw new IllegalArgumentException(String.format("Funny card format: %s", card_zh.japanese_name));
			}

			toAdd.action_skill_damage = skillDamageTranslate(toAdd.action_skill_damage);
			toAdd.evolved_action_skill_damage = skillDamageTranslate(toAdd.evolved_action_skill_damage);

			database.put(toAdd.name, toAdd);
		}

		if (notInDatabase.size() > 0) {
			System.out.println("These cards are not shared by the two database, or there were some errors:");
			Iterator<Card> ite = notInDatabase.iterator();
			while (ite.hasNext()) {
				System.out.println(ite.next());
			}
		}

		translator.printUntranslated();
	}

	void writeCSV() {
		String fileName = "C:/Users/kokon/PycharmProjects/hoshimori_project/database/revamped_card_database.csv";

		try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
			writer.writeNext(FIELDS);
			for (Card card : database.values()) {
				writer.writeNext(card.csvFriendlyEntry());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CrawledDataCsvParser cdsp = new CrawledDataCsvParser();
		cdsp.merge();
		cdsp.writeCSV();
	}
}
