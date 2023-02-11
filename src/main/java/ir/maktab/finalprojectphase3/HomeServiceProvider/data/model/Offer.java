package ir.maktab.finalprojectphase3.HomeServiceProvider.data.model;

import ir.maktab.finalprojectphase3.HomeServiceProvider.Utils.DateUtil;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OfferStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Offer extends BaseEntity {
    @OneToOne
    Expert expert;

    @ManyToOne
    Orders orders;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date offerDate;

    Long offerPrice;

    @Temporal(TemporalType.TIMESTAMP)
    Date proposedStartDate;

    int durationOfWork;

    OfferStatus offerStatus;

    boolean isDeleted;

    public Offer() {
        this.offerDate = DateUtil.asDate(LocalDateTime.now());
        this.offerStatus = OfferStatus.WAITING;
        this.isDeleted = false;
    }

    public Offer(Long id, Expert expert, Orders orders, Long offerPrice, Date proposedStartDate, int durationOfWork) {
        super(id);
        this.expert = expert;
        this.orders = orders;
        this.offerPrice = offerPrice;
        this.proposedStartDate = proposedStartDate;
        this.offerDate = DateUtil.asDate(LocalDateTime.now());
        this.offerStatus = OfferStatus.WAITING;
        this.durationOfWork = durationOfWork;
    }
}
