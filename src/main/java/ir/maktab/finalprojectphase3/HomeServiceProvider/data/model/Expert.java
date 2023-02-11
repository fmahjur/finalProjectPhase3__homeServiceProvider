package ir.maktab.finalprojectphase3.HomeServiceProvider.data.model;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.ExpertStatus;
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
public class Expert extends Account {
    @Enumerated(EnumType.STRING)
    ExpertStatus expertStatus;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    byte[] personalPhoto;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = SubService.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<SubService> subServices = new ArrayList<>();

    double rate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "expert", cascade = {CascadeType.ALL})
    List<Comment> comments = new ArrayList<>();

    boolean isDeleted;

    public void setSubServices(SubService subServices) {
        this.subServices.add(subServices);
    }

    public Expert() {
        this.expertStatus = ExpertStatus.NEW;
        this.rate = 0;
        this.isDeleted = false;
    }

    public Expert(Long id, String firstname, String lastname, String emailAddress, String password, Credit credit, byte[] personalPhoto) {
        super(id, firstname, lastname, emailAddress, password, credit);
        this.expertStatus = ExpertStatus.NEW;
        this.rate = 0;
        this.personalPhoto = personalPhoto;
        this.isDeleted = false;
    }

    public Expert(String firstname, String lastname, String emailAddress, String password, Credit credit, byte[] personalPhoto) {
        super(firstname, lastname, emailAddress, password, credit);
        this.expertStatus = ExpertStatus.NEW;
        this.rate = 0;
        this.personalPhoto = personalPhoto;
        this.isDeleted = false;
    }

    public void setRate() {
        double sum = 0;
        for (Comment comment :
                comments) {
            sum += comment.getScore();
        }
        this.rate = sum / comments.size();
    }
}
