public class Passenger {
    private String name;
    private String passportNumber;
    private int age;

    public Passenger(String name, String passportNumber, int age) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " (Passport: " + passportNumber + ")";
    }
}