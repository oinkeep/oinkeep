package org.oinkeep.backend.core.vo;

import org.oinkeep.backend.util.vo.ValueObject;

import java.io.Serial;
import java.util.Objects;

import static org.oinkeep.backend.core.util.messages.ValueObjectMessages.*;

public class Name extends ValueObject<String> {

    private static final @Serial long serialVersionUID = 1L;

    public static final int MIN_LENGTH = 2;
    public static final int MAX_LENGTH = 35;
    public static final String NAME_PATTERN = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s\\-]+$";

    private final String value;

    private Name(final String value) {
        this.value = value;
        this.validate();
    }

    public static Name valueOf( final String value ) {
        return new Name (value);
    }

    private void validate() {
        if ( this.value == null) {
            throw  new IllegalArgumentException(NAME_IS_NULL.getMessage());
        }
        if ( this.value().length() < MIN_LENGTH ) {
            throw  new IllegalArgumentException( NAME_IS_TOO_SHORT.getMessage());
        }
        if ( this.value().length() > MAX_LENGTH) {
            throw  new IllegalArgumentException( NAME_IS_TOO_LONG.getMessage());
        }
        if ( !this.value().matches(NAME_PATTERN)) {
            throw  new IllegalArgumentException( NAME_IS_INVALID.getMessage() );
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
        Name name = (Name) o;
        return Objects.equals(this.value(), name.value());
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
