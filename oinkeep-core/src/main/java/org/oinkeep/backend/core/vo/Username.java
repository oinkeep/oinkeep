package org.oinkeep.backend.core.vo;

import org.oinkeep.backend.util.vo.ValueObject;

import java.io.Serial;
import java.util.Objects;

import static org.oinkeep.backend.core.util.messages.ValueObjectMessages.*;

public class Username extends ValueObject<String> {

    private static final @Serial long serialVersionUID = 1L;
    public static final int MIN_LENGTH = 3;
    public static final int MAX_LENGTH = 25;
    public static final String USERNAME_PATTERN = "^[a-z][a-z0-9_-]+$";

    private final String value;

    private Username( final String value ) {
        this.value = value;
        this.validate();
    }

    public static Username valueOf( final String value ) {
        return new Username( value );
    }

    private void validate(){
        if( this.value == null) {
            throw new IllegalArgumentException( USERNAME_IS_NULL.getMessage() );
        }
        if( this.value().length() < MIN_LENGTH) {
            throw new IllegalArgumentException( USERNAME_IS_TOO_SHORT.getMessage());
        }
        if( this.value().length() > MAX_LENGTH){
            throw new IllegalArgumentException( USERNAME_IS_TOO_LONG.getMessage());
        }
        if( !this.value().matches( USERNAME_PATTERN ) ) {
            throw new IllegalArgumentException( USERNAME_IS_INVALID.getMessage() );
        }
    }

    @Override
    public String value() {
        return this.value.toLowerCase();
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Username username = (Username) o;
        return Objects.equals( this.value(), username.value() );
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.value() );
    }

    @Override
    public String toString() {
        return this.value();
    }

}
