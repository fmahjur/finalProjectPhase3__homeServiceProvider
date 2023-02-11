package ir.maktab.finalprojectphase3.HomeServiceProvider.data.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
public class BaseService extends BaseEntity implements Service {
    @Column(unique = true)
    String name;

    @OneToMany(mappedBy = "baseService", cascade = CascadeType.ALL)
    List<SubService> subServiceList = new ArrayList<>();

    boolean isDeleted;

    public BaseService() {
        this.isDeleted = false;
    }

    public BaseService(Long id, String name) {
        super(id);
        this.name = name;
        this.isDeleted = false;
    }
    public BaseService(String name) {
        this.name = name;
        this.isDeleted = false;
    }

    public void addSubService(SubService subService) {
        subServiceList.add(subService);
    }

    public void removeSubService(SubService subService) {
        subServiceList.remove(subService);
    }

    public void showServiceDetails() {
        System.out.println("Service name: " + name);
        for (SubService sub : subServiceList) {
            sub.showServiceDetails();
        }
    }
}
