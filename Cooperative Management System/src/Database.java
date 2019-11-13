import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

class Database {
	HashMap<String, Student> students = new HashMap<String, Student>();
	HashMap<String, Professor> professors = new HashMap<String, Professor>();
	
	HashMap<String, Book> books = new HashMap<String, Book>();
	HashMap<String, Lace> laces = new HashMap<String, Lace>();
	HashMap<String, Scantron> scantrons = new HashMap<String, Scantron>();
	HashMap<String, Uniform> uniforms = new HashMap<String, Uniform>();
	HashMap<String, ArrayList<ArrayList<Object>>> receipt =  new HashMap<String, ArrayList<ArrayList<Object>>>();

	
	
	ArrayList<Object> items = new ArrayList<Object>();

	String[] login_menu = {"Student", "Professor", "Admin"};
	String[] register_menu = {"Student", "Professor"};
	
	int student_population = 0;
	int professor_population = 0;
	
	public Database(){
		this.students = students;
		this.professors = professors;
		this.books = books;
		this.laces = laces;
		this.scantrons = scantrons;
		this.uniforms = uniforms;
	}
	
	public static int main_menu(){
		String[] main_menu = {"Login", "Register"};
		return JOptionPane.showOptionDialog(null,"-----Cooperative Management System----- \n     Developed By: Sherwyne Costiniano\n\n\n",
    													"Main Menu",
    													JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
    													null, main_menu, main_menu[0]);
	}
	
	void login(){
		int user_type = JOptionPane.showOptionDialog(null, "What type of user are you?",
														"Login | User Type",
														JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
														null, login_menu, login_menu[0]);
		if(user_type == JOptionPane.CLOSED_OPTION){
			//DO NOTHING
		}
		else{
			String user_id = JOptionPane.showInputDialog(null, "Enter your User ID: ", "Login | User ID", JOptionPane.INFORMATION_MESSAGE);
			if(user_id == null){
				//DO NOTHING
			}
			
			else{
				String password = "";
				JPasswordField jpf = new JPasswordField(24);
				int action = JOptionPane.showConfirmDialog(null, jpf,"Login | Password" ,JOptionPane.OK_CANCEL_OPTION);
				
			    if(action == JOptionPane.CANCEL_OPTION || action == JOptionPane.CLOSED_OPTION){
			    	//DO NOTHING
		    	}
			    else{
			    	password = String.valueOf(jpf.getPassword());
			    	switch(user_type){
				    	case 0:
				    		if(students.get(user_id) != null){
				    			Student user = students.get(user_id);
				    			if(password.equals(user.password)){
				    				student_flow(user.user_id, user.user_name, user.user_type, user.password);
				    			}
				    			else{
				    				JOptionPane.showMessageDialog(null, "Incorrect UserID or Password!");
				    			}
				    		}
				    		else{
				    			JOptionPane.showMessageDialog(null, "Student Not Found!");
				    		}
				    		break;
				    	case 1:
				    		if(professors.get(user_id) != null){
				    			Professor user = professors.get(user_id);
				    			if(password.equals(user.password)){
				    				professor_flow(user.user_id, user.user_name, user.user_type, user.password, user.loan_balance);
				    			}
				    			else{
				    				JOptionPane.showMessageDialog(null, "Incorrect UserID or Password!");
				    			}
				    		}
				    		else{
				    			JOptionPane.showMessageDialog(null, "Professor Not Found!");
				    		}
				    		break;
				    	case 2:
				    		//ADMIN
				    		if(user_id.equals("admin")){
				    			if(password.equals("admin")){
				    				admin_flow(user_id, "Admin", "Admin", "admin");
				    			}
				    			else{
				    				JOptionPane.showMessageDialog(null, "Incorrect UserID or Password!");
				    			}
				    		}
				    		else{
				    			JOptionPane.showMessageDialog(null, "Admin Not Found!");
				    		}
				    		break;
				    }
				}	
			}	
				
		}
	}
	
	void register(){
		String user_id = "";
		String user_type = "";
		String password = "";


		String user_name = JOptionPane.showInputDialog(null, "Enter your name: ", "Register | Name", JOptionPane.INFORMATION_MESSAGE);
		if(user_name == null){
	    	//DO NOTHING
	    }
	    else if(user_name.isEmpty()){
	    	JOptionPane.showMessageDialog(null, "Invalid Input!");
	    }
	    else{
			int type_choice = JOptionPane.showOptionDialog(null, "What type of user are you?",
															"Register | User Type",
															JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
															null, register_menu, register_menu[0]);
			if(type_choice == JOptionPane.CANCEL_OPTION || type_choice == JOptionPane.CLOSED_OPTION){
	    		//DO NOTHING
			}
			else{
				JPasswordField jpf = new JPasswordField(24);
				int action = JOptionPane.showConfirmDialog(null, jpf,"Register | Password" ,JOptionPane.OK_CANCEL_OPTION);
		
			    if(action == JOptionPane.CANCEL_OPTION || action == JOptionPane.CLOSED_OPTION){
			    	//DO NOTHING
			    }
			    else{
			    	password = String.valueOf(jpf.getPassword());
			    	if (password.isEmpty()){	
	    				JOptionPane.showMessageDialog(null, "Invalid Input!");
			    	}
			    	else{
				    	switch(type_choice){
				    		case 0:
				    			//Student
				    			student_population = students.size();
				    			user_type = "Student";
				    			user_id = "STUDENT" + student_population;
								students.put(user_id, new Student(user_id, user_name, user_type, password));
				    			break;
				    		case 1:
				    			//Professor
				    			professor_population = professors.size();
				    			user_type = "Professor";
				    			user_id = "PROFESSOR" + professor_population;
				    			professors.put(user_id, new Professor(user_id, user_name, user_type, password, 0));
				    			break;
				    	}
				    	JOptionPane.showMessageDialog(null, "Registered Successfully! \nYour UserID is: " + user_id, "Register | Success", JOptionPane.INFORMATION_MESSAGE);
			    	}
			    }
			}
		}
	}
		
