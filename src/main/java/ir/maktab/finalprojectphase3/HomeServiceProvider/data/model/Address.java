package ir.maktab.finalprojectphase3.HomeServiceProvider.data.model;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.City;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Address extends BaseEntity {
    @Enumerated(EnumType.STRING)
    City city;
    String addressDetail;
}
