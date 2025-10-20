/*
Stwórz klasę Time, która będzie reprezentować czas w godzinach, minutach i sekundach. Klasa powinna zawierać trzy pola: hours, minutes, i seconds (wszystkie typu int). 
Dodaj metody: addTime, która dodaje czas przekazany jako argument do bieżącego czasu, oraz subtractTime, która odejmuje czas (również podany jako argument) od bieżącego czasu. 
Zakładamy, że metody te będą przyjmować i zwracać czas w poprawnym formacie (godziny: 0-23, minuty i sekundy: 0-59) - 
Za pomocą nadpisanej metody toString w formacie np.: 11:59:30 lub 00:00:00.

Napisz wyłącznie klasę.
 */

class Time {

    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hours, int minutes, int seconds) {
        if ((hours > 23 && hours < 0) || (minutes > 59 && minutes < 0) || (seconds > 59 && seconds < 0)) {
            throw new IllegalArgumentException("Podany czas jest w nieprawidłowym formacie!");
        }

        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", this.hours, this.minutes, this.seconds);
    }

    public void addTime(Time time) {
        if (time == null) {
            throw new IllegalArgumentException("Oczekiwano Time, podano null!");
        }

        int fullSeconds = this.seconds + time.seconds;
        int overMinutes = (this.seconds + time.seconds) / 60;
        this.seconds = fullSeconds % 60;

        int fullMinutes = this.minutes + time.minutes + overMinutes;
        int overHours = fullMinutes / 60;
        this.minutes = fullMinutes % 60;

        this.hours = (this.hours + time.hours + overHours) % 24;
    }

    public void subtractTime(Time time) {
        if (time == null) {
            throw new IllegalArgumentException("Oczekiwano Time, podano null!");
        }
        if (time.hours > this.hours){
            throw new IllegalArgumentException("Ilość godzin do odjęcia przekracza ilość godzin pozostałych!");
        }
        this.hours = this.hours - time.hours;
    }
}
