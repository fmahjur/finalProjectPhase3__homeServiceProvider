package ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OfferStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OrderStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Offer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAllByOrdersIs(Orders orders);

    List<Offer> findAllByExpert(Expert expert);

    @Query("select o from Offer o where o.isDeleted= :isDeleted")
    List<Offer> findAllByDeletedIs(boolean isDeleted);

    @Query("select o from Offer o where o.orders= :orders order by o.offerPrice")
    List<Offer> findByOrdersSortByOfferPrice(Orders orders);

    @Query("select o from Offer o where o.orders= :orders order by o.expert.rate")
    List<Offer> findByOrdersSortByExOrderByExpertRate(Orders orders);

    @Query("select o from Offer o where o.orders= :orders order by o.offerPrice, o.expert.rate")
    List<Offer> findByOrdersSortByOfferPriceAndExpertRate(Orders orders);

    List<Offer> findOffersByExpertAndOfferStatus(Expert expert, OfferStatus offerStatus);
}
