package ir.maktab.finalprojectphase3.HomeServiceProvider.data.model;

import ir.maktab.finalprojectphase3.HomeServiceProvider.Utils.DateUtil;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Orders extends BaseEntity {
    String orderNumber;
    @ManyToOne
    Customer customer;

    @ManyToOne
    SubService subService;

    @OneToMany(mappedBy = "orders")
    List<Offer> offers = new ArrayList<>();

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    Long CustomerProposedPrice;

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @OneToOne
    Comment comment;

    boolean isDeleted;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date orderRegistrationDate;

    @Temporal(TemporalType.TIMESTAMP)
    Date workStartDate;

    int durationOfWork;

    @OneToOne(fetch = FetchType.EAGER)
    Address address;

    public Orders() {
        this.orderStatus = OrderStatus.WAITING_FOR_EXPERTS_OFFER;
        this.isDeleted = false;
    }

    public Orders(Long id, String orderNumber, Customer customer, SubService subService, String description, Long customerProposedPrice, Date workStartDate, int durationOfWork, Address address) {
        super(id);
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.subService = subService;
        this.description = description;
        this.CustomerProposedPrice = customerProposedPrice;
        this.orderRegistrationDate = DateUtil.asDate(LocalDateTime.now());
        this.workStartDate = workStartDate;
        this.durationOfWork = durationOfWork;
        this.address = address;
        this.orderStatus = OrderStatus.WAITING_FOR_EXPERTS_OFFER;
        this.isDeleted = false;
    }
    public Orders(String orderNumber, Customer customer, SubService subService, String description, Long customerProposedPrice, Date workStartDate, int durationOfWork, Address address) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.subService = subService;
        this.description = description;
        this.CustomerProposedPrice = customerProposedPrice;
        this.orderRegistrationDate = DateUtil.asDate(LocalDateTime.now());
        this.workStartDate = workStartDate;
        this.durationOfWork = durationOfWork;
        this.address = address;
        this.orderStatus = OrderStatus.WAITING_FOR_EXPERTS_OFFER;
        this.isDeleted = false;
    }
}
