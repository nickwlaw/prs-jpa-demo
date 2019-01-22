package com.prs;

import java.time.*;
import java.util.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prs.business.Product;
import com.prs.business.ProductDB;
import com.prs.business.PurchaseRequest;
import com.prs.business.PurchaseRequestDB;
import com.prs.business.PurchaseRequestLineItem;
import com.prs.business.PurchaseRequestLineItemDB;
import com.prs.business.User;
import com.prs.business.UserDB;
import com.prs.business.Vendor;
import com.prs.business.VendorDB;
import com.prs.util.Console;

@SpringBootApplication
public class PrsJpaDemoApplication2 {

	public static void main(String[] args) {
		SpringApplication.run(PrsJpaDemoApplication2.class, args);
		System.out.println("\nWelcome to the PRS JPA Demo Application");
		System.out.println("Which database would you like to test?\n");
		int choice;
		while (true) {
			displayMenu();
			choice = Console.getIntWithinRange("<> ", 1, 6);
			if (choice == 1)
				users();
			if (choice == 2)
				vendors();
			if (choice == 3)
				prods();
			if (choice == 4)
				prs();
			if (choice == 5)
				prlis();
			if (choice == 6) {
				System.out.println("Exiting PRS JPA Demo Application");
				break;
			}
		}
	}

	private static void users() {
		int choice;
		while (true) {
			System.out.println();
			displayCrud();
			choice = Console.getIntWithinRange("<> ", 1, 6);
			if (choice == 1) {
				List<User> users = UserDB.getAll();
				for (User u : users) {
					System.out.println(u);
				}
			}
			if (choice == 2) {
				int id = Console.getInt("Enter User ID to view: ");
				User u = UserDB.getUserById(id);
				if (u == null)
					System.out.println("No user was found with ID " + id);
				else
					System.out.println(u);
			}
			if (choice == 3) {
				String uname = Console.getString("Username: ");
				String pword = Console.getString("Password: ");
				String fname = Console.getString("First Name: ");
				String lname = Console.getString("Last Name: ");
				String phone = Console.getString("Phone Number: ");
				String email = Console.getString("Email: ");
				User u = new User(0, uname, pword, fname, lname, phone, email, false, false);
				boolean successful = UserDB.insertUser(u);
				if (successful)
					System.out.println(u + "\nhas successfully been added to the database.");
				if (!successful)
					System.out.println("There was an error adding the user to the database.");
			}
			if (choice == 4) {
				int id = Console.getInt("Enter User ID to update password: ");
				User u = UserDB.getUserById(id);
				while (true) {
					String opw = Console.getString("Enter old password: ");
					if (opw.equals(u.getPassword())) {
						String npw = Console.getString("Enter new password: ");
						u.setPassword(npw);
						boolean successful = UserDB.update(u);
						if (successful) {
							System.out.println("You successfully updated the password for " + u.getUserName());
							break;
						}
						if (!successful) {
							System.out.println("There was an error updating the password for " + u.getUserName());
							break;
						}
					} else
						System.out.println("Incorrect password, try again");
				}
			}
			if (choice == 5) {
				int id = Console.getInt("Enter User ID to delete: ");
				User u = UserDB.getUserById(id);
				boolean successful = UserDB.deleteUser(u);
				if (successful)
					System.out.println("You successfully deleted " + u.getUserName() + " from the database.");
				if (!successful)
					System.out.println("There was an error deleting the user from the database.");
			}
			if (choice == 6)
				break;
		}
	}

	private static void vendors() {
		int choice;
		while (true) {
			System.out.println();
			displayCrud();
			choice = Console.getIntWithinRange("<> ", 1, 6);
			if (choice == 1) {
				List<Vendor> vendors = VendorDB.getAll();
				for (Vendor v : vendors) {
					System.out.println(v);
				}
			}
			if (choice == 2) {
				int id = Console.getInt("Enter Vendor ID to view: ");
				Vendor v = VendorDB.getVendorById(id);
				if (v == null)
					System.out.println("No vendor was found with ID " + id);
				else
					System.out.println(v);
			}
			if (choice == 3) {
				String code = Console.getString("Vendor Code: ");
				String name = Console.getString("Company: ");
				String address = Console.getString("Street Address: ");
				String city = Console.getString("City: ");
				String state = Console.getString("State Code: ");
				String zip = Console.getString("Zip Code: ");
				String phone = Console.getString("Phone Number: ");
				String email = Console.getString("Email: ");
				Vendor v = new Vendor(0, code, name, address, city, state, zip, phone, email, true);
				boolean successful = VendorDB.insertVendor(v);
				if (successful)
					System.out.println(v + "\nhas successfully been added to the database.");
				if (!successful)
					System.out.println("There was an error adding the vendor to the database.");
			}
			if (choice == 4) {
				int id = Console.getInt("Enter Vendor ID to update e-mail: ");
				Vendor v = VendorDB.getVendorById(id);
				String email = Console.getString("Enter new e-mail: ");
				v.setEmail(email);
				boolean successful = VendorDB.update(v);
				if (successful)
					System.out.println("You successfully updated the e-mail for " + v.getName());
				if (!successful) {
					System.out.println("There was an error updating the e-mail for " + v.getName());
				}
			}
			if (choice == 5) {
				int id = Console.getInt("Enter Vendor ID to delete: ");
				Vendor v= VendorDB.getVendorById(id);
				boolean successful = VendorDB.deleteVendor(v);
				if (successful)
					System.out.println("You successfully deleted " + v.getName() + " from the database.");
				if (!successful)
					System.out.println("There was an error deleting the vendor from the database.");
			}
			if (choice == 6)
				break;
		}
	}

