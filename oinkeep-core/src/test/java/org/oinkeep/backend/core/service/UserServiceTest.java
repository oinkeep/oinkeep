package org.oinkeep.backend.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.oinkeep.backend.core.dto.NewUser;
import org.oinkeep.backend.core.entity.User;
import org.oinkeep.backend.core.spi.respository.IUserRepository;
import org.oinkeep.backend.core.util.messages.ServiceMessages;
import org.oinkeep.backend.core.util.messages.ValueObjectMessages;
import org.oinkeep.backend.core.vo.LastName;
import org.oinkeep.backend.core.vo.Name;
import org.oinkeep.backend.core.vo.UserId;
import org.oinkeep.backend.core.vo.Username;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@Tag( "service" )
@Tag( "service-user" )
class UserServiceTest {

    private IUserRepository userRepository;
    private UserService userService;


    @BeforeEach
    void setUp() {
        this.userRepository = mock( IUserRepository.class );
        this.userService = new UserService( this.userRepository );
    }

    static Stream<NewUser> validNewUserDataProvider() {
        return Stream.of(
                new NewUser( "username", "name", "firstLastName", "secondLastName", "image" ),
                new NewUser( "username123", "name", "firstLastName", "secondLastName", "image" ),
                new NewUser( "username_user", "name", "firstLastName", "secondLastName", "image" ),
                new NewUser( "username_123", "name", "firstLastName", "secondLastName", "image" ),
                new NewUser( "username-user", "name", "firstLastName", "secondLastName", "image" ),
                new NewUser( "username-123", "name", "firstLastName", "secondLastName", "image" ),
                new NewUser( "use", "name", "firstLastName", "secondLastName", "image" ),
                new NewUser( "usernameusernameusernameu", "name", "firstLastName", "secondLastName", "image" )
        );
    }

    @DisplayName( "Creación de un usuario con datos válidos" )
    @ParameterizedTest(name = "User: {0}")
    @MethodSource( "validNewUserDataProvider" )
    void givenValidNewUserData_whenCreate_thenNewUserData( final NewUser validNewUserData ) {

        final User result = mock( User.class );
        when( result.getId() ).thenReturn( UserId.random() );
        when( result.getUsername() ).thenReturn( Username.valueOf( validNewUserData.getUsername() ) );
        when( result.getName() ).thenReturn( Name.valueOf( validNewUserData.getName() ) );
        when( result.getFirstLastName() ).thenReturn( LastName.valueOf( validNewUserData.getFirstLastName() ) );
        when( result.getSecondLastName() ).thenReturn( LastName.valueOf( validNewUserData.getSecondLastName() ) );
        when( result.getImage() ).thenReturn( validNewUserData.getImage() );

        when( this.userRepository.exists( any( Username.class ) ) ).thenReturn( false );
        when( this.userRepository.save( any( User.class ) )).thenReturn( Optional.of( result ) );

        // When
        final User newUser = this.userService.create( validNewUserData );

        // Then
        assertNotNull( newUser );
        assertNotNull( newUser.getId() );
        assertEquals( validNewUserData.getImage(), newUser.getImage() );
        assertNotNull( newUser.getUsername());
        assertEquals( validNewUserData.getUsername(), newUser.getUsername().toString());
        assertEquals( validNewUserData.getName(), newUser.getName().toString() );
        assertEquals( validNewUserData.getFirstLastName(), newUser.getFirstLastName().toString() );

    }


