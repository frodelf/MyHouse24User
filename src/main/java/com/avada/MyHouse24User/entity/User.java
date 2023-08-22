package com.avada.MyHouse24User.entity;

import com.avada.MyHouse24User.enums.UserStatus;
import com.avada.MyHouse24User.services.registration.RegistrationRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "flats")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_Name")
    private String firstName;
    @Column(name = "last_Name")
    private String lastName;
    @Column(name = "fathers_Name")
    private String fathersName;
    private Date birthday;
    private Date fromDate;
    @Column(name = "oauth_provider")
    private String oauthProvider;
    @Column(name = "oauth_id")
    private Integer oauthId;
    private String phone;
    private String viber;
    private String telegram;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String description;
    private String image;
    private Theme theme;
    private boolean enabled = false;
    @OneToMany(mappedBy = "user")
    List<Flat> flats;
    @ManyToOne
    private Role roles;
    public User(RegistrationRequest request) {
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.fathersName = request.getFathersName();
        this.email = request.getEmail();
        this.password = request.getPassword();
    }

    public User(Long id, String firstName, String lastName, String fathersName, String phoneNumber, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fathersName = fathersName;
        this.phone = phoneNumber;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fathersName='" + fathersName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
