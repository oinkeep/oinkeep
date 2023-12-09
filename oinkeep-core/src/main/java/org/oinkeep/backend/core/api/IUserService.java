package org.oinkeep.backend.core.api;

import org.oinkeep.backend.core.dto.NewUser;
import org.oinkeep.backend.core.entity.User;

public interface IUserService {
    User create( NewUser newUserData );

}
