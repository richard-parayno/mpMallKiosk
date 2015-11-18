package mpMallKiosk;

import java.util.Scanner;

public class TextBasedMain {
	
	public static void main(String[] args) {
		
		Scanner trp = new Scanner(System.in);
		String hold, pass = null;
		int ctr = 0;
		boolean isAdmin = true;
		
		System.out.println("Admin or user?");
			hold = trp.nextLine();
			
		if(hold.equalsIgnoreCase("admin")){
			while(!pass.equals("DLSU2015") && ctr <= 3){
				System.out.println("Password: ");
					pass = trp.nextLine();
				ctr++;
			}
			if (ctr == 4) isAdmin = false;
		}
		
		else if ()
		
		
	}
}
