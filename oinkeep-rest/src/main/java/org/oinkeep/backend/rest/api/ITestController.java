package org.oinkeep.backend.rest.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping( "/test" )
public interface ITestController {

    @GetMapping( { "/hi", "/hi/{name}" } )
    ResponseEntity<String> hi( @PathVariable( required = false ) String name );

}
