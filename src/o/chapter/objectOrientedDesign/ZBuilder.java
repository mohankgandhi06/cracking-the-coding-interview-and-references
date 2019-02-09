package o.chapter.objectOrientedDesign;

import java.util.ArrayList;
import java.util.List;

public class ZBuilder {
    public static void main(String[] args) {
        Room hallRoom = new Room(20, 20, 12, "hall");
        Room bedRoom = new Room(20, 20, 12, "bedroom");
        Room kitchenRoom = new Room(20, 20, 12, "kitchen");
        Room washRoom = new Room(20, 20, 12, "washroom");
        Room playRoom = new Room(20, 20, 12, "playroom");
        List<Room> rooms = new ArrayList<>();
        rooms.add(hallRoom);
        rooms.add(bedRoom);
        rooms.add(kitchenRoom);
        rooms.add(washRoom);
        rooms.add(playRoom);
        /* Home beachHouse = new Home(rooms, "Beach House"); We cannot instantiate like this since the constructor
         * is private. we are intentionally making it private and creating a static class for creation of the individual
         * variable/Objects of the class whose instance we need to create, in our case the Home. Home's variable/Object
         * are nameHome and roomsHome which are created using withName and withRooms and then build will access the
         * private constructor and then return the Home instance */
        Home.HomeBuilder homeBuilder = new Home.HomeBuilder();
        homeBuilder.withName("Beach House");
        homeBuilder.withRooms(rooms);
        Home beachHouse = homeBuilder.build();

        System.out.println("House Name: " + beachHouse.getNameHome());
        System.out.println("================================================================");
        System.out.println("Rooms: ");
        for (Room room : beachHouse.getRoomsHome()) {
            System.out.println("Name: " + room.getName());
            System.out.println("Width: " + room.getWidth());
            System.out.println("Height: " + room.getWidth());
            System.out.println("Length: " + room.getWidth());
            System.out.println();
        }
        System.out.println("================================================================");
    }
}

class Home {
    private List<Room> roomsHome;
    private String nameHome;

    public static class HomeBuilder {
        private List<Room> rooms;
        private String name;

        public void withRooms(List<Room> rooms) {
            this.rooms = rooms;
        }

        public void withName(String name) {
            this.name = name;
        }

        public Home build() {
            return new Home(rooms, name);
        }
    }

    private Home(List<Room> rooms, String name) {
        this.roomsHome = rooms;
        this.nameHome = name;
    }

    public List<Room> getRoomsHome() {
        return roomsHome;
    }

    public String getNameHome() {
        return nameHome;
    }
}

class Room {
    private int length;
    private int width;
    private int height;
    private String name;

    public Room(int length, int width, int height, String name) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }
}