    static Stream<Arguments> invalidNewUserDataProvider() {
        return Stream.of(
                Arguments.of(
                        new NewUser( null, "name", "firstLastName", "secondLastName", "image" ),
                        ValueObjectMessages.USERNAME_IS_NULL.getMessage()
                ),
                Arguments.of(
                        new NewUser( "us", "name", "firstLastName", "secondLastName", "image" ),
                        ValueObjectMessages.USERNAME_IS_TOO_SHORT.getMessage()
                ),
                Arguments.of(
                        new NewUser( "usernameusernameusernameus", "name", "firstLastName", "secondLastName", "image" ),
                        ValueObjectMessages.USERNAME_IS_TOO_LONG.getMessage()
                ),
                Arguments.of(
                        new NewUser( "1username", "name", "firstLastName", "secondLastName", "image" ),
                        ValueObjectMessages.USERNAME_IS_INVALID.getMessage()
                ),
                Arguments.of(
                        new NewUser( "username", null, "firstLastName", "secondLastName", "image" ),
                        ValueObjectMessages.NAME_IS_NULL.getMessage()
                ),
                Arguments.of(
                        new NewUser( "username", "", "firstLastName", "secondLastName", "image" ),
                        ValueObjectMessages.NAME_IS_TOO_SHORT.getMessage()
                ),
                Arguments.of(
                        new NewUser( "username", "namenamenamenamenamenamenamenamenamenamename", "firstLastName", "secondLastName", "image" ),
                        ValueObjectMessages.NAME_IS_TOO_LONG.getMessage()
                ),
                Arguments.of(
                        new NewUser( "username", "name1", "firstLastName", "secondLastName", "image" ),
                        ValueObjectMessages.NAME_IS_INVALID.getMessage()
                ),
                Arguments.of(
                        new NewUser( "username", "name", null, "secondLastName", "image" ),
                        ValueObjectMessages.LASTNAME_IS_NULL.getMessage()
                ),
                Arguments.of(
                        new NewUser( "username", "name", "", "secondLastName", "image" ),
                        ValueObjectMessages.LASTNAME_IS_TOO_SHORT.getMessage()
                ),
                Arguments.of(
                        new NewUser( "username", "name", "firstLastNamefirstLastNamefirstLastNamefirstLastName", "secondLastName", "image" ),
                        ValueObjectMessages.LASTNAME_IS_TOO_LONG.getMessage()
                ),
                Arguments.of(
                        new NewUser( "username", "name", "firstLastName1", "secondLastName", "image" ),
                        ValueObjectMessages.LASTNAME_IS_INVALID.getMessage()
                ),
                Arguments.of(
                        new NewUser( "username", "name", "firstLastName", null, "image" ),
                        ValueObjectMessages.LASTNAME_IS_NULL.getMessage()
                ),
                Arguments.of(
                        new NewUser( "username", "name", "firstLastName", "", "image" ),
                        ValueObjectMessages.LASTNAME_IS_TOO_SHORT.getMessage()
                ),
                Arguments.of(
                        new NewUser( "username", "name", "firstLastName", "secondLastNamesecondLastNamesecondLastNamesecondLastName", "image" ),
                        ValueObjectMessages.LASTNAME_IS_TOO_LONG.getMessage()
                ),
                Arguments.of(
                        new NewUser( "username", "name", "firstLastName", "secondLastName1", "image" ),
                        ValueObjectMessages.LASTNAME_IS_INVALID.getMessage()
                )
        );
    }

    @DisplayName( "Creación de un usuario con datos inválidos" )
    @ParameterizedTest( name = "User: {0}, Message: {1}" )
    @MethodSource( "invalidNewUserDataProvider" )
    void givenInvalidNewUserData_whenCreate_thenThrowIllegalArgumentException( final NewUser invalidNewUserData, final String expectedMessage ) {
        //When & Then
        final IllegalArgumentException exception = assertThrows( IllegalArgumentException.class,
                () -> this.userService.create( invalidNewUserData ));
        assertEquals( expectedMessage, exception.getMessage() );
    }

    @Test
    @DisplayName( "Creación de un usuario con un username ya registrado" )
    void givenNewUserDataWithUsernameAlreadyRegistered_whenCreate_thenThrowIllegalArgumentException() {
        when( this.userRepository.exists( any( Username.class ))).thenReturn( true );

        //Given
        final NewUser newUser = new NewUser( "username", "name", "firstLastName", "secondLastName", "image" );

        //When & Then
        final IllegalArgumentException exception = assertThrows( IllegalArgumentException.class,
                () -> this.userService.create( newUser ));
        assertEquals( ServiceMessages.USER_USERNAME_ALREADY_EXISTS.getMessage(), exception.getMessage() );
    }

    @Test
    @DisplayName( "Creación de un usuario que no se guarda en el repositorio" )
    void givenNewUserData_whenCreate_thenThrowIllegalStateException() {
        when( this.userRepository.exists( any( Username.class ))).thenReturn( false );
        when( this.userRepository.save( any( User.class ))).thenReturn( Optional.empty() );

        //Given
        final NewUser newUser = new NewUser( "username", "name", "firstLastName", "secondLastName", "image" );

        //When & Then
        final IllegalStateException exception = assertThrows( IllegalStateException.class,
                () -> this.userService.create( newUser ));
        assertEquals( ServiceMessages.USER_NOT_CREATED.getMessage(), exception.getMessage() );
    }
}
