package org.oinkeep.backend.core.util.messages;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.oinkeep.backend.core.entity.User;
import org.oinkeep.backend.core.vo.LastName;
import org.oinkeep.backend.core.vo.Name;
import org.oinkeep.backend.core.vo.Username;

@AllArgsConstructor
@Getter
public enum ValueObjectMessages {

    USER_ID_IS_NULL( "El ID del usuario no puede ser nulo" ),
    USER_ID_IS_INVALID( "El ID del usuario no tiene un formato de UUID válido" ),

    USERNAME_IS_NULL( "El nombre de usuario no puede ser nulo" ),
    USERNAME_IS_TOO_SHORT( String.format( "El nombre de usuario debe tener al menos %d caracteres", Username.MIN_LENGTH )),
    USERNAME_IS_TOO_LONG( String.format( "El nombre de usuario no puede tener más de %d caracteres", Username.MAX_LENGTH )),
    USERNAME_IS_INVALID( "El nombre de usuario no tiene un formato válido" ),

    NAME_IS_NULL( "El nombre no puede ser nulo" ),
    NAME_IS_TOO_SHORT( String.format( "El nombre debe tener al menos %d caracteres", Name.MIN_LENGTH )),
    NAME_IS_TOO_LONG( String.format( "El nombre no puede tener más de %d caracteres", Name.MAX_LENGTH )),
    NAME_IS_INVALID( "El nombre no tiene un formato válido" ),

    LASTNAME_IS_NULL( "El apellido no puede ser nulo" ),
    LASTNAME_IS_TOO_SHORT( String.format( "El apellido debe tener al menos %d caracteres", LastName.MIN_LENGTH )),
    LASTNAME_IS_TOO_LONG( String.format( "El apellido no puede tener más de %d caracteres", LastName.MAX_LENGTH ) ),
    LASTNAME_IS_INVALID( "El apellido no tiene un formato válido" );

    private final String message;
}
