import java.time.LocalDate;

public class Car {

    private String model;
    private int year;

    public Car(String model, int year) {
        this.model = model;
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public void setModel(String model) { 
        this.model = model;
    }

    public void setYear(int year) {
        final int CURRENT_YEAR = LocalDate.now().getYear();
        final int MIN_YEAR = 1800;

        if (year > CURRENT_YEAR) {
            System.err.println("Rocznik jest późniejszy niż obecny rok!");
            return;
        }
        if (year < MIN_YEAR) {
            System.err.println("Bądźmy realistami.");
            return;
        }
        
        this.year = year;
    }

}
