public class Automobile {

    private String make;
    private String model;
    private String color;
    private int year;
    private int mileage;

    public Automobile() {
        make = "";
        model = "";
        color = "";
        year = 0;
        mileage = 0;
    }

    public Automobile(String make, String model, String color, int year, int mileage) {
        this();
        String result = addVehicle(make, model, color, year, mileage);
        if (result.startsWith("Failed")) {
            System.out.println(result);
        }
    }

    public String addVehicle(String make, String model, String color, int year, int mileage) {
        try {
            setAttributes(make, model, color, year, mileage);
            return "Vehicle added successfully: " + year + " " + make + " " + model + ".";
        } catch (Exception e) {
            return "Failed to add vehicle: " + e.getMessage();
        }
    }

    public String[] listVehicle() {
        try {
            if (make.isEmpty()) {
                throw new IllegalStateException("There is no vehicle information to list.");
            }
            return new String[] {
                "Make: " + make,
                "Model: " + model,
                "Color: " + color,
                "Year: " + year,
                "Mileage: " + String.format("%,d", mileage)
            };
        } catch (Exception e) {
            return new String[] {"Failed to list vehicle: " + e.getMessage()};
        }
    }

    public String removeVehicle() {
        try {
            if (make.isEmpty()) {
                throw new IllegalStateException("There is no vehicle to remove.");
            }
            String removed = year + " " + make + " " + model;
            make = "";
            model = "";
            color = "";
            year = 0;
            mileage = 0;
            return "Vehicle removed successfully: " + removed + ".";
        } catch (Exception e) {
            return "Failed to remove vehicle: " + e.getMessage();
        }
    }

    public String updateVehicle(String make, String model, String color, int year, int mileage) {
        try {
            if (this.make.isEmpty()) {
                throw new IllegalStateException("There is no vehicle to update.");
            }
            setAttributes(make, model, color, year, mileage);
            return "Vehicle updated successfully: " + year + " " + make + " " + model + ".";
        } catch (Exception e) {
            return "Failed to update vehicle: " + e.getMessage();
        }
    }

    private void setAttributes(String make, String model, String color, int year, int mileage) {
        if (make == null || make.trim().isEmpty()
                || model == null || model.trim().isEmpty()
                || color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Make, model, and color cannot be blank.");
        }
        if (year < 1886 || year > 2027) {
            throw new IllegalArgumentException("Year must be between 1886 and 2027.");
        }
        if (mileage < 0) {
            throw new IllegalArgumentException("Mileage cannot be negative.");
        }
        this.make = make.trim();
        this.model = model.trim();
        this.color = color.trim();
        this.year = year;
        this.mileage = mileage;
    }
}