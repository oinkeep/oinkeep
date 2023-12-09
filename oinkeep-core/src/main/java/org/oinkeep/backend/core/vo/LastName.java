package org.oinkeep.backend.core.vo;

import org.oinkeep.backend.util.vo.ValueObject;

import java.io.Serial;
import java.util.Objects;

import static org.oinkeep.backend.core.util.messages.ValueObjectMessages.*;

public class LastName extends ValueObject<String> {

    private static final @Serial long serialVersionUID = 1L;

    public static final int MIN_LENGTH = Name.MIN_LENGTH;
    public static final int MAX_LENGTH = Name.MAX_LENGTH;
    public static final String LASTNAME_PATTERN = Name.NAME_PATTERN;

    private final String value;

    private LastName(final String value) {
        this.value = value;
        this.validate();
    }

    public static LastName valueOf( final String value ) {
        return new LastName (value);
    }

    private void validate() {
        if ( this.value == null) {
            throw new IllegalArgumentException(LASTNAME_IS_NULL.getMessage());
        }
        if ( this.value().length() < MIN_LENGTH ){
            throw new IllegalArgumentException( LASTNAME_IS_TOO_SHORT.getMessage());
        }
        if ( this.value().length() > MAX_LENGTH) {
            throw new IllegalArgumentException( LASTNAME_IS_TOO_LONG.getMessage());
        }
        if ( !this.value().matches(LASTNAME_PATTERN)) {
            throw new IllegalArgumentException(LASTNAME_IS_INVALID.getMessage());
        }
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LastName lastName = (LastName) o;
        return Objects.equals(this.value(), lastName.value());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value());
    }

    @Override
    public String toString() {
        return this.value();
    }
}
