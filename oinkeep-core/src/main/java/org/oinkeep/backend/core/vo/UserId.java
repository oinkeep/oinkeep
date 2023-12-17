package org.oinkeep.backend.core.vo;

import org.oinkeep.backend.core.util.messages.ValueObjectMessages;
import org.oinkeep.backend.util.vo.ValueObject;

import java.io.Serial;
import java.util.Objects;
import java.util.UUID;

import static org.oinkeep.backend.core.util.messages.ValueObjectMessages.USER_ID_IS_INVALID;
import static org.oinkeep.backend.core.util.messages.ValueObjectMessages.USER_ID_IS_NULL;

public class UserId extends ValueObject<UUID> {

    private static final @Serial long serialVersionUID = 1L;

    private final UUID value;

    private UserId( final UUID value ) {
        this.value = value;
        this.validate();
    }

    public static UserId valueOf( final UUID value ) {
        return new UserId( value );
    }

    public static UserId valueOf( final String value ) {
        UUID uuid = null;
        if(value != null) {
            try {
                uuid = UUID.fromString(value);
            } catch(Exception e) {
                throw new IllegalArgumentException( USER_ID_IS_INVALID.getMessage() );
            }
        }
        return UserId.valueOf(uuid);
    }

    public static UserId random() {
        return UserId.valueOf( UUID.randomUUID() );
    }


    private void validate(){
        if ( this.value == null ) {
            throw new IllegalArgumentException( USER_ID_IS_NULL.getMessage() );
        }
    }

    @Override
    public UUID value() {
        return this.value;
    }

    @Override
    public boolean equals( final Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        final UserId userId = (UserId) o;
        return Objects.equals( this.value(), userId.value() );
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.value() );
    }

    @Override
    public String toString() {
        return this.value().toString();
    }

}
