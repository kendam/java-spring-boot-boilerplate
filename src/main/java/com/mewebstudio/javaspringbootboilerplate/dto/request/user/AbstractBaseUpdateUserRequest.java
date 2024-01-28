package com.mewebstudio.javaspringbootboilerplate.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractBaseUpdateUserRequest {
    @Email(message = "{invalid_email}")
    @Size(max = 100, message = "{max_length}")
    @Schema(
        name = "email",
        description = "Email of the user",
        type = "String",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example = "mail@example.com"
    )
    private String email;

    @Size(max = 50, message = "{max_length}")
    @Schema(
        name = "userName",
        description = "Name of the user",
        type = "String",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example = "John"
    )
    private String userName;

    @Size(max = 50, message = "{max_length}")
    @Schema(
            name = "firstName",
            description = "Name of the user",
            type = "String",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "John"
    )
    private String firstName;

    @Size(max = 50, message = "{max_length}")
    @Schema(
        name = "lastName",
        description = "Lastname of the user",
        type = "String",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example = "DOE"
    )
    private String lastName;

    @Size(max = 50, message = "{max_length}")
    @Schema(
            name = "gender",
            description = "User's gender",
            type = "String",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "DOE"
    )
    private String gender;

    @Size(max = 50, message = "{max_length}")
    @Schema(
            name = "dateOfBirth",
            description = "User's date of birth",
            type = "String",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "DOE"
    )
    private String dateOfBirth;
}
