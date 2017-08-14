package kokonguyen191;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
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
			"evolved_action_skill_damage", "nakayoshi_title", "japanese_nakayoshi_title", "nakayoshi_skill_requirement",
			"nakayoshi_skill_effect", "nakayoshi_skill_target", "evolved_nakayoshi_skill_requirement",
			"evolved_nakayoshi_skill_effect", "evolved_nakayoshi_skill_target", "charge_comment", "charge_damage",
			"charge_hit", "charge_name", "charge_range" };

	private static Translator translator = new Translator(new Dictionary().getTranslationDict());
	private static Translator converter = new Translator(new Dictionary().getConversionDict());
	private static Translator replacerSkillDamage = new Translator(new Dictionary().getReplaceDict1());
	private static Translator replacerSkillRange = new Translator(new Dictionary().getReplaceDict2());

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
			///////////////////////////////////////////////////////////////
			// Input dengeki database path. Please put CSV as the format //
			///////////////////////////////////////////////////////////////
			dengekiDatabase = "E:/temp/java/card_database.csv";
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
				toAdd.nakayoshi_skill_effect = line[36];
				toAdd.evolved_nakayoshi_skill_effect = line[38];
				dengeki.put(toAdd.japanese_name, toAdd);
			}
			// Create zh database
			///////////////////////////////////////////////////////////////
			// Input zh database file path. Please put CSV as the format //
			///////////////////////////////////////////////////////////////
			zhDatabase = "E:/temp/java/zh_card_database.csv";
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
	 * @param prefix
	 *            static path
	 * @param str
	 *            url
	 * @param loc
	 *            location of image
	 * @return
	 */
	static String returnFileName(String prefix, String str, int loc) {
		if (str == null || str.equals("")) {
			return "";
		} else {
			return String.format("%s/%s", prefix, str.split("/")[loc]);
		}
	}

	/**
	 * Get filename from url
	 * 
	 * @param prefix
	 *            static path
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
		if (str == null || str.equals("－") || str.equals("—")) {
			return "";
		} else {
			StringBuilder sb = new StringBuilder(str.trim());
			int i = 0;
			int max = sb.length();
			boolean hasUnknownChar = false;
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
					sb.replace(i, i + 1, " x");
					i += 1;
					max += 1;
				} else if (c == '／') {
					sb.replace(i, i + 1, " / ");
					i += 2;
					max += 2;
				} else if (c == '日') {
					sb.replace(i, i + 1, "JPN");
					i += 2;
					max += 2;
				} else if (c == '台') {
					sb.replace(i, i + 1, "TWN");
					i += 2;
					max += 2;
				} else if (c == '值') {
					sb.deleteCharAt(i);
					i--;
					max--;
				} else if (c == '―') {
					sb.deleteCharAt(i);
					i--;
					max--;
				} else if (c == '（' || (c == '(' && i > 0 && sb.charAt(i - 1) != ' ')) {
					sb.replace(i, i + 1, " (");
					i += 1;
					max += 1;
				} else if (c == '）') {
					sb.setCharAt(i, ')');
				} else if (c == '，') {
					sb.replace(i, i + 1, ", ");
					i += 1;
					max += 1;
				} else if (c > 255) {
					if (c != '親' && c != '密' && c != '度') {
						hasUnknownChar = true;
					}
				}
				i++;
			}
			String result = replacerSkillDamage.replaceEverything(sb.toString());
			if (replacerSkillDamage.hasUntranslated(result)) {
				System.out.println(result);
			}
			return result;
		}
	}

	static String skillRangeTranslate(String str) {

		if (str == null || str.equals("－") || str.equals("―")) {
			return "";
		} else {
			StringBuilder sb = new StringBuilder(str.trim());
			int i = 0;
			int max = sb.length();
			boolean hasUnknownChar = false;
			while (i < max) {
				char c = sb.charAt(i);
				if (c == '：') {
					sb.replace(i, i + 1, ": ");
					i += 1;
					max += 1;
				} else if (c == '+') {
					sb.replace(i, i + 1, " + ");
					i += 2;
					max += 2;
				} else if (c == '×') {
					sb.replace(i, i + 1, " x");
					i += 1;
					max += 1;
				} else if (c == '／') {
					sb.replace(i, i + 1, " / ");
					i += 2;
					max += 2;
				} else if (c == '日') {
					sb.replace(i, i + 1, "JPN");
					i += 2;
					max += 2;
				} else if (c == '台') {
					sb.replace(i, i + 1, "TWN");
					i += 2;
					max += 2;
				} else if (c == '―' | c == '—') {
					sb.deleteCharAt(i);
					i--;
					max--;
				} else if (c == '（' || (c == '(' && i > 0 && sb.charAt(i - 1) != ' ')) {
					sb.replace(i, i + 1, " (");
					i += 1;
					max += 1;
				} else if (c == '）') {
					sb.setCharAt(i, ')');
				} else if (c == '，') {
					sb.replace(i, i + 1, ", ");
					i += 1;
					max += 1;
				} else if (c == '〜') {
					sb.setCharAt(i, '~');
				}
				i++;
			}
			String result = replacerSkillRange.replaceEverything(sb.toString().replace("範圍", ""));
			if (translator.hasUntranslated(result)) {
				StringBuilder sbsb = new StringBuilder(translator
						.linearSearchAndTranslate(replacerSkillRange.replaceEverything(sb.toString().replace("範圍", "")))
						.trim());
				sbsb.setCharAt(0, String.valueOf(sbsb.charAt(0)).toUpperCase().toCharArray()[0]);
				return sbsb.toString().replaceAll("\\( ", "(");
			} else {
				if (result.equals("")) {
					return result;
				} else {
					StringBuilder sbsb = new StringBuilder(result.trim());
					sbsb.setCharAt(0, String.valueOf(sbsb.charAt(0)).toUpperCase().toCharArray()[0]);
					return sbsb.toString().replaceAll("\\( ", "(");
				}
			}
		}
	}

	static int getNumber(String str) {
		return Integer.valueOf(str.replaceAll("\\D+", ""));
	}

	static int isThisAnEffectOrRequirementOrBoth(String str) {
		// 1 for effect, 0 for req, 2 for both
		if (str.startsWith("操") || str.contains("以")) {
			// Must be req
			if (str.contains("up") || str.contains("UP") || str.contains("down") || str.contains("DOWN")) {
				return 2;
			} else {
				return 0;
			}
		} else {
			return 1;
		}
	}

	static String[] splitRequirementAndEffect(String str) {
		if (isThisAnEffectOrRequirementOrBoth(str) == 2) {
			String[] result = new String[2];
			if (str.contains("で")) {
				result = str.split("で");
				return result;
			} else if (str.equals("5コンボ以降HP10％UP")) {
				result[0] = "5コンボ以降";
				result[1] = "HP10％UP";
				return result;
			} else if (str.endsWith("5コンボ以降HP13％UP")) {
				result[0] = "5コンボ以降";
				result[1] = "HP13％UP";
				return result;
			} else {
				throw new IllegalArgumentException(String.format("Unknown nakayoshi requirement and effect: %s", str));
			}
		} else {
			throw new IllegalArgumentException(String.format("Unknown nakayoshi requirement and effect: %s", str));
		}
	}

	static String getNakayoshiRequirement(String str) {
		if (isThisAnEffectOrRequirementOrBoth(str) == 0) {
			Integer requirement = null;
			// Check for requirement
			if (str.startsWith("残")) {
				// Time
				// Split into requirement and effect
				String[] splitted = str.split("で");
				requirement = getNumber(splitted[0]);
				// Get condition
				if (splitted[0].contains("下")) {
					// Requirement: <=
					return String.format("Remaining time less than or equal to %ss", requirement);
				} else if (splitted[0].contains("上")) {
					// Requirement: >=
					return String.format("Remaining time more than or equal to %ss", requirement);
				} else {
					// Requirement: ==
					return String.format("Remaining time is %ss", requirement);
				}
			} else if (str.startsWith("操")) {
				// HP
				// Split into requirement and effect
				String[] splitted = str.split("で");
				if (str.contains("50") || str.contains("1/2")) {
					requirement = 50;
				} else if (str.contains("75") || str.contains("3/4")) {
					requirement = 75;
				} else if (str.contains("25") || str.contains("1/4")) {
					requirement = 25;
				} else if (str.contains("100")) {
					requirement = 100;
				} else {
					throw new IllegalArgumentException(String.format("Unknown HP requirement :", str));
				}
				// Get condition
				if (splitted[0].contains("下")) {
					// Requirement: <=
					return String.format("Remaining HP less than or equal to %s%%", requirement);
				} else if (splitted[0].contains("上")) {
					// Requirement: >=
					return String.format("Remaining HP more than or equal to %s%%", requirement);
				} else {
					// Requirement: ==
					return String.format("Remaining HP is %s%%", requirement);
				}
			} else if (Character.isDigit(str.substring(0, 1).toCharArray()[0])) {
				// Combo
				// Split into requirement and effect
				String[] splitted = str.split("コ");
				requirement = getNumber(splitted[0]);
				// Get condition
				return String.format("After %s combo", requirement);
			}
			return "";
		} else {
			throw new IllegalArgumentException(String.format("Not a nakayoshi requirement: %s", str));
		}
	}

	static String getNakayoshiEffectType(String str) {
		if (isThisAnEffectOrRequirementOrBoth(str) == 1) {
			// Nullify types
			if (str.contains("無効")) {
				if (str.startsWith("SP")) {
					return "Nullify SP Damage";
				} else if (str.startsWith("マヒ")) {
					return "Nullify Paralysis";
				} else if (str.startsWith("毒")) {
					return "Nullify Poison";
				} else if (str.startsWith("スキル封印")) {
					return "Nullify Skill Seal";
				} else if (str.startsWith("スロウ")) {
					return "Nullify Slow";
				} else {
					throw new IllegalArgumentException("Unknown nakayoshi type: " + str);
				}
			} else {
				String head = str.substring(0, 1);
				String effectType = "";
				// Pass 1, convert irregular cases to regular cases
				switch (head) {
				// Irregular cases
				case "ブ": // Bu-Blade Cannon
					if (str.startsWith("ブレイドカノン")) {
						head = "";
						break;
					} else {
						throw new IllegalArgumentException("Unknown nakayoshi type: " + str);
					}
				case "銃": // Gun
				case "ロ": // Rod
				case "砲": // Blade Cannon
					// May go into a loop
					if (str.equals("銃")) {
						head = "";
						break;
					} else {
						head = str.split("は")[1].substring(0, 1);
						break;
					}
				case "杖": // Rod
					if (str.equals("杖")) {
						head = "";
						break;
					} else if (str.endsWith("オートリロード")) {
						head = "オ";
						break;
					} else {
						throw new IllegalArgumentException("Unknown nakayoshi type: " + str);
					}
				case "ガ":
					if (str.endsWith("オートリロード")) {
						head = "オ";
						break;
					} else if (str.equals("ガード貫通")) {
						head = "貫";
						break;
					} else if (str.equals("ガン")) {
						head = "";
						break;
					} else {
						throw new IllegalArgumentException("Unknown nakayoshi type: " + str);
					}
				case "た":
					if (str.equals("ただし")) {
						head = "";
						break;
					} else {
						head = str.substring(3, 4);
						break;
					}
				case "み":
					if (str.startsWith("みんなの")) {
						head = str.substring(4, 5);
					} else {
						throw new IllegalArgumentException("Unknown nakayoshi type: " + str);
					}
					break;
				default:
					break;
				}

				// Pass 2
				switch (head) {
				case "攻":
					effectType = "Attack Power";
					break;
				case "S":
					effectType = "SP";
					break;
				case "コ":
					effectType = "Combo Damage";
					break;
				case "ス":
					effectType = "Skill Combo";
					break;
				case "被":
					effectType = "Received Damage";
					break;
				case "H":
					effectType = "HP";
					break;
				case "弾":
					effectType = "Bullets";
					break;
				case "獲":
					if (str.contains("素材数")) {
						effectType = "Item Drops";
						break;
					} else if (str.contains("応援")) {
						effectType = "Cheerpoints";
						break;
					} else if (str.contains("絆")) {
						effectType = "Bonding Points";
						break;
					} else if (str.contains("コイン")) {
						effectType = "Coins";
						break;
					} else if (str.contains("経験値")) {
						effectType = "EXP";
						break;
					} else {
						throw new IllegalArgumentException(String.format("Unknown effect type: %s", str));
					}
				case "与":
					effectType = "Damage";
					break;
				case "オ":
					effectType = "Auto-reload";
					break;
				case "消":
					effectType = "SP Consumption";
					break;
				case "移":
					effectType = "Movement Rate";
					break;
				case "回":
					effectType = "Evade";
					break;
				case "宝":
					effectType = "Drop Retrieval Range";
					break;
				case "貫":
					effectType = "Guard Penetration";
					break;
				case "":
					break;
				default:
					throw new IllegalArgumentException(String.format("Unknown effect type: %s", str));
				}
				return effectType;
			}
		} else {
			throw new IllegalArgumentException(String.format("Not a nakayoshi effect: %s", str));
		}
	}

	static String[] nakayoshiEffectTranslate(String str) {
		// Null test
		if (str == null || str.equals("－") || str.equals("―") || str.equals(" ") || str.equals("")) {
			return new String[] { null, null };
		} else {
			String[] splitted = str.split("、");
			String requirement = null;
			ArrayList<String> effects = new ArrayList<String>();

			// Get requirement
			if (isThisAnEffectOrRequirementOrBoth(splitted[0]) == 0) {
				// If requirement
				requirement = getNakayoshiRequirement(splitted[0]);
			} else if (isThisAnEffectOrRequirementOrBoth(splitted[0]) == 2) {
				// Requirement with effect
				String[] reqEffSplitted = splitRequirementAndEffect(splitted[0]);
				requirement = getNakayoshiRequirement(reqEffSplitted[0]);
				effects.add(reqEffSplitted[1]);
			} else {
				effects.add(splitted[0]);
			}

			// Get only effects
			for (int i = 1; i < splitted.length; i++) {
				if (!getNakayoshiEffectType(splitted[i]).equals("")) {
					effects.add(splitted[i]);
				}
			}

			//////////////////////////////////////////////////////////////////

			HashMap<String, String> convertScale = new HashMap<String, String>();
			String[] scales = { "Damage_S,6%", "Damage_M,8%", "Damage_L,10%", "Damage_X,15%", "Combo Damage_S,10%",
					"Combo Damage_M,15%", "Combo Damage_L,20%", "Combo Damage_X,30%", "SP Consumption_S,5%",
					"SP Consumption_M,10%", "SP Consumption_L,15%", "SP Consumption_X,20%", "Coins_S,50%",
					"Coins_M,100%", "Cheerpoints_S,50%", "Cheerpoints_M,100%", "EXP_S,50%", "EXP_M,100%",
					"Bonding Points_S,25%", "Bonding Points_M,50%", "Movement Rate_M,(M)", "Movement Rate_L,(L)",
					"Drop Retrieval Range_S,(S)", "Drop Retrieval Range_M,(M)", "Item Drops_M,1",
					"Received Damage_M,15%", "Received Damage_S,10%", };

			for (String scale : scales) {
				String[] splittedScale = scale.split(",");
				convertScale.put(splittedScale[0], splittedScale[1]);
			}

			//////////////////////////////////////////////////////////////////
			// Translate
			LinkedList<String> resultLL = new LinkedList<String>();
			for (int i = 0; i < effects.size(); i++) {
				boolean positiveEffect = true;
				String effect = effects.get(i);
				if (effect.contains("UP") || effect.contains("up") || effect.contains("+")) {
					positiveEffect = true;
				} else if (effect.contains("DOWN") || effect.contains("down") || effect.contains("-") || effect.contains("−") || effect.contains("‐")) {
					positiveEffect = false;
				} else {
					positiveEffect = true;
				}

				String skillType = getNakayoshiEffectType(effect);
				Integer value;

				// Non value
				if (effect.contains("無効") || effect.contains("貫") || skillType.equals("Auto-reload")) {
					value = null;
				} else {
					try {
						value = getNumber(effect);
						if (effect.contains("％")) {
							resultLL.add(String.format("%s +%d%%", skillType, value));
						} else {
							resultLL.add(String.format("%s +%d", skillType, value));
						}
					} catch (NumberFormatException e) {
						String valueDegree;
						String memo;
						if (effect.contains("小")) {
							memo = "S";
							valueDegree = String.format("%s_S", skillType);
						} else if (effect.contains("超")) {
							memo = "X";
							valueDegree = String.format("%s_X", skillType);
						} else if (effect.contains("大")) {
							memo = "L";
							valueDegree = String.format("%s_L", skillType);
						} else {
							memo = "M";
							valueDegree = String.format("%s_M", skillType);
						}
						String stringValue = convertScale.get(valueDegree); // Get
																			// value
																			// from
						// text
						if (stringValue == null) {
							throw new IllegalArgumentException(String.format("Cannot parse nakayoshi: %s", effect));
						} else {
							if (positiveEffect) {
								resultLL.add(String.format("%s +%s", skillType, stringValue));
							} else {
								resultLL.add(String.format("%s -%s", skillType, stringValue));
							}
						}
					}
				}
			}

			String[] finalResult = new String[2];
			finalResult[0] = requirement;
			finalResult[1] = String.join(", ", resultLL);

			return finalResult;
		}
	}

	void merge() throws IllegalArgumentException {
		HashSet<Card> notInDatabase = new HashSet<Card>();
		for (Card card_zh : zh) {
			try {
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
								dengekiEquivalence.skill_preview, 8);
						toAdd.charge_name = dengekiEquivalence.charge_name;
						toAdd.charge_hit = dengekiEquivalence.charge_hit;
						toAdd.charge_damage = dengekiEquivalence.charge_damage;
						toAdd.charge_range = dengekiEquivalence.charge_range;
						toAdd.charge_comment = dengekiEquivalence.charge_comment;
						toAdd.nakayoshi_skill_effect = dengekiEquivalence.nakayoshi_skill_effect;
						toAdd.evolved_nakayoshi_skill_effect = dengekiEquivalence.evolved_nakayoshi_skill_effect;
					}
				} else {
					toAdd.card_type = dengekiEquivalence.card_type;
					toAdd.action_skill_effects = dengekiEquivalence.action_skill_effects;
					toAdd.skill_comment = dengekiEquivalence.skill_comment;
					toAdd.skill_preview = returnFileName("hoshimori/static/uploaded/c/skill",
							dengekiEquivalence.skill_preview, 8);
					toAdd.charge_name = dengekiEquivalence.charge_name;
					toAdd.charge_hit = dengekiEquivalence.charge_hit;
					toAdd.charge_damage = dengekiEquivalence.charge_damage;
					toAdd.charge_range = dengekiEquivalence.charge_range;
					toAdd.charge_comment = dengekiEquivalence.charge_comment;
					toAdd.nakayoshi_skill_effect = dengekiEquivalence.nakayoshi_skill_effect;
					toAdd.evolved_nakayoshi_skill_effect = dengekiEquivalence.evolved_nakayoshi_skill_effect;
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
								translator.translatePhrase(splitted_twice[0]), translator.translatePhrase(
										splitted_twice[1].substring(0, splitted_twice[1].length() - 1)));
					} else {
						throw new IllegalArgumentException(
								String.format("Funny card format: %s", card_zh.japanese_name));
					}
				} else {
					throw new IllegalArgumentException(String.format("Funny card format: %s", card_zh.japanese_name));
				}

				// Translate obtaining method
				String obtainingMethod = toAdd.obtain_method;
				if (translator.canTranslate(obtainingMethod)) {
					toAdd.obtain_method = translator.translatePhrase(obtainingMethod);
				} else {
					if (obtainingMethod.startsWith("限定")) { // Limited gacha
						String[] obtaining_methods = obtainingMethod.split("限定");
						obtainingMethod = "";
						LinkedList<String> oM = new LinkedList<String>();
						for (String method : obtaining_methods) {
							if (!method.equals("")) {
								method = method.substring(1, method.length() - 1);
								method = method.replace("(1)", "");
								method = method.replace("(2)", "");
								oM.add(translator.translatePhrase(method));
							}
						}
						obtainingMethod = String.join(" and ", oM);
						toAdd.obtain_method = obtainingMethod;
					} else if (obtainingMethod.startsWith("常設")) { // Permanent
																	// gacha
						StringBuilder sbOM = new StringBuilder(obtainingMethod);
						int max = sbOM.length();
						for (int i = 0; i < max; i++) {
							if (sbOM.charAt(i) == '常' || sbOM.charAt(i) == '設') {
								sbOM.deleteCharAt(i);
								i--;
								max--;
							} else if (sbOM.charAt(i) == '「' || sbOM.charAt(i) == '」') {
								sbOM.setCharAt(i, '\'');
							} else if (sbOM.charAt(i) == '(' && sbOM.charAt(i - 1) != ' ') {
								sbOM.replace(i, i + 1, " (");
								i++;
								max++;
							} else if (sbOM.charAt(i) == '〜') {
								sbOM.setCharAt(i, '~');
							}
						}
						String[] obtainingMethods = sbOM.toString().split("'");
						obtainingMethods[0] = "Permanent Gacha: ";
						obtainingMethods[1] = translator.translatePhrase(obtainingMethods[1]);
						toAdd.obtain_method = String.join("'", obtainingMethods);
					} else if (obtainingMethod.startsWith("活動")) { // Special
						toAdd.obtain_method = translator
								.translatePhrase(obtainingMethod.substring(3, obtainingMethod.length() - 1));
					} else {
						toAdd.obtain_method = translator.translatePhrase(obtainingMethod);
					}
				}

				// Get action skill effects
				String action_skill_effects = toAdd.action_skill_effects;
				if (action_skill_effects.equals("武器相性無視") || action_skill_effects.equals("苦手無視")
						|| action_skill_effects.equals("―")) {
					toAdd.action_skill_effects = null;
				}

				String[] actionSkillSplitted = action_skill_effects.split("／");
				LinkedList<String> actionSkillTemp = new LinkedList<String>();
				if (actionSkillSplitted.length > 1) {
					for (int i = 1; i < actionSkillSplitted.length; i++) {
						actionSkillTemp.add(actionSkillSplitted[i]);
					}
					toAdd.action_skill_effects = String.join(" / ", actionSkillTemp);
				}

				String[] actionSkillSplitted2 = action_skill_effects.split("\n");
				LinkedList<String> actionSkillTemp2 = new LinkedList<String>();
				if (actionSkillSplitted2.length > 1) {
					for (int i = 1; i < actionSkillSplitted2.length; i++) {
						if (!actionSkillSplitted2[i].equals("武器相性無視") && !actionSkillSplitted2[i].equals("苦手無視")) {
							actionSkillTemp2.add(actionSkillSplitted2[i]);
						}
					}
					toAdd.action_skill_effects = String.join("\n", actionSkillTemp2);
				}

				// Translate action skill damage
				toAdd.action_skill_damage = skillDamageTranslate(toAdd.action_skill_damage);
				toAdd.evolved_action_skill_damage = skillDamageTranslate(toAdd.evolved_action_skill_damage);

				// Translate action skill range
				toAdd.skill_range = skillRangeTranslate(toAdd.skill_range);

				// Translate charge
				toAdd.charge_damage = skillDamageTranslate(toAdd.charge_damage);
				if (toAdd.charge_range.equals("近：全方位・範囲小、中：前方・範囲小、遠：前方・直線")) {
					toAdd.charge_range = "Close: All directions small range - Mid: Front small range - Far: Front straight line";
				} else if (toAdd.charge_range.equals("前方・直線")) {
					toAdd.charge_range = "Front straight line";
				} else if (toAdd.charge_range.equals("遠：前方・直線、中：前方・範囲小、近：全方位・範囲小")) {
					toAdd.charge_range = "Close: All directions small range - Mid: Front small range - Far: Front straight line";
				}
				if (toAdd.charge_hit.equals("残弾数×1")) {
					toAdd.charge_hit = "Number of bullets x1";
				}

				// Translate nakayoshi target
				if (!toAdd.nakayoshi_skill_target.equals("") && !toAdd.nakayoshi_skill_target.equals(" ")) {
					StringBuilder sbNaka = new StringBuilder(toAdd.nakayoshi_skill_target);
					for (int i = 0; i < sbNaka.length(); i++) {
						if (sbNaka.charAt(i) == '與' || sbNaka.charAt(i) == '、') {
							sbNaka.setCharAt(i, ',');
						}
					}
					toAdd.nakayoshi_skill_target = translator.translateSentence(sbNaka.toString(), ",");
				}

				if (!toAdd.evolved_nakayoshi_skill_target.equals("")
						&& !toAdd.evolved_nakayoshi_skill_target.equals(" ")) {
					StringBuilder sbNakaEvolved = new StringBuilder(toAdd.evolved_nakayoshi_skill_target);
					for (int i = 0; i < sbNakaEvolved.length(); i++) {
						if (sbNakaEvolved.charAt(i) == '與' || sbNakaEvolved.charAt(i) == '、') {
							sbNakaEvolved.setCharAt(i, ',');
						}
					}
					toAdd.evolved_nakayoshi_skill_target = translator.translateSentence(sbNakaEvolved.toString(), ",");
				}

				// Translate nakayoshi
				String[] translated_nakayoshi_skill_effect = nakayoshiEffectTranslate(toAdd.nakayoshi_skill_effect);
				String[] translated_evolved_nakayoshi_skill_effect = nakayoshiEffectTranslate(
						toAdd.evolved_nakayoshi_skill_effect);

				toAdd.nakayoshi_skill_requirement = translated_nakayoshi_skill_effect[0];
				toAdd.nakayoshi_skill_effect = translated_nakayoshi_skill_effect[1];
				toAdd.evolved_nakayoshi_skill_requirement = translated_evolved_nakayoshi_skill_effect[0];
				toAdd.evolved_nakayoshi_skill_effect = translated_evolved_nakayoshi_skill_effect[1];

				// Add to database
				database.put(toAdd.name, toAdd);
			} catch (Exception e) {
				System.err.println(card_zh);
				e.printStackTrace();
			}
		}

		if (notInDatabase.size() > 0)

		{
			System.out.println("These cards are not shared by the two database, or there were some errors:");
			Iterator<Card> ite = notInDatabase.iterator();
			while (ite.hasNext()) {
				System.out.println(ite.next());
			}
		}

		converter.printUntranslated();
		translator.printUntranslated();
	}

	void writeCSV() {
		////////////////////////////////////////////////////
		// Output file path. Please put CSV as the format //
		////////////////////////////////////////////////////
		String fileName = "E:/temp/java/output.csv";

		try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
			writer.writeNext(FIELDS);
			for (Card card : database.values()) {
				try {
					writer.writeNext(card.csvFriendlyEntry());
				} catch (Exception e) {
					System.err.println(card);
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		StopWatch sw = new StopWatch();
		sw.reset();
		sw.start();
		CrawledDataCsvParser cdsp = new CrawledDataCsvParser();
		cdsp.merge();
		cdsp.writeCSV();
		sw.stop();
		System.out.println();
		System.out.println("=============================================");
		System.out.println("=============================================");
		System.out.println(String.format("==New file written. Total time:%ss==", sw.getTime()));
		System.out.println("=============================================");
		System.out.println("=============================================");
		System.out.println();
		// CrawledDataCsvParser cd = new CrawledDataCsvParser();
		// String[] haha = { "攻撃力5％UP", "SPが9％UP", "コンボダメージ（大）", "スキルコンボ＋1",
		// "残り時間60秒以下で消費SPdown（大）", "被ダメdown（小）",
		// "HP5％UP", "攻撃力が2％UP！", "スキルコンボ−1", "SPが7％UP！", "コンボダメ（大）UP",
		// "HP10％UP", "コンボダメup（大）", "銃と杖はオートリロード",
		// "コンボダメージUP（大）", "残り時間90秒以下で攻撃力8％UP", "被ダメージdown(小)", "コンボダメUP（小）",
		// "残り時間60秒以下で与ダメージUP（大）", "弾数＋2",
		// "コンボダメージUP（小）", "被ダメージdown", "攻撃力4％UP＆コンボダメup（小）", "みんなのスキルコンボ数-1",
		// "コンボダメup＆SPが6％UP！", "コンボダメUP（大）",
		// "攻撃10％UP", "獲得コインUP（小）", "ただし", "攻撃力1％UP！", "コンボダメup（小）",
		// "スキルコンボ数‐1", "獲得応援ptUP（小）", "与ダメージ（大）",
		// "攻撃力10％UP", "残り時間60秒以下でコンボダメUP（大）", "攻撃力が4％UP", "SPダメージ無効",
		// "SPが5％UP！", "HP13％UP",
		// "操作キャラHP100％で攻撃力7％UP", "オートリロード", "操作キャラHP100％で与ダメUP（超）", "攻撃力7％UP",
		// "ロッドとガンはオートリロード", "与ダメUP",
		// "操作キャラHP75％以上で", "コンボダメUP（超）", "消費Spdown（大）", "HPが2％UP！",
		// "コンボダメージup(小)", "ただしHP10％DOWN", "SPが7％UP",
		// "SPが30％UP！", "残り時間60秒以下でコンボダメUP（超）", "操作キャラのHP50％以上でコンボダメUP",
		// "SP4％UP", "残り時間120秒以下で",
		// "コンボダメup＆攻撃力3％UP！", "ただし消費SPup（小）", "攻撃力4％UP", "獲得経験値UP（小）",
		// "操作キャラのHP1/2以下で", "ガード貫通", "15コンボ以降",
		// "残時間90秒以下でSP20％UP", "砲剣は弾数＋2", "与ダメ（小）UP", "12コンボ以降", "SP30％UP",
		// "銃と杖と砲剣は弾数＋2", "HPが6％UP！", "攻撃力が4％UP！",
		// "ガンとロッドはオートリロード", "獲得素材数UP", "残り時間90以下で攻撃力10％down", "銃と杖と砲剣はオートリロード",
		// "残り時間60秒以下でコンボダメージUP（大）",
		// "HP15％UP", "コンボダメージUP", "コンボダメージup", "コンボダメUP", "コンボダメup",
		// "ロッドとガンは弾数＋2", "攻撃力1％UP", "SPが9％UP！",
		// "HP20％UP", "SPが6％UP！", "獲得素材数＋1", "SP9％UP", "移動性能UP", "移動性能up",
		// "攻撃力が3％UP！", "攻撃力7％", "HPが8％UP！",
		// "操作キャラHP1/4以下で", "与ダメージUP（超）", "マヒ無効", "杖と銃オートリロード",
		// "残り時間60秒以下で攻撃力4％UP", "スロウ無効", "与ダメUP（超）",
		// "消費SPdown（大）", "HP9％UP", "獲得絆ptUP（小）", "SP6％UP", "攻撃力が5％UP",
		// "SP20％UP", "5コンボ以降", "残時間90秒以下で攻撃10％UP",
		// "攻撃力が1％UP！", "残り時間90秒以下で攻撃力10％down", "スキル封印無効", "回避数＋1", "攻撃力6％UP！",
		// "操作キャラHP75％以上でコンボダメUP（大）", "回避数＋2",
		// "攻撃力6％UP", "ただし被ダメージup", "毒無効", "攻撃3％UP", "SPが4％UP！",
		// "操作キャラHP75％以上でコンボダメUP", "与ダメージUP", "与ダメージup",
		// "攻撃力6％", "宝箱回収性能UP（小）", "残時間60秒以下で与ダメージUP（大）", "ブレイドカノン",
		// "残り時間60秒以下で攻撃力7％UP", "残り時間90秒以下で攻撃力7％UP",
		// "10コンボ以降", "消費SPdown（小）", "攻撃力3％UP", "操作キャラHP1/2以下で", "ガン",
		// "HPが3％UP！", "砲銃は弾数＋3", "コンボダメ（大）",
		// "コンボダメージup（大）", "残り時間60秒以下で与ダメUP（大）", "HP7％UP", "SP12％UP", "攻撃力5％",
		// "SP15％UP", "被ダメdown",
		// "残り時間60秒以下で攻撃力6％UP", "5コンボ以降HP10％UP", "操作キャラHP50％以上で", "ロッドは弾数＋3",
		// "11コンボ以降", "消費Spdown",
		// "残り時間90秒以下で攻撃力6％UP", "杖と銃はオートリロード", "回避回数＋1", "HPが8％UP",
		// "与ダメージUP（大）", "操作キャラHP100％で攻撃力8％UP",
		// "操作キャラHP75％以上で攻撃力7％UP", "攻撃力8％UP", "残り時間90秒以下でコンボダメUP（超）",
		// "移動性能UP（大）", "攻撃力が5％UP！", "与ダメUP（小）",
		// "消費SPdown（超）", "攻撃力4％", "残り時間90秒以下で与ダメUP（超）", "攻撃5％UP", "HP2％UP",
		// "与ダメUP（大）", "宝箱獲得素材数UP",
		// "みんなの消費SPdown", "スキルコンボ数−1", "操作キャラHP100％で",
		// "操作キャラのHP75％以上でコンボダメUP（大）", "与ダメージUP（小）", "消費SPdown",
		// "残り時間60秒以下で攻撃力10％UP", };
		// for (String str : haha) {
		// System.out.println(cd.nakayoshiEffectTranslate(str));
		// }
	}
}
