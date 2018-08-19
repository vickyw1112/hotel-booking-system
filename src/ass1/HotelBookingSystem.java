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
	
	/**
	 * convert capicity to int
	 * @param str
	 * @return
	 */
	public int transformCap(String str) {
		int num = 0;
		if(str.equals("single"))
			num = 1;
		else if(str.equals("double"))
			num = 2;
		else if(str.equals("triple"))
			num = 3;
		return num;
	}
	
	/**
	 * print method
	 * @param s
	 */
	public void state(String[] s) {
		String p = "";
		int i = 0;
		while(i < s.length - 1) {
			p += s[i] + " ";
			i++;
		}
		p += s[i];
		System.out.println(p);
	}
	
	public void manageInput(HotelSystem hs, String input) {
		String [] inputs = input.split(" ");
		int index = 0;
		if(inputs[0].equals("Hotel")) {
			for(Hotel h: hs.getHotels()) {
				if(h.getName().equals(inputs[1]))
					break;
				index++;
			}
			Room room = new Room(inputs[2], Integer.parseInt(inputs[3]));
			if(index == hs.getHotels().size()) {
				Hotel hotel = new Hotel(inputs[1]);
				hs.addHotel(hotel);
				hotel.addRoom(room);
			}else {
				hs.getHotels().get(index).addRoom(room);
			}
			return;
		}
		if(inputs[0].equals("Booking")) {
			
			Pair returnVar;
			Date date = new Date();
			LocalDate dates = date.dateToLocal(2018, inputs[2], inputs[3]);
			int nights = Integer.parseInt(inputs[4]);
			LinkedList<Integer> capicity = new LinkedList<Integer>();
			LinkedList<Integer> numBookings = new LinkedList<Integer>();

			for(index = 5; index < inputs.length; index++) {
				if(index % 2 != 0)
					capicity.add(this.transformCap(inputs[index]));
				else if(index % 2 == 0)
					numBookings.add(Integer.parseInt(inputs[index]));
			}
			// get avaiableroom
			returnVar = hs.availableRoom(dates, nights, capicity, numBookings);
			if(returnVar == null) {
				System.out.println("Booking rejected");
				return;
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
			String[] output = {inputs[0], inputs[1], hname, rname};
			// print statement
			this.state(output);
			return;
		}
		if(inputs[0].equals("Cancel")) {
			hs.cancelBooking(inputs[0]);
			String[] s = {"Cancel", inputs[0]};
			this.state(s);
			return;
		}
		if(inputs[0].equals("Change")) {
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
			Pair change = hs.changeBooking(dates, nights, capicity, numBookings, inputs[1]);
			if(change != null) {
				// get a print string
				String hname = change.getHotel().getName();
				String rname = "";
				LinkedList<Room> rooms = change.getRooms();
				int i = 0;
				while(i < rooms.size() - 1){
					rname += rooms.get(i++) + " ";
				}
				rname += rooms.get(i);
				String[] output = {inputs[0], hname, rname};
				// print statement
				this.state(output);
				return;
			}
			if(inputs[0].equals("Print")) {
				for(Hotel hotel: hs.getHotels()) {
					if(hotel.getName().equals(inputs[1])) {
						for(Room room: hotel.getRooms()) {
							if(room.noBookings()) {
								String[] s = {hotel.toString(), room.toString()};
								this.state(s);
							}
							else {
								String b = ""; // for booking
								int i = 0;
								while(i < room.getBookings().size() - 1) {
									Booking booking = room.getBookings().get(i);
									if(booking.getStatus().equals(Status.Completed)) {
										room.removeBooking(booking);
									}
									else {
										b += booking.toString() + " ";
									}
									i++;
								}
								b += room.getBookings().get(i);
								String[] s = {hotel.toString(), room.toString(), b};
								this.state(s);
							}
						}
					}
				}
				return;
			}
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
