
public class Vehicle {

    private int maxSpeed;
    private String model;

    public Vehicle(int maxSpeed, String model) {
        this.maxSpeed = validateMaxSpeed(maxSpeed);
        this.model = validateModel(model);
    }

    public void displayInfo() {
        System.out.println("Model: " + model + ", Max Speed: " + maxSpeed + " km/h");
    }

    private int validateMaxSpeed(int speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("Prędkość maksymalna nie może być ujemna.");
        } else if (speed > 300) {
            throw new IllegalArgumentException("Prędkość maksymalna nie może przekraczać 300 km/h.");
        } else {
            return speed;
        }
    }

    private String validateModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model pojazdu nie może być pusty, lub znacznikiem null.");
        } else {
            return model;
        }
    }

}
