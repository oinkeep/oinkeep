package org.oinkeep.backend.core.service;

import org.oinkeep.backend.core.api.IUserService;
import org.oinkeep.backend.core.dto.NewUser;
import org.oinkeep.backend.core.entity.User;
import org.oinkeep.backend.core.spi.respository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.oinkeep.backend.core.util.messages.ServiceMessages.USER_NOT_CREATED;
import static org.oinkeep.backend.core.util.messages.ServiceMessages.USER_USERNAME_ALREADY_EXISTS;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService( final IUserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public User create( final NewUser newUserData ) {
        final User newUser = User.create(newUserData.getUsername(), newUserData.getName(), newUserData.getFirstLastName(), newUserData.getSecondLastName(), newUserData.getImage());
        if (this.userRepository.exists(newUser.getUsername())) {
            throw new IllegalArgumentException( USER_USERNAME_ALREADY_EXISTS.getMessage() );
        }
        final Optional<User> result = this.userRepository.save(newUser);
        return result.orElseThrow(() -> new IllegalStateException( USER_NOT_CREATED.getMessage()));
    }
}
