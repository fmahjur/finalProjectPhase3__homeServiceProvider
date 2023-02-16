package ir.maktab.finalprojectphase3.HomeServiceProvider.data.model;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class Account extends Person {
    @Column(unique = true)
    String username;

    String password;

    Long credit;
    @CreationTimestamp
    LocalDateTime registeryDate;

    private Boolean isActive;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Account(String firstname, String lastname, String emailAddress) {
        super(firstname, lastname, emailAddress);
        this.username = emailAddress;
    }

    public Account(Long id, String firstname, String lastname, String emailAddress, String password, Long credit) {
        super(id, firstname, lastname, emailAddress);
        this.username = emailAddress;
        this.password = password;
        this.credit = credit;
    }

    public Account(String firstname, String lastname, String emailAddress, String password, Long credit) {
        super(firstname, lastname, emailAddress);
        this.username = emailAddress;
        this.password = password;
        this.credit = credit;
    }
}
