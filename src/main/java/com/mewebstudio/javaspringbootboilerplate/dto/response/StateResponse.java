package com.mewebstudio.javaspringbootboilerplate.dto.response;

import com.mewebstudio.javaspringbootboilerplate.entity.LocationState;
import com.mewebstudio.javaspringbootboilerplate.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
public class StateResponse extends AbstractBaseResponse {
    @Schema(
        name = "id",
        description = "Primary Key",
        type = "String",
        example = "1"
    )
    private int id;

    @Schema(
        name = "stateName",
        description = "State Name",
        type = "String",
        example = "Lagos"
    )
    private String stateName;

    @Schema(
            name = "createdAt",
            description = "Date time field of user creation",
            type = "LocalDateTime",
            example = "2022-09-29T22:37:31"
    )
    private LocalDateTime createdAt;

    @Schema(
            name = "updatedAt",
            type = "LocalDateTime",
            description = "Date time field of user update",
            example = "2022-09-29T22:37:31"
    )
    private LocalDateTime updatedAt;


    /**
     * Convert state to StateResponse
     * @param state LocationState
     * @return UserResponse
     */
    public static StateResponse convert(LocationState state) {
        return StateResponse.builder()
            .id(state.getId())
            .stateName(state.getStateName())
            .createdAt(state.getCreatedAt())
            .updatedAt(state.getUpdatedAt())
            .build();
    }
}
