package edu.kit.wavelength.client.view.export;

import com.google.gwt.user.client.Random;

public class ArrowFactory {

	//private static final String ARROWS = "→⮕➡↣⇾⇝⟿↠⇸⇻⤀⤁⤔⤕⤅⤖⤐⤗⤘⤞⤠↳⤷⥂⥱⥸⭈⥵⭇⥲⭌⥴⥅⥹⇴⥇⟴⭃⭄↯⤳⇀⇁⥋⥓⥗⥛⥟⥤⥬⥭⇢⤍⤏⤑⇉⇶⇒⇍⟹⤂⤇⇛⭆↦⟼⇥⤚⤜↪↬⥼⤼⤿⤹⤻⮎➩➪➫➬➭➮➯➱➳➵➸➙➾⇰➛➜➔➝➞➟➠➥➦➧➨➲➢➣➤➺➻➼➽►▻▶🔜";
	private static final String ARROWS = "⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇍⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒⇒";
	
	public char getArrow() {
		return ARROWS.charAt(Random.nextInt(ARROWS.length()));
	}
}
