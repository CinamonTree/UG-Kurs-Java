
public class Car {
    
    private String licensePlate;
    private String owner;
    private String carType;

    public Car(String licensePlate, String owner, String carType) {
        this.licensePlate = licensePlate;
        this.owner = owner;
        this.carType = carType;
    }

    public String getCarType() {
        return carType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getOwner() {
        return owner;
    }

}
