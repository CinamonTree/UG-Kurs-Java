
public class Building {
    
    private Room[] rooms;
    private int numberOfRooms;

    public Building(int numberOfRooms) {
        this.numberOfRooms = validateNumberOfRooms(numberOfRooms);
        this.rooms = new Room[this.numberOfRooms];
    }

    public void addRoom(Room room, int index) {
        if (isIndexNull(index)) {
            this.rooms[index] = room;
            return;
        }
        throw new IllegalArgumentException("Pod danym indeksem jest już pokój.");
    }

    public void controlRoomLights(String roomName, boolean status) {
        Room room = findRoom(roomName);
        room.controlLights(status);
    }

    private int validateNumberOfRooms(int numberOfRooms){
        if (numberOfRooms <= 0) {
            throw new IllegalArgumentException("Nie można utworzyć budynku z liczbą pokoji mniejszą niż 1.");
        }
        return numberOfRooms;
    }

    private boolean  isIndexNull(int index) {
        return this.rooms[index] == null;
    }

    private Room findRoom(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

}
