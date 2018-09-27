/**
 * 
 */
package ass1;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.*;

/**
 * @author vicky
 *
 */
public class HotelBookingSystem {
	private LinkedList<Hotel> hotels;
	private HashMap<String, List<Booking>> customerBookings;

	public HotelBookingSystem() {
		this.hotels = new LinkedList<>();
		this.customerBookings = new HashMap<>();
	}

	private Hotel getHotelByName(String name){
	    for(Hotel hotel: this.hotels){
	        if (hotel.getName().equals(name))
	            return hotel;
        }
        return null;
    }

    /**
     *
     */
	public BookingResult makeBookings(String name, LocalDate date, int numDays,
                             HashMap<Integer, Integer> roomDemand) {

        for (Hotel hotel : hotels) {
            BookingResult res = hotel.tryBooking(date, numDays, roomDemand);
            if(res != BookingResult.REJECT) {
                List<Booking> bookings = new LinkedList<>();
                for(Room room: res){
                    Booking booking = new Booking(name, date, numDays);
                    room.addBooking(booking);
                    bookings.add(booking);
                }
                customerBookings.put(name, bookings);
                return res;
            }
        }
        return BookingResult.REJECT;
	}

	/**
	 * cancel booking
	 * @param name
	 */
	public void cancelBooking(String name) {
		List<Booking> bookings = this.customerBookings.get(name);
		for(Booking booking: bookings) {
			booking.toggleActiveness();
		}
		this.customerBookings.remove(name);
	}

	/**

	 */
    public BookingResult changeBooking(String name, LocalDate date, int numDays,
                                      HashMap<Integer, Integer> roomDemand) {
        List<Booking> oldBookings = this.customerBookings.get(name);
        // toggle all previous bookings to be inactive
        // so that they won't be considered during Room#canBook
		cancelBooking(name);

		BookingResult res = makeBookings(name, date, numDays, roomDemand);
        if(res == BookingResult.REJECT){
            // restore old bookings
            oldBookings.forEach(Booking::toggleActiveness);
            customerBookings.put(name, oldBookings);
        }

        return res;
	}


	/**
	 * convert capicity to int
	 * @param str
	 * @return
	 */
	public int parseRoomCapacity(String str) {
		int num = 0;
		if(str.equals("single"))
			num = 1;
		else if(str.equals("double"))
			num = 2;
		else if(str.equals("triple"))
			num = 3;
		return num;
	}
	
	public void processInput(String input) {
		String[] inputs = input.split(" ");
        if (inputs.length < 2){
            System.err.println("Invalid arguments");
            return;
        }

		// Command to declare a new room
		if(inputs[0].equals("Hotel")) {
		    if (inputs.length < 4){
		        System.err.println("Invalid arguments");
		        return;
            }

            Hotel hotel = this.getHotelByName(inputs[1]);

            if(hotel == null) {
                // if hotel does not exist, create a new one
                // and add to the list of all hotels
                hotel = new Hotel(inputs[1]);
                hotels.add(hotel);
            }

            Room room;
			try {
                room = new Room(inputs[2], Integer.parseInt(inputs[3]));
            } catch (NumberFormatException e){
                System.err.format("Invalid capacity: %s\n", inputs[3]);
                return;
            }
            hotel.addRoom(room);
		}
		else if(inputs[0].equals("Booking") || inputs[0].equals("Change")) {
            if (inputs.length < 7){
                System.err.println("Invalid arguments");
                return;
            }

            String dateStr = String.format("%d-%s-%s", Year.now().getValue(), inputs[2], inputs[3]);
            Date date;
            try {
                date = new SimpleDateFormat("yyyy-MMM-d").parse(dateStr);
            } catch (ParseException e) {
                System.err.println("Invalid date " + dateStr);
                return;
            }
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		    HashMap<Integer, Integer> roomDemand = new HashMap<>();
            for (int i = 5; i + 1 < inputs.length; i += 2)
                roomDemand.put(parseRoomCapacity(inputs[i]),
                        Integer.parseInt(inputs[i + 1]));

            BookingResult res;
            if(inputs[0].equals("Booking")) {
                res = makeBookings(inputs[1], localDate, Integer.parseInt(inputs[4]), roomDemand);
            } else {
                res = changeBooking(inputs[1], localDate, Integer.parseInt(inputs[4]), roomDemand);
            }

            if(res == BookingResult.REJECT) {
                System.out.println(inputs[0] + " Reject");
            }
            else {
                System.out.format("%s %s %s", inputs[0], inputs[1], res.getHotelName());
                for (Room room: res) {
                    System.out.print(" " + room.getNumber());
                }
                System.out.println();
            }
		}

		else if(inputs[0].equals("Cancel")) {
			cancelBooking(inputs[1]);
			System.out.println("Cancel " + inputs[1]);
		}
        else if(inputs[0].equals("Print")) {
            Hotel hotel = getHotelByName(inputs[1]);
            for (Room room : hotel.getRooms()) {
                System.out.format("%s %s\n", hotel.getName(), room);
            }
        } else {
            System.err.println("Invalid command " + inputs[0]);
        }
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HotelBookingSystem system = new HotelBookingSystem();
		Scanner sc = null;
		System.out.print("> ");
		if(args.length == 0 || args[0].equals("-")){
            sc = new Scanner(System.in);
        }
        else {
            try {
                sc = new Scanner(new File(args[0]));    // args[0] is the first command line argument
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        // Read input from the scanner here
        while(sc.hasNextLine()) {
            system.processInput(sc.nextLine());
            System.out.print("> ");
            System.out.flush();
        }

        sc.close();

	}
}
