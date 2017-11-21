public class Card {
	private String quantity;
	private String color;
	private String shape;
	private String shading;
	
	public Card(String quantity, String color, String shape, String shading) {
		this.quantity = quantity;
		this.color = color;
		this.shape = shape;
		this.shading = shading;
	}
	
	public String get_quantity() {
		return this.quantity;
	}
	
	public String get_color() {
		return this.color;
	}
	
	public String get_shape() {
		return this.shape;
	}
	
	public String get_shading() {
		return this.shading;
	}
	
	public String get_picture_name() {
		return this.quantity + "_" + this.color + "_" + this.shape + "_" + this.shading;
	}
	
	public void printout() {
		System.out.println(this.quantity + " " + this.color + " " + this.shape + " " + this.shading);
	}
}


