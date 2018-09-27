package ass1;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel {
	private String name;
	private LinkedList<Room> rooms;
	
	/**
	 * 
	 * @param name
	 * @category:constructor
	 */
	public Hotel(String name) {
		this.name = name;
		this.rooms = new LinkedList<Room>();
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the rooms
	 */
	public LinkedList<Room> getRooms() {
		return rooms;
	}
	
	/**
	 * append a new room to list
	 * @param room
	 */
	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	/**
	 *
	 */
	public BookingResult tryBooking(LocalDate date, int numDays,
									HashMap<Integer, Integer> roomDemand){
        List<Room> bookedRooms = new LinkedList<>();
	    for (Room room : getCompatibleRooms(date, numDays)) {
	        if(!roomDemand.containsKey(room.getCapacity()))
	            continue;
            int demand = roomDemand.get(room.getCapacity());
	        if(demand > 0){
                bookedRooms.add(room);
                roomDemand.put(room.getCapacity(), demand - 1);
            }
        }

        if(roomDemand.values().stream().allMatch(val -> val == 0))
            return new BookingResult(this, bookedRooms);
        else
            return BookingResult.REJECT;
	}

	private List<Room> getCompatibleRooms(LocalDate date, int numDays){
	    return rooms.stream().filter(room -> room.canBook(date, numDays))
                .collect(Collectors.toList());
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}
	
	
}
