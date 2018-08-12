package domain;

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
	 * find a room for request capicity
	 * @param capicity
	 * @return
	 */
	public Room searchEmptyRoom(int capicity) {
		for(Room room: this.rooms) {
			if(room.getCapicity() == capicity && room.getBookings().isEmpty())
				return room;
		}
		return null;
	}
}
