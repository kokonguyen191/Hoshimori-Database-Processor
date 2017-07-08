package kokonguyen191;

public class Card {
	Integer id;
	Integer owner_id;
	String card_type;
	Integer i_card_type;
	String name;
	String japanese_name;
	String character;
	Integer i_character;
	String rarity;
	Integer i_rarity;
	String weapon;
	Integer i_weapon;
	String obtain_method;
	String image;
	String special_icon;
	String art;
	String special_front;
	String front_top;
	String front_bottom;
	String front_name;
	String front_rarity;
	String front_weapon;
	String transparent;
	boolean subcard_effect;
	Integer hp_1;
	Integer sp_1;
	Integer atk_1;
	Integer def_1;
	Integer hp_50;
	Integer sp_50;
	Integer atk_50;
	Integer def_50;
	Integer hp_70;
	Integer sp_70;
	Integer atk_70;
	Integer def_70;
	String skill_name;
	String japanese_skill_name;
	Integer skill_SP;
	String skill_range;
	String skill_affinity;
	Integer i_skill_affinity;
	String action_skill_effects;
	String skill_comment;
	String skill_preview;
	Integer action_skill_combo;
	Integer evolved_action_skill_combo;
	String action_skill_damage;
	String evolved_action_skill_damage;
	String nakayoshi_title;
	String japanese_nakayoshi_title;
	String nakayoshi_skill_effect;
	String nakayoshi_skill_target;
	String evolved_nakayoshi_skill_effect;
	String evolved_nakayoshi_skill_target;
	String charge_comment;
	String charge_damage;
	String charge_hit;
	String charge_name;
	String charge_range;

	public boolean isReprint() {
		return japanese_name.endsWith("復刻)");
	}

	public boolean isAnotherVersion() {
		return japanese_name.endsWith("剣)") || japanese_name.endsWith("双弾)") || japanese_name.endsWith("槌)")
				|| japanese_name.endsWith("杖)") || japanese_name.endsWith("銃)") || japanese_name.endsWith("槍)")
				|| japanese_name.endsWith("200万)");
	}

	public String getOriginal() {
		if (isReprint() || isAnotherVersion()) {
			return japanese_name.substring(0, japanese_name.lastIndexOf('('));
		} else {
			return japanese_name;
		}
	}

	@Override
	public String toString() {
		return japanese_name;
	}
	
	private static String getString(Object o) {
		if (o == null) {
			return "";
		} else if (o.getClass().equals(String.class)) {
			return (String) o;
		} else {
			return String.valueOf(o);
		}
	}
	
	String[] csvFriendlyEntry() {
		String[] result = {
				getString(id),
				getString(owner_id),
				getString(card_type),
				getString(i_card_type),
				getString(name),
				getString(japanese_name),
				getString(character),
				getString(i_character),
				getString(rarity),
				getString(i_rarity),
				getString(weapon),
				getString(i_weapon),
				getString(obtain_method),
				getString(image),
				getString(special_icon),
				getString(art),
				getString(special_front),
				getString(front_top),
				getString(front_bottom),
				getString(front_name),
				getString(front_rarity),
				getString(front_weapon),
				getString(transparent),
				getString(subcard_effect),
				getString(hp_1),
				getString(sp_1),
				getString(atk_1),
				getString(def_1),
				getString(hp_50),
				getString(sp_50),
				getString(atk_50),
				getString(def_50),
				getString(hp_70),
				getString(sp_70),
				getString(atk_70),
				getString(def_70),
				getString(skill_name),
				getString(japanese_skill_name),
				getString(skill_SP),
				getString(skill_range),
				getString(skill_affinity),
				getString(i_skill_affinity),
				getString(action_skill_effects),
				getString(skill_comment),
				getString(skill_preview),
				getString(action_skill_combo),
				getString(evolved_action_skill_combo),
				getString(action_skill_damage),
				getString(evolved_action_skill_damage),
				getString(nakayoshi_title),
				getString(japanese_nakayoshi_title),
				getString(nakayoshi_skill_effect),
				getString(nakayoshi_skill_target),
				getString(evolved_nakayoshi_skill_effect),
				getString(evolved_nakayoshi_skill_target),
				getString(charge_comment),
				getString(charge_damage),
				getString(charge_hit),
				getString(charge_name),
				getString(charge_range)
		};
		return result;
	}
}
