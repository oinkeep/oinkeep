package org.oinkeep.backend.core.service;

import org.oinkeep.backend.core.api.ITestService;
import org.oinkeep.backend.core.spi.persistence.respository.ITestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService implements ITestService {

    private final ITestRepository testRepository;

    public TestService( final @Autowired ITestRepository testRepository ) {
        this.testRepository = testRepository;
    }

    @Override
    public String greeting( final String name ) {
        final String greeting = this.testRepository.findGreeting();
        return String.format( greeting, name );
    }

}