	public int professor_menu(String user_name, String user_type){
    	String[] menu = {"Check Balance", "Loan", "Pay Loan", "Get Book/s"};
		return JOptionPane.showOptionDialog(null, "Welcome " + user_type + " " + user_name,
    													"Main Menu",
    													JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
    													null, menu, menu[0]);												
	}
	
	public void professor_flow(String user_id, String user_name, String user_type, String password, double loan_balance){
		//Professor Menu
    	Professor user = new Professor(user_id, user_name, user_type, password, loan_balance);
    	int choice=0;
    	while(choice >= 0){
    		choice = professor_menu(user.user_name,user.user_type);
    		switch(choice){
    			case 0:
    				user.check_balance();
    				break;
    			case 1:
    				user.add_loan();
    				break;
    			case 2:
    				user.pay_loan();
    				break;
    			case 3:
    				try{
    					buy_book_professor(user_type);
    				}
    				catch(Exception e){
    					JOptionPane.showMessageDialog(null, "Item Not Found!");
    				}
    				break;
    			case JOptionPane.CLOSED_OPTION:
    				//DO NOTHING
    				break;
			}
		}
	}

	public int student_menu(String user_name, String user_type){
    	String[] menu = {"Items", "Purchase History", "Refund Item"};
		return JOptionPane.showOptionDialog(null, "Welcome " + user_type + " " + user_name,
    													"Main Menu",
    													JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
    													null, menu, menu[0]);												
	}
		
	public void student_flow(String user_id, String user_name, String user_type, String password){
		Student user = new Student(user_id, user_name, user_type, password);
		String item_id, title, author, college, available; 
		int item_quantity;
		double item_price;
		int choice=0, choice_menu = JOptionPane.CLOSED_OPTION, choice_item = JOptionPane.CLOSED_OPTION;
    	while(choice >= 0){
    		choice = student_menu(user.user_name,user.user_type);
    		switch(choice){
    			case 0:
    				//ITEM
    				choice_menu = manage_items(user.user_type);
    				switch(choice_menu){
    					case 0:
    						choice_item = user.view_items();
    						try{
	    						switch(choice_item){
	    							case 0:
	    								view_book();
	    								break;
	    							case 1:
	    								view_lace();
	    								break;
	    							case 2:
	    								view_scantron();
	    								break;
	    							case 3:
	    								view_uniform();
	    								break;
	    						}
    						}
    						catch(Exception e){
    							JOptionPane.showMessageDialog(null, "Item Not Found!");
    						}
    						break;
    					case 1:
    						choice_item = user.view_items();
    						try{
	    						switch(choice_item){
	    							case 0:
	    								buy_book(user_type, user, receipt);
	    								break;
	    							case 1:
	    								buy_lace(user_type, user, receipt);
	    								break;
	    							case 2:
	    								buy_scantron(user_type, user, receipt);
	    								break;
	    							case 3:
	    								buy_uniform(user_type, user, receipt);
	    								break;
	    						}
    						}
    						catch(Exception e){
    							e.printStackTrace();
    							JOptionPane.showMessageDialog(null, "Item Not Found!");
    						}
    						break;
    				}
    				break;
    			case 1:
    				//PURCHASE HISTORY
    				user.view_purchase(receipt, user_id);
    				break;
    			case 2:
    				//REFUND ITEM
    				try{
	    				Object[] refund = user.refund_item(receipt, user_id);
	    				String refund_id = refund[0].toString();
	    				String refund_type = refund[1].toString();
	 	    			int refund_quantity = ((Integer) refund[2]).intValue();
	 	    			
	    				switch(refund_type){
	    					case "Book":
	    						Book book = books.get(refund_id);
	    						book.add_quantity(refund_quantity);
	    						break;
	    					case "Lace":
	    						Lace lace = laces.get(refund_id);
	    						lace.add_quantity(refund_quantity);
	    						break;
	    					case "Scantron":
	    						Scantron scantron = scantrons.get(refund_id);
	    						scantron.add_quantity(refund_quantity);
	    						break;
	    					case "Uniform":
	    						Uniform uniform = uniforms.get(refund_id);
	    						uniform.add_quantity(refund_quantity);
	    						break;
	    				}
    				}
    				catch(NullPointerException e){
    					//DO NOTHING!
    				}
    				break;
    			case JOptionPane.CLOSED_OPTION:
    				//DO NOTHING
    				break;
			}
		}
	}
	
	public int admin_menu(String user_name, String user_type){
    	String[] menu = {"Manage Users", "Manage Items", "Activity Log"};
		return JOptionPane.showOptionDialog(null, "Welcome " + user_type,
    													"Main Menu",
    													JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
    													null, menu, menu[0]);												
	}
	
