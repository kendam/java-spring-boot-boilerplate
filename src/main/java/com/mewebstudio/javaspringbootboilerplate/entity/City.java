package com.mewebstudio.javaspringbootboilerplate.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "cities", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"city_name"}, name = "uk_cities_city_name")
}, indexes = {
    @Index(columnList = "city_name", name = "idx_cities_city_name")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City extends AbstractBaseEntity {
    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    @OneToOne(mappedBy = "state", cascade = CascadeType.ALL, orphanRemoval = true)
    private int stateId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(
            name = "state_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_states_state_id")
    )
    private State state;


}
