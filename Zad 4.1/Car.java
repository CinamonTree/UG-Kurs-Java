
public class Car extends Vehicle {
    
    private int doors;

    public Car(int maxSpeed, String model, int numberOfDoors) {
        super(maxSpeed, model);
        this.doors = validateDoors(numberOfDoors);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Doors: " + doors);
    }

    private int validateDoors(int doors) {
        if (doors <= 0) {
            throw new IllegalArgumentException("Liczba drzwi musi być większa od zera.");
        } else {
            return doors;
        }
    }
    
}
