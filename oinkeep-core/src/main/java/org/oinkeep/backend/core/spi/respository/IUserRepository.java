package org.oinkeep.backend.core.spi.respository;

import org.oinkeep.backend.core.entity.User;
import org.oinkeep.backend.core.vo.Username;

import java.util.Optional;

public interface IUserRepository {

    Optional<User> save( User user );

    boolean exists( Username username );

}
