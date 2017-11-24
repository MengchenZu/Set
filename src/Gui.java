import java.awt.GridLayout;
import java.util.ArrayList;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class Gui {
	static Display display;
	static Shell shell;
	static Deck set;
	static ArrayList<Card> board_cards;
	static ArrayList<Card> clicked_cards = new ArrayList<Card>();
	static ArrayList<Composite> composites = new ArrayList<Composite>();
	static ArrayList<Button> buttons = new ArrayList<Button>();
	static ArrayList<Integer> empty_buttons = new ArrayList<Integer>();
	
	static Label dialog;
	static Button no_set;
	static Label point_record;
	static int points;
	
	public static Button generate_card_button(Display display, Composite composite, Card card) {
		String image_name = "data/" + card.get_picture_name() + ".png";
		Image image = new Image(display, image_name);
	    Button button = new Button(composite, SWT.PUSH);
	    button.setImage(image);
	    
		return button;
	}
	
	public static void draw_new_cards() {
		if(board_cards.size() == 12) {
			ArrayList<Card> new_cards = set.draw_card(3);
			for(int i = 0;i < 3; i++) {
				String image_name = "data/" + new_cards.get(i).get_picture_name() + ".png";
				Image image = new Image(display, image_name);
				buttons.get(board_cards.indexOf(clicked_cards.get(i))).setImage(image);
				board_cards.set(board_cards.indexOf(clicked_cards.get(i)), new_cards.get(i));
			}
		} else if(board_cards.size() == 15) {
			for(int i = 2;i >= 0; i--) {
				String image_name = "data/" + board_cards.get(i + 12).get_picture_name() + ".png";
				Image image = new Image(display, image_name);
				buttons.get(board_cards.indexOf(clicked_cards.get(i))).setImage(image);
				board_cards.set(board_cards.indexOf(clicked_cards.get(i)), board_cards.get(i + 12));
				
				image_name = "data/empty.png";
				image = new Image(display, image_name);
				buttons.get(i + 12).setImage(image);
				board_cards.remove(i + 12);
			}
		} else if(board_cards.size() == 18) {
			for(int i = 2;i >= 0; i--) {
				String image_name = "data/" + board_cards.get(i + 15).get_picture_name() + ".png";
				Image image = new Image(display, image_name);
				buttons.get(board_cards.indexOf(clicked_cards.get(i))).setImage(image);
				board_cards.set(board_cards.indexOf(clicked_cards.get(i)), board_cards.get(i + 15));
				
				image_name = "data/empty.png";
				image = new Image(display, image_name);
				buttons.get(i + 15).setImage(image);
				board_cards.remove(i + 15);
			}
		}
	}
	
	public static void check_congratulation() {
		ArrayList<Card> last_cards = new ArrayList<Card>();
		for(int i = 0;i < board_cards.size(); i++) {
			if(!empty_buttons.contains(i)) last_cards.add(board_cards.get(i));
		}
		if(!Set.exist_set(last_cards)) {
			Shell congratulation = new Shell(shell);
			congratulation.setText("Congratulation!");
			congratulation.setSize(200, 200);

			FillLayout fillLayout = new FillLayout();
			fillLayout.type = SWT.VERTICAL;
			congratulation.setLayout(fillLayout);
			
			Label congratulation_text = new Label(congratulation, SWT.READ_ONLY | SWT.CENTER);
			congratulation_text.setText("\nCongratulation!!!\nClick the button to close the window.");
			
			//TODO restart
			Button close = new Button(congratulation, SWT.PUSH);
			close.setText("Close");
			
			close.addListener(SWT.Selection, new Listener() {
		        public void handleEvent(Event e) {
		          switch (e.type) {
		          case SWT.Selection:
		        	display.dispose();
		            break;
		    }}});
			
			congratulation.open();
		}
	}
	
	public static void check_end() {
		for(int i = 0;i < 3; i++) {
			String image_name = "data/empty.png";
			Image image = new Image(display, image_name);
			buttons.get(board_cards.indexOf(clicked_cards.get(i))).setImage(image);
			empty_buttons.add(board_cards.indexOf(clicked_cards.get(i)));
		}
		check_congratulation();
	}
	
	public static boolean GUI_check_set() {
		for(int i = 0;i < 3; i++) {
			clicked_cards.get(i).printout();
		}
		
		if(Set.check_set(clicked_cards.get(0), clicked_cards.get(1), clicked_cards.get(2))) {
			if(set.deck_size() != 0) draw_new_cards();
			else check_end();
			clicked_cards.clear();
    		dialog.setText("\n\nWell Done. That is a Set.");
    		points++;
    		point_record.setText("\n\nYou have got " + points + " set.");
			return true;
		}
		else {
			dialog.setText("\n\nSorry. That is not a Set.");
			clicked_cards.clear();
			return false;
		}
	}
	
	public static void initial_buttons() {

		// generate composites
		for(int i = 0;i < 4; i++) {
			Composite composite = new Composite(shell, 0);
			composite.setLayout(new FillLayout());
			composites.add(composite);
		}
		
		Composite left = new Composite(composites.get(3), 0);
		left.setLayout(new FillLayout());
		Composite right = new Composite(composites.get(3), 0);
		right.setLayout(new FillLayout());
		
		dialog = new Label(left, SWT.READ_ONLY | SWT.CENTER);
		dialog.setText("\n\nWelcome to Set");
		
		no_set = new Button(right, SWT.PUSH);
		no_set.setText("No Set exists.");
		no_set.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	      		if(!Set.exist_set(board_cards)) {
	      			dialog.setText("\n\nThere are no Set. Add 3 more cards.");
	      			ArrayList<Card> new_cards = set.draw_card(3);
	      			board_cards.addAll(new_cards);
	      			if(board_cards.size() == 15) {
	      				for(int i = 12;i < 15; i++) {
		      				String image_name = "data/" + board_cards.get(i).get_picture_name() + ".png";
		      				Image image = new Image(display, image_name);
		      				buttons.get(i).setImage(image);
	      				}
	      			} else if(board_cards.size() == 18) {
	      				for(int i = 15;i < 18; i++) {
		      				String image_name = "data/" + board_cards.get(i).get_picture_name() + ".png";
		      				Image image = new Image(display, image_name);
		      				buttons.get(i).setImage(image);
	      				}
	      			}
	      		}
	      		else dialog.setText("\n\nBe patient. There is Set exists.");
	            break;
	    }}});
		
		point_record = new Label(right, SWT.READ_ONLY | SWT.CENTER);
		point_record.setText("\n\nYou have got " + points + " set.");
		
		// generate new buttons
		for(int i = 0;i < 12; i++) {
			Button button = generate_card_button(display, composites.get(i % 3), board_cards.get(i));
			buttons.add(button);
		}
		
		for(int i = 12;i < 18; i++) {
			String image_name = "data/empty.png";
			Image image = new Image(display, image_name);
		    Button button = new Button(composites.get(i % 3), SWT.PUSH);
		    button.setImage(image);
		    buttons.add(button);
		}
		
		// add listener to each button
	    buttons.get(0).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	int button_num = 0;
	        	if(clicked_cards.contains(board_cards.get(button_num))) break;
        		clicked_cards.add(board_cards.get(button_num));
	        	if(clicked_cards.size() == 3) GUI_check_set();
	            break;
	    }}});
	    
	    buttons.get(1).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  int button_num = 1;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;
	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});
	    
	    buttons.get(2).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  int button_num = 2;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;
	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});
	    
	    buttons.get(3).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  int button_num = 3;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;
	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});
	    
	    buttons.get(4).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  int button_num = 4;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;
	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});
	    
	    buttons.get(5).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  int button_num = 5;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;
	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});
	    
	    buttons.get(6).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  int button_num = 6;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;
	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});
	    
	    buttons.get(7).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  int button_num = 7;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;

	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});
	    
	    buttons.get(8).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  int button_num = 8;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;
	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});
	    
	    buttons.get(9).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  int button_num = 9;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;
	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});

	    buttons.get(10).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  int button_num = 10;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;
	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});

	    buttons.get(11).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  int button_num = 11;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;
	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});

	    buttons.get(12).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  if(board_cards.size() <= 12) break;
	        	  int button_num = 12;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;
	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});

	    buttons.get(13).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  if(board_cards.size() <= 12) break;
	        	  int button_num = 13;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;
	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});

	    buttons.get(14).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  if(board_cards.size() <= 12) break;
	        	  int button_num = 14;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;
	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});

	    buttons.get(15).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  if(board_cards.size() <= 15) break;
	        	  int button_num = 15;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;
	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});

	    buttons.get(16).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  if(board_cards.size() <= 15) break;
	        	  int button_num = 16;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;
	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});

	    buttons.get(17).addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  if(board_cards.size() <= 15) break;
	        	  int button_num = 17;
		        	if(clicked_cards.contains(board_cards.get(button_num))) break;
	        		clicked_cards.add(board_cards.get(button_num));
		        	if(clicked_cards.size() == 3) GUI_check_set();
		            break;
	    }}});
	}
	
	public static void main(String args[]) {
		display = new Display();
		shell = new Shell(display);
		shell.setText("Set");
		shell.setSize(900, 400);
		Image image = new Image(display, "images/set.jpg");
		shell.setImage(image);
		
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		shell.setLayout(fillLayout);
		
		// generate a new deck and initial the cards
		set = new Deck();
		set.shuffle();
		board_cards = set.initialization();
		
		initial_buttons();
		
		shell.open();
		while(!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}
}