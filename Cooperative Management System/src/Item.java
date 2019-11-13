import java.util.HashMap;
import javax.swing.JOptionPane;

public class Item {
	String item_id, item_type, item_name;
	boolean available;
	int item_quantity;
	double item_price;
	
	
    public Item(String item_id, String item_name, String item_type, int item_quantity, double item_price, boolean available) {
    	this.item_id = item_id;
    	this.item_name = item_name;
    	this.item_type = item_type;
    	this.item_quantity = item_quantity;
    	this.item_price = item_price;
    	this.available = available;
    }
    
    void update_id(String new_value){
    	item_id = new_value;
    }
    
    void update_name(String new_value){
    	item_name = new_value;
    }
    
    void update_type(String new_value){
    	item_type = new_value;
    }
    
    void update_quantity(int new_value){
    	if(new_value > 0){
    		item_quantity = new_value;
    		available = true;
    	}
    	else{
    		item_quantity = 0;
    		available = false;
    	}
    }
    
    void add_quantity(int new_value){
    	item_quantity += new_value;
    	available = true;	
    }
    
    void update_price(double new_value){
    	item_price = new_value;
    }
    
    void update_available(boolean new_value){
    	available = new_value;
    }
    
    void buy(double amount_pay, int buy_quantity, String user_type){
		if(item_quantity - buy_quantity >= 0){
			double total = buy_quantity * item_price;
	    	double change = amount_pay - total;
    		item_quantity -= buy_quantity;
    		available = (item_quantity == 0) ? false : true;
    		
    		if(user_type.equals("Student")){	
    			JOptionPane.showMessageDialog(null, "Transaction Complete! \nChange: " + change);
    		}
    		else{
    			JOptionPane.showMessageDialog(null, "Transaction Complete!");
    		}
    	}
    	else if(item_quantity - buy_quantity < 0){
    		JOptionPane.showMessageDialog(null, "Out of Stock!");
    	}	
    }
}

class Book extends Item{
	String title, author;
	
	public Book(String item_id, String item_name, String item_type, int item_quantity, double item_price, String title, String author, boolean available){
		super(item_id, item_name, item_type, item_quantity, item_price, available);
		this.title = title;
		this.author = author;
		
	}
	
	void update_title(String new_value){
		title = new_value;
	}
	
	void update_author(String new_value){
		author = new_value;
	}
	
	@Override
	public String toString(){
		return item_id + "," + item_type + "," + item_quantity + "," + item_price + "," + title + "," + author + "," + available;
	}
	
}

class Lace extends Item{
	String college;
	public Lace(String item_id, String item_name, String item_type, int item_quantity, double item_price, String college, boolean available){
		super(item_id, item_name, item_type, item_quantity, item_price, available);
		this.college = college;
	}
	
	void update_college(String new_value){
		college = new_value;
	}
	
	@Override
	public String toString(){
		return item_id + "," + item_type + "," + item_quantity + "," + item_price + "," + college + "," + available;
	}
}

class Scantron extends Item{
	
	public Scantron(String item_id, String item_name, String item_type, int item_quantity, double item_price, boolean available){
		super(item_id, item_name, item_type, item_quantity, item_price, available);
		
	}
	
	@Override
	public String toString(){
		return item_id + "," + item_type + "," + item_quantity + "," + item_price + "," + available;
	}
}

class Uniform extends Item{
	String college;
	
	public Uniform(String item_id, String item_name, String item_type, int item_quantity, double item_price, String college, boolean available){
		super(item_id, item_name, item_type, item_quantity, item_price, available);
		this.college = college;
	}
	
	void update_college(String new_value){
		college = new_value;
	}
	
	@Override
	public String toString(){
		return item_id + "," + item_type + "," + item_quantity + "," + item_price + "," + college + "," + available;
	}
}