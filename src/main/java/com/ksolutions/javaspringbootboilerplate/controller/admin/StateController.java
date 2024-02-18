package com.ksolutions.javaspringbootboilerplate.controller.admin;

import com.ksolutions.javaspringbootboilerplate.controller.AbstractBaseController;
import com.ksolutions.javaspringbootboilerplate.dto.response.ErrorResponse;
import com.ksolutions.javaspringbootboilerplate.dto.response.StateResponse;
import com.ksolutions.javaspringbootboilerplate.entity.LocationState;
import com.ksolutions.javaspringbootboilerplate.service.MessageSourceService;
import com.ksolutions.javaspringbootboilerplate.service.StateService;
import com.ksolutions.javaspringbootboilerplate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.ksolutions.javaspringbootboilerplate.util.Constants.SECURITY_SCHEME_NAME;

@RestController
@RequiredArgsConstructor
@RequestMapping("/states")
@Tag(name = "State", description = "State API")
public class StateController extends AbstractBaseController {
    private static final String[] SORT_COLUMNS = new String[]{"id", "email", "name", "lastName", "blockedAt",
        "createdAt", "updatedAt"};

    private final UserService userService;

    private final StateService stateService;

    private final MessageSourceService messageSourceService;

    @GetMapping
    @Operation(
        summary = "States list endpoint",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success operation",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = LocationState.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Bad Request",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            ),

        }
    )
    public ResponseEntity<List<StateResponse>> list() {
        List<LocationState> states = stateService.getList();
        return ResponseEntity.ok(states.stream().map(StateResponse::convert).toList());
    }

    @PostMapping
    @Operation(
        summary = "Create state endpoint",
        security = @SecurityRequirement(name = SECURITY_SCHEME_NAME),
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Success operation",
                content = @Content(schema = @Schema(hidden = true)),
                headers = @Header(
                    name = "Location",
                    description = "Location of created user",
                    schema = @Schema(type = "string")
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Bad request",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            ),
            @ApiResponse(
                responseCode = "401",
                description = "Full authentication is required to access this resource",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Not Found",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            ),
            @ApiResponse(
                responseCode = "422",
                description = "Validation Failed",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            )
        }
    )
    public ResponseEntity<LocationState> create(
        @Parameter(description = "Request body to create a state", required = true)
        @RequestBody @Valid final LocationState request
    ) throws BindException {
        LocationState state = stateService.create(request);

        return ResponseEntity.ok(state);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get Single state",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success operation",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = LocationState.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Not Found",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)
                )
            )
        }
    )
    public ResponseEntity<Optional<LocationState>> show(
        @Parameter(name = "id", description = "State ID", required = true)
        @PathVariable("id") final int id
    ) {
        return ResponseEntity.ok(stateService.getStateById(id));
    }
}
