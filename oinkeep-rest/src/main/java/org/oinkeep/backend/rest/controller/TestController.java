package org.oinkeep.backend.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.oinkeep.backend.core.api.ITestService;
import org.oinkeep.backend.rest.api.ITestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class TestController implements ITestController {

    private final ITestService testService;

    private @Value("${app.name}") String appName;

    public TestController( final @Autowired ITestService testService ) {
        this.testService = testService;
    }

    @Override
    public ResponseEntity<String> hi( final String name ) {
        String nameToGreets = name;
        if( name == null || name.isEmpty() ){
            nameToGreets = "World";
        }
        final String message = this.testService.greeting( nameToGreets );
        return ResponseEntity.ok( this.appName + ": " + message );
    }

}
