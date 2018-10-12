package j.chapter.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FReflection {
    public static void main(String[] args) throws NoSuchMethodException {
        House house = new House();

        try {
            Class cls = house.getClass();
            Constructor[] constructors = cls.getConstructors();
            Method[] methods = cls.getMethods();

            System.out.println();
            System.out.println("Constructors: ");
            for (Constructor constructor : constructors) {
                System.out.println(constructor.getName());
            }

            System.out.println();
            System.out.println("Methods: ");
            for (Method method : methods) {
                System.out.println(method.getName());
            }

            System.out.println();
            Method addRoomMateMethod = cls.getDeclaredMethod("addRoomMate", String.class);
            addRoomMateMethod.invoke(house, "mohan");
            addRoomMateMethod.invoke(house, "bharath");

            System.out.println("Added Room Mates: ");
            int i = 1;
            for (String roomMate : house.getRoomMates()) {
                System.out.println(i + ") " + roomMate);
                i++;
            }

            Method payRentMethod = cls.getMethod("payRent", int.class, int.class);
            /*int rentAmount, int electricityBill*/
            payRentMethod.invoke(house, 4000, 400);
            System.out.println();
            System.out.println("Paid Rent: ");
            System.out.println("Rent paid so far: " + house.getRent() + "\nElectricity Bill paid so far: " + house.getElectricityBill());

            /*Method payRentDeclaredMethod = cls.getDeclaredMethod("payRent", String.class);*/

            Method payElectricityBillMethod = cls.getDeclaredMethod("payElectricityBill", int.class);
            Field electricityBillField = cls.getDeclaredField("electricityBill");
            electricityBillField.setAccessible(true);
            payElectricityBillMethod.setAccessible(true);
            payElectricityBillMethod.invoke(house, 300);

            System.out.println();
            System.out.println("Extra Electricity Bill paid: ");
            System.out.println(house.getElectricityBill());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getLocalizedMessage() + " :Issue occurred in the Class");
        }

    }
}

class House {
    private int doorNo;
    private String houseName;
    private long areaInSquareFeet;
    private List<String> roomMates = new ArrayList<>();
    private int rent;
    private int electricityBill;

    public int getDoorNo() {
        return doorNo;
    }

    public String getHouseName() {
        return houseName;
    }

    public long getAreaInSquareFeet() {
        return areaInSquareFeet;
    }

    public List<String> getRoomMates() {
        return roomMates;
    }

    public int getRent() {
        return rent;
    }

    public int getElectricityBill() {
        return electricityBill;
    }

    /* Constructors */

    public House() {

    }

    public House(int doorNo) {
        this.doorNo = doorNo;
    }

    public House(int doorNo, String houseName) {
        this.doorNo = doorNo;
        this.houseName = houseName;
    }

    private House(int doorNo, String houseName, long areaInSquareFeet) {
        this.doorNo = doorNo;
        this.houseName = houseName;
        this.areaInSquareFeet = areaInSquareFeet;
    }

    /* Methods */

    public void addRoomMate(String newRoomie) {
        this.roomMates.add(newRoomie);
    }

    public void addRoomMates(List<String> newRoomies) {
        this.roomMates.addAll(newRoomies);
    }

    public void payRent(int rentAmount, int electricityBill) {
        this.payElectricityBill(electricityBill);
        this.rent = +(rentAmount - electricityBill);
    }

    private void payElectricityBill(int electricityBillAmount) {
        this.electricityBill = +(electricityBillAmount);
    }
}