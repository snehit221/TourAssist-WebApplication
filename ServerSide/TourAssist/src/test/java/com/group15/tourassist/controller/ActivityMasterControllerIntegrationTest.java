package com.group15.tourassist.controller;

import com.group15.tourassist.entity.ActivityMaster;
import com.group15.tourassist.repository.IActivityMasterRepository;
import com.group15.tourassist.service.ActivityMasterService;
import com.group15.tourassist.web.controller.ActivityMasterController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActivityMasterControllerIntegrationTest {

    @InjectMocks
    ActivityMasterController activityMasterController;

    @Mock
    private ActivityMasterService activityMasterService;

    @Autowired
    private IActivityMasterRepository activityMasterRepository;

    @Autowired
    private TestRestTemplate restTemplate;


    // integration test for /api/v1/activities GET end-point
    @Test
    void testGetCustomerAppUserId() {

        // Arrange
        ActivityMaster activityMaster = new ActivityMaster(10L, "Sky Diving");
        activityMasterRepository.save(activityMaster);

        // Act
        ResponseEntity<List<ActivityMaster>> response = restTemplate.exchange("/api/v1/activities", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ActivityMaster>>() {});


        // Assert
        Assert.assertEquals(activityMaster.getId(), response.getBody().get(0).getId());

    }

}