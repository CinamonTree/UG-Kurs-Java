
public class RemoteControl {
    
    private ElectronicDevice device;

    public RemoteControl(ElectronicDevice device) {
        validateDevice(device);
        this.device = device;
    }

    public void validateDevice(ElectronicDevice device) {
        if (device == null) {
            throw new IllegalArgumentException("Null is not correct argument for this function.");
        }
    }

    public void turnDeviceOn() {
        device.turnOn();
    }

    public void turnDeviceOff() {
        device.turnOff();
    }

}
