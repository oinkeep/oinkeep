package org.oinkeep.backend.core.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.oinkeep.backend.core.dto.NewUser;
import org.oinkeep.backend.core.vo.LastName;
import org.oinkeep.backend.core.vo.Name;
import org.oinkeep.backend.core.vo.UserId;
import org.oinkeep.backend.core.vo.Username;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.oinkeep.backend.core.util.messages.ValueObjectMessages.*;

@Tag("entity")
@Tag("entity-user")
class UserTest {

    @Test
    void givenValidUserData_whenCreate_thenNewUserData(){

        final String username = "Anne94";
        final String name = "Anne with an E";
        final String firstLastName = "Shirley";
        final String secondLastName = "Cuthbert";
        final String image = "image";

        final String usernameExpected = "anne94";

        final User user = User.create(username, name, firstLastName, secondLastName, image);

        assertNotNull(user);
        assertEquals(usernameExpected, user.getUsername().toString());
        assertEquals(name, user.getName().toString());
        assertEquals(firstLastName, user.getFirstLastName().toString());
        assertEquals(secondLastName, user.getSecondLastName().toString());

    }

    static Stream<Arguments> invalidNewUserParameters() {
        return Stream.of(
                Arguments.of(
                        new NewUser("Anne 94","Anne with an E","Shirley","Cuthbert","image"),
                        USERNAME_IS_INVALID.getMessage()
                ),
                Arguments.of(
                        new NewUser("Anne","Anne with an E.","Shirley","Cuthbert","image"),
                        NAME_IS_INVALID.getMessage()
                ),
                Arguments.of(
                        new NewUser("Anne","Anne with an E","Shirley9","Cuthbert","image"),
                        LASTNAME_IS_INVALID.getMessage()
                ),
                Arguments.of(
                        new NewUser("Anne","Anne with an E","Shirley","Cuthbert4","image"),
                        LASTNAME_IS_INVALID.getMessage()
                )

        );
    }

    @DisplayName( "Creación de un usuario con datos inválidos" )
    @ParameterizedTest( name = "NewUser: {0}" )
    @MethodSource( "invalidNewUserParameters" )
    void givenInvalidUserData_whenCreate_thenThrowIllegalArgumentException(final NewUser newUser, final String expectedMessage){

        final String username = newUser.getUsername();
        final String name = newUser.getName();
        final String firstLastName = newUser.getFirstLastName();
        final String secondLastName = newUser.getSecondLastName();
        final String image = newUser.getImage();

        final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
            () -> User.create(username, name, firstLastName, secondLastName, image));
        assertEquals(expectedMessage, illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Comparacion de dos User iguales")
    void givenTwoEqualUser_whenCompare_thenTheyShouldBeEqual(){

        final UserId id = UserId.valueOf("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");
        final Username username1 = Username.valueOf("Anne94");
        final Name name1 = Name.valueOf("Anne with an E");
        final LastName firstLastName1 = LastName.valueOf("Shirley");
        final LastName secondLastName1 = LastName.valueOf("Cuthbert");
        final String image1 = "image";

        final Username username2 = Username.valueOf("GilbertBlythe");
        final Name name2 = Name.valueOf("Gilbert");
        final LastName firstLastName2 = LastName.valueOf("Blythe");
        final LastName secondLastName2 = null;
        final String image2 = null;

        final User user1 = new User(id, username1, name1, firstLastName1, secondLastName1, image1);
        final User user2 = new User(id, username2, name2, firstLastName2, secondLastName2, image2);

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.getUsername(), user2.getUsername());
        assertNotEquals(user1.getName(), user2.getName());
        assertNotEquals(user1.getFirstLastName(), user2.getFirstLastName());
        assertNotEquals(user1.getSecondLastName(), user2.getSecondLastName());
        assertNotEquals(user1.getImage(), user2.getImage());
        assertNotEquals(user1.toString(), user2.toString());

    }

}
