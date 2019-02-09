package o.chapter.objectOrientedDesign;

import java.util.BitSet;

public class DParkingLot {
    /* Question: Design a parking lot using object-oriented principles. */
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLot.parkVehicle(new Bike("Pulsar", "TN 38 AA 0001"));
    }
}

class ParkingLot {
    /* Here the assumption is that we are going to show only the available space for parking */

    /* If a new vehicle needs to be added all we need to do is: 1) Create an entry in the Vehicle class's enum
     * VehicleSize and then and else if block under the parkVehicle. This method will return the location
     * which can be printed out on a card and provided for the reference of the user */

    /* Here currently the space for vehicle is found out without specific location for bus and car. We need to
     * intially set the parking lots map that is where the locations are available for driving and getting out
     * they can be set as 1's initially to avoiding finding those locations */
    BitSet[] parkingSpace;

    public ParkingLot(int size) {
        parkingSpace = new BitSet[ size ];
        for (BitSet each : parkingSpace) {
            /* 0b1111111111 {0,1,2,3,4,5,6,7,8,9}*/
            each = new BitSet(10); /*BitSet.valueOf(new long[]{0b0000000000});*/
            System.out.println(" " + each.toString());
        }
    }

    public void parkVehicle(Vehicle vehicle) {
        if (vehicle instanceof Bike) {
            findLocation(Vehicle.VehicleSize.BIKE);
        } else if (vehicle instanceof Car) {
            findLocation(Vehicle.VehicleSize.CAR);
        } else if (vehicle instanceof Bus) {
            findLocation(Vehicle.VehicleSize.BUS);
        }
    }

    private void findLocation(Vehicle.VehicleSize vehicleSize) {
        /* Here the logic is to find the location available based on the vehicle size*/
    }
}

class Vehicle {
    private String name;
    private String number;
    private int length;
    private int width;

    protected enum VehicleSize {
        BIKE(2, 2), CAR(4, 2), BUS(8, 3);

        private int length;
        private int width;

        VehicleSize(int length, int width) {
            this.length = length;
            this.width = width;
        }

        public int getLength() {
            return length;
        }

        public int getWidth() {
            return width;
        }
    }

    public Vehicle(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}

class Bike extends Vehicle {
    public Bike(String name, String number) {
        super(name, number);
    }
}

class Car extends Vehicle {
    public Car(String name, String number) {
        super(name, number);
    }
}

class Bus extends Vehicle {
    public Bus(String name, String number) {
        super(name, number);
    }
}