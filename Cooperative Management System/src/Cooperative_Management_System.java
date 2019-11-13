										/* Features:
										 * Login System
										 * Registration System
										 *	Main Menu
										 *		Student
										 *			View Available Item/s
										 *			Buy Item/s
										 *				Books(Title, Professor Referral[Optional])
										 *				Lace(College)
										 *				Scantron(Quantity)
										 *				Uniform(College, Sex, Size)
										 *			Note: Give receipt every bought(must be unique)
										 *
										 *			Replace Item/s(Item, Receipt)
										 *			Activity Log
										 *		Professor
										 *			Loan(Amount, Deadline)
										 * Note(9/22/2019): 	
										 *		Add deadline feature in loan
										 *		//If user did not paid on or before the deadline
										 *		  Penalty = (Paid Date - Deadline Date) * 5  
										 *				
										 *			
										 *			Books(Title)
										 *		Admin
										 *			C(Create)R(Read)U(Update)D(Delete) Items
										 *	`		Balance/Loan of Professor
										 *			Logs
										 *		
										 */
/*
 	//FOR GETTING A DATA
	Object book1 = db.items.get("BOOK");
	System.out.print(dataset(book1.toString()));
	
	//SPECIFIC
	System.out.print(dataset(book1.toString()).get("item_author"));

	
*/
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Cooperative_Management_System {
	public static Database db = new Database();

	public static void main(String[] args) {
		
											//TEST DATA//
		
		//ACCOUNTS
		db.students.put("admin", new Student("admin", "Sherwyne", "Student", "admin"));
		db.professors.put("admin", new Professor("admin", "Dr. Sherwyne", "Professor", "admin", 0));
	
		//ITEMS//
		
		//BOOKS
		for(int i = 1; i <= 10; i++){
			db.books.put("book" + i, new Book("book" + i, "BOOK_"+i, "Book", 5 + i, 100 + i, "Noli Me", "SHERWYNE", true));
		}
		
		//LACE
		db.laces.put("lace1", new Lace("lace1", "CCS Lace", "Lace", 50, 100, "CSS", true));
		db.laces.put("lace2", new Lace("lace2", "CBFS Lace", "Lace", 50, 100, "CBFS", true));
		db.laces.put("lace3", new Lace("lace3", "CTM Lace", "Lace", 50, 100, "CTM", true));

		//SCANTRON
		db.scantrons.put("scantron1", new Scantron("scantron1", "Scantron", "Scantron", 9999, 5, true));
		
		//
		db.uniforms.put("uniform1", new Uniform("uniform1", "CCS Uniform (Male)", "Uniform", 100, 1200, "CCS", true));
		db.uniforms.put("uniform2", new Uniform("uniform2", "CCS Uniform (Female)", "Uniform", 100, 1200, "CCS", true));
		db.uniforms.put("uniform3", new Uniform("uniform3", "CBFS Uniform (Male)", "Uniform", 100, 2000, "CBFS", true));
		db.uniforms.put("uniform4", new Uniform("uniform4", "CBFS Uniform (Female)", "Uniform", 100, 2000, "CBFS", true));
		db.uniforms.put("uniform5", new Uniform("uniform5", "CTM Uniform (Male)", "Uniform", 100, 3000, "CTM", true));
		db.uniforms.put("uniform6", new Uniform("uniform6", "CTM Uniform (Female)", "Uniform", 100, 3000, "CTM", true));

		int main_choice = 999;
		while(main_choice != -1){
			main_choice = db.main_menu();
			switch(main_choice){
				case 0:
					//Login
					db.login();
					break;
				case 1:
					//Register
					db.register();
					break;
			}
		}
	}
	
	public static HashMap<String, Object> dataset(String object){
		HashMap<String, Object> data = new HashMap<String, Object>();
		String[] raw = object.split(",");
		String item_type = raw[1];
		int raw_length = raw.length - 1;
		
		switch(item_type){
			case "book":
				data.put("item_id", raw[0]);
				data.put("item_type", raw[1]);
				data.put("item_quantity", raw[2]);
				data.put("item_price", raw[3]);
				data.put("item_title", raw[4]);
				data.put("item_author", raw[5]);
				data.put("is_available", raw[6]);
				break;
			case "scantron":
				data.put("item_id", raw[0]);
				data.put("item_type", raw[1]);
				data.put("item_quantity", raw[2]);
				data.put("item_price", raw[3]);
				data.put("is_available", raw[6]);
				break;
			case "lace":
				data.put("item_id", raw[0]);
				data.put("item_type", raw[1]);
				data.put("item_quantity", raw[2]);
				data.put("item_price", raw[3]);
				data.put("college", raw[4]);
				data.put("is_available", raw[5]);
				break;
			case "uniform":
				data.put("item_id", raw[0]);
				data.put("item_type", raw[1]);
				data.put("item_quantity", raw[2]);
				data.put("item_price", raw[3]);
				data.put("college", raw[4]);
				data.put("gender", raw[5]);
				data.put("size", raw[6]);
				data.put("is_available", raw[7]);
				break;		
		}
		return data;
	}
}