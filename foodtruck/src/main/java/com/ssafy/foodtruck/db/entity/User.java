package com.ssafy.foodtruck.db.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder @Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime regDate;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String pw;
    @Column(unique = true, nullable = false)
    private String mobileNumber;
    @Column(unique = true, nullable = false)
    private String nickname;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "userAuth",
            joinColumns = {
                    @JoinColumn(name = "userId", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "authName", referencedColumnName = "authName")
            }
    )
    private Set<Authority> authorities;

}
