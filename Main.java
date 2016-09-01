package beta1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Main {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.print("What would you like to do ? Choose the number for the corresponding option. \n1. Calculate divident yield \n2. Calculate P/E Ration \n");
		System.out.println("3. Calculate volume weighted stock price based on last 15 mins trades. \n4. Calculate GBCE All share Index \n5. Record a trade");
		int choice = Integer.parseInt(reader.nextLine());

		if (choice == 1) {
			System.out
					.println("Type in last known price and divident numbers by pressing enter key after each number.");
			double price = Double.parseDouble(reader.nextLine());
			double divident = Double.parseDouble(reader.nextLine());
			Double yield = divident / price;
			System.out.println("Yield: " + yield);
			
		} else if (choice == 2) {
			System.out.println("Type in last known price and divident numbers by pressing enter key after each number.");
			double price = Double.parseDouble(reader.nextLine());
			double divident = Double.parseDouble(reader.nextLine());
			Double yield = price / divident;
			System.out.println("Yield: " + yield);
			
		} else if (choice == 3) {
			ArrayList<Double> shares = new ArrayList<Double>();
			System.out.print("Type in quantity: ");
			int quantity = Integer.parseInt(reader.nextLine());
			System.out.println("Enter total quantity either as a single number or many numbers by pressing enter after each. When all numbers have been entered simply press enter one more time: ");
			while (reader.hasNextLine()) {
				String input = reader.nextLine();
				if (input.equals(""))
					break;
				double share = Double.parseDouble(input);
				shares.add(share);
			}
			System.out.println("Enter total price: ");
			double price = Double.parseDouble(reader.nextLine());
			double sum = 0;
			for (Double d : shares) {
				sum += d;
			}
			System.out.println("Volume weighted stock price: " + (price * quantity) / sum);

		} else if (choice == 4) {
			ArrayList<Double> prices2 = new ArrayList<Double>();
			System.out.println(
					"Enter prices folowed by pressing the enter key. When all prices have been entered simply press enter key again:");
			while (reader.hasNextLine()) {
				String input = reader.nextLine();
				if (input.equals(""))
					break;
				double price = Double.parseDouble(input);
				prices2.add(price);
			}
			int multi = 1;
			for (int i = 0; i < prices2.size(); i++) {
				multi *= prices2.get(i);
			}
			System.out.println("GBCE all share price index: " + Math.pow(multi, 1.0 / prices2.size()));
			
		} else if (choice == 5) {
			java.util.Date date = new java.util.Date();
			System.out.println(new Timestamp(date.getTime()));
			System.out.println(date);
			BufferedWriter writer = null;
			try {
				// This will create a temporary file
				String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
				File logFile = new File(timeLog);
				// This below will show where the file is located
				System.out.println(logFile.getCanonicalPath());

				writer = new BufferedWriter(new FileWriter(logFile));
				System.out.println("Type in quantity of shares, buy or sell indicator and actually everything else that you would like. \nAll the data will be saved in the location specified above:  ");
				String trade = reader.nextLine();
				writer.write(trade + " " + date);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					writer.close();
				} catch (Exception e) {
				}
			}
			
		} else {
			System.out.println("Error, non existent option is chosen.");
		}
		reader.close();
	}
}