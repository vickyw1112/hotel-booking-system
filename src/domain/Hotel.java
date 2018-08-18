package domain;

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
	
	/**
	 * get avaliable room for corresponding bookings
	 * @param numBookings
	 * @param date
	 * @param nights
	 * @param capicity
	 * @return
	 */
	public LinkedList<Room> searchAvailabeRooms(int numBookings, LocalDate date, int nights, int capicity){
		int i = 0; //count available room
		LinkedList<Room> rooms = new LinkedList<Room>();
		while(i < numBookings) {
			for(Room room: this.rooms) {
				if(room.isRoomAvailable(capicity, date, nights)) {
					i++;
					rooms.add(room);
				}
			}
		}
		if(i == numBookings)
			return rooms;
		return null;	
	}
}