 	public void admin_flow(String user_id, String user_name, String user_type, String password){
		Admin user = new Admin(user_id, user_name, user_type, password);
		int choice = 0, choice_menu = JOptionPane.CLOSED_OPTION, choice_item = JOptionPane.CLOSED_OPTION;
    	while(choice >= 0){
    		choice = admin_menu(user.user_name,user.user_type);
    		switch(choice){
    			case 0:
    				choice_menu = manage_user(user.user_type);
    				switch(choice_menu){
    					case 0:
    						add_users(user);
    						break;
    					case 1:
    						view_users(user);
    						break;
    					case 2:
    						update_users(user);
    						break;
    					case 3:
    						delete_users(user);
    						break;
    				}
    				break;
    			case 1:
    				choice_menu = manage_items(user.user_type);
    				switch(choice_menu){
    					case 0:
    						//ADD ITEM
    						try{
	    						choice_item = user.menu_item();
	    						switch(choice_item){
	    							case 0:
	    								add_book();
	    								break;
	    							case 1:
	    								add_lace();
	    								break;
	    							case 2:
	    								add_scantron();	
	    								break;
	    							case 3:
	    								add_uniform();
	    								break;
	    						}
    						}
    						catch(Exception e){
								JOptionPane.showMessageDialog(null, "Add Failed!");
    						}
    						break;
    					case 1:
    						choice_item = user.menu_item();
    						try{
	    						switch(choice_item){
	    							case 0:
	    								view_book();
	    								break;
	    							case 1:
	    								view_lace();
	    								break;
	    							case 2:
	    								view_scantron();	
	    								break;
	    							case 3:
	    								view_uniform();
	    								break;
	    						}
    						}
    						catch(Exception e){
								JOptionPane.showMessageDialog(null, "Item Not Found!");
    						}
    						break;
    					case 2:
    						try{
	    						choice_item = user.menu_item();
	    						switch(choice_item){
	    							case 0:
	    								update_book();
	    								break;
	    							case 1:
	    								update_lace();
	    								break;
	    							case 2:
	    								update_scantron();	
	    								break;
	    							case 3:
	    								update_uniform();
	    								break;
	    						}
    						}
    						catch(Exception e){
								JOptionPane.showMessageDialog(null, "Update Failed!");
    						}
    						break;
    					case 3:
    						try{
	    						choice_item = user.menu_item();
	    						switch(choice_item){
	    							case 0:
	    								delete_book();
	    								break;
	    							case 1:
	    								delete_lace();
	    								break;
	    							case 2:
	    								delete_scantron();	
	    								break;
	    							case 3:
	    								delete_uniform();
	    								break;
	    						}
    						}
    						catch(Exception e){
								JOptionPane.showMessageDialog(null, "Delete Failed!");
    						}
    						break;
    				}
    			case 2:
    				//ACTIVITY LOG
    				break;
    			case JOptionPane.CLOSED_OPTION:
    				//DO NOTHING
    				break;
			}
		}
	}
	
	int manage_items(String user_type){
		String[] admin_options = {"Add", "Search", "Replace", "Delete"};
		String[] normal_option = {"Search", "Buy"};
		String[] options = (!user_type.equals("Admin")) ? normal_option : admin_options;
		
		Object[] cols = {"Item ID", "Item Name","Quantity","Price", "Status"};
		
		String item_id, item_name, available; 
		int item_quantity, option_choice;
		double item_price;
		
		DefaultTableModel tableModel;
		JTable table;
		tableModel = new DefaultTableModel(cols, 0);
		
		for(String key : books.keySet()){
			Book book = books.get(key);
			item_id = book.item_id;
			item_name = book.item_name;
			item_quantity = book.item_quantity;
			item_price = book.item_price;
			available = book.available ? "Available" : "Not Available";
		 	Object[] brows = {item_id, item_name, item_quantity, "\u20B1"+item_price, available};
		 	tableModel.addRow(brows);
		}
		for(String key : laces.keySet()){
			Lace lace = laces.get(key);
			item_id = lace.item_id;
			item_name = lace.item_name;
			item_quantity = lace.item_quantity;
			item_price = lace.item_price;
			available = lace.available ? "Available" : "Not Available";
		 	Object[] lrows = {item_id, item_name, item_quantity, "\u20B1"+item_price, available};
		 	tableModel.addRow(lrows);
		}
		for(String key : scantrons.keySet()){
			Scantron scantron = scantrons.get(key);
			item_id = scantron.item_id;
			item_name = scantron.item_name;
			item_quantity = scantron.item_quantity;
			item_price = scantron.item_price;
			available = scantron.available ? "Available" : "Not Available";
		 	Object[] srows = {item_id, item_name, item_quantity, "\u20B1"+item_price, available};
		 	tableModel.addRow(srows);
		}
		for(String key : uniforms.keySet()){
			Uniform uniform = uniforms.get(key);
			item_id = uniform.item_id;
			item_name = uniform.item_name;
			item_quantity = uniform.item_quantity;
			item_price = uniform.item_price;
			available = uniform.available ? "Available" : "Not Available";
		 	Object[] urows = {item_id, item_name, item_quantity, "\u20B1"+item_price, available};
		 	tableModel.addRow(urows);
		}
    							
		table = new JTable(tableModel);
		table.setAutoCreateRowSorter(true);
		option_choice = JOptionPane.showOptionDialog(null, new JScrollPane(table), "List of Items", 
									JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
									null, options, options[0]);
		return option_choice;
	}	
	int manage_user(String user_type){
		int option_choice;
		String[] options = {"Add User", "View User", "Update User", "Delete User"};
		String user_id, user_name, user_password;
		Object[] cols = {"User ID", "User Type", "Name","Password"};
		
		DefaultTableModel tableModel;
		JTable table;
		tableModel = new DefaultTableModel(cols, 0);
		
		for(String key : students.keySet()){
			Student student = students.get(key);
			user_id = student.user_id;
			user_type = student.user_type;
			user_name = student.user_name;
			user_password = student.password;
		 	Object[] rows = {user_id, user_type, user_name, user_password};
		 	tableModel.addRow(rows);
		}
		for(String key : professors.keySet()){
			Professor professor = professors.get(key);
			user_id = professor.user_id;
			user_type = professor.user_type;
			user_name = professor.user_name;
			user_password = professor.password;
		 	Object[] rows = {user_id, user_type, user_name, user_password};
		 	tableModel.addRow(rows);
		}
								
		table = new JTable(tableModel);
		table.setAutoCreateRowSorter(true);
		option_choice = JOptionPane.showOptionDialog(null, new JScrollPane(table), "List of Users", 
									JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
									null, options, options[0]);
		return option_choice;
	}
	
