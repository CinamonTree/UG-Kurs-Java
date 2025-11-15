
public class Room {

    private Light[] lights;
    private String name;
    private int numberOfLights;

    public Room(String name, int numberOfLights) {
        this.numberOfLights = validateNumberOfLights(numberOfLights);
        this.name = validateName(name);
        this.lights = createLights(numberOfLights);
    }

    public String getName() {
        return this.name;
    }

    public Light[] getLights() {
        return this.lights.clone();
    }

    public void controlLights(boolean status) {
        if (status) {
            for (Light light : this.lights) {
                light.turnOn();
            }
        } else {
            for (Light light : this.lights) {
                light.turnOff();
            }
        }   
    }

    private String validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Nie można stworzyć obiektu bez nazwy.");
        }
        return name;
    }

    private int validateNumberOfLights(int numberOfLights) {
        if (numberOfLights < 0) {
            throw new IllegalArgumentException("Ilość świateł nie może być ujemna.");
        }
        return numberOfLights;
    }

    private Light[] createLights(int numberOfLights) {
        this.lights = new Light[numberOfLights];
        for (int i = 0; i < this.numberOfLights; i++) {
            lights[i] = new Light();
        }
        return lights;
    }

}
