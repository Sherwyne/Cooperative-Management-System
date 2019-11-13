import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class User{
	String user_id,user_name,user_type,password,category;
	
	//IF UPDATING THIS ONE MAKE SURE TO UPDATE Database.java
	//Also update calling of User Object and its Child Class
	public User(String user_id, String user_name, String user_type, String password){
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_type = user_type; 
		this.password = password;
	}
	
	void update_name(String value){
		user_name = value;
	}
	
	void update_password(String value){
		password = value;
	}
}

class Professor extends User{
	static double loan_balance;
	
	public Professor(String user_id, String user_name, String user_type, String password, double loan_balance){
		super(user_id, user_name, user_type, password);
		this.loan_balance = loan_balance;
	}
	
	@Override
	public String toString(){
		return user_name + " " + user_type + " " + loan_balance;
	}
	
	void check_balance(){
		JOptionPane.showMessageDialog(null, "Loan Balance: \u20B1" + loan_balance);
	}
	
	void add_loan(){
		try{
			String str_loan = JOptionPane.showInputDialog(null, "Enter Amount to Loan: ");
			if(str_loan == null){
				//DO NOTHING!
			}
			else{
				double amount_loan = Double.parseDouble(str_loan);
				if(amount_loan == 0){
					JOptionPane.showMessageDialog(null, "Invalid Input!");
					add_loan();
				}
				else{
					double total = loan_balance + amount_loan;
					int confirm = JOptionPane.showConfirmDialog(null, "Do you want to loan \u20B1" + amount_loan + "? \nTotal Loan: \u20B1" + total, 
																		"Confirm Loan",
																		JOptionPane.YES_NO_OPTION);
					if(confirm == JOptionPane.YES_OPTION){
						loan_balance += amount_loan;
						JOptionPane.showMessageDialog(null, "Successfully Loaned \u20B1" + total);
					}
					total = 0;
				}
				
			}
		}
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Invalid Input!");
			add_loan();
		}
	}
	
	void pay_loan(){
		if(loan_balance == 0){
			JOptionPane.showMessageDialog(null, "No Balance To Pay");
		}
		else{
			try{
				
				String str_pay = JOptionPane.showInputDialog(null, "Total Loan Balance: \u20B1" + loan_balance + 
																						 "\nEnter Amount To Pay:");
			 	if(str_pay == null){
					//DO NOTHING
				}	
				else{
					double amount_pay = Double.parseDouble(str_pay);
					if(amount_pay > 0){
						int confirm = JOptionPane.showConfirmDialog(null, "Do you want to pay \u20B1" + amount_pay + "?", 
																	"Confirm Payment",
																	JOptionPane.YES_NO_OPTION);
						if(confirm == JOptionPane.YES_OPTION){
							double change = amount_pay - loan_balance;
							if(amount_pay < loan_balance){
								change = 0;
							}
							loan_balance -= amount_pay - change;
							JOptionPane.showMessageDialog(null, "Successfully Paid \u20B1" + amount_pay + 
																"\nChange: " + change +
																"\nTotal Loan Balance: \u20B1" + loan_balance);
						}
						double total = 0;
					}
					else{
						JOptionPane.showMessageDialog(null, "Invalid Input!");
						pay_loan();
					}
				}
			}
			
			catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Invalid Input!");
				pay_loan();
			}
		}	
	}
	
	void update_balance(double value){
		loan_balance = value;
	}
}

class Student extends User{

	public Student(String user_id, String user_name, String user_type, String password){
		super(user_id, user_name, user_type, password);
	}
	
	@Override
	public String toString(){
		return user_name + " " + user_type;
	}
	
	int view_items(){
		String[] item_type_menu = {"Book", "Lace", "Scantron", "Uniform"};
		int choice_type = JOptionPane.showOptionDialog(null, "Choose Item", "Menu | Item", 
							 JOptionPane.DEFAULT_OPTION,
							 JOptionPane.INFORMATION_MESSAGE,
							 null,
							 item_type_menu, item_type_menu[0]);
		return choice_type;
	}
		
	void view_purchase(HashMap<String, ArrayList<ArrayList<Object>>> receipt, String user_id){
		Object[] cols = {"Receipt Code", "Item ID", "Item Name", "Quantity", "Price", "Paid", "Change", "Item Type", "Refunded"};
		
		DefaultTableModel tableModel;
		JTable table;
		tableModel = new DefaultTableModel(cols, 0);
		try{
			ArrayList<ArrayList<Object>> list_purchase_info = receipt.get(user_id);
			for(ArrayList<Object> purchase_info : list_purchase_info){
				Object receipt_code = purchase_info.get(0);
				Object item_id = purchase_info.get(1);
				Object item_name = purchase_info.get(2);
				Object quantity = purchase_info.get(3);
				Object price = purchase_info.get(4);
				Object paid = purchase_info.get(5);
				Object change = purchase_info.get(6);
				Object type = purchase_info.get(7);
				Object isRefunded = purchase_info.get(8);
			 	Object[] rows = {receipt_code, type, item_id, item_name, quantity, "\u20B1"+price, "\u20B1"+paid, "\u20B1"+change, isRefunded};
			 	tableModel.addRow(rows);
			}
		}	
		catch(NullPointerException e){
			//DO NOTHING
		}
		finally{
			table = new JTable(tableModel);
			table.setAutoCreateRowSorter(true);
			JOptionPane.showMessageDialog(null, new JScrollPane(table));
		}		
	}
	
