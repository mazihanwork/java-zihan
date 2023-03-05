package jp.co.axa.apidemo.controllers;


import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
    @Mock
    EmployeeService service;
    EmployeeController controller;
    @Before
    public void init() {
        controller = new EmployeeController();
        controller.setEmployeeService(service);
    }

    @Test
    public void testGetOK() {
        // prepare
        Employee e = mockData();
        when(service.getEmployee(any())).thenReturn(e);

        // test
        ResponseEntity<Employee> response = controller.getEmployee(1001L);

        // assert
        assertNotNull(response.getBody());
        assertEquals(e, response.getBody());
    }
    @Test
    public void testGetNotFound() {
        // prepare
        Employee e = mockData();

        // test
        ResponseEntity<Employee> response = controller.getEmployee(1001L);

        // assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    public void testSaveOK() {
        // prepare
        Employee e = mockData();

        // test
        ResponseEntity<String> response = controller.saveEmployee(e);

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void testSaveAlreadyExist() {
        // prepare
        Employee e = mockData();
        when(service.getEmployee(any())).thenReturn(e);

        // test
        ResponseEntity<String> response = controller.saveEmployee(e);

        // assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDeleteOK() {
        // prepare
        Employee e = mockData();
        when(service.getEmployee(any())).thenReturn(e);

        // test
        ResponseEntity<String> response = controller.deleteEmployee(1001L);

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @Test
    public void testDeleteNotFound() {
        // prepare
        Employee e = mockData();

        // test
        ResponseEntity<String> response = controller.deleteEmployee(1001L);

        // assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    public void testUpdateOK() {
        // prepare
        Employee e = mockData();
        when(service.getEmployee(any())).thenReturn(e);

        // test
        ResponseEntity<String> response = controller.updateEmployee(e, 1001L);

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @Test
    public void testUpdateNotFound() {
        // prepare
        Employee e = mockData();

        // test
        ResponseEntity<String> response = controller.updateEmployee(e, 1001L);

        // assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private Employee mockData() {
        Employee e = new Employee();
        e.setId(1001L);
        e.setName("t name");
        e.setSalary(100);
        e.setDepartment("dep");
        return e;
    }

}
