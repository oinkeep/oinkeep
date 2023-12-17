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
@Tag("value-object-lastname")
class LastNameTest {

    static Stream<String> validLastNameParameters() {
        return Stream.of(
                "Foz",
                "Fernández",
                "Da Silva",
                "Montañez-Mendoza",
                "Pingüi"
        );
    }

    @DisplayName("Creación del valueObject lastname con datos válidos")
    @ParameterizedTest(name= "LastName: {0}")
    @MethodSource("validLastNameParameters")
    void givenValidLastNameData_whenCreate_thenOK(final String lastName){

        // when
        final LastName newLastName = LastName.valueOf(lastName);

        // then
        assertEquals(lastName, newLastName.value());
        assertEquals(lastName, newLastName.toString());

    }

    static Stream<Arguments> invalidLastNameParameters() {
        return Stream.of(
            Arguments.of( null, LASTNAME_IS_NULL.getMessage()),
            Arguments.of( "", LASTNAME_IS_TOO_SHORT.getMessage()),
            Arguments.of( "Fernández-Hernández-Valenzuela-González", LASTNAME_IS_TOO_LONG.getMessage()),
            Arguments.of( "Föz", LASTNAME_IS_INVALID.getMessage()),
            Arguments.of( "Muiño_Soto", LASTNAME_IS_INVALID.getMessage()),
            Arguments.of( "Lopez93", LASTNAME_IS_INVALID.getMessage())
        );
    }

    @DisplayName("Creación del valueObject lastname con datos inválidos")
    @ParameterizedTest(name= "LastName: {0}, Message: {1}")
    @MethodSource("invalidLastNameParameters")
    void givenInvalidNameData_whenCreate_thenThrowIllegalArgumentException(final String lastName, final String expectedMessage){

        final IllegalArgumentException illegalArgumentException =
                Assertions.assertThrows(IllegalArgumentException.class, () -> LastName.valueOf(lastName));
        assertEquals(expectedMessage, illegalArgumentException.getMessage());

    }

    @Test
    @DisplayName("Comparacion de dos LastName")
    void givenTwoEqualLastNames_whenCompare_thenTheyShouldBeEqual(){

        final LastName lastname1 = LastName.valueOf("Shirley");
        final LastName lastname2 = LastName.valueOf("Shirley");

        assertEquals(lastname1, lastname2);
        assertEquals(lastname1.hashCode(), lastname2.hashCode());

    }
}