	void generate_receipt(HashMap<String, ArrayList<ArrayList<Object>>> receipt, String user_id, String product_id, String product_name, int product_quantity, double product_price, double paid_total, double change, String type){
		int receipt_count = receipt.size();
		String gen_receipt = user_id + product_id + "-" + receipt_count;
		ArrayList<ArrayList<Object>> list_purchase_info = new  ArrayList<ArrayList<Object>>();

		if(receipt.get(user_id) != null){
			list_purchase_info = receipt.get(user_id);
		}
		
		ArrayList<Object> purchase_info = new ArrayList<Object>(){
			{
				add(gen_receipt);
				add(product_id); 
				add(product_name);
				add(product_quantity);
				add(product_price);
				add(paid_total);
				add(change);
				add(type);
				add(false);
			}
		};
		
		list_purchase_info.add(purchase_info);
		
		receipt.put(user_id, list_purchase_info);
		JOptionPane.showMessageDialog(null, "Successfully Generated Receipt!" + "\nReceipt Code: " + gen_receipt);
	}

	Object[] refund_item(HashMap<String, ArrayList<ArrayList<Object>>> receipt,  String user_id){
		try{
			ArrayList<ArrayList<Object>> receipt_item= receipt.get(user_id);
			if(receipt_item.size() > 0){
				String input_receipt = JOptionPane.showInputDialog(null, "Enter Receipt Code: ");
				int receipt_index = -1;
				for(int i = 0; i < receipt_item.size(); i++){
						ArrayList<Object> item = receipt_item.get(i);
					Object gen_receipt = item.get(0);
					if(gen_receipt.equals(input_receipt)){
						receipt_index = i;
					}
				}
				if(receipt_index >= 0){
					ArrayList<Object> receipt_details = receipt_item.get(receipt_index);
					Boolean isRefunded = ((Boolean) receipt_details.get(8)).booleanValue();
					if(!isRefunded){
						String receipt_code = receipt_details.get(0).toString();
						String id = receipt_details.get(1).toString();
						String item_name = receipt_details.get(2).toString();
						int quantity = ((Integer) receipt_details.get(3)).intValue();
						double price = ((Double) receipt_details.get(4)).doubleValue();
						double paid = ((Double) receipt_details.get(5)).doubleValue();
						double change = ((Double) receipt_details.get(6)).doubleValue();
						String type = receipt_details.get(7).toString();
						String details = "Receipt Code: " + receipt_code + "\n" + 
								 		 "Item Type: " + type + "\n" + 
								 		 "Item ID: " + id + "\n" +  
								 		 "Item Name: " + item_name + "\n" +  
				 						 "Quantity: " + quantity + "\n" + 
				 						 "Item Price: " + "\u20B1"+price + "\n" + 
				 						 "Amount Paid: " + "\u20B1"+paid + "\n" +  
				 					     "Change: " + "\u20B1"+change;
						
						int confirm = JOptionPane.showConfirmDialog(null, "Do you want to refund the following? \n" + details, "Confirm Refund", JOptionPane.YES_NO_OPTION);
						if(confirm == JOptionPane.YES_OPTION){
							Object item_id = receipt_details.get(1);
							Object item_type = receipt_details.get(7);
							Object item_quantity = receipt_details.get(3);
							receipt_details.set(8, true);
							JOptionPane.showMessageDialog(null, "Successfully Refunded!");
							Object[] output = {item_id, item_type, item_quantity};
							return output;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Item Already Refunded!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Receipt Not Found!");
				}
			}
		}
		catch(NullPointerException e){
			//DO NOTHING
			JOptionPane.showMessageDialog(null, "No Items To Refund!");

		}
		return null;
	}
}

class Admin extends User{
	
	public Admin(String user_id, String user_name, String user_type, String password){
		super(user_id, user_name, user_type, password);
	}
	
	//USER MANAGEMENT
	void manage_user(){
		JOptionPane.showMessageDialog(null, "MANAGE USER");
	}
	
	//CONTROLS USER
	void view_user(){
		JOptionPane.showMessageDialog(null, "VIEW USER");
	}
	
	void update_user(){
		JOptionPane.showMessageDialog(null, "UPDATE USER");
	}
	
	void delete_user(){
		JOptionPane.showMessageDialog(null, "DELETE USER");
	}
	
	//CONTROLS ITEM
	int menu_item(){
		String[] item_type_menu = {"Book", "Lace", "Scantron", "Uniform"};
		int choice_type = JOptionPane.showOptionDialog(null, "Choose Item", "Menu | Item", 
							 JOptionPane.DEFAULT_OPTION,
							 JOptionPane.INFORMATION_MESSAGE,
							 null,
							 item_type_menu, item_type_menu[0]);
		return choice_type;
	}
	//CONTROLS USER
	int menu_user(){
		String[] user_type_menu = {"Student", "Professor"};
		double loan_balance;
		
		int choice_type = JOptionPane.showOptionDialog(null, "Choose User", "Menu | User", 
							 JOptionPane.DEFAULT_OPTION,
							 JOptionPane.INFORMATION_MESSAGE,
							 null,
							 user_type_menu, user_type_menu[0]);
		return choice_type;
	}
}