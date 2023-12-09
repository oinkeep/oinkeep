package org.oinkeep.backend.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.oinkeep.backend.core.vo.LastName;
import org.oinkeep.backend.core.vo.Name;
import org.oinkeep.backend.core.vo.UserId;
import org.oinkeep.backend.core.vo.Username;
import org.oinkeep.backend.util.entity.Entity;

import java.io.Serial;
import java.util.Objects;

@Getter
@AllArgsConstructor
@ToString
public class User extends Entity {

    private static final @Serial long serialVersionUID = 1L;

    private UserId id;
    private Username username;
    private Name name;
    private LastName firstLastName;
    private LastName secondLastName;
    private String image;

    public static User create( final String username, final String name, final String firstLastName, final String secondLastName, final String image ) {
        final UserId newId = UserId.random();
        final Username newUsername = Username.valueOf( username );
        final Name newName = Name.valueOf( name );
        final LastName newFirstLastName = LastName.valueOf( firstLastName );
        final LastName newSecondLastName = LastName.valueOf( secondLastName );
        return new User( newId, newUsername, newName, newFirstLastName, newSecondLastName, image );
    }

    @Override
    public boolean equals( final Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        User user = (User) o;
        return Objects.equals( this.id, user.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.id );
    }

}
