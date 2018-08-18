package ass1;

import java.time.LocalDate;
import java.util.LinkedList;

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
	 * get avaliable room for corresponding bookings
	 * @param numBookings
	 * @param date
	 * @param nights
	 * @param capicity
	 * @return
	 */
	public LinkedList<Room> searchAvailabeRooms(LocalDate date, int nights, LinkedList<Integer> capicity, LinkedList<Integer> numBookings){
		int i = 0; //count available room
		int count = 0;
		int num = 0;
		LinkedList<Room> rooms = new LinkedList<Room>();
		while(num < numBookings.size()) {
			count++;
			i = 0;
			while(i < numBookings.get(count)) {
				for(Room room: this.rooms) {
					if(!room.isRoomAvailable(capicity.get(count).intValue(), date, nights)) {
						return null;
					}
					rooms.add(room);
					i++;
				}
			}
		}
		return rooms;	
	}
}