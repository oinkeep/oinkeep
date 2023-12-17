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
@Tag("value-object-username")
class UsernameTest {

    static Stream<String> validUsernameParameters() {
        return Stream.of(
                "Ann",
                "Anne94",
                "AnneOfGreenGables_theBest",
                "AnneOfGreenGables_94",
                "federico-lorenzo",
                "Federico-Lorenzo27"
        );
    }

    @DisplayName("Creación del valueObject username con datos válidos")
    @ParameterizedTest(name= "Username: {0}")
    @MethodSource("validUsernameParameters")
    void givenValidUsernameData_whenCreate_thenOK(final String username){

        // when
        final Username newUsername = Username.valueOf(username);

        // then
        assertEquals(username.toLowerCase(), newUsername.value());
        assertEquals(username.toLowerCase(), newUsername.toString());

    }

    static Stream<Arguments> invalidUsernameParameters() {
        return Stream.of(
            Arguments.of( null, USERNAME_IS_NULL.getMessage()),
            Arguments.of( "", USERNAME_IS_TOO_SHORT.getMessage()),
            Arguments.of( "An", USERNAME_IS_TOO_SHORT.getMessage()),
            Arguments.of( "Ana_Maria_Juliana_Valentina", USERNAME_IS_TOO_LONG.getMessage()),
            Arguments.of( "Evä", USERNAME_IS_INVALID.getMessage()),
            Arguments.of( "Maria Isabel", USERNAME_IS_INVALID.getMessage()),
            Arguments.of( "MaríaIsabel", USERNAME_IS_INVALID.getMessage())
        );
    }

    @DisplayName("Creación del valueObject username con datos inválidos")
    @ParameterizedTest(name= "Username: {0}, Message: {1}")
    @MethodSource("invalidUsernameParameters")
    void givenInvalidNameData_whenCreate_thenThrowIllegalArgumentException(final String username
            , final String expectedMessage){

        final IllegalArgumentException illegalArgumentException =
                Assertions.assertThrows(IllegalArgumentException.class, () -> Username.valueOf(username));
        assertEquals(expectedMessage, illegalArgumentException.getMessage());

    }

    @Test
    @DisplayName("Comparacion de dos Username")
    void givenTwoEqualUsername_whenCompare_thenTheyShouldBeEqual(){

        final Username username1 = Username.valueOf("Anne94");
        final Username username2 = Username.valueOf("Anne94");

        assertEquals(username1, username2);
        assertEquals(username1.hashCode(), username2.hashCode());

    }
}
