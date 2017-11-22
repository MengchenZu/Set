import java.util.ArrayList;
import java.util.*;

public class Deck {
	static int N = 3;
	static String[] quantities;
	static String[] colors;
	static String[] shapes;
	static String[] shadings;
	
	static ArrayList<Card> deck;
	
	public Deck() {
		quantities = new String [N];
		colors = new String [N];
		shapes = new String [N];
		shadings = new String [N];
		
		deck = new ArrayList<Card>();
		
		quantities[0] = "1";
		quantities[1] = "2";
		quantities[2] = "3";
		colors[0] = "green";
		colors[1] = "purple";
		colors[2] = "red";
		shapes[0] = "oval";
		shapes[1] = "diamond";
		shapes[2] = "squiggle";
		shadings[0] = "solid";
		shadings[1] = "shaded";
		shadings[2] = "outlined";
		
		for(int i = 0;i < N; i++) {
			for(int j = 0;j < N; j++) {
				for(int k = 0;k < N; k++) {
					for(int p = 0;p < N; p++) {
						Card new_card = new Card(quantities[i], colors[j], shapes[k], shadings[p]);
						deck.add(new_card);
					}
				}
			}
		}
	}
	
	public int deck_size() {
		return deck.size();
	}
	
	public void shuffle() {
		for (int i = deck.size()-1; i > 0; i--) {
            int rand = (int)(Math.random()*(i+1));
            Collections.swap(deck, i, rand);
        }
	}
	
	public ArrayList<Card> draw_card(int num) {
		ArrayList<Card> drawn_cards = new ArrayList<Card>();
		for(int i = 0;i < num; i++) {
			drawn_cards.add(deck.get(0));
			deck.remove(0);
		}
		return drawn_cards;
	}
	
	public ArrayList<Card> initialization() {
		return draw_card(12);
	}
	
	public void printout() {
		for(int i = 0;i < deck_size(); i++) {
			deck.get(i).printout();
		}
	}
}


