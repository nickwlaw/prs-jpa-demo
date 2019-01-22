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
public class PrsJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrsJpaDemoApplication.class, args);
		System.out.println("You established a successful connection");
		while (true) {
			displayMenu();
			int choice = Console.getIntWithinRange("<> ", 1, 30);
			if (choice == 1) {
				List<User> users = UserDB.getAll();
				for (User u : users) {
					System.out.println(u);
				}
			}
			if (choice == 2) {
				int id = Console.getInt("Enter User ID: ");
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
				User u = new User(0, uname, pword, fname, lname, phone, email, true, true);
				UserDB.insertUser(u);
				System.out.println("You successfully added " + uname + " to the database.");
			}
			if (choice == 4) {
				int id = Console.getInt("Enter User ID to delete: ");
				User u = UserDB.getUserById(id);
				UserDB.deleteUser(u);
				System.out.println("You successfully deleted " + u.getUserName() + " from the database.");
			}
			if (choice == 5) {
				List<Vendor> vendors = VendorDB.getAll();
				for (Vendor v : vendors) {
					System.out.println(v);
				}
			}
			if (choice == 6) {
				int id = Console.getInt("Enter Vendor ID: ");
				Vendor v = VendorDB.getVendorById(id);
				if (v == null)
					System.out.println("No vendor was found with ID " + id);
				else
					System.out.println(v);
			}
			if (choice == 7) {
				String code = Console.getString("Vendor Code: ");
				String name = Console.getString("Vendor Name: ");
				String address = Console.getString("Street Address: ");
				String city = Console.getString("City: ");
				String state = Console.getString("Two Letter State Code: ");
				String zip = Console.getString("Zip Code: ");
				String pnum = Console.getString("Phone Number: ");
				String email = Console.getString("E-mail: ");
				Vendor v = new Vendor(0, code, name, address, city, state, zip, pnum, email, true);
				VendorDB.insertVendor(v);
				System.out.println("You successfully added " + name + " to the database.");
			}
			if (choice == 8) {
				int id = Console.getInt("Enter Vendor ID to delete: ");
				Vendor v = VendorDB.getVendorById(id);
				VendorDB.deleteVendor(v);
				System.out.println("You successfully deleted " + v.getName() + " from the database.");
			}
			if (choice == 9) {
				List<Product> products = ProductDB.getAll();
				for (Product p : products) {
					System.out.println(p);
				}
			}
			if (choice == 10) {
				int id = Console.getInt("Enter Product ID: ");
				Product p = ProductDB.getProductById(id);
				if (p == null)
					System.out.println("No product was found with ID " + id);
				else
					System.out.println(p);
			}
			if (choice == 11) {
				int vid = Console.getInt("Product Vendor ID: ");
				String pn = Console.getString("Part Number: ");
				String name = Console.getString("Product Name: ");
				double price = Console.getDouble("Product Price: ");
				String unit = Console.getString("Product Unit: ");
				String photoPath = Console.getString("Product Photo Path: ");
				Vendor v = VendorDB.getVendorById(vid);
				Product p = new Product(0, v, pn, name, price, unit, photoPath);
				ProductDB.insertProduct(p);
				System.out.println("You successfully added " + name + " from " + v.getName() + " to the database.");
			}
			if (choice == 12) {
				int id = Console.getInt("Enter Product ID to delete: ");
				Product p = ProductDB.getProductById(id);
				ProductDB.deleteProduct(p);
				System.out.println("You successfully deleted " + p.getName() + " from the database.");
			}
			if (choice == 13) {
				List<PurchaseRequest> purchaseRequests = PurchaseRequestDB.getAll();
				for (PurchaseRequest pr : purchaseRequests) {
					System.out.println(pr);
				}
			}
			if (choice == 14) {
				int id = Console.getInt("Enter Purchase Request ID: ");
				PurchaseRequest pr = PurchaseRequestDB.getPurchaseRequestById(id);
				if (pr == null)
					System.out.println("No purchase request was found with ID " + id);
				else
					System.out.println(pr);
			}
			if (choice == 15) {
				int uid = Console.getInt("Purchase Request User ID: ");
				User u = UserDB.getUserById(uid);
				String desc = Console.getString("Purchase Request Description: ");
				String just = Console.getString("Purchase Request Justification: ");
				int days = Console.getInt("Days until needed: ");
				LocalDate dn = LocalDate.now().plusDays(days);
				String dm = Console.getString("Delivery Mode: ");
				String status = "new";
				double total = Console.getDouble("Purchase Request Total: ");
				LocalDateTime sd = LocalDateTime.now();
				String rfr = "n/a";
				PurchaseRequest pr = new PurchaseRequest(0, u, desc, just, dn, dm, status, total, sd, rfr);
				PurchaseRequestDB.insertPurchaseRequest(pr);
				System.out.println(
						"You successfully added a purchase request for " + u.getUserName() + " to the database.");
			}
			if (choice == 16) {
				int id = Console.getInt("Enter Purchase Request ID to delete: ");
				PurchaseRequest pr = PurchaseRequestDB.getPurchaseRequestById(id);
				PurchaseRequestDB.deletePurchaseRequest(pr);
				System.out.println(
						"You successfully deleted purchase request with ID " + pr.getId() + " from the database.");
			}
			if (choice == 17) {
				List<PurchaseRequestLineItem> prlis = PurchaseRequestLineItemDB.getAll();
				for (PurchaseRequestLineItem prli : prlis) {
					System.out.println(prli);
				}
			}
			if (choice == 18) {
				int id = Console.getInt("Enter Purchase Request Line Item ID: ");
				PurchaseRequestLineItem prli = PurchaseRequestLineItemDB.getPurchaseRequestLineItemById(id);
				if (prli == null)
					System.out.println("No purchase request line item was found with ID " + id);
				else
					System.out.println(prli);
			}
			if (choice == 19) {
				int id = Console.getInt("Enter User ID to update password: ");
				User u = UserDB.getUserById(id);
				while (true) {
					String opw = Console.getString("Enter old password: ");
					if (opw.equals(u.getPassword())) {
						String npw = Console.getString("Enter new password: ");
						u.setPassword(npw);
						UserDB.update(u);
						System.out.println("You successfully updated the password for " + u.getUserName());
						break;
					} else
						System.out.println("Incorrect password, try again");
				}
			}
			if (choice == 20) {
				int prid = Console.getInt("Enter Purchase Request ID to update: ");
				PurchaseRequest pr = PurchaseRequestDB.getPurchaseRequestById(prid);
				PurchaseRequestDB.recalculateTotal(pr);
				System.out.println(pr);
			}
			if (choice == 21) {
				int vid = Console.getInt("Enter Vendor ID: ");
				List<Product> products = ProductDB.getAllbyVendorID(vid);
				for (Product p : products) {
					System.out.println(p);
				}
			}
			if (choice == 22) {
				int prid = Console.getInt("Enter Purchase Request ID: ");
				List<PurchaseRequestLineItem> prlis = PurchaseRequestLineItemDB.getAllByPRId(prid);
				for (PurchaseRequestLineItem prli : prlis) {
					System.out.println(prli);
				}
			}
			if (choice == 30) {
				System.out.println("Disconnecting from the database.");
				break;
			}
		}
	}

	private static void displayMenu() {
		System.out.println("MENU");
		System.out.println("1:    List all Users");
		System.out.println("2:    View User by ID");
		System.out.println("3:    Add a User");
		System.out.println("4:    Delete a User");
		System.out.println("5:    List all Vendors");
		System.out.println("6:    View Vendor by ID");
		System.out.println("7:    Add a Vendor");
		System.out.println("8:    Delete a Vendor");
		System.out.println("9:    List all Products");
		System.out.println("10:   View Product by ID");
		System.out.println("11:   Add a Product");
		System.out.println("12:   Delete a Product");
		System.out.println("13:   List all Purchase Requests");
		System.out.println("14:   View Purchase Request by ID");
		System.out.println("15:   Add a Purchase Request");
		System.out.println("16:   Delete a Purchase Request");
		System.out.println("17:   List all Purchase Request Line Items");
		System.out.println("18:   View Purchase Request Line Item by ID");
		System.out.println("19:   Change a User Password");
		System.out.println("20:   Update an invoice total");
		System.out.println("21:   View all Products by Vendor ID");
		System.out.println("22:   View all Line Items by Purchase Request ID");

	}

}
