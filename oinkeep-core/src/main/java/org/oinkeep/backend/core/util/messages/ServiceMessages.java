package org.oinkeep.backend.core.util.messages;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.oinkeep.backend.core.vo.LastName;
import org.oinkeep.backend.core.vo.Name;
import org.oinkeep.backend.core.vo.Username;

@AllArgsConstructor
@Getter
public enum ServiceMessages {

    USER_USERNAME_ALREADY_EXISTS( "El nombre de usuario ya esta registrado" ),
    USER_NOT_CREATED( "No se pudo crear el usuario" );

    private final String message;
}
