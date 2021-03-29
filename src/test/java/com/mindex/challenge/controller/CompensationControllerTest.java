package com.mindex.challenge.controller;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.impl.CompensationServiceImpl;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompensationControllerTest {

    @InjectMocks
    CompensationController compensationController;

    @InjectMocks
    private CompensationServiceImpl compensationService;

    @Mock
    private CompensationRepository compensationRepository;

    @Before
    public void setUp() {
        compensationController = new CompensationController();
        compensationService = new CompensationServiceImpl();
    }

    @Test
    public void shouldCreateCompensation() throws Exception {
        Compensation compensation = new Compensation();

        when(compensationService.create(any())).thenReturn(compensation);
        ResponseEntity<?> responseEntity = compensationController.createCompensation(compensation);
        MatcherAssert.assertThat(responseEntity.getStatusCode(), CoreMatchers.is(HttpStatus.OK));
    }

    @Test
    public void shouldGetCompensationByEmployeeId() throws Exception {
        Compensation compensation = new Compensation();
        when(compensationRepository.findCompByEmployeeEmployeeId(anyString())).thenReturn(compensation);
        when(compensationService.read(anyString())).thenReturn(compensation);
        ResponseEntity<?> responseEntity = compensationController.getCompensation("1234");
        MatcherAssert.assertThat(responseEntity.getStatusCode(), CoreMatchers.is(HttpStatus.OK));
    }
}
