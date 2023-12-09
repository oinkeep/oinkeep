package org.oinkeep.backend.core.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.oinkeep.backend.core.util.messages.ValueObjectMessages.USER_ID_IS_INVALID;
import static org.oinkeep.backend.core.util.messages.ValueObjectMessages.USER_ID_IS_NULL;

@Tag("value-object")
@Tag("value-object-user-id")
class UserIdTest {

    static Stream<String> validUserIdParameters() {
        return Stream.of(
                "550e8400-e29b-41d4-a716-446655440000",
                "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"
        );
    }

    @DisplayName("Creación del valueObject userid con datos tipo String válidos")
    @ParameterizedTest(name= "Name: {0}")
    @MethodSource("validUserIdParameters")
    void givenValidStringUserIdData_whenCreate_thenOK(final String userId){

        // when
        final UserId newUserId = UserId.valueOf(userId);

        // then
        assertEquals(userId, newUserId.value().toString());
        assertEquals(userId, newUserId.toString());

    }

    @Test
    @DisplayName("Creación del valueObject userId con datos tipo UUID válidos")
    void givenValidUserIdData_whenCreate_thenOK(){

        final UUID uuid = UUID.randomUUID();
        final UserId newUserId = UserId.valueOf(uuid);

        assertNotNull(newUserId);
        assertEquals(uuid, newUserId.value());
        assertEquals(uuid.toString(), newUserId.toString());

    }

    @Test
    @DisplayName("Creación del valueObject userId con datos random válidos")
    void givenValidUserIdData_whenCreateRandom_thenOK(){

        final UserId newUserId = UserId.random();

        assertNotNull(newUserId);

    }

    static Stream<Arguments> invalidUserIdParameters() {
        return Stream.of(
            Arguments.of( null, USER_ID_IS_NULL.getMessage()),
            Arguments.of( "123e4567-89ab-cdef-ghij-klmnopqrstuv", USER_ID_IS_INVALID.getMessage())
        );
    }

    @DisplayName("Creación del valueObject userid con datos inválidos")
    @ParameterizedTest(name= "UserId: {0}, Message: {1}")
    @MethodSource("invalidUserIdParameters")
    void givenInvalidUserIdData_whenCreate_thenThrowIllegalArgumentException(final String userId, final String expectedMessage){

        final IllegalArgumentException illegalArgumentException =
                Assertions.assertThrows(IllegalArgumentException.class, () -> UserId.valueOf(userId));
        assertEquals(expectedMessage, illegalArgumentException.getMessage());

    }

    @Test
    @DisplayName("Comparacion de dos UserId")
    void givenTwoEqualUserId_whenCompare_thenTheyShouldBeEqual(){

        final UserId userId1 = UserId.valueOf("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");
        final UserId userId2 = UserId.valueOf("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");

        assertEquals(userId1, userId2);
        assertEquals(userId1.hashCode(), userId2.hashCode());

    }
}
