package ir.maktab.finalprojectphase3.HomeServiceProvider.data.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Customer extends Account {
    @OneToMany(mappedBy = "customer", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Orders> orders = new ArrayList<>();

    boolean isDeleted;

    public Customer() {
        this.isDeleted = false;
    }

    public Customer(Long id, String firstname, String lastname, String emailAddress, String password, Long credit) {
        super(id, firstname, lastname, emailAddress, password, credit);
        this.isDeleted = false;
    }

    public Customer(String firstname, String lastname, String emailAddress, String password, Long credit) {
        super(firstname, lastname, emailAddress, password, credit);
        this.isDeleted = false;
    }
}
