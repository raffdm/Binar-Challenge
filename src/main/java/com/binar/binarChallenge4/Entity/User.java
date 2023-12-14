package com.binar.binarChallenge4.Entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user") //membuat table user
public class User {

    @GenericGenerator(strategy = "uuid2", name = "uuid")
    @GeneratedValue(generator = "uuid")
    @Id
    private String id;

    @Column(nullable = false, unique = true, length = 20) //menandakan kolom yang tidak dapat dikosongkan dan bersifat unik
    private String username;

    @Column(nullable = false, unique = true) //menandakan kolom yang tidak dapat dikosongkan dan bersifat unik
    private String email;

    @Column(nullable = false, unique = false, length = 10)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
