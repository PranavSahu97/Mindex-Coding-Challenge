package com.mindex.challenge.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class Compensation {
    private Employee employee;
    private double salary;
    private String effectiveDate;
}
