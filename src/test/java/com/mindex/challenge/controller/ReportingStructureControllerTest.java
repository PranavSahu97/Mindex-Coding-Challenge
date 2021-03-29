package com.mindex.challenge.controller;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.impl.EmployeeServiceImpl;
import com.mindex.challenge.service.impl.ReportingStructureServiceImpl;
import lombok.Setter;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ReportingStructureControllerTest {

    @InjectMocks
    private ReportingStructureServiceImpl reportingStructureService;

    @Setter
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    ReportingStructureController reportingStructureController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(reportingStructureController).build();
    }

    @Test
    public void shouldGetReportingStructure() throws Exception {
        ReportingStructure reportingStructure = new ReportingStructure();
        Employee employee = new Employee();
        employee.setEmployeeId("12345");
        employee.setFirstName("John");
        employee.setLastName("Lennon");
        employee.setDepartment("Music");
        employee.setDirectReports(null);

        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(0);

        when(employeeRepository.findByEmployeeId(any())).thenReturn(employee);
        when(employeeService.read(any(String.class))).thenReturn(employee);
        when(reportingStructureService.getReports(any(String.class))).thenReturn(reportingStructure);
        ResponseEntity<?> responseEntity = reportingStructureController.getReportingStructure("12345");
        MatcherAssert.assertThat(responseEntity.getStatusCode(), CoreMatchers.is(HttpStatus.OK));
    }

    @Test
    public void shouldNotGetReportingStructure() throws Exception {
        when(reportingStructureService.getReports(anyString())).thenThrow(new RuntimeException("Test Error"));
        mockMvc.perform(post("/reportingStructure?employeeId=12345")).andExpect(status().isInternalServerError());
    }
}
