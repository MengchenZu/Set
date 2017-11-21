import java.awt.GridLayout;
import java.util.ArrayList;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class Gui {
	static ArrayList<Card> clicked_cards = new ArrayList<Card>();
	
	public static Button generate_card_button(Display display, Composite composite, Card card) {
		String image_name = "data/" + card.get_picture_name() + ".png";
		Image image = new Image(display, image_name);
	    Button button = new Button(composite, SWT.PUSH);
	    button.setImage(image);
	    
		return button;
	}
	
	public static void initial_buttons(Display display, Shell shell, ArrayList<Card> cards) {

		Composite composite1 = new Composite(shell, 0);
		composite1.setLayout(new FillLayout());
		Composite composite2 = new Composite(shell, 0);
		composite2.setLayout(new FillLayout());
		Composite composite3 = new Composite(shell, 0);
		composite3.setLayout(new FillLayout());
		
		// generate new buttons
		Button button1 = generate_card_button(display, composite1, cards.get(0));
		Button button2 = generate_card_button(display, composite2, cards.get(1));
		Button button3 = generate_card_button(display, composite3, cards.get(2));
		Button button4 = generate_card_button(display, composite1, cards.get(3));
		Button button5 = generate_card_button(display, composite2, cards.get(4));
		Button button6 = generate_card_button(display, composite3, cards.get(5));
		Button button7 = generate_card_button(display, composite1, cards.get(6));
		Button button8 = generate_card_button(display, composite2, cards.get(7));
		Button button9 = generate_card_button(display, composite3, cards.get(8));
		Button button10 = generate_card_button(display, composite1, cards.get(9));
		Button button11 = generate_card_button(display, composite2, cards.get(10));
		Button button12 = generate_card_button(display, composite3, cards.get(11));
	    
		// add listener to each button
	    button1.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	if(clicked_cards.size() == 2) {
	        		clicked_cards.add(cards.get(0));
	        		
	        		clicked_cards.clear();
	        		for(int i = 0;i < clicked_cards.size(); i++) {
	        			clicked_cards.get(i).printout();
	        		}
	        	}
	        	else {
	        		clicked_cards.add(cards.get(0));
	        		for(int i = 0;i < clicked_cards.size(); i++) {
	        			clicked_cards.get(i).printout();
	        		}
	        	}
	        	System.out.println(clicked_cards.size());
	        	System.out.println("Button 1 has been clicked");
	            break;
	    }}});
	    
	    button2.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	System.out.println("Button 2 has been clicked");
	            break;
	    }}});
	    
	    button3.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	System.out.println("Button 3 has been clicked");
	            break;
	    }}});
	    
	    button4.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	System.out.println("Button 4 has been clicked");
	            break;
	    }}});
	    
	    button5.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	System.out.println("Button 5 has been clicked");
	            break;
	    }}});
	    
	    button6.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	System.out.println("Button 6 has been clicked");
	            break;
	    }}});
	    
	    button7.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	System.out.println("Button 7 has been clicked");
	            break;
	    }}});
	    
	    button8.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	System.out.println("Button 8 has been clicked");
	            break;
	    }}});
	    
	    button9.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	System.out.println("Button 9 has been clicked");
	            break;
	    }}});
	    
	    button10.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	System.out.println("Button 10 has been clicked");
	            break;
	    }}});

	    button11.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	System.out.println("Button 11 has been clicked");
	            break;
	    }}});

	    button12.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	System.out.println("Button 12 has been clicked");
	            break;
	    }}});
	}
	
	public static void main(String args[]) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Set");
		shell.setSize(500, 250);
		
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		shell.setLayout(fillLayout);
		
		// generate a new deck and initial the cards
		Deck set = new Deck();
		set.shuffle();
		ArrayList<Card> board_cards = set.initialization();
		
		initial_buttons(display, shell, board_cards);
		
		shell.open();
		while(!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}
}