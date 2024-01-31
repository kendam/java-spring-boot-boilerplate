package com.mewebstudio.javaspringbootboilerplate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "states", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"state_name"}, name = "uk_states_state_name")
}, indexes = {
    @Index(columnList = "state_name", name = "idx_states_state_name")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationState extends AbstractBaseIntEntity {
    @Column(name = "state_name", nullable = false)
    private String stateName;

}
