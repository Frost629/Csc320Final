import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AutomobileInventory {

    private static final String FILE_PATH = "C:\\Temp\\Autos.txt";

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("=== Automobile Inventory ===");

            Automobile car = new Automobile("Toyota", "Camry", "Silver", 2020, 35000);
            System.out.println();
            System.out.println("Vehicle created with the parameterized constructor:");
            printVehicle(car);

            System.out.println();
            System.out.println("Removing the vehicle:");
            System.out.println(car.removeVehicle());

            System.out.println();
            System.out.println("Adding a new vehicle:");
            System.out.println(car.addVehicle("Honda", "Civic", "Blue", 2022, 18500));
            printVehicle(car);

            System.out.println();
            System.out.println("Updating the vehicle:");
            System.out.println(car.updateVehicle("Honda", "Civic", "Red", 2022, 21750));
            printVehicle(car);

            System.out.println();
            System.out.println("Trying an invalid update (negative mileage):");
            System.out.println(car.updateVehicle("Honda", "Civic", "Red", 2022, -100));
            printVehicle(car);

            System.out.println();
            String answer = askYesOrNo(input, "Do you want to print the vehicle information to a file? (Y/N): ");

            if (answer.equals("Y")) {
                System.out.println(printToFile(car, FILE_PATH));
            } else {
                System.out.println("The vehicle information will not be printed to a file.");
            }

            System.out.println("Program complete.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void printVehicle(Automobile car) {
        for (String line : car.listVehicle()) {
            System.out.println("  " + line);
        }
    }

    private static String askYesOrNo(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String answer = input.nextLine().trim().toUpperCase();
            if (answer.equals("Y") || answer.equals("N")) {
                return answer;
            }
            System.out.println("Please enter Y or N.");
        }
    }

    private static String printToFile(Automobile car, String path) {
        try {
            File file = new File(path);
            File folder = file.getParentFile();
            if (folder != null && !folder.exists() && !folder.mkdirs()) {
                throw new IOException("Could not create the folder " + folder.getPath());
            }
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                for (String line : car.listVehicle()) {
                    writer.println(line);
                }
            }
            return "Vehicle information printed to " + file.getAbsolutePath();
        } catch (IOException e) {
            return "Failed to print to file: " + e.getMessage();
        }
    }
}