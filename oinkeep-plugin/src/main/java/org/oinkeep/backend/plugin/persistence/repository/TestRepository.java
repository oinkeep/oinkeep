package org.oinkeep.backend.plugin.persistence.repository;

import org.oinkeep.backend.core.spi.persistence.respository.ITestRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository implements ITestRepository {

    @Override
    public String findGreeting() {
        return "Hello %s !";
    }

}
