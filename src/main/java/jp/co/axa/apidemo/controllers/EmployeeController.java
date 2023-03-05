package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@Valid @Min(message = "Id should be >= 0.", value = 0)
                                                  @PathVariable(name = "employeeId") Long employeeId) {
        Employee exist = employeeService.getEmployee(employeeId);
        if (exist != null) {
            return ResponseEntity.ok(exist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/employees")
    public ResponseEntity<String> saveEmployee(@Valid Employee employee) {
        Employee exist = employeeService.getEmployee(employee.getId());
        if (exist == null) {
            employeeService.saveEmployee(employee);
            return ResponseEntity.ok("Employee Saved Successfully");
        } else {
            return ResponseEntity.badRequest().body("Employee ID Already Exists");
        }
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@Valid @Min(message = "Id should be >= 0.", value = 0)
                                                     @PathVariable(name = "employeeId") Long employeeId) {
        Employee exist = employeeService.getEmployee(employeeId);
        if (exist != null) {
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.ok("Employee Deleted Successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<String> updateEmployee(@Valid @RequestBody Employee employee,
                                                 @Valid @Min(message = "Id should be >= 0.", value = 0) @PathVariable(name = "employeeId") Long employeeId) {
        Employee emp = employeeService.getEmployee(employeeId);
        if (emp != null) {
            employeeService.updateEmployee(employee);
            return ResponseEntity.ok("Employee Updated Successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
