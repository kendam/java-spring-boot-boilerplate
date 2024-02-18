package com.ksolutions.javaspringbootboilerplate.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"}, name = "uk_users_email")
}, indexes = {
    @Index(columnList = "user_name", name = "idx_users_user_name"),
    @Index(columnList = "last_name", name = "idx_users_last_name"),
        @Index(columnList = "first_name", name = "idx_users_first_name"),

})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractBaseEntity {
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "other_name", length = 50)
    private String otherName;

    @Column(name = "date_of_birth", nullable = false, length = 50)
    private String dateOfBirth;

    @Column(name = "gender", nullable = false, length = 50)
    private String gender;

    @Column(name = "avatar", columnDefinition = "text")
    private String avatar;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(
            name = "city_id",
            referencedColumnName = "id",
            nullable = true,
            foreignKey = @ForeignKey(name = "fk_cities_city_id")
    )
    private City city;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(
            name = "state_id",
            referencedColumnName = "id",
            nullable = true,
            foreignKey = @ForeignKey(name = "fk_states_state_id")
    )
    private LocationState state;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(
                name = "fk_user_roles_user_id",
                foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE"
            ),
            nullable = false
        ),
        inverseJoinColumns = @JoinColumn(
            name = "role_id",
            foreignKey = @ForeignKey(
                name = "fk_user_roles_role_id",
                foreignKeyDefinition = "FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE"
            ),
            nullable = false
        ),
        uniqueConstraints = {
            @UniqueConstraint(
                columnNames = {"user_id", "role_id"},
                name = "uk_user_roles_user_id_role_id"
            )
        }
    )
    @Builder.Default
    private List<Role> roles = new ArrayList<>();


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmailVerificationToken emailVerificationToken;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private PasswordResetToken passwordResetToken;

    @Column(name = "email_verified_at")
    private LocalDateTime emailVerifiedAt;

    @Column(name = "blocked_at")
    private LocalDateTime blockedAt;

    /**
     * Get full name of user.
     *
     * @return String
     */
    public String getFullName() {
        return this.lastName + " " + this.firstName + " " + (this.otherName == null? "" : this.otherName);
    }
}