	private static void prods() {
		int choice;
		while (true) {
			System.out.println();
			displayCrud();
			choice = Console.getIntWithinRange("<> ", 1, 6);
			if (choice == 1) {
				List<Product> prods = ProductDB.getAll();
				for (Product p : prods) {
					System.out.println(p);
				}
			}
			if (choice == 2) {
				int id = Console.getInt("Enter Product ID to view: ");
				Product p = ProductDB.getProductById(id);
				if (p == null)
					System.out.println("No product was found with ID " + id);
				else
					System.out.println(p);
			}
			if (choice == 3) {
				int vid = Console.getInt("Vendor ID: ");
				String pnum = Console.getString("Part Number: ");
				String name = Console.getString("Product Name: ");
				double price = Console.getDouble("Price: ");
				String unit = Console.getString("Unit: ");
				Vendor v = VendorDB.getVendorById(vid);
				Product p = new Product(0, v, pnum, name, price, unit, "n/a");
				boolean successful = ProductDB.insertProduct(p);
				if (successful)
					System.out.println(p + "\nhas successfully been added to the database.");
				if (!successful)
					System.out.println("There was an error adding the product to the database.");
			}
			if (choice == 4) {
				int id = Console.getInt("Enter Product ID to update unit: ");
				Product p = ProductDB.getProductById(id);
				String unit = Console.getString("Enter new unit: ");
				p.setUnit(unit);
				boolean successful = ProductDB.update(p);
				if (successful)
					System.out.println("You successfully updated the unit for " + p.getName());
				if (!successful) {
					System.out.println("There was an error updating the unit for " + p.getName());
				}
			}
			if (choice == 5) {
				int id = Console.getInt("Enter Product ID to delete: ");
				Product p = ProductDB.getProductById(id);
				boolean successful = ProductDB.deleteProduct(p);
				if (successful)
					System.out.println("You successfully deleted " + p.getName() + " from the database.");
				if (!successful)
					System.out.println("There was an error deleting the product from the database.");
			}
			if (choice == 6)
				break;
		}
	}

	private static void prs() {
		int choice;
		while (true) {
			System.out.println();
			displayCrud();
			choice = Console.getIntWithinRange("<> ", 1, 6);
			if (choice == 1) {
				List<PurchaseRequest> prs = PurchaseRequestDB.getAll();
				for (PurchaseRequest pr : prs) {
					System.out.println(pr);
				}
			}
			if (choice == 2) {
				int id = Console.getInt("Enter Purchase Request ID to view: ");
				PurchaseRequest pr = PurchaseRequestDB.getPurchaseRequestById(id);
				if (pr == null)
					System.out.println("No purchase request was found with ID " + id);
				else
					System.out.println(pr);
			}
			if (choice == 3) {
				int uid = Console.getInt("Purchase Request User ID: ");
				User u = UserDB.getUserById(uid);
				String desc = Console.getString("Purchase Request Description: ");
				String just = Console.getString("Purchase Request Justification: ");
				int days = Console.getInt("Days until needed: ");
				LocalDate dn = LocalDate.now().plusDays(days);
				String dm = Console.getString("Delivery Mode: ");
				String status = "new";
				double total = 0.0;
				LocalDateTime sd = LocalDateTime.now();
				String rfr = "n/a";
				PurchaseRequest pr = new PurchaseRequest(0, u, desc, just, dn, dm, status, total, sd, rfr);
				boolean successful = PurchaseRequestDB.insertPurchaseRequest(pr);
				PurchaseRequestDB.recalculateTotal(pr);
				if (successful)
					System.out.println(pr + "\nhas successfully been added to the database.");
				if (!successful)
					System.out.println("There was an error adding the purchase request to the database.");
			}
			if (choice == 4) {
				int id = Console.getInt("Enter Purchase Request ID to update the reason for rejection: ");
				PurchaseRequest pr = PurchaseRequestDB.getPurchaseRequestById(id);
				String rfr = Console.getString("Enter the reason for rejection: ");
				pr.setReasonForRejection(rfr);
				boolean successful = PurchaseRequestDB.update(pr);
				PurchaseRequestDB.recalculateTotal(pr);
				if (successful)
					System.out.println("You successfully updated the reason for rejection for purchase request ID " + pr.getId() + ".");
				if (!successful) {
					System.out.println("There was an error updating the reason for rejection for purchase request ID " + pr.getId() + ".");
				}
			}
			if (choice == 5) {
				int id = Console.getInt("Enter Purchase Request ID to delete: ");
				PurchaseRequest pr = PurchaseRequestDB.getPurchaseRequestById(id);
				boolean successful = PurchaseRequestDB.deletePurchaseRequest(pr);
				if (successful)
					System.out.println("You successfully deleted purchase request with ID " + pr.getId() + " from the database.");
				if (!successful)
					System.out.println("There was an error deleting the purchase request from the database.");
			}
			if (choice == 6)
				break;
		}
	}

