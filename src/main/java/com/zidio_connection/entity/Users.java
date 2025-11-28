package com.zidio_connection.entity;

//import javax.persistence.*;
import com.zidio_connection.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    @Column(unique = true)
    private String userEmail;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean active = true;
}
