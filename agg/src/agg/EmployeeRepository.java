package agg;

import java.util.List;

public interface EmployeeRepository {
    void addEmployee(String companyName, Employee employee);
    void removeEmployee(String companyName, String employeeName);
    List<Employee> getEmployees(String companyName);
}
