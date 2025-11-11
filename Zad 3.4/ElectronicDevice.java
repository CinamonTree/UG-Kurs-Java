
public class ElectronicDevice {
    
    private boolean powerStatus;
    private String brand;
    private String model;

    public ElectronicDevice(String brand, String model) {
        this.powerStatus = false;
        this.brand = brand;
        this.model = model;
    }

    public void turnOn() {
        this.powerStatus = true;
    }

    public void turnOff() {
        this.powerStatus = false;
    }

    public boolean isPowerOn() {
        return this.powerStatus;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

}
