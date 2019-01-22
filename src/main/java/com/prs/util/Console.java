package com.prs.util;


import java.util.Scanner;

public class Console {

	private static Scanner sc = new Scanner(System.in);

	public static String getString(String prompt) {

		// prompt user for any string where entry is required

		String s = "";
		while ("".equals(s)) {
			System.out.print(prompt);
			s = sc.nextLine();
			if (s.equals("")) {
				System.out.println("This entry is required. Please try again.");
				continue;
			} else {
				break;
			}
		}
		return s;
	}

	public String getString(String prompt, String s1, String s2) {

		// prompt user for a string matching one of two options

		String s = "";
		boolean isValid = false;
		while (!isValid) {
			s = getString(prompt);
			if (!s.equalsIgnoreCase(s1) && !s.equalsIgnoreCase(s2)) {
				System.out.println("Please enter " + s1 + " or " + s2 + " to continue.");
			} else {
				isValid = true;
			}
		}
		return s;
	}

	public static int getInt(String prompt) {

		// prompt user for any integer value

		int i = 0;
		boolean isValid = false;
		while (!isValid) {
			System.out.print(prompt);
			if (sc.hasNextInt()) {
				i = sc.nextInt();
				isValid = true;
			} else {
				System.out.println("Invalid number. Please try again.");
			}
			sc.nextLine();
		}
		return i;
	}

	public static int getIntWithinRange(String prompt, int min, int max) {

		// prompt user for an integer value within a range

		int i = 0;
		boolean isValid = false;
		while (!isValid) {
			i = getInt(prompt);
			if (i < min) {
				System.out.println("Number must be equal to or greater than " + min + ".");
			} else if (i > max) {
				System.out.println("Number must be equal to or less than " + max + ".");
			} else {
				isValid = true;
			}
		}
		return i;
	}

	public static double getDouble(String prompt) {

		// prompt user for any double value

		double d = 0;
		boolean isValid = false;
		while (!isValid) {
			System.out.print(prompt);
			if (sc.hasNextDouble()) {
				d = sc.nextDouble();
				isValid = true;
			} else {
				System.out.println("Invalid number. Please try again.");
			}
			sc.nextLine();
		}
		return d;
	}

	public static double getDoubleWithinRange(String prompt, double min, double max) {

		// prompt user for a double value within a range

		double d = 0;
		boolean isValid = false;
		while (!isValid) {
			d = getDouble(prompt);
			if (d < min) {
				System.out.println("Number must be equal to or greater than " + min + ".");
			} else if (d > max) {
				System.out.println("Number must be equal to or less than " + max + ".");
			} else {
				isValid = true;
			}
		}
		return d;
	}

	public static boolean askToContinue() {

		// see if the user wants to continue

		boolean isValid = false;
		while (!isValid) {
			System.out.print("Continue? (y/n): ");
			String choice = sc.next();

			if (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
				System.out.println("Please enter \"y\" to continue or \"n\" to quit.");

			} else if (choice.equalsIgnoreCase("y")) {
				isValid = true;
				sc.nextLine();
				System.out.println();

			} else {
				break;
			}
		}
		return isValid;
	}
	
	public static boolean askToContinue(String prompt) {

		// see if the user wants to continue

		boolean isValid = false;
		while (!isValid) {
			System.out.print(prompt);
			String choice = sc.next();

			if (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
				System.out.println("Please enter \"y\" to continue or \"n\" to quit.");

			} else if (choice.equalsIgnoreCase("y")) {
				isValid = true;
				sc.nextLine();
				System.out.println();

			} else {
				break;
			}
		}
		return isValid;
	}
}