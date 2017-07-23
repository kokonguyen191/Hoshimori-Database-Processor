package kokonguyen191;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.ibm.icu.text.Transliterator;

import java.util.HashSet;

public class Translator {

	private HashMap<String, String> dict;
	HashSet<String> untranslated;
	public LinkedList<String> keySet;
	

	private void generate(String[] raw) {
		for (String entry : raw) {
			String[] splitted = entry.split(",");
			dict.put(splitted[0], splitted[1]);
			keySet.add(splitted[0]);
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

	String translateSentence(String sen, String delimiter) {
		StringBuilder sb = new StringBuilder();
		for (String word : sen.split(delimiter)) {
			sb.append(translatePhrase(word));
			sb.append(delimiter);
			sb.append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	String linearSearchAndTranslate(String str) {
		if (str.equals("")) {
			return str;
		} else {
			StringBuilder sb = new StringBuilder(str);
			if (sb.length() == 1) {
				return sb.charAt(0) > 255 ? str : "";
			} else if (sb.length() == 2) {
				if (sb.charAt(0) > 255 && sb.charAt(1) > 255) {
					return str;
				} else if (sb.charAt(0) > 255) {
					return String.valueOf(sb.charAt(0));
				} else if (sb.charAt(1) > 255) {
					return String.valueOf(sb.charAt(1));
				} else {
					return "";
				}
			} else {
				ArrayList<Integer> memo = new ArrayList<Integer>();
				LinkedList<String> result = new LinkedList<String>();
				if (sb.charAt(0) > 255) {
					memo.add(0);
				}
				for (int i = 1; i < sb.length(); i++) {
					if (((double) sb.charAt(i) - 255.5) * ((double) sb.charAt(i - 1) - 255.5) < 0) {
						memo.add(i);
					}
				}
				if (memo.get(memo.size() - 1) != sb.length()) {
					memo.add(sb.length());
				}
				for (int i = 0; i < memo.size() - 1; i++) {
					String temp = sb.substring(memo.get(i), memo.get(i + 1));
					if (i % 2 == 0) {
						temp = translatePhrase(temp);
					}
					result.add(temp);
				}
				return String.join("", result);
			}
		}
	}

	// Don't use this too much
	String replaceEverything(String str) {
		for (String key : keySet) {
			str = str.replaceAll(key, dict.get(key));
		}
		hasUntranslated(str);
		return str;
	}

	boolean canTranslate(String str) {
		return dict.containsKey(str);
	}

	boolean hasUntranslated(String str) {
		if (str.equals("")) {
			return false;
		} else {
			StringBuilder sb = new StringBuilder(str);
			if (sb.length() == 1) {
				if (sb.charAt(0) > 255) {
					untranslated.add(sb.toString());
					return true;
				} else {
					return false;
				}
			} else if (sb.length() == 2) {
				if (sb.charAt(0) > 255 && sb.charAt(1) > 255) {
					untranslated.add(sb.toString());
					return true;
				} else if (sb.charAt(0) > 255) {
					untranslated.add(String.valueOf(sb.charAt(0)));
					return true;
				} else if (sb.charAt(1) > 255) {
					untranslated.add(String.valueOf(sb.charAt(1)));
					return true;
				} else {
					return false;
				}
			} else {
				ArrayList<Integer> memo = new ArrayList<Integer>();
				boolean result = false;
				if (sb.charAt(0) > 255) {
					memo.add(0);
				}
				for (int i = 1; i < sb.length(); i++) {
					if (((double) sb.charAt(i) - 255.5) * ((double) sb.charAt(i - 1) - 255.5) < 0) {
						memo.add(i);
					}
				}
				if (memo.size() == 0) {
					return false;
				} else {
					if (memo.get(memo.size() - 1) != sb.length()) {
						memo.add(sb.length());
					}
					for (int i = 0; i < memo.size() - 1; i++) {
						String temp = sb.substring(memo.get(i), memo.get(i + 1));
						if (i % 2 == 0) {
							untranslated.add(temp);
						}
						result = true;
					}
					return result;
				}
			}
		}
	}

	void printUntranslated() {
		if (!untranslated.isEmpty()) {
			System.out.println("=====================================================");
			System.out.println("=====================================================");
			System.out.println("==These words or sentences haven't been translated:==");
			System.out.println("=====================================================");
			System.out.println("=====================================================");
			System.out.println();
			Iterator<String> ite = untranslated.iterator();
			while (ite.hasNext()) {
				System.out.println(ite.next());
			}
		}
	}

	public Translator(String[] raw) {
		untranslated = new HashSet<String>();
		dict = new HashMap<String, String>();
		keySet = new LinkedList<String>();
		generate(raw);
	}

	public static void main(String[] args) {
		// Dictionary dict = new Dictionary();
		// String[] rawDict = dict.rawDict;
		// Translator tran = new Translator(rawDict);
		//
		// // Paste shit to translate here
		// String[] sentences = { "主線 初始卡片", "主線 第1部 第1章 1-1", "主線 第1部 第2章
		// 10-1", "主線 第1部 第2章 9-4", "主線 第1部 第2章 7-1",
		// "主線 第1部 第1章 4-4", "主線 第1部 第2章 8-7", "主線 第1部 第2章 11-4", "主線 第1部 第2章
		// 6-4", "主線 第1部 第2章 11-1",
		// "主線 第1部 第2章 7-4", "通常轉蛋", "通常轉蛋(～2017/06/20 14:59)",
		// "首次：通常轉蛋（至2015年6月30日17:59）復刻：通常轉蛋（至2015年11月30日14:59）", "通常轉蛋卡片交換所",
		// "通常轉蛋（至2015年11月13日14:59）",
		// "限定轉蛋（至2015年7月31日14:59）", "活動「星守定期テスト～謎のイロウス対話編～」",
		// "活動「利用者200万人感謝キャンペーン 第3弾」",
		// "限定轉蛋（至2015年9月30日 14:59）", "限定「浴衣轉蛋」",
		// "限定「ハロウィン記念ガチャ」限定「復刻ハロウィン記念ガチャ」", "主線 第1部 第6章 44-7",
		// "限定「アイドル記念ガチャ」限定「復刻アイドル記念ガチャ(1)(2)」", "活動「わたしたちのスタートライン！ 後編」",
		// "限定「星守女僕轉蛋」", "宿題",
		// "主線關卡「第48話 笑容的前方所看見的東西」", "限定「白色Xmas記念轉蛋」", "限定「聖夜記念轉蛋」",
		// "限定「平安夜記念轉蛋」", "活動「第1部総集編 Part1 二人の出会い」",
		// "限定「迎春記念ガチャ」", "活動「新年のごあいさつ」", "限定「アイドル「蒼」記念ガチャ」限定「復刻アイドル「蒼」記念ガチャ」",
		// "活動「第1部総集編 Part2 16人ならできること」",
		// "限定「スウィートチョコ記念ガチャ」", "限定「ビターチョコ記念ガチャ」", "限定「スター記念ガチャ」",
		// "限定「湯けむり記念ガチャ」", "活動「秘密の合言葉を探せキャンペーン」",
		// "活動「アイドル応援キャンペーン」", "限定「アイドル「ＲＥＤ」記念ガチャ」", "限定「星守アイドル記念ガチャ」",
		// "常設「第2部ストーリーガチャ」", "主線 第2部 第3章 60-7",
		// "常設「デビューガチャ」", "限定「5月4日バースデー記念ガチャ」", "限定「湯けむり記念ガチャ2」",
		// "活動「ほしもり湯けむりロマン紀行 後編」", "限定「永遠の誓い記念ガチャ」",
		// "限定「6月3日バースデー記念ガチャ」", "限定「ヴァージンロード記念ガチャ」", "限定「6月26日バースデー記念ガチャ」",
		// "限定「ミッドナイト記念ガチャ」",
		// "限定「7月7日バースデー記念ガチャ」", "限定「アイランド記念ガチャ」", "限定「夏祭り’16記念ガチャ」",
		// "限定「8月14日バースデー記念ガチャ」", "限定「湯けむり記念ガチャ3」",
		// "限定「8月28日バースデー記念ガチャ」", "限定「お月見記念ガチャ」", "常設「第2部クライマックスガチャ」",
		// "活動「シルバーウィーク限定ログインボーナスチャレンジ」",
		// "限定「9月23日バースデー記念ガチャ」", "限定「9月30日バースデー記念ガチャ」", "限定「眠れぬ夜の記念ガチャ」",
		// "限定「10月2日バースデー記念ガチャ」",
		// "限定「おやすみなさい記念ガチャ」", "活動「ハロウィンログインボーナスチャレンジ」", "限定「トリック・ナイト記念ガチャ」",
		// "限定「トリート・ナイト記念ガチャ」",
		// "限定「10月24日バースデー記念ガチャ」", "限定「学園祭OPEN記念ガチャ」", "限定「11月1日バースデー記念ガチャ」",
		// "限定「もっともっと!!学園祭記念ガチャ」",
		// "活動「合同学園祭記念 ログインボーナスチャレンジ」", "獎勵交換所2016/11/25 14:00 ~ 2016/12/14
		// 14:59(UTC+9)", "限定「内緒の約束記念ガチャ」",
		// "限定「12月7日バースデー記念ガチャ」", "限定「300万人突破記念ガチャ」", "限定「ときめきウィンター記念ガチャ」",
		// "限定「ジングルベル記念ガチャ」",
		// "限定「12月24日バースデー記念ガチャ」", "活動「年末年始 ログインボーナスチャレンジ」", "限定「賀春記念ガチャ」",
		// "限定「エンジェルの癒し記念ガチャ」",
		// "限定「1月18日バースデー記念ガチャ」", "活動「2017年スタート記念 ログインボーナスチャレンジ」",
		// "限定「台湾バトガコラボ記念ガチャ」", "限定「ほんとのキモチは…記念ガチャ」",
		// "限定「2月3日バースデー記念ガチャ」", "限定「とろける想い記念ガチャ」", "限定「白銀の想い出記念ガチャ」",
		// "活動「儚き冬の恋心 ログインボーナスチャレンジ」",
		// "限定「手料理めしあがれ記念ガチャ」", "限定「ホワイトセレクション記念ガチャ」", "限定「3月15日バースデー記念ガチャ」",
		// "常設「清律音楽学院ガチャ」",
		// "活動「かけがえのないトキ ログインボーナスチャレンジ」", "限定「3月27日バースデー記念ガチャ」", "主線 第3部 第2章
		// 100-7", "常設「第3部ガチャ」",
		// "常設「星守ガチャ」2017/04/04 15:00 ~ 06/09 14:59(UTC+9)",
		// "常設「星守ガチャ」2017/04/04 15:00 ~ 05/30 14:59(UTC+9)",
		// "常設「星守ガチャ」2017/04/04 15:00 ~ 06/20 14:59(UTC+9)",
		// "限定「4月15日バースデー記念ガチャ」",
		// "常設「星守ガチャ」2017/04/17 15:00 ~ 06/09 14:59(UTC+9)",
		// "常設「星守ガチャ」2017/04/17 15:00 ~ 05/30 14:59(UTC+9)",
		// "常設「星守ガチャ」2017/04/17 15:00 ~ 06/20 14:59(UTC+9)",
		// "限定「2017年昴誕生日記念ガチャ」", "限定「星守メモリーズガチャ 第1弾」",
		// "常設「第3部ガチャ」2017/05/25 00:00〜23:59(UTC+9)", "常設「星守ガチャ」2017/05/30 15:00
		// ~ (UTC+9)",
		// "常設「星守ガチャ」2017/05/30 15:00 ~ 06/30 14:59 (UTC+9)",
		// "限定「2017年ミシェル誕生日記念ガチャ」", "限定「星守メモリーズガチャ 第2弾」",
		// "常設「星守ガチャ」2017/06/09 15:00 ~ 06/30 14:59 (UTC+9)",
		// "常設「星守ガチャ」2017/06/09 15:00 ~ (UTC+9)",
		// "限定「星守メモリーズガチャ 第3弾」", "常設「星守ガチャ」2017/06/20 15:00 ~ (UTC+9)",
		// "通常轉蛋(2017/06/20 15:00～)",
		// "常設「第3部ガチャ」2017/06/23 15:00〜06/25 23:59(UTC+9)",
		// "限定「2017年蓮華誕生日記念ガチャ」",
		// "常設「星守ガチャ」2017/06/30 15:00 ~ (UTC+9)", "活動「アニメ放送記念ログインボーナスチャレンジ」",
		// "限定「2017年望誕生日記念ガチャ」" };
		//
		// for (String sen : sentences) {
		// sen = sen.replace('與', ',');
		// sen = sen.replace('、', ',');
		// System.out.println(tran.translatePhrase(sen));
		// }
		// tran.printUntranslated();
		Dictionary dict = new Dictionary();
		String[] rawDict = dict.rawDict;
		Translator tran = new Translator(rawDict);
		String[] me = { "全方位特大範圍×2",
				"以自身作起點小範圍×5",
				"全方位特大範圍×3",
				"移動型極小範圍",
				"全方位中範圍×5",
				"全方位中範圍×8",
				"前方直線×2",
				"1〜25combo：全方位小範圍",
				"26〜38combo：全方位大範圍",
				"39combo以後：全方位特大範圍",
				"前方n型（反轉的U型）",
				"全方位中範圍×2",
				"右&前&左&後方直線",
				"全方位中範圍×3",
				"自身",
				"前方小範圍（移動時瞄準敵人）",
				"—",
				"前方直線×5",
				"前方極小範圍",
				"前方直線超長距離(T字形)",
				"前方中範圍",
				"全方位特大範圍×4",
				"前方極小範圍×2",
				"前方&右方&左方直線",
				"右前方小範圍",
				"全方位極小範圍（炸彈：全方位中範圍）",
				"自身周邊中範圍×9",
				"敵人中心小範圍（距離限制）",
				"全方位中範囲",
				"敵人的中心極小範圍x2",
				"直線十字4個方向（大範圍）",
				"全方位大範圍×3",
				"敵人中心中範圍×1",
				"普通攻擊：全方位小範圍",
				"降低敵人攻擊力：全方位中範圍",
				"降低敵人防御力：全方位中範圍",
				"我方全員",
				"全方位特大範圍",
				"前方直線長距離(自動追蹤)",
				"前方中範圍×2",
				"前面直線",
				"全方位小範圍（每段攻擊有2次傷害）",
				"前方直線（追蹤型）",
				"全方位大範圍",
				"左前方&右前方小範圍×3",
				"直線十字4個方向（自動追蹤）",
				"左方小範圍",
				"自身五個方位小範圍",
				"敵人的中心中範圍×4",
				"自身四周中範圍",
				"前方直線三方向",
				"前方直線",
				"移動至敵人的中心中範圍",
				"落地點小範圍",
				"前方特大範圍",
				"敵人的中心中範圍",
				"敵人中心小範圍×3",
				"全方位中範圍x2",
				"敵人中心小範圍×9",
				"全方位小範圍",
				"右方小範圍",
				"全方位中範圍",
				"移動至敵人中心小範圍",
				"敵人中心中範圍",
				"自身前方直線超長距離",
				"前方直線（追蹤型）×2",
				"自身中心小範圍(移動型)",
				"移動至敵人中心極小範圍",
				"直線十字4個方向×3（每段攻擊有2次傷害）",
				"自身前方中範圍",
				"前方直線長距離",
				"四周小範圍×8",
				"木桶位置的中範圍",
				"前方極小範圍（移動時瞄準敵人）×3",
				"以自身作起點中範圍",
				"前方極小範圍(追蹤移動型)",
				"自身左方和右方小範圍×3",
				"敵人的中心小範圍×5",
				"以自身作起點中範圍×5",
				"以自身作起點中範圍×4",
				"全方位小範圍×3",
				"全方位小範圍×9",
				"敵人的中心小範圍×9",
				"敵人的中心極小範圍×3",
				"全方位小範圍×2",
				"直線十字4個方向",
				"敵人中心小範圍",
				"四周極小範圍×9",
				"敵人的中心大範圍",
				"前方直線3方向",
				"敵人中心小範圍(距離限制)",
				"全方位極小範圍",
				"前方直線超長距離(自動追蹤)",
				"敵人的中心小範圍",
				"自身中範圍",
				"自身前方特大範圍",
				"自身前方大範圍",
				"前方小範圍",
				"自身後方短距離",
				"移動至敵人中心小範圍×6",
				"敵人中心特大範圍",
				"前方左右中距離",
				"自身中心極小範圍(移動追蹤型)",
				"左前方小範圍",
				"前方小範圍×2",
				"後方短距離",
				"前方大範圍",
				"落地點中範圍",
				"敵人中心大範圍", };
		for (String str : me) {
			tran.linearSearchAndTranslate(str);
		}
		tran.printUntranslated();
	}
}
