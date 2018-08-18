/**
 * 
 */
package ass1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author vicky
 *
 */
public class HotelBookingSystem {
	
	public int transformCap(String str) {
		int num = 0;
		if(str.equals("single"))
			num = 1;
		else if(str.equals("double"))
			num = 2;
		return num;
	}
	
	public void state(String[] s) {
		String p = "";
		int i = 0;
		while(i < s.length) {
			p += s[i] + " ";
			i++;
		}
		p += s[i];
		System.out.println(p);
	}
	
	public void manageInput(HotelSystem hs, String input) {
		String [] inputs = input.split(" ");
		int index = 0;
		switch (inputs[0]) {
		case "Hotel":
			for(Hotel h: hs.getHotels()) {
				if(h.getName().equals(inputs[1]))
					break;
				index++;
			}
			Room room = new Room(inputs[2], Integer.valueOf(inputs[3]));
			if(hs.getHotels().get(index) == null) {
				Hotel hotel = new Hotel(inputs[1]);
				hs.addHotel(hotel);
				hotel.addRoom(room);
			}else {
				hs.getHotels().get(index).addRoom(room);
			}
			break;
		case "Booking":
			hs.removePassBooking();
			
			Pair returnVar;
			Date date = new Date();
			LocalDate dates = date.dateToLocal(2018, inputs[2], inputs[3]);
			int nights = Integer.valueOf(inputs[4]);
			LinkedList<Integer> capicity = new LinkedList<Integer>();
			LinkedList<Integer> numBookings = new LinkedList<Integer>();

			for(index = 5; index < inputs.length; index++) {
				if(index % 2 != 0)
					capicity.add(this.transformCap(inputs[index]));
				else if(index % 2 == 0)
					numBookings.add(Integer.valueOf(inputs[index]));
			}
			// get avaiableroom
			returnVar = hs.availableRoom(dates, nights, capicity, numBookings);
			if(returnVar == null) {
				System.out.println("Booking rejected");
				break;
			}
			// make booking
			hs.makeBookings(returnVar.getRooms(), inputs[1], dates, nights);
			// get a print string
			String hname = returnVar.getHotel().getName();
			String rname = "";
			LinkedList<Room> rooms = returnVar.getRooms();
			int i = 0;
			while(i < rooms.size() - 1){
				rname += rooms.get(i++) + " ";
			}
			rname += rooms.get(i);
			String[] output = {inputs[0], hname, rname};
			// print statement
			this.state(output);
			break;
		case "Cancle":
			hs.cancelBooking(inputs[0]);
			String[] s = {"Cancel", inputs[0]};
			this.state(s);
			hs.removePassBooking();
			break;
		case "Change": // TODO
			Pair change;
			Date cDate = new Date();
			LocalDate cDates = cDate.dateToLocal(2018, inputs[2], inputs[3]);
			LinkedList<Integer> changeCa = new LinkedList<Integer>();
			LinkedList<Integer> changeBooking = new LinkedList<Integer>();

			for(index = 5; index < inputs.length; index++) {
				if(index % 2 != 0)
					capicity.add(this.transformCap(inputs[index]));
				else if(index % 2 == 0)
					numBookings.add(Integer.valueOf(inputs[index]));
			}
			
			change = hs.changeBooking(cDates, inputs[4], capicity, numBookings, inputs[1]);
		}	
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HotelBookingSystem hbs = new HotelBookingSystem();
		Scanner sc = null;
	    String input;
		HotelSystem hs = new HotelSystem();
	      try{
	          sc = new Scanner(new File(args[0]));    // args[0] is the first command line argument
	          // Read input from the scanner here
	          while(sc.hasNextLine()) {
	        	  input = sc.nextLine();
	        	  hbs.manageInput(hs, input);
	          }
	      }
	      catch (FileNotFoundException e){
	          System.out.println(e.getMessage());
	      }
	      finally{
	          if (sc != null) sc.close();
	      }
	}

}
