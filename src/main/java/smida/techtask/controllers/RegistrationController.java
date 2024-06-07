package smida.techtask.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smida.techtask.controllers.dto.ErrorDto;
import smida.techtask.controllers.dto.RegistrationDto;

import static smida.techtask.constants.RegistrationConstantMessages.USERNAME_IS_ALREADY_TAKEN_MESSAGE;
import static smida.techtask.constants.RegistrationConstantMessages.USER_REGISTERED_SUCCESSFULLY_MESSAGE;

/**
 * REST controller for handling user registration.
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Tag(name = "Registration", description = "Endpoints for registration and confirmation")
public class RegistrationController {

    private final RegistrationService registrationService;


    @PostMapping("/sign-up")
    @Operation(summary = "Register a user",
            description = "Attempt to sign up new user",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = USER_REGISTERED_SUCCESSFULLY_MESSAGE + "\t\n"
                                    + USERNAME_IS_ALREADY_TAKEN_MESSAGE),
                    @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content(schema = @Schema(implementation = ErrorDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
            })
    public void signUp(@RequestBody @Valid @NotNull RegistrationDto requestBody) {
        registrationService.register(requestBody);
    }

}
