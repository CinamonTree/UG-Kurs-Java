
public class ParkingLot {
    
    private int capacity;
    private Car[] parkedCars;
    private int countOfParkedCars;

    public ParkingLot(int capacity) {
        validateCapacity(capacity);
        this.capacity = capacity;
        this.parkedCars = new Car[capacity];
        this.countOfParkedCars = 0;
    }

    public boolean parkCar(Car car) {
        if (this.capacity <= this.countOfParkedCars) {
            return false;
        }
        this.parkedCars[findEmptyParkingIndex()] = car;
        this.countOfParkedCars++;
        return true;
    }

    public boolean removeCar(String licensePlate) {
        int carIndex = findCarByLicensePlate(licensePlate);
        if (carIndex < 0) {
            return false;
        }
        this.parkedCars[carIndex] = null;
        this.countOfParkedCars--;
        return true;
    }

    public Car findCar(String licensePlate) {
        int indexOfCar = findCarByLicensePlate(licensePlate);
        if (indexOfCar < 0) {
            return null;
        }
        return this.parkedCars[indexOfCar];
    }

    private int findEmptyParkingIndex() {
        int i;
        for (i = 0; i < this.capacity; i++) {
            if (this.parkedCars[i] == null) {
                break;
            }
        }
        return i;
    }

    private int findCarByLicensePlate(String licensePlate) {
        for (int i = 0; i < this.capacity; i++) {
            Car currentCar = this.parkedCars[i];
            if (currentCar != null && currentCar.getLicensePlate().equals(licensePlate)) {
                return i;
            }
        }
        return -1;
    }

    private void validateCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be lower or equal to 0.");
        }
    }

}
