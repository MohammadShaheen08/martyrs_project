package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Driver {
	

	static String S1[];

	public static void main(String[] args) {
		DoubleList Dlist=new DoubleList();
		
		AVLStack tree=new AVLStack();
		Martyers M;

		File file = new File("martyrs.txt");
		try {
			Scanner input = new Scanner(file);
			while (input.hasNext()) {

				String s = input.nextLine();

				int age;
				S1 = s.split(",");
				if (S1[1].equals("")) {
					age = -1;
				} else
					age = Integer.valueOf(S1[1]);
				String date = S1[3];
				 String dateOfDeath = (S1.length > 3 && !S1[3].isEmpty()) ? S1[3] : "Unknown";

				M = new Martyers(S1[0], age,parseDate(dateOfDeath), S1[4]);
				if (!Dlist.contains(S1[2])) {

					Dlist.addLast(S1[2]);
				}
				
				if (Dlist.contains(S1[2])) {
					Dlist.getNode(Dlist.find(S1[2])).getFirstTree().insert(M);
					Dlist.getNode(Dlist.find(S1[2])).getStackTree().insert(M.getDod());
					if(Dlist.getNode(Dlist.find(S1[2])).getStackTree().find(M.getDod())!=null) {
						
						Dlist.getNode(Dlist.find(S1[2])).getStackTree().find(M.getDod()).getStack().push(M);
						
					}
					

				}

				
				

			}
		//	System.out.println(Dlist.toString());
	//	System.out.println(Dlist.first.next.getFirstTree().count);	
	//Dlist.first.getFirstTree().printTree();;
	//Dlist.first.getFirstTree().displayHeight();;;

	//System.out.println(Dlist.first.getStackTree().find(parseDate("3/23/2023")).getStack().toString());;
		//	Dlist.getNode(Dlist.find("Jenin")).getFirstTree().printLevelOrder();
		System.out.println(Dlist.findNode("Aamallah").getStackTree().findMaximum());
//		System.out.println(Dlist.toString());
//		System.out.println("hh");
//		Dlist.update("Aamallah","Betlehem");
			//Dlist.findNode("Jenin").getFirstTree().displayHeight();

//			DNode cur;
//			cur=Dlist.first;
//			while(cur!=null) {
//				System.out.println(cur.element);
//				cur=cur.next;
//			}



//			tree.printTree();
//			Calendar calendar = Calendar.getInstance();
//			calendar.set(2023, 1, 29, 0, 0, 0);
//			calendar.set(Calendar.MILLISECOND, 0);
//			Date date1 = calendar.getTime();
//				
//			
//			System.out.println(tree.root.getStack().toString());
//			
//			
	
			
			

		}
		

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			    }
		
	}
	 private static LocalDate parseDate(String dateStr) {
	        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	        try {
	            return formatter.parse(dateStr).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        } catch (ParseException e) {
	            return null;
	        }
	    }
		
	}

