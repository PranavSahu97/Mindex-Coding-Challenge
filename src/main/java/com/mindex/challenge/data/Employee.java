package com.mindex.challenge.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private List<Employee> directReports;

    @Override
    public int hashCode() {
        int hashCode = 3;
        hashCode += employeeId.hashCode();
        return hashCode;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ReportingStructure){
            Employee rs = (Employee) obj;
            return (rs.equals(this.employeeId));
        } else
            return false;
    }
}
