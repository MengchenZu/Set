import java.util.ArrayList;

public class Set {
	public static boolean check_set(Card card1, Card card2, Card card3) {
		Boolean quantity_set = false;
		Boolean color_set = false;
		Boolean shape_set = false;
		Boolean shading_set = false;

		if(card1.get_quantity() == card2.get_quantity() && card1.get_quantity() == card3.get_quantity()) quantity_set = true;
		else if(card1.get_quantity() != card2.get_quantity() && card2.get_quantity() != card3.get_quantity() && 
			            card1.get_quantity() != card3.get_quantity()) quantity_set = true;
		if(quantity_set == false) return false;

		if(card1.get_color() == card2.get_color() && card1.get_color() == card3.get_color()) color_set = true;
		else if(card1.get_color() != card2.get_color() && card2.get_color() != card3.get_color() && 
			            card1.get_color() != card3.get_color()) color_set = true;
		if(color_set == false) return false;

		if(card1.get_shape() == card2.get_shape() && card1.get_shape() == card3.get_shape()) shape_set = true;
		else if(card1.get_shape() != card2.get_shape() && card2.get_shape() != card3.get_shape() &&
			            card1.get_shape() != card3.get_shape()) shape_set = true;
		if(shape_set == false) return false;

		if(card1.get_shading() == card2.get_shading() && card1.get_shading() == card3.get_shading()) shading_set = true;
		else if(card1.get_shading() != card2.get_shading() && card2.get_shading() != card3.get_shading() &&
			            card1.get_shading() != card3.get_shading()) shading_set = true;
		if(shading_set == false) return false;

		return true;
	}
	
	public static boolean exist_set(ArrayList<Card> card_list) {
	    for(int i = 0;i < card_list.size(); i++) {
	        for(int j = i + 1;j < card_list.size(); j++) {
	            for(int k = j + 1;k < card_list.size(); k++) {
	                if(check_set(card_list.get(i), card_list.get(j), card_list.get(k))) {
	                    card_list.get(i).printout();
	                    card_list.get(j).printout();
	                    card_list.get(k).printout();
	                    return true;
	                }
	            }
	        }
	    }

	    return false;
	}
}