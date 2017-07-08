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

	public static void main(String[] args) {
		String[] rawDict = {
				// Character translate
				"みき,Miki", "昴,Subaru", "遥香,Haruka", "望,Nozomi", "ゆり,Yuri", "くるみ,Kurumi", "あんこ,Anko", "蓮華,Renge",
				"明日葉,Asuha", "桜,Sakura", "ひなた,Hinata", "楓,Kaede", "ミシェル,Michelle", "心美,Kokomi", "うらら,Urara",
				"サドネ,Sadone", "花音,Kanon", "詩穂,Shiho", "茉梨,Mari", "樹,Itsuki", "風蘭,Fuuran",
				"明日葉(中1),Asuha (Middle School Year 1)",

				// Repair card name
				"【Chuuuuu♡Lip集合】ミシェル,【Chuuuuu Lip集合】ミシェル", "【サブカ専用】Chuuuuu♡Lip(ロゴ),【サブカ専用】Chuuuuu Lip(ロゴ)",
				"【サブカ専用】Chuuuuu♡Lip(サイン),【サブカ専用】Chuuuuu Lip(サイン)",

				// Batch and special card name
				"冬制服,Winter Uniform", "体操服,Gym Uniform", "私服,Plain Clothes", "ラクロス部,Lacrosse Club",
				"フットサル部,Futsal Club", "吹奏楽部,Brass Band Club", "テニス部,Tennis Club", "剣道部,Kendo Club", "化学部,Chemical Club",
				"パソコン部,PC Club", "新体操部,New Gymnastic Club", "生徒会長,Student Council President", "将棋部,Shogi Club",
				"ソフトボール部,Softball Club", "美術部,Arts Club", "手芸部,Handcraft Club", "天文部,Astronomy Club",
				"水泳部,Swimming Club", "夕暮れの帰り道,The Way Back Home in Dusk",
				"校舎裏の告白,Confession Behind the School Building", "ジャズ喫茶,Jazz Teahouse", "夕焼けの屋上,Rooftop in Sunset",
				"生活指導,Life Guidance", "木陰で読書,Reading in the Shade", "大盛りラーメン,Super-size Ramen",
				"女の子ウォッチング,Watching Girls", "文武両道,Diligent Student", "雨宿り,Shelter from the Rain", "動物大好き,Animals Lover",
				"初めての味,The First Taste", "おやすみなさい,Good Night", "ホラー映画,Horror Film", "ヒミツ発見☆,Secret Finding ☆",
				"星衣フェニックス,Starsuit Phoenix", "星衣ユニコーン,Starsuit Unicorn", "星衣リヴァイアサン,Starsuit Leviathan",
				"未来のパティシエ,Future Pâtissière", "サッカー観戦,Soccer Match", "クラシックコンサート,Classical Concert",
				"ウインドウショッピング,Window Shopping", "自宅菜園,Home Garden", "休日の軒先,Under the Eaves on a Day Off",
				"大好き遊園地,At the Amusement Park", "ピアノコンクール,Piano Competition", "メイド,Maid",
				"早朝ジョギング,Early Morning Jogging", "アイドルおっかけ,Idol Groupie", "屋内撮影会,Indoor Photo Session",
				"巫女のおしごと,A Shrine Maiden's Job", "アイドルコスプレ♡,Idol Cosplay ♡", "チアガール,Cheerleader",
				"星衣ギャラクシー,Starsuit Galaxy", "夏制服,Summer Uniform", "水着’15,Swimsuit '15", "私服2,Plain Clothes 2",
				"チャイナドレス,China Dress", "ゲーセンクイーン,Arcade Queen", "楠家の庭園,Kusunoki Family's Garden", "浴衣’15,Yukata '15",
				"アニマル,Animal", "星衣ヴァルキリー,Starsuit Valkyrie", "星衣パイレーツ,Starsuit Pirate", "ハロウィン’15,Halloween '15",
				"制服,Uniform", "星衣グリム,Starsuit Grim", "人形あそび,Doll Play", "Chuuuuu♡Lip,Chuuuuu♡Lip", "サブカ専用,Subcard Only",
				"星守メイド,Hoshimori Maid", "クリスマス’15,Christmas '15", "メリークリスマス！,Merry Christmas!", "大晦日,New Year's Eve",
				"元旦,New Year's Day", "Sirius,Sirius", "バレンタイン’16,Valentine '16", "ザ・ムービー,The Movie", "入浴,Bathing",
				"湯上がり,After Bath", "Tiara,Tiara", "Tiara集合,Tiara Set", "Sirius集合,Sirius Set",
				"Chuuuuu♡Lip集合,Chuuuuu♡Lip Set", "星衣フローラ,Starsuit Flora", "f*f,f*f", "おしのび,In Disguise",
				"Princess,Princess", "ROUGE,ROUGE", "バースデー’16,Birthday '16", "ウェディング,Wedding", "/MUTE,/MUTE",
				"Pixie,Pixie", "水着’16,Swimsuit '16", "浴衣’16,Yukata '16", "バニー,Bunny", "祈り,Prayer", "嘆き,Mourning",
				"パティシエ修行中,Pâtissière in Training", "Clover,Clover", "f*f(アドガver),f*f (Adoga ver)",
				"添い寝,Sleeping Together", "小悪魔の誘惑,Temptation of the Little Devil", "ハロウィン’16,Halloween '16",
				"復活ライブ,The Live of Rebirth", "星守メイド’16,Hoshimori Maid '16", "眠り姫名演技中,Sleeping Beauty",
				"冬デート,Winter Date", "クリスマスパーティー！,Christmas Party!", "みんな一緒,Everyone Together", "巫女,Shrine Maiden",
				"新春かるた大会,New Year Karuta Tournament", "白衣の天使,Angel in White", "バースデー’17,Birthday '17",
				"年初めのご挨拶,First Greeting of the Year", "バレンタイン’17,Valentine '17", "雪あそび,Snow Play",
				"泡沫の恋,Ephemeral Love", "キッチン,Kitchen", "マーチング,Marching", "時計じかけの魔法,Clockwork Magic",
				"旧体操服,Old Gym Clothes", "神樹ヶ峰制服,Shinjugamine Uniform", "2ndメモリアル,2nd Memorial", "水着’17,Swimsuit '17",
				"寝間着,Sleepwear", "教師,Teacher", "星衣フローラ散壊,Hoshimori Flora Annihilated", "おうちデート,House Date",
				"新制服,New Uniform", "希望,Hope", "赤ずきん,Little Red Riding Hood", "18人で出撃！,Sortie with all 18 Students!",
				"みんな笑顔で,Everyone's Smile", "放課後の約束,Afterschool Promise", "マーチングクラス,Marching Class",
				"ロマン紀行,Romantic Trip", "16人なら…！,If it is 16 of us...!", "新年挨拶,New Year Greetings",
				"二人の出会い,The Encounter of the Two",

				// Special
				"ロゴ,Logo", "200万,2 Million", "銃,Gun", "剣,Sword", "ホワイトデー復刻,White Day Reprint", "台湾,Taiwan",
				"300万復刻,3 Million Reprint", "メモリーズ復刻,Memories Reprint", "双弾,Twin Barrett", "サイン,Sign", "復刻,Reprint",
				"砲剣,Gunblade", "杖,Rod", "槌,Hammer", "槍,Spear",
				
				// Nakayoshi target
				"1年生,Year 1 Students", "2年生,Year 2 Students", "莎朶霓,Sadone", "3年生,Year 3 Students", "雙槍,Twin Barrett",
				"劍槍,Gunblade", "矛,Spear", "所有人,Everyone", "劍,Sword", "美紀,Miki",

		};

		Translator tran = new Translator(rawDict);

		String[] sentences = { "所有人",
				"2年生",
				"矛與槌",
				"3年生",
				"1年生",
				"劍",
				"矛與杖",
				"槍",
				"美紀",
				"遥香,ひなた,ミシェル,うらら",
				"雙槍與劍與矛",
				"槌與矛",
				"槍與杖",
				"矛",
				"槌",
				"2年生與1年生",
				"サドネ,望,蓮華,楓,花音",
				"槌與槍",
				"劍與杖",
				"杖",
				"劍與矛與槌",
				"矛與槍",
				"矛與劍",
				"槌與杖與雙槍",
				"杖與劍",
				"劍與槍",
				"雙槍與槍與杖",
				"杖與槍",
				"ゆり",
				"劍槍與槍與杖",
				"杖與槌",
				"槍與雙槍",
				"詩穂,花音,心美,うらら",
				"雙槍",
				"杖與矛",
				"劍與矛",
				"劍槍",
				"槍與劍槍",
				"槍與矛與槌",
				"雙槍與槍與劍槍",
				"槍與劍槍與雙槍",
				"槌與劍",
				"杖與槌與劍槍",
				"劍與槌",
				"詩穂,みき,ゆり,桜",
				"杖與槍與劍槍",
				"劍與雙槍",
				"槌與杖",
				"槍與槌",
				"あんこ",
				"莎朶霓",
				"茉梨,樹,風蘭",
				"劍槍與槍與雙槍",
				"槍與劍",
				"杖與劍槍",
				"劍槍與槌",
				"劍與杖與雙槍",
				"雙槍與槍",
				"劍與矛與雙槍",
				"矛與雙槍",
				"花音、詩穂、心美、うらら",
				"くるみ",
				"あんこ,昴,くるみ,明日葉,心美",
				"詩穂",
				"劍與矛與杖",
				"雙槍與劍槍",
				"矛與劍與杖",
				"杖與槍與雙槍",
				"矛與劍與雙槍",
				"槌與劍與矛",
				"槌與劍槍與杖",
				"劍槍與槌與杖", };

		for (String sen: sentences) {
			sen = sen.replace('與', ',');
			sen = sen.replace('、', ',');
			System.out.println(tran.translateSentence(sen, ","));
		}
		tran.printUntranslated();
	}
}
