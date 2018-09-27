package ass1;

import java.util.Iterator;
import java.util.List;

public class BookingResult implements Iterable<Room> {
    public static BookingResult REJECT = null;

	private List<Room> rooms;
	private Hotel hotel;

	public BookingResult(Hotel hotel, List<Room> rooms) {
		this.rooms = rooms;
		this.hotel = hotel;
	}
	
	
	/**
	 * @return the name
	 */
	public String getHotelName() {
		return hotel.getName();
	}

    @Override
    public Iterator iterator() {
        return rooms.iterator();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    /**
	 * @return the hotel
	 */
	public Hotel getHotel() {
		return hotel;
	}
	
	
}
