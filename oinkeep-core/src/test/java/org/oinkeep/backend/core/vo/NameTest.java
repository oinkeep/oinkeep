package org.oinkeep.backend.core.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.oinkeep.backend.core.util.messages.ValueObjectMessages.*;

@Tag("value-object")
@Tag("value-object-name")
class NameTest {

    static Stream<String> validNameParameters() {
        return Stream.of(
                "Anne",
                "Alejandro Javier",
                "Federico-Lorenzo",
                "Ángel"
        );
    }

    @DisplayName("Creación del valueObject name con datos válidos")
    @ParameterizedTest(name= "Name: {0}")
    @MethodSource("validNameParameters")
    void givenValidNameData_whenCreate_thenOK(final String name){

        // when
        final Name newName = Name.valueOf(name);

        // then
        assertEquals(name, newName.value());
        assertEquals(name, newName.toString());

    }

    static Stream<Arguments> invalidNameParameters() {
        return Stream.of(
            Arguments.of( null, NAME_IS_NULL.getMessage()),
            Arguments.of( "", NAME_IS_TOO_SHORT.getMessage()),
            Arguments.of( "Ana Maria Juliana Valentina Federica", NAME_IS_TOO_LONG.getMessage()),
            Arguments.of( "Evä", NAME_IS_INVALID.getMessage()),
            Arguments.of( "María_Isabel", NAME_IS_INVALID.getMessage()),
            Arguments.of( "Catty93", NAME_IS_INVALID.getMessage())
        );
    }

    @DisplayName("Creación del valueObject name con datos inválidos")
    @ParameterizedTest(name= "Name: {0}, Message: {1}")
    @MethodSource("invalidNameParameters")
    void givenInvalidNameData_whenCreate_thenThrowIllegalArgumentException(final String name, final String expectedMessage){

        final IllegalArgumentException illegalArgumentException =
                Assertions.assertThrows(IllegalArgumentException.class, () -> Name.valueOf(name));
        assertEquals(expectedMessage, illegalArgumentException.getMessage());

    }

    @Test
    @DisplayName("Comparacion de dos Name")
    void givenTwoEqualNames_whenCompare_thenTheyShouldBeEqual(){

        final Name name1 = Name.valueOf("Gilbert");
        final Name name2 = Name.valueOf("Gilbert");

        assertEquals(name1, name2);
        assertEquals(name1.hashCode(), name2.hashCode());

    }
}
