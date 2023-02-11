package ir.maktab.finalprojectphase3.HomeServiceProvider.data.model;

import jakarta.persistence.*;
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
public class SubService extends BaseEntity implements Service {
    @ManyToOne //(cascade = {CascadeType.ALL})
    BaseService baseService;

    @Column(unique = true)
    String name;

    String description;

    Double basePrice;

    public SubService() {
        this.isDeleted = false;
    }

    @ManyToMany(mappedBy = "subServices", targetEntity = Expert.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE, })
    List<Expert> experts = new ArrayList<>();

    boolean isDeleted;

    public SubService(Long id, BaseService baseService, String name, String description, Double basePrice) {
        super(id);
        this.baseService = baseService;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.isDeleted = false;
    }
    public SubService(BaseService baseService, String name, String description, Double basePrice) {
        this.baseService = baseService;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.isDeleted = false;
    }

    public void showServiceDetails() {
        System.out.println("name: " + name + " | " +
                "description: " + description + " | " +
                "basePrice: " + basePrice);
    }
}
