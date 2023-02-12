package ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Offer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAllByOrdersIs(Orders orders);

    List<Offer> findAllByExpert(Expert expert);

    @Query("select o from Offer o where o.isDeleted=?1")
    List<Offer> findAllByDeletedIs(@Param("is_deleted") boolean isDeleted);
}
