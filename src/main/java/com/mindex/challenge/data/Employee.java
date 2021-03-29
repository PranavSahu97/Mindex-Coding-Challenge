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
        return employeeId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof Employee))
            return false;
        if(obj == this)
            return true;
        return this.getEmployeeId() == ((Employee) obj).getEmployeeId();
    }
}
