package agg;
import java.util.Scanner;

public class CompanyManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Use InMemoryEmployeeRepository as the default data layer
        EmployeeRepository repository = new SQLEmployeeRepository();

        // Add some initial employees
        repository.addEmployee("Company A", new Employee("Alice"));
        repository.addEmployee("Company A", new Employee("Bob"));
        repository.addEmployee("Company B", new Employee("Eve"));
        repository.addEmployee("Company B", new Employee("Frank"));

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display Employees");
            System.out.println("2. Add Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Transfer Employee");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Display Employees
                    System.out.println("\n1. Company A");
                    System.out.println("2. Company B");
                    System.out.print("Choose a company: ");
                    int displayChoice = scanner.nextInt();
                    String displayCompany = (displayChoice == 1) ? "Company A" : "Company B";
                    System.out.println("Employees in " + displayCompany + ":");
                    for (Employee e : repository.getEmployees(displayCompany)) {
                        System.out.println("- " + e.getName());
                    }
                    break;

                case 2: // Add Employee
                    System.out.println("\n1. Company A");
                    System.out.println("2. Company B");
                    System.out.print("Choose a company: ");
                    int addChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    String addCompany = (addChoice == 1) ? "Company A" : "Company B";
                    System.out.print("Enter employee name: ");
                    String newEmployeeName = scanner.nextLine();
                    repository.addEmployee(addCompany, new Employee(newEmployeeName));
                    System.out.println(newEmployeeName + " added to " + addCompany);
                    break;

                case 3: // Remove Employee
                    System.out.println("\n1. Company A");
                    System.out.println("2. Company B");
                    System.out.print("Choose a company: ");
                    int removeChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    String removeCompany = (removeChoice == 1) ? "Company A" : "Company B";
                    System.out.print("Enter employee name: ");
                    String removeEmployeeName = scanner.nextLine();
                    repository.removeEmployee(removeCompany, removeEmployeeName);
                    break;

                case 4: // Transfer Employee
                    System.out.println("\n1. Transfer from Company A to Company B");
                    System.out.println("2. Transfer from Company B to Company A");
                    System.out.print("Choose an option: ");
                    int transferChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter employee name: ");
                    String transferEmployeeName = scanner.nextLine();

                    if (transferChoice == 1) {
                        repository.removeEmployee("Company A", transferEmployeeName);
                        repository.addEmployee("Company B", new Employee(transferEmployeeName));
                    } else if (transferChoice == 2) {
                        repository.removeEmployee("Company B", transferEmployeeName);
                        repository.addEmployee("Company A", new Employee(transferEmployeeName));
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case 5: // Exit
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
