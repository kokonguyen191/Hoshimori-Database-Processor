package kokonguyen191;

public class Dictionary {
	public String[] rawDict = {
			// Character translate
			"みき,Miki", "昴,Subaru", "遥香,Haruka", "望,Nozomi", "ゆり,Yuri", "くるみ,Kurumi", "あんこ,Anko", "蓮華,Renge",
			"明日葉,Asuha", "桜,Sakura", "ひなた,Hinata", "楓,Kaede", "ミシェル,Michelle", "心美,Kokomi", "うらら,Urara", "サドネ,Sadone",
			"花音,Kanon", "詩穂,Shiho", "ミサキ,Misaki", "茉梨,Mari", "樹,Itsuki", "風蘭,Fuuran",
			"明日葉(中1),Asuha (Middle School Year 1)",

			// Repair card name
			"【Chuuuuu♡Lip集合】ミシェル,【Chuuuuu Lip集合】ミシェル", "【サブカ専用】Chuuuuu♡Lip(ロゴ),【サブカ専用】Chuuuuu Lip(ロゴ)",
			"【サブカ専用】Chuuuuu♡Lip(サイン),【サブカ専用】Chuuuuu Lip(サイン)", "【サブカ専用】マーチングクラス,カード一覧/【サブカ専用】マーチングクラス",
			"【サブカ専用】ｱﾆﾒ放送記念(楓),【サブカ専用】アニメ放送記念(楓)", "【サブカ専用】ｱﾆﾒ放送記念(ミサキ),【サブカ専用】アニメ放送記念(ミサキ)",
			"【サブカ専用】ｱﾆﾒ放送記念(うらら),【サブカ専用】アニメ放送記念(うらら)", "【サブカ専用】ｱﾆﾒ放送記念(サドネ),【サブカ専用】アニメ放送記念(サドネ)",
			"【サブカ専用】ｱﾆﾒ放送記念(あんこ),【サブカ専用】アニメ放送記念(あんこ)", "【サブカ専用】ｱﾆﾒ放送記念(みき),【サブカ専用】アニメ放送記念(みき)",

			// Batch and special card name
			"冬制服,Winter Uniform", "体操服,Gym Uniform", "私服,Plain Clothes", "ラクロス部,Lacrosse Club", "フットサル部,Futsal Club",
			"吹奏楽部,Brass Band Club", "テニス部,Tennis Club", "剣道部,Kendo Club", "化学部,Chemical Club", "パソコン部,PC Club",
			"新体操部,New Gymnastic Club", "生徒会長,Student Council President", "将棋部,Shogi Club", "ソフトボール部,Softball Club",
			"美術部,Arts Club", "手芸部,Handcraft Club", "天文部,Astronomy Club", "水泳部,Swimming Club",
			"夕暮れの帰り道,The Way Back Home in Dusk", "校舎裏の告白,Confession Behind the School Building", "ジャズ喫茶,Jazz Teahouse",
			"夕焼けの屋上,Rooftop in Sunset", "生活指導,Life Guidance", "木陰で読書,Reading in the Shade", "大盛りラーメン,Super-size Ramen",
			"女の子ウォッチング,Watching Girls", "文武両道,Diligent Student", "雨宿り,Shelter from the Rain", "動物大好き,Animals Lover",
			"初めての味,The First Taste", "おやすみなさい,Good Night", "ホラー映画,Horror Film", "ヒミツ発見☆,Secret Finding ☆",
			"星衣フェニックス,Starsuit Phoenix", "星衣ユニコーン,Starsuit Unicorn", "星衣リヴァイアサン,Starsuit Leviathan",
			"未来のパティシエ,Future Pâtissière", "サッカー観戦,Soccer Match", "クラシックコンサート,Classical Concert",
			"ウインドウショッピング,Window Shopping", "自宅菜園,Home Garden", "休日の軒先,Under the Eaves on a Day Off",
			"大好き遊園地,At the Amusement Park", "ピアノコンクール,Piano Competition", "メイド,Maid", "早朝ジョギング,Early Morning Jogging",
			"アイドルおっかけ,Idol Groupie", "屋内撮影会,Indoor Photo Session", "巫女のおしごと,A Shrine Maiden's Job",
			"アイドルコスプレ♡,Idol Cosplay ♡", "チアガール,Cheerleader", "星衣ギャラクシー,Starsuit Galaxy", "夏制服,Summer Uniform",
			"水着’15,Swimsuit '15", "私服2,Plain Clothes 2", "チャイナドレス,China Dress", "ゲーセンクイーン,Arcade Queen",
			"楠家の庭園,Kusunoki Family's Garden", "浴衣’15,Yukata '15", "アニマル,Animal", "星衣ヴァルキリー,Starsuit Valkyrie",
			"星衣パイレーツ,Starsuit Pirate", "ハロウィン’15,Halloween '15", "制服,Uniform", "星衣グリム,Starsuit Grim", "人形あそび,Doll Play",
			"Chuuuuu♡Lip,Chuuuuu♡Lip", "サブカ専用,Subcard Only", "星守メイド,Hoshimori Maid", "クリスマス’15,Christmas '15",
			"メリークリスマス！,Merry Christmas!", "大晦日,New Year's Eve", "元旦,New Year's Day", "Sirius,Sirius",
			"バレンタイン’16,Valentine '16", "ザ・ムービー,The Movie", "入浴,Bathing", "湯上がり,After Bath", "Tiara,Tiara",
			"Tiara集合,Tiara Set", "Sirius集合,Sirius Set", "Chuuuuu♡Lip集合,Chuuuuu♡Lip Set", "星衣フローラ,Starsuit Flora",
			"f*f,f*f", "おしのび,In Disguise", "Princess,Princess", "ROUGE,ROUGE", "バースデー’16,Birthday '16",
			"ウェディング,Wedding", "/MUTE,/MUTE", "Pixie,Pixie", "水着’16,Swimsuit '16", "浴衣’16,Yukata '16", "バニー,Bunny",
			"祈り,Prayer", "嘆き,Mourning", "パティシエ修行中,Pâtissière in Training", "Clover,Clover",
			"f*f(アドガver),f*f (Adoga ver)", "添い寝,Sleeping Together", "小悪魔の誘惑,Temptation of the Little Devil",
			"ハロウィン’16,Halloween '16", "復活ライブ,The Live of Rebirth", "星守メイド’16,Hoshimori Maid '16",
			"眠り姫名演技中,Sleeping Beauty", "冬デート,Winter Date", "クリスマスパーティー！,Christmas Party!", "みんな一緒,Everyone Together",
			"巫女,Shrine Maiden", "新春かるた大会,New Year Karuta Tournament", "白衣の天使,Angel in White", "バースデー’17,Birthday '17",
			"年初めのご挨拶,First Greeting of the Year", "バレンタイン’17,Valentine '17", "雪あそび,Snow Play", "泡沫の恋,Ephemeral Love",
			"キッチン,Kitchen", "マーチング,Marching", "時計じかけの魔法,Clockwork Magic", "旧体操服,Old Gym Clothes",
			"神樹ヶ峰制服,Shinjugamine Uniform", "2ndメモリアル,2nd Memorial", "水着’17,Swimsuit '17", "寝間着,Sleepwear", "教師,Teacher",
			"星衣フローラ散壊,Hoshimori Flora Annihilated", "おうちデート,House Date", "新制服,New Uniform", "希望,Hope",
			"赤ずきん,Little Red Riding Hood", "18人で出撃！,Sortie with all 18 Students!", "みんな笑顔で,Everyone's Smile",
			"放課後の約束,Afterschool Promise", "マーチングクラス,Marching Class", "ロマン紀行,Romantic Trip",
			"16人なら…！,If it is 16 of us...!", "新年挨拶,New Year Greetings", "二人の出会い,The Encounter of the Two",
			"ｱﾆﾒ放送記念,Anime Airing Commemoration", "ｱﾆﾒ記念復刻,Anime Commemoration Reprint",
			"星衣フローラ変身,Starsuit Flora Transform", "アニメ放送記念ガチャ<星月 みき>,Anime Airing Commemoration Gacha 'Hoshitsuki Miki'",
			"アニメ放送記念ガチャ<芹沢 蓮華>,Anime Airing Commemoration Gacha 'Serizawa Renge'",
			"アニメ放送記念ガチャ<楠 明日葉>,Anime Airing Commemoration Gacha 'Kusunoki Asuha'",
			"アニメ放送記念ガチャ<火向井 ゆり>,Anime Airing Commemoration Gacha 'Himekai Yuri'",
			"アニメ放送記念ガチャ<煌上 花音>,Anime Airing Commemoration Gacha 'Kougami Kanon'",
			"アニメ放送記念ガチャ<藤宮 桜>,Anime Airing Commemoration Gacha 'Fujimiya Sakura'",
			"アニメ放送記念ガチャ<サドネ>,Anime Airing Commemoration Gacha 'Sadone'",
			"アニメ放送記念ガチャ<南 ひなた>,Anime Airing Commemoration Gacha 'Minami Hinata'",
			"アニメ放送記念ガチャ<綿木 ミシェル>,Anime Airing Commemoration Gacha 'Watagi Michelle'",
			"アニメ放送記念ガチャ<常磐 くるみ>,Anime Airing Commemoration Gacha 'Tokiwa Kurumi'",
			"アニメ放送記念ガチャ<蓮見 うらら>,Anime Airing Commemoration Gacha 'Hasumi Urara'",
			"アニメ放送記念ガチャ<千導院 楓>,Anime Airing Commemoration Gacha 'Sendouin Kaede'",
			"アニメ放送記念ガチャ<粒咲 あんこ>,Anime Airing Commemoration Gacha 'Tsubuzaki Anko'",
			"アニメ放送記念ガチャ<成海 遥香>,Anime Airing Commemoration Gacha ", "アニメ放送記念ガチャ<天野 望>,Anime Airing Commemoration Gacha ",
			"アニメ放送記念ガチャ<若葉 昴>,Anime Airing Commemoration Gacha ", "アニメ放送記念ガチャ<国枝 詩穂>,Anime Airing Commemoration Gacha ",
			"アニメ放送記念ガチャ<朝比奈 心美>,Anime Airing Commemoration Gacha ",

			// Special
			"ロゴ,Logo", "200万,2 Million", "銃,Gun", "剣,Sword", "ホワイトデー復刻,White Day Reprint", "台湾,Taiwan",
			"300万復刻,3 Million Reprint", "メモリーズ復刻,Memories Reprint", "双弾,Twin Barrett", "サイン,Sign", "復刻,Reprint",
			"砲剣,Gunblade", "杖,Rod", "槌,Hammer", "槍,Spear",

			// Nakayoshi target
			"1年生,Year 1 Students", "2年生,Year 2 Students", "莎朶霓,Sadone", "3年生,Year 3 Students", "雙槍,Twin Barrett",
			"劍槍,Gunblade", "矛,Spear", "所有人,Everyone", "劍,Sword", "美紀,Miki",

			// Obtaining method
			// Story
			"主線 初始卡片,Starting Card", "主線 第1部 第1章 1-1,Part 1 Chapter 1 Story 1-1",
			"主線 第1部 第2章 10-1,Part 1 Chapter 2 Story 10-1", "主線 第1部 第2章 9-4,Part 1 Chapter 2 Story 9-4",
			"主線 第1部 第2章 7-1,Part 1 Chapter 2 Story 7-1", "主線 第1部 第1章 4-4,Part 1 Chapter 1 Story 4-4",
			"主線 第1部 第2章 8-7,Part 1 Chapter 2 Story 8-7", "主線 第1部 第2章 11-4,Part 1 Chapter 2 Story 11-4",
			"主線 第1部 第2章 6-4,Part 1 Chapter 2 Story 6-4", "主線 第1部 第2章 11-1,Part 1 Chapter 2 Story 11-1",
			"主線 第1部 第2章 7-4,Part 1 Chapter 2 Story 7-4", "主線 第1部 第6章 44-7,Part 1 Chapter 6 Story 44-7",
			"主線關卡「第48話 笑容的前方所看見的東西」,Part 1 Chapter 6 Story 48-7", "主線 第2部 第3章 60-7,Part 2 Chapter 3 Story 60-7",
			"主線 第3部 第2章 100-7,Part 3 Chapter 2 Story 100-7", "主線 第4部 第1章 113-1,Part 4 Chapter 1 Story 113-1",

			// Regular gacha
			"宿題,Achievement/Homework", "通常轉蛋,Regular Gacha", "通常轉蛋(～2017/06/20 14:59),Regular Gacha (After 2017/06/20 14:59)",
			"首次：通常轉蛋（至2015年6月30日17:59）復刻：通常轉蛋（至2015年11月30日14:59）,First Appearance: Regular Gacha (Until 2015/06/30 17:59)\nReprint: Regular Gacha (Until 2015/11/30 14:59)",
			"通常轉蛋卡片交換所,Regular Gacha and Card Clearinghouse",
			"通常轉蛋（至2015年11月13日14:59）,Regular Gacha (Until 2015/11/30 14:59)",
			"通常轉蛋(2017/06/20 15:00～),Regular Gacha (After 2017/06/20 15:00)",
			// Event gacha
			"限定轉蛋（至2015年7月31日14:59）,Limited Gacha (Until 2015/07/31 14:59)", // Inconsistent
																				// format
			"限定轉蛋（至2015年9月30日 14:59）,Limited Gacha (Until 2015/09/30 14:59)", // Inconsistent
																				// format
			"2017年望誕生日記念ガチャ,Limited Gacha: Nozomi's Birthday 2017 Event Gacha",
			"2017年ゆり誕生日記念ガチャ, Limited Gacha: Yuri's Birthday 2017 Event Gacha",
			"2017年蓮華誕生日記念ガチャ,Limited Gacha: Renge's Birthday 2017 Event Gacha",
			"星守メモリーズガチャ 第3弾,Limited Gacha: Hoshimori Memories Gacha Part 3",
			"星守メモリーズガチャ 第2弾,Limited Gacha: Hoshimori Memories Gacha Part 2",
			"星守メモリーズ水着’16ガチャ,Limited Gacha: Hoshimori Memories Swimwuit '16 Gacha",
			"星守メモリーズ浴衣ガチャ,Limited Gacha: Hoshimori Memories Yukata Gacha",
			"2017年ミシェル誕生日記念ガチャ,Limited Gacha: Michelle's Birthday 2017 Event Gacha",
			"星守メモリーズガチャ 第1弾,Limited Gacha: Hoshimori Memories Gacha Part 1",
			"2017年昴誕生日記念ガチャ,Limited Gacha: Subaru's Birthday 2017 Event Gacha",
			"4月15日バースデー記念ガチャ,Limited Gacha: April 15 Birthday Event Gacha",
			"3月27日バースデー記念ガチャ,Limited Gacha: March 27 Birthday Event Gacha",
			"3月15日バースデー記念ガチャ,Limited Gacha: March 15 Birthday Event Gacha",
			"ホワイトセレクション記念ガチャ,Limited Gacha: White Selection Event Gacha",
			"手料理めしあがれ記念ガチャ,Limited Gacha: Homecooking Bon appétit Event Gacha",
			"白銀の想い出記念ガチャ,Limited Gacha: Silvery Memory Event Gacha",
			"とろける想い記念ガチャ,Limited Gacha: Melt feelings Event Gacha",
			"2月3日バースデー記念ガチャ,Limited Gacha: February 03 Birthday Event Gacha",
			"ほんとのキモチは…記念ガチャ,Limited Gacha: The True Feeling… Event Gacha",
			"台湾バトガコラボ記念ガチャ,Limited Gacha: Taiwan Batoga Collab Event Gacha",
			"1月18日バースデー記念ガチャ,Limited Gacha: January 18 Birthday Event Gacha",
			"エンジェルの癒し記念ガチャ,Limited Gacha: Angel's Healing Event Gacha", "賀春記念ガチャ,Limited Gacha: New Year Event Gacha",
			"12月24日バースデー記念ガチャ,Limited Gacha: December 24 Birthday Event Gacha",
			"ジングルベル記念ガチャ,Limited Gacha: Jingle Bell Event Gacha",
			"ときめきウィンター記念ガチャ,Limited Gacha: Exciting Winter Event Gacha",
			"300万人突破記念ガチャ,Limited Gacha: 3 Million Players Event Gacha",
			"12月7日バースデー記念ガチャ,Limited Gacha: December 07 Birthday Event Gacha",
			"内緒の約束記念ガチャ,Limited Gacha: Promise of a Secret Event Gacha",
			"もっともっと!!学園祭記念ガチャ,Limited Gacha: Even more!! School Fes Event Gacha",
			"11月1日バースデー記念ガチャ,Limited Gacha: November 01 Birthday Event Gacha",
			"学園祭OPEN記念ガチャ,Limited Gacha: School Fes OPEN Event Gacha",
			"10月24日バースデー記念ガチャ,Limited Gacha: October 24 Birthday Event Gacha",
			"トリート・ナイト記念ガチャ,Limited Gacha: Treat Night Event Gacha",
			"トリック・ナイト記念ガチャ,Limited Gacha: Trick Night Event Gacha",
			"おやすみなさい記念ガチャ,Limited Gacha: Good Night Event Gacha",
			"10月2日バースデー記念ガチャ,Limited Gacha: October 02 Birthday Event Gacha",
			"眠れぬ夜の記念ガチャ,Limited Gacha: Sleepless Night Event Gacha",
			"9月30日バースデー記念ガチャ,Limited Gacha: September 30 Birthday Event Gacha",
			"9月23日バースデー記念ガチャ,Limited Gacha: September 23 Birthday Event Gacha",
			"お月見記念ガチャ,Limited Gacha: Moon Viewing Event Gacha",
			"8月28日バースデー記念ガチャ,Limited Gacha: August 28 Birthday Event Gacha",
			"湯けむり記念ガチャ3,Limited Gacha: Steaming Water Event Gacha 3",
			"8月14日バースデー記念ガチャ,Limited Gacha: August 14 Birthday Event Gacha",
			"夏祭り’16記念ガチャ,Limited Gacha: Summer Festival '16 Event Gacha",
			"アイランド記念ガチャ,Limited Gacha: Island Event Gacha",
			"7月7日バースデー記念ガチャ,Limited Gacha: July 07 Birthday Event Gacha",
			"ミッドナイト記念ガチャ,Limited Gacha: Midnight Event Gacha",
			"6月26日バースデー記念ガチャ,Limited Gacha: June 26 Birthday Event Gacha",
			"ヴァージンロード記念ガチャ,Limited Gacha: Virgin Road Event Gacha",
			"6月3日バースデー記念ガチャ,Limited Gacha: June 03 Birthday Event Gacha",
			"永遠の誓い記念ガチャ,Limited Gacha: Eternal Vow Event Gacha",
			"湯けむり記念ガチャ2,Limited Gacha: Steaming Water Event Gacha 2",
			"5月4日バースデー記念ガチャ,Limited Gacha: May 04 Birthday Event Gacha",
			"星守アイドル記念ガチャ,Limited Gacha: Hoshimori Idol Event Gacha",
			"アイドル「ＲＥＤ」記念ガチャ,Limited Gacha: Idol 'RED' Event Gacha",
			"湯けむり記念ガチャ,Limited Gacha: Steaming Water Event Gacha", "スター記念ガチャ,Limited Gacha: Star Event Gacha",
			"ビターチョコ記念ガチャ,Limited Gacha: Bitter Choco Event Gacha",
			"スウィートチョコ記念ガチャ,Limited Gacha: Sweet Choco Event Gacha",
			"アイドル「蒼」記念ガチャ,Limited Gacha: Idol 'Blue' Event Gacha", "迎春記念ガチャ,Limited Gacha: Happy New Year Event Gacha",
			"平安夜記念轉蛋,Limited Gacha: Peaceful Night Event Gacha", "聖夜記念轉蛋,Limited Gacha: Holy Night Event Gacha",
			"白色Xmas記念轉蛋,Limited Gacha: White Xmas Event Gacha", "星守女僕轉蛋,Limited Gacha: Hoshimori Maid Gacha",
			"アイドル記念ガチャ,Limited Gacha: Idol Event Gacha", "ハロウィン記念ガチャ,Limited Gacha: Halloween Event Gacha",
			"浴衣轉蛋,Limited Gacha: Yukata Event Gacha", "復刻アイドル記念ガチャ,Limited Gacha: Reprint Idol Event Gacha",
			"復刻ハロウィン記念ガチャ,Limited Gacha: Reprint Halloween Event Gacha",
			"復刻アイドル「蒼」記念ガチャ,Limited Gacha: Reprint Idol 'Blue' Event Gacha",
			// Permanent gacha
			"星守ガチャ,Hoshimori Gacha", "第3部ガチャ,Part 3 Gacha", "清律音楽学院ガチャ,Marching Class Gacha",
			"第2部クライマックスガチャ,Part 2 Climax Gacha", "デビューガチャ,Debut Gacha", "第2部ストーリーガチャ,Part 2 Story Gacha",
			"第4部ストーリーガチャ,Part 4 Story Gacha",

			// Special cards
			"アニメ放送記念ログインボーナスチャレンジ,Anime Airing Commemoration Login Bonus Challenge",
			"かけがえのないトキ　 ログインボーナスチャレンジ,Irreplaceable Time Login Bonus Challenge",
			"儚き冬の恋心　ログインボーナスチャレンジ,Transient Winter Love Login Bonus Challenge",
			"2017年スタート記念 ログインボーナスチャレンジ,Start of 2017 Commemoration Login Bonus Challenge",
			"年末年始　ログインボーナスチャレンジ,New Year's Holiday Login Bonus Challenge",
			"合同学園祭記念 ログインボーナスチャレンジ,Joint School Fes Commemoration Login Bonus Challenge",
			"ハロウィンログインボーナスチャレンジ,Halloween Login Bonus Challenge",
			"シルバーウィーク限定ログインボーナスチャレンジ,Silver Week Limited Login Bonus Challenge",
			"ほしもり湯けむりロマン紀行　後編,Hoshimori Steamy Romance Trip Sequel",
			"秘密の合言葉を探せキャンペーン,Searching for the Secret Password Campaign", "アイドル応援キャンペーン,Idol support campaign",
			"第1部総集編 Part2 16人ならできること,Story Part 1 Summary", "新年のごあいさつ,New Year's Greetings",
			"第1部総集編 Part1 二人の出会い,Story Part 1 Summary", "わたしたちのスタートライン！ 後編,Watashitachi no Start Line!' Sequel",
			"利用者200万人感謝キャンペーン 第3弾,2 Million Players Appreciation Campaign",
			"星守定期テスト～謎のイロウス対話編～,Hoshimori Test - Mysterious Irous Interaction -",
			"獎勵交換所2016/11/25 14:00 ~ 2016/12/14 14:59(UTC+9),Reward Exchange 2016/11/25 14:00 ~ 2016/12/14 14:59",

	};

	private String[] convertDict = {
			// Character convert
			"みき,1", "昴,2", "遥香,3", "望,4", "ゆり,5", "くるみ,6", "あんこ,7", "蓮華,8", "明日葉,9", "桜,10", "ひなた,11", "楓,12",
			"ミシェル,13", "心美,14", "うらら,15", "サドネ,16", "花音,17", "詩穂,18", "ミサキ,19", "茉梨,101", "樹,102", "風蘭,103",
			"明日葉(中1),104",

			// Card type
			"normal,0", "extra,1", "sub,2",

			// Rarity
			"★,0", "★★,1", "★★★,2", "★★★★,3",

			// Weapon
			"劍,0", "矛,1", "槌,2", "槍,3", "杖,4", "劍槍,5", "雙槍,6", "牙爪,7", " 劍,0", " 矛,1", " 槌,2", " 槍,3", " 杖,4", " 劍槍,5",
			" 雙槍,6", " 牙爪,7",

			// Affinity
			"無視武器屬性,1", "無視弱勢,2", };

	private String[] replaceDict1 = {

			// Charge damage
			"近14 times、中13 times、遠9 times,Close: 14 times - Mid: 13 times - Far: 9 times",

			// Skill damage
			"自身中心小圓,Small area around self", "前方扇形,Front", "劍尖中心小圓,Tip", "有延遲判定,Extension", "擊飛無效化,Null ranged attack",
			"最大4段,Max 4 hits", "金色炸彈,Gold bombs", "黑色炸彈,Black bombs", "親密度,Affection Level", "巧克力,Chocolate",
			"普通,Normal", "黃色,Yellow", "紫色,Purple", "黑色,Black", "藍色,Blue", "彩虹,Rainbow", "綠色,Green", "紅色,Red",
			"櫻桃,Cherry", "薄荷,Mint", "傷害,Damage", "擊暈,Stun", "毒,Poison", "藍眼罩,Blue Blindfold", "綠眼罩,Green Blindfold",
			"低機率,Rare" };

	private String[] replaceDict2 = {
			// Skill range
			"自身前方直線超長距離, front straight line very long range", "移動至敵人的中心中, enemy tracking medium range",
			"移動至敵人中心極小, enemy tracking very small range", "前方直線超長距離, front straight line very long range",
			"自身左方和右方小, left and right small range", "移動至敵人中心小, enemy tracking small range",
			"前方直線三方向, front straight lines in three directions", "移動時瞄準敵人, moving towards enemy",
			"以自身作起點中, around self medium range", "前方直線長距離, front straight line long range",
			"敵人的中心極小, around enemy very small range", "以自身作起點小, around self small range",
			"自身五個方位小, five directions small range", "自身後方短距離, rear short range",
			"前方左右中距離, front left and right medium range", "降低敵人防御力, reduce enemy defense",
			"降低敵人攻擊力, reduce enemy attack", "自身前方特大, front very large range", "敵人的中心大, around enemy large range",
			"敵人的中心中, around enemy medium range", "自身中心極小, around self very small range", "木桶位置的中, medium range",
			"全方位中範囲, all directions medium range", "敵人中心特大, around enemy very large range",
			"敵人的中心小, around enemy small range", "敵人中心中, around enemy medium range", "敵人中心大, around enemy large",
			"敵人中心小, around enemy small range", "移動追蹤型, moving towards enemy type", "自身前方大, front large range",
			"追蹤移動型, enemy tracking type", "自身中心小, around self small range", "每段攻擊有, each hit has",
			"自身周邊中, self medium range", "全方位極小, all directions very small range", "自身前方中, front medium range",
			"自身四周中, four directions medium range", "移動型極小, moving towards enemy short range",
			"全方位特大, all directions very large range", "後方短距離, rear short range", "左方直線, left straight line",
			"前方直線, front straight line", "距離限制, restricted range", "落地點中, front medium range",
			"全方位中, all directions medium range", "四周極小, four directions very small range", "自動追蹤, auto tracking",
			"後方直線, rear straight line", "左前方小, front left small range", "前方特大, front very large range",
			"前方極小, front very small range", "落地點小, small range", "右前方小, front right small range", "我方全員, healing",
			"普通攻擊, normal attack", "直線十字, straight cross shape", "前面直線, front straight line",
			"全方位小, all directions small range", "全方位大, all directions large range", "四周小, four directions small range",
			"左前方, front left", "次傷害, damage", "反轉的, reversed", "左方小, left side small range", "自身中, self medium range",
			"前方小, front small range", "移動型, moving type", "追蹤型, tracking type", "前方大, front large range",
			"前方中, front medium range", "右方小, right side small range", "個方向, direction", "前方, front", "自身, self",
			"炸彈, bomb", "右方, right side", "以後, rear", "字形, shape", "方向, direction", "心中,Center", "型, type",
			"大, large range", "前, front", "左, left", "右, right", };

	public String[] getConversionDict() {
		return convertDict;
	}

	public String[] getTranslationDict() {
		return rawDict;
	}

	public String[] getReplaceDict1() {
		return replaceDict1;
	}

	public String[] getReplaceDict2() {
		return replaceDict2;
	}
}