	private static void prlis() {
		int choice;
		while (true) {
			System.out.println();
			displayCrud();
			choice = Console.getIntWithinRange("<> ", 1, 6);
			if (choice == 1) {
				List<PurchaseRequestLineItem> prlis = PurchaseRequestLineItemDB.getAll();
				for (PurchaseRequestLineItem prli : prlis) {
					System.out.println(prli);
				}
			}
			if (choice == 2) {
				int id = Console.getInt("Enter Purchase Request Line Item ID: ");
				PurchaseRequestLineItem prli = PurchaseRequestLineItemDB.getPurchaseRequestLineItemById(id);
				if (prli == null)
					System.out.println("No purchase request line item was found with ID " + id);
				else {
					PurchaseRequestDB.recalculateTotal(prli.getPurchaseRequest());
					System.out.println(prli);
				}
			}
			if (choice == 3) {
				int prid = Console.getInt("Enter Purchase Request ID: ");
				PurchaseRequest pr = PurchaseRequestDB.getPurchaseRequestById(prid);
				int pid = Console.getInt("Enter Product ID: ");
				Product p = ProductDB.getProductById(pid);
				int quantity = Console.getInt("Enter Quantity: ");
				PurchaseRequestLineItem prli = new PurchaseRequestLineItem(0, pr, p, quantity);
				boolean successful = PurchaseRequestLineItemDB.insertPurchaseRequestLineItem(prli);
				PurchaseRequestDB.recalculateTotal(prli.getPurchaseRequest());
				if (successful)
					System.out.println(prli + "\nhas successfully been added to the database.");
				if (!successful)
					System.out.println("There was an error adding the purchase request line item to the database.");
			}
			if (choice == 4) {
				int id = Console.getInt("Enter Purchase Request Line Item ID to update the quantity: ");
				PurchaseRequestLineItem prli = PurchaseRequestLineItemDB.getPurchaseRequestLineItemById(id);
				int quantity = Console.getInt("Enter the quantity: ");
				prli.setQuantity(quantity);
				boolean successful = PurchaseRequestLineItemDB.update(prli);
				PurchaseRequestDB.recalculateTotal(prli.getPurchaseRequest());
				if (successful)
					System.out.println("You successfully updated the quantity for purchase request line item ID " + prli.getId() + ".");
				if (!successful) {
					System.out.println("There was an error updating the quantity for purchase request line item ID " + prli.getId() + ".");
				}
			}
			if (choice == 5) {
				int id = Console.getInt("Enter Purchase Request Line Item ID to delete: ");
				PurchaseRequestLineItem prli = PurchaseRequestLineItemDB.getPurchaseRequestLineItemById(id);
				boolean successful = PurchaseRequestLineItemDB.deletePurchaseRequestLineItem(prli);
				if (successful)
					System.out.println("You successfully deleted purchase request line item with ID " + prli.getId() + " from the database.");
				if (!successful)
					System.out.println("There was an error deleting the purchase request line item from the database.");
			}
			if (choice == 6)
				break;
		}
	}

	private static void displayMenu() {
		System.out.println("DATABASES");
		System.out.println("1: Users");
		System.out.println("2: Vendors");
		System.out.println("3: Products");
		System.out.println("4: Purchase Requests");
		System.out.println("5: Purchase Request Line Items");
		System.out.println("6: Exit");
	}

	private static void displayCrud() {
		System.out.println("OPTIONS");
		System.out.println("1: View All");
		System.out.println("2: View by ID");
		System.out.println("3: Add");
		System.out.println("4: Update");
		System.out.println("5: Delete");
		System.out.println("6: Change Database");
	}
}