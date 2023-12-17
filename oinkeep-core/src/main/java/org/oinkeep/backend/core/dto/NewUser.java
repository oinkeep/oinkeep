package org.oinkeep.backend.core.dto;

import lombok.*;
import org.oinkeep.backend.util.dto.DTO;

import java.io.Serial;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class NewUser extends DTO {

    private static final @Serial long serialVersionUID = 1L;

    private String username;
    private String name;
    private String firstLastName;
    private String secondLastName;
    private String image;
}