	void buy_book(String user_type, Student user, HashMap<String, ArrayList<ArrayList<Object>>> receipt){
		String item_id,item_title, item_author, college;
		int item_quantity, confirm, buy_quantity=0;
		double item_price, amount_pay=0;
		try{
			item_id = JOptionPane.showInputDialog(null, "Enter Book ID: ");
			Book book = books.get(item_id);
			
			item_title = book.title;
			item_author = book.author;
			item_quantity = book.item_quantity;
			item_price = book.item_price;
			String item_type = book.item_type;
			if(item_quantity > 0){
				confirm = JOptionPane.showConfirmDialog(null, "Do you want to BUY the following?\n" +
					"Book ID: " + item_id + "\n" +
					"Title: " + item_title  + "\n" +
					"Author: " + item_author  + "\n" +
					"Quantity: " + item_quantity + "\n" +
					"Price: " + item_price, "Buy | Book", JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION){
					buy_quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Quantity: "));
	
		    		double total = buy_quantity * item_price;
		    		int pay_confirm = JOptionPane.showConfirmDialog(null, "Total: \u20B1" + total, "Confirm Payment", JOptionPane.YES_NO_OPTION);
		    		
					if(pay_confirm == JOptionPane.YES_OPTION){
						
						while(amount_pay < total){
							amount_pay = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter Amount To Pay: "));
								if(amount_pay >= total){
									book.buy(amount_pay, buy_quantity, user_type);
									user.generate_receipt(receipt, user.user_id, item_id, item_title, buy_quantity, item_price, amount_pay, amount_pay - total, item_type);
								}
								else{
						    		JOptionPane.showMessageDialog(null, "Insufficient Amount!");
						    	}
						}
					}
					else{
					    JOptionPane.showMessageDialog(null, "Transaction Aborted!");
					}	
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Out Of Stock!");
			}
			
		}
		catch(Exception e){
			//DO NOTHING
		}
	}
	void buy_book_professor(String user_type){
		String item_id,item_title, item_author, college;
		int item_quantity, confirm, buy_quantity=0;
		double item_price, amount_pay=0;
		item_id = JOptionPane.showInputDialog(null, "Enter Book ID: ");
		Book book = books.get(item_id);
		
		item_title = book.title;
		item_author = book.author;
		item_quantity = book.item_quantity;
		item_price = book.item_price;
		if(item_quantity > 0){
			confirm = JOptionPane.showConfirmDialog(null, "Do you want to BUY the following?\n" +
				"Book ID: " + item_id + "\n" +
				"Title: " + item_title  + "\n" +
				"Author: " + item_author  + "\n" +
				"Quantity: " + item_quantity + "\n" +
				"Price: " + item_price, "Buy | Book", JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION){
				buy_quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Quantity: "));
	    		double total = buy_quantity * item_price;
	    		int pay_confirm = JOptionPane.showConfirmDialog(null, "Total: \u20B1" + total, "Confirm Payment", JOptionPane.YES_NO_OPTION);
	    		
				if(pay_confirm == JOptionPane.YES_OPTION){
					amount_pay = total;
						if(amount_pay >= total){
							book.buy(amount_pay, buy_quantity, user_type);
						}
						else{
				    		JOptionPane.showMessageDialog(null, "Insufficient Amount!");
				    	}
				}
				else{
				    JOptionPane.showMessageDialog(null, "Transaction Aborted!");
				}	
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Out Of Stock!");
		}
	}	
	void buy_lace(String user_type, Student user, HashMap<String, ArrayList<ArrayList<Object>>> receipt){
		try{
			String item_id, item_name, college;
			int item_quantity, confirm, buy_quantity=0;
			double item_price, amount_pay=0;
			item_id = JOptionPane.showInputDialog(null, "Enter Lace ID: ");
			Lace lace = laces.get(item_id);
			item_name = lace.item_name;
			college = lace.college;
			item_quantity = lace.item_quantity;
			item_price = lace.item_price;
			String item_type = lace.item_type;
			if(item_quantity > 0){
				confirm = JOptionPane.showConfirmDialog(null, "Do you want to BUY the following?\n" +
					"Lace ID: " + item_id + "\n" +
					"College: " + college  + "\n" +
					"Quantity: " + item_quantity + "\n" +
					"Price: " + item_price, "Buy | Lace", JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION && user_type.equals("Student")){
					buy_quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Quantity: "));
		    		double total = buy_quantity * item_price;
		    		int pay_confirm = JOptionPane.showConfirmDialog(null, "Total: \u20B1" + total, "Confirm Payment", JOptionPane.YES_NO_OPTION);
		    		
					if(pay_confirm == JOptionPane.YES_OPTION){
						amount_pay = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter Amount To Pay: "));
							if(amount_pay >= total){
								lace.buy(amount_pay, buy_quantity, user_type);
								user.generate_receipt(receipt, user.user_id, item_id, item_name, buy_quantity, item_price, amount_pay, amount_pay - total, item_type);
							}
							else{
					    		JOptionPane.showMessageDialog(null, "Insufficient Amount!");
					    	}
					}
					else{
					    JOptionPane.showMessageDialog(null, "Transaction Aborted!");
					}	
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Out Of Stock!");
			}
		}
		catch(Exception e){
			//DO NOTHING
		}
	}	
	void buy_scantron(String user_type, Student user, HashMap<String, ArrayList<ArrayList<Object>>> receipt){
		try{
			String item_id,item_name;
			int item_quantity, confirm, buy_quantity=0;
			double item_price, amount_pay=0;
			item_id = JOptionPane.showInputDialog(null, "Enter Scantron ID: ");
			Scantron scantron = scantrons.get(item_id);
			item_name = scantron.item_name;
			item_quantity = scantron.item_quantity;
			item_price = scantron.item_price;
			String item_type = scantron.item_type;
			if(item_quantity > 0){
				confirm = JOptionPane.showConfirmDialog(null, "Do you want to BUY the following?\n" +
					"Scantron ID: " + item_id + "\n" +
					"Quantity: " + item_quantity + "\n" +
					"Price: " + item_price, "Buy | Scantron", JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION){
					buy_quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Quantity: "));
		    		double total = buy_quantity * item_price;
		    		int pay_confirm = JOptionPane.showConfirmDialog(null, "Total: \u20B1" + total, "Confirm Payment", JOptionPane.YES_NO_OPTION);
		    		
					if(pay_confirm == JOptionPane.YES_OPTION){
						amount_pay = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter Amount To Pay: "));
							if(amount_pay >= total){
								scantron.buy(amount_pay, buy_quantity, user_type);
								user.generate_receipt(receipt, user.user_id, item_id, item_name, buy_quantity, item_price, amount_pay, amount_pay - total, item_type);
							}
							else{
					    		JOptionPane.showMessageDialog(null, "Insufficient Amount!");
					    	}
					}
					else{
					    JOptionPane.showMessageDialog(null, "Transaction Aborted!");
					}	
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Out Of Stock!");
			}
		}
		catch(Exception e){
			//DO NOTHING
		}
	}	
	void buy_uniform(String user_type, Student user, HashMap<String, ArrayList<ArrayList<Object>>> receipt){
		try{
			String item_id,item_name, college;
			int item_quantity, confirm, buy_quantity=0;
			double item_price, amount_pay=0;
			item_id = JOptionPane.showInputDialog(null, "Enter Uniform ID: ");
			Uniform uniform = uniforms.get(item_id);
			item_name = uniform.item_name;
			college = uniform.college;
			item_quantity = uniform.item_quantity;
			item_price = uniform.item_price;
			String item_type = uniform.item_type;
			if(item_quantity > 0){
				confirm = JOptionPane.showConfirmDialog(null, "Do you want to BUY the following?\n" +
					"Uniform ID: " + item_id + "\n" +
					"College: " + college  + "\n" +
					"Quantity: " + item_quantity + "\n" +
					"Price: " + item_price, "Buy | Uniform", JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION && user_type.equals("Student")){
					buy_quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Quantity: "));
		    		double total = buy_quantity * item_price;
		    		int pay_confirm = JOptionPane.showConfirmDialog(null, "Total: \u20B1" + total, "Confirm Payment", JOptionPane.YES_NO_OPTION);
		    		
					if(pay_confirm == JOptionPane.YES_OPTION){
						amount_pay = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter Amount To Pay: "));
							if(amount_pay >= total){
								uniform.buy(amount_pay, buy_quantity, user_type);
								user.generate_receipt(receipt, user.user_id, item_id, item_name, buy_quantity, item_price, amount_pay, amount_pay - total, item_type);
							}
							else{
					    		JOptionPane.showMessageDialog(null, "Insufficient Amount!");
					    	}
					}
					else{
					    JOptionPane.showMessageDialog(null, "Transaction Aborted!");
					}	
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Out Of Stock!");
			}
		}
		catch(Exception e){
			//DO NOTHING
		}
	}
	
	void delete_users(Admin user){
		//UPDATE USER
		String user_type, user_id, user_name, password;
		int confirm;
		int choice_user = user.menu_user();
		double balance;
		try{
			switch(choice_user){
				case 0:
					user_id = JOptionPane.showInputDialog(null, "Enter Student ID: ");
					Student student = students.get(user_id);
					if(student != null){					
						confirm = JOptionPane.showConfirmDialog(null, "Do you want to DELETE the following?\n" +
							"Student ID: " + student.user_id + "\n" +
							"Student Name: " + student.user_name  + "\n" + 
							"Student Password: " + student.password, "Delete | Student", JOptionPane.YES_NO_OPTION);
						if(confirm == JOptionPane.YES_OPTION){
							students.remove(user_id);
							JOptionPane.showMessageDialog(null, "Successfully Deleted!");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Student Not Found!");
					}
					break;
				case 1:
					user_id = JOptionPane.showInputDialog(null, "Enter Professor ID: ");
					Professor professor = professors.get(user_id);
					if(professor != null){					
						confirm = JOptionPane.showConfirmDialog(null, "Do you want to DELETE the following?\n" +
							"Professor ID: " + professor.user_id + "\n" +
							"Professor Name: " + professor.user_name  + "\n" + 
							"Professor Password: " + professor.password  + "\n" +
							"Professor Loan Balance: " + professor.loan_balance, "Delete | Professor", JOptionPane.YES_NO_OPTION);
						if(confirm == JOptionPane.YES_OPTION){
							professors.remove(user_id);
							JOptionPane.showMessageDialog(null, "Successfully Deleted!");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Professor Not Found!");
					}
					break;
			}
		}
		catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "Invalid Input!");
		}
	}
	void update_users(Admin user){
		//UPDATE USER
		String user_type, user_id, user_name, password;
		int confirm;
		int choice_user = user.menu_user();
		double balance;
		try{
			
			switch(choice_user){
				case 0:
					user_id = JOptionPane.showInputDialog(null, "Enter Student ID: ");
					Student student = students.get(user_id);
					System.out.print(student);
					if (student != null){
						user_name = JOptionPane.showInputDialog(null, "Enter New Student Name: ");
						password = JOptionPane.showInputDialog(null, "Enter New Student Password: ");
						
						if(!user_name.equals("") || !password.equals("")){					
							confirm = JOptionPane.showConfirmDialog(null, "Do you want to UPDATE the following?\n" +
								"Student ID: " + user_id + "\n" +
								"Student Name: " + user_name  + "\n" + 
								"Student Password: " + password, "Update | Student", JOptionPane.YES_NO_OPTION);
							if(confirm == JOptionPane.YES_OPTION){
								student.update_name(user_name);
								student.update_password(password);
								JOptionPane.showMessageDialog(null, "Successfully Updated!");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Invalid Input!");
						}
	
					}
					else{
						JOptionPane.showMessageDialog(null, "Student Not Found!");
					}
					break;
				case 1:
					user_id = JOptionPane.showInputDialog(null, "Enter Professor ID: ");
					Professor professor = professors.get(user_id);
					if(professor != null){
						user_name = JOptionPane.showInputDialog(null, "Enter New Professor Name: ");
						password = JOptionPane.showInputDialog(null, "Enter New Professor Password: ");
						balance = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter New Loan Balance: "));
						
						if(!user_name.equals("") || !password.equals("")){					
							confirm = JOptionPane.showConfirmDialog(null, "Do you want to UPDATE the following?\n" +
								"Professor ID: " + user_id + "\n" +
								"Professor Name: " + user_name  + "\n" + 
								"Professor Password: " + password  + "\n" +
								"Professor Loan Balance: " + balance, "Update | Professor", JOptionPane.YES_NO_OPTION);
							if(confirm == JOptionPane.YES_OPTION){
								professor.update_name(user_name);
								professor.update_password(password);
								professor.update_balance(balance);
								JOptionPane.showMessageDialog(null, "Successfully Updated!");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Invalid Input!");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Professor Not Found!");
					}
					break;
			}
		}
		catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "Invalid Input!");
		}			
	}
	void view_users(Admin user){
		//VIEW USER
		String user_type, user_id, user_name, password;
		int choice = 0, choice_user = JOptionPane.CLOSED_OPTION, action = JOptionPane.CLOSED_OPTION;
		try{
			choice_user = user.menu_user();
			switch(choice_user){
				case 0:
					//STUDENT
					user_id = JOptionPane.showInputDialog(null, "Enter Student ID: ");
					Student student = students.get(user_id);
					user_name = student.user_name;
					password = student.password;
					JOptionPane.showMessageDialog(null, "STUDENT INFORMATION\n" + 
														"Student Name: " + user_name + "\n" +
														"Password: " + password);
					break;
				case 1:
					//PROFESSOR
					user_id = JOptionPane.showInputDialog(null, "Enter Professor ID: ");
					Professor professor = professors.get(user_id);
					user_name = professor.user_name;
					password = professor.password;
					double loan_balance = professor.loan_balance;
					JOptionPane.showMessageDialog(null, "PROFESSOR INFORMATION\n" + 
														"Professor Name: " + user_name + "\n" +
														"Password: " + password + "\n" +
														"Balance: " + loan_balance);
					break;
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "User Not Found");
		}
	}	
	void add_users(Admin user){
		//ADD USER
		String user_type, user_id, user_name, password;
		int choice = 0, choice_user = JOptionPane.CLOSED_OPTION, action = JOptionPane.CLOSED_OPTION;
		JPasswordField jpf = new JPasswordField(24);
		choice_user = user.menu_user();
		try{
			
			switch(choice_user){
				case 0:
					//STUDENT
					user_type = "Student";
					user_id = JOptionPane.showInputDialog(null, "Enter Student ID: ");
					user_name = JOptionPane.showInputDialog(null, "Enter Student Name: ");
					password = "";
					if(!user_name.equals("") || !user_id.equals("")){					
						action = JOptionPane.showConfirmDialog(null, jpf,"Add | Password" ,JOptionPane.OK_CANCEL_OPTION);
					    if(action == JOptionPane.CANCEL_OPTION || action == JOptionPane.CLOSED_OPTION){
					    	//DO NOTHING
				    	}
					    else{
					    	password = String.valueOf(jpf.getPassword());
					    	int add = JOptionPane.showConfirmDialog(null, "Do you want to ADD the following?" + "\n" +
					    		"Student ID: " + user_id + "\n" +
					    		"Student Name: " + user_name + "\n" +
					    		"Student Password: " + password + "\n", "Add | Student", JOptionPane.YES_NO_OPTION);
					    	if(add == JOptionPane.NO_OPTION || add == JOptionPane.CLOSED_OPTION){
					    		//DO NOTHING
				    		}
				    		else{
								students.put(user_id, new Student(user_id, user_name, user_type, password));
				    			JOptionPane.showMessageDialog(null, "Successfully Added!");
				    		}
					    }
					}
					else{
						JOptionPane.showMessageDialog(null, "Invalid Input!");
					}
					break;
				case 1://PROFESSOR
					user_type = "Professor";
					user_id = JOptionPane.showInputDialog(null, "Enter Professor ID: ");
					user_name = JOptionPane.showInputDialog(null, "Enter Professor Name: ");
					password = "";
						
					if(!user_id.isEmpty() || !user_name.isEmpty()){
						action = JOptionPane.showConfirmDialog(null, jpf,"Add | Password" ,JOptionPane.OK_CANCEL_OPTION);
					    if(action == JOptionPane.NO_OPTION || action == JOptionPane.CLOSED_OPTION){
					    	//DO NOTHING
				    	}
					    else{
					    	password = String.valueOf(jpf.getPassword());
					    	int add = JOptionPane.showConfirmDialog(null, "Do you want to ADD the following?" + "\n" +
					    		"Professor ID: " + user_id + "\n" +
					    		"Professor Name: " + user_name + "\n" +
					    		"Professor Password: " + password + "\n", "Add | Professor", JOptionPane.YES_NO_OPTION);
					    	if(add == JOptionPane.NO_OPTION || add == JOptionPane.CLOSED_OPTION){
					    		//DO NOTHING
				    		}
				    		else{
								professors.put(user_id, new Professor(user_id, user_name, user_type, password, 0));
				    			JOptionPane.showMessageDialog(null, "Successfully Added!");
				    		}
					    }
					}
					else{
						JOptionPane.showMessageDialog(null, "Invalid Input!");
					}
					break;	
			}
		}
		catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "Invalid Input!");
		}
	}	
	
	void delete_book(){
		String item_id,item_title, item_author, college;
		int item_quantity, confirm;
		double item_price;
		item_id = JOptionPane.showInputDialog(null, "Enter Book ID: ");
		Book book = books.get(item_id);
		
		item_title = book.title;
		item_author = book.author;
		item_quantity = book.item_quantity;
		item_price = book.item_price;
		confirm = JOptionPane.showConfirmDialog(null, "Do you want to DELETE the following?\n" +
			"Book ID: " + item_id + "\n" +
			"Title: " + item_title  + "\n" +
			"Author: " + item_author  + "\n" +
			"Quantity: " + item_quantity + "\n" +
			"Price: " + item_price, "Delete | Book", JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION){
			books.remove(item_id);
			JOptionPane.showMessageDialog(null, "Successfully Deleted!");

		}
	}
	void delete_lace(){
		String item_id,item_title, item_author, college;
		int item_quantity, confirm;
		double item_price;
		item_id = JOptionPane.showInputDialog(null, "Enter Lace ID: ");
		Lace lace = laces.get(item_id);
		
		college = lace.college;
		item_quantity = lace.item_quantity;
		item_price = lace.item_price;
		confirm = JOptionPane.showConfirmDialog(null, "Do you want to DELETE the following?\n" +
			"Lace ID: " + item_id + "\n" +
			"College: " + college  + "\n" +
			"Quantity: " + item_quantity + "\n" +
			"Price: " + item_price, "Delete | Lace", JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION){
			laces.remove(item_id);
			JOptionPane.showMessageDialog(null, "Successfully Deleted!");

		}
	}
	void delete_scantron(){
		String item_id,item_title, item_author, college;
		int item_quantity, confirm;
		double item_price;
		item_id = JOptionPane.showInputDialog(null, "Enter Scantron ID: ");
		Scantron scantron = scantrons.get(item_id);
		
		item_quantity = scantron.item_quantity;
		item_price = scantron.item_price;
		confirm = JOptionPane.showConfirmDialog(null, "Do you want to DELETE the following?\n" +
			"Scantron ID: " + item_id + "\n" +
			"Quantity: " + item_quantity + "\n" +
			"Price: " + item_price, "Delete | Scantron", JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION){
			scantrons.remove(item_id);
			JOptionPane.showMessageDialog(null, "Successfully Deleted!");

		}
	}
	void delete_uniform(){
		String item_id,item_title, item_author, college;
		int item_quantity, confirm;
		double item_price;
		item_id = JOptionPane.showInputDialog(null, "Enter Uniform ID: ");
		Uniform uniform = uniforms.get(item_id);
		
		college = uniform.college;
		item_quantity = uniform.item_quantity;
		item_price = uniform.item_price;
		confirm = JOptionPane.showConfirmDialog(null, "Do you want to DELETE the following?\n" +
			"Uniform ID: " + item_id + "\n" +
			"College: " + college  + "\n" +
			"Quantity: " + item_quantity + "\n" +
			"Price: " + item_price, "Delete | Uniform", JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION){
			uniforms.remove(item_id);
			JOptionPane.showMessageDialog(null, "Successfully Deleted!");

		}
	}

	void update_book(){
		String item_id,item_title, item_author, college;
		int item_quantity, confirm;
		double item_price;
		item_id = JOptionPane.showInputDialog(null, "Enter Book ID: ");
		Book book = books.get(item_id);
		
		item_title = JOptionPane.showInputDialog(null, "Enter New Title: ");
		item_author = JOptionPane.showInputDialog(null, "Enter New Author: ");
		item_quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter New Quantity: "));
		item_price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter New Price: "));
		confirm = JOptionPane.showConfirmDialog(null, "Do you want to UPDATE the following?\n" +
			"Book ID: " + item_id + "\n" +
			"Title: " + item_title  + "\n" +
			"Author: " + item_author  + "\n" +
			"Quantity: " + item_quantity + "\n" +
			"Price: " + item_price, "Update | Book", JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION){
			book.update_title(item_title);
			book.update_name(item_title);
			book.update_author(item_author);
			book.update_quantity(item_quantity);
			book.update_price(item_price);
			JOptionPane.showMessageDialog(null, "Successfully Updated!");
		}
	}
	void update_lace(){
		String item_id,item_title, item_author, college;
		int item_quantity, confirm;
		double item_price;
		item_id = JOptionPane.showInputDialog(null, "Enter Lace ID: ");
		Lace lace = laces.get(item_id);
		
		college = JOptionPane.showInputDialog(null, "Enter New College Lace: ");
		item_quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter New Quantity: "));
		item_price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter New Price: "));
		confirm = JOptionPane.showConfirmDialog(null, "Do you want to UPDATE the following?\n" +
			"Lace ID: " + item_id + "\n" +
			"College: " + college  + "\n" +
			"Quantity: " + item_quantity + "\n" +
			"Price: " + item_price, "Update | Lace", JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION){
			lace.update_name(college + " Lace");
			lace.update_college(college);
			lace.update_quantity(item_quantity);
			lace.update_price(item_price);
			JOptionPane.showMessageDialog(null, "Successfully Updated!");
		}
	}	
	void update_scantron(){
		String item_id,item_title, item_author, college;
		int item_quantity, confirm;
		double item_price;
		item_id = JOptionPane.showInputDialog(null, "Enter Scantron ID: ");
		Scantron scantron = scantrons.get(item_id);
		
		item_quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter New Quantity: "));
		item_price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter New Price: "));
		confirm = JOptionPane.showConfirmDialog(null, "Do you want to UPDATE the following?\n" +
			"Scantron ID: " + item_id + "\n" +
			"Quantity: " + item_quantity + "\n" +
			"Price: " + item_price, "Update | Scantron", JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION){
			scantron.update_quantity(item_quantity);
			scantron.update_price(item_price);
			JOptionPane.showMessageDialog(null, "Successfully Updated!");
		}
	}
	void update_uniform(){
		String item_id,item_title, item_author, college, size, gender;
		int item_quantity, confirm;
		double item_price;
		item_id = JOptionPane.showInputDialog(null, "Enter Uniform ID: ");
		Uniform uniform = uniforms.get(item_id);
		
		college = JOptionPane.showInputDialog(null, "Enter New College Lace: ");
		size = JOptionPane.showInputDialog(null, "Enter New Gender: ");
		gender = JOptionPane.showInputDialog(null, "Enter New Size: ");
		item_quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter New Quantity: "));
		item_price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter New Price: "));
		confirm = JOptionPane.showConfirmDialog(null, "Do you want to UPDATE the following?\n" +
			"Uniform ID: " + item_id + "\n" +
			"College: " + college  + "\n" +
			"Quantity: " + item_quantity + "\n" +
			"Price: " + item_price, "Update | Uniform", JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION){
			uniform.update_name(college + " Uniform " + " " + gender + " " + size);
			uniform.update_college(college);
			uniform.update_quantity(item_quantity);
			uniform.update_price(item_price);
			JOptionPane.showMessageDialog(null, "Successfully Updated!");
		}
	}
	
	void view_book(){
		String item_id,item_title, item_author, college;
		int item_quantity, confirm;
		double item_price;
		item_id = JOptionPane.showInputDialog(null, "Enter Book ID: ");
		Book book = books.get(item_id);
		
		item_title = book.title;
		item_author = book.author;
		item_quantity = book.item_quantity;
		item_price = book.item_price;

		JOptionPane.showMessageDialog(null,
			"Book ID: " + item_id + "\n" +
			"Title: " + item_title  + "\n" +
			"Author: " + item_author  + "\n" +
			"Quantity: " + item_quantity + "\n" +
			"Price: " + item_price);
	}	
	void view_lace(){
		String item_id,item_title, item_author, college;
		int item_quantity, confirm;
		double item_price;
		item_id = JOptionPane.showInputDialog(null, "Enter Lace ID: ");
		Lace lace = laces.get(item_id);
		
		college = lace.college;
		item_quantity = lace.item_quantity;
		item_price = lace.item_price;

		JOptionPane.showMessageDialog(null,
			"Lace ID: " + item_id + "\n" +
			"College: " + college  + "\n" +
			"Quantity: " + item_quantity + "\n" +
			"Price: " + item_price);
	}	
	void view_scantron(){
		String item_id,item_title, item_author, college;
		int item_quantity, confirm;
		double item_price;
		item_id = JOptionPane.showInputDialog(null, "Enter Scantron ID: ");
		Scantron scantron = scantrons.get(item_id);
		
		item_quantity = scantron.item_quantity;
		item_price = scantron.item_price;
		JOptionPane.showMessageDialog(null,
			"Scantron ID: " + item_id + "\n" +
			"Quantity: " + item_quantity + "\n" +
			"Price: " + item_price);
	}	
	void view_uniform(){
		String item_id,item_title, item_author, college;
		int item_quantity, confirm;
		double item_price;
		item_id = JOptionPane.showInputDialog(null, "Enter Uniform ID: ");
		Uniform uniform = uniforms.get(item_id);
		
		college = uniform.college;
		item_quantity = uniform.item_quantity;
		item_price = uniform.item_price;
		JOptionPane.showMessageDialog(null,
			"Uniform ID: " + item_id + "\n" +
			"College: " + college  + "\n" +
			"Quantity: " + item_quantity + "\n" +
			"Price: " + item_price);
	}
	
	void add_book(){
		//BOOK
		String item_id = JOptionPane.showInputDialog(null, "Enter Book ID: ");
		String item_type = "Book";
		String item_title = JOptionPane.showInputDialog(null, "Enter Book Title: ");
		String item_author = JOptionPane.showInputDialog(null, "Enter " + item_title + " Author: ");
		int item_quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter " + item_title + " Quantity: "));
		double item_price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter " + item_title + " Price: "));
		boolean available = (item_quantity > 0) ? true : false;
		int confirm = JOptionPane.showConfirmDialog(null, "Do you want to ADD the following?\n" +
			"Book ID: " + item_id + "\n" +
			"Title: " + item_title  + "\n" +
			"Author: " + item_author  + "\n" +
			"Quantity: " + item_quantity + "\n" +
			"Price: " + item_price, "Add | Book", JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION){
			books.put(item_id, new Book(item_id, item_title, item_type, item_quantity, item_price, item_title, item_author, available));
			JOptionPane.showMessageDialog(null, "Successfully Added!");
		}
	}	
	void add_lace(){
		//Lace
		String item_id = JOptionPane.showInputDialog(null, "Enter Lace ID: ");
		String item_type = "Lace";
		String college = JOptionPane.showInputDialog(null, "Enter College: ");
		int item_quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Quantity: "));
		double item_price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter Price: "));
		boolean available = (item_quantity > 0) ? true : false;

		int confirm = JOptionPane.showConfirmDialog(null, "Do you want to ADD the following?\n" +
			"Lace ID: " + item_id + "\n" +
			"College: " + college  + "\n" +
			"Quantity: " + item_quantity + "\n" +
			"Price: " + item_price, "Add | Lace", JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION){
			laces.put(item_id, new Lace(item_id, college + " Lace", item_type, item_quantity, item_price, college, available));
			JOptionPane.showMessageDialog(null, "Successfully Added!");
		}
	}	
	void add_scantron(){
		//Scantron
		String item_id = JOptionPane.showInputDialog(null, "Enter Scantron ID: ");
		String item_type = "Scantron";
		int item_quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Quantity: "));
		double item_price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter Price: "));
		boolean available = (item_quantity > 0) ? true : false;

		int confirm = JOptionPane.showConfirmDialog(null, "Do you want to ADD the following?\n" +
			"Scantron ID: " + item_id + "\n" +
			"Quantity: " + item_quantity + "\n" +
			"Price: " + item_price, "Add | Lace", JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION){
			scantrons.put(item_id, new Scantron(item_id, "Scantron", item_type, item_quantity, item_price, available));
			JOptionPane.showMessageDialog(null, "Successfully Added!");
		}
	}	
	void add_uniform(){
		//Uniform
		String item_id = JOptionPane.showInputDialog(null, "Enter Uniform ID: ");
		String item_type = "Uniform";
		String college = JOptionPane.showInputDialog(null, "Enter College: ");
		int item_quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Quantity: "));
		double item_price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter Price: "));
		boolean available = (item_quantity > 0) ? true : false;

		int confirm = JOptionPane.showConfirmDialog(null, "Do you want to ADD the following?\n" +
			"Uniform ID: " + item_id + "\n" +
			"College: " + college  + "\n" +
			"Quantity: " + item_quantity + "\n" +
			"Price: " + item_price, "Add | Uniform", JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION){
			uniforms.put(item_id, new Uniform(item_id, college + " Uniform", item_type, item_quantity, item_price, college, available));
			JOptionPane.showMessageDialog(null, "Successfully Added!");
		}
	}
}
