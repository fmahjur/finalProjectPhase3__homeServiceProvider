package ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OrderStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Customer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Orders;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByCustomer(Customer customer);

    @Query("select o from Orders o where (o.orderStatus=?1 or o.orderStatus =?2) and o.subService=?3")
    List<Orders> findAllBySubService(@Param("order_status") OrderStatus orderStatus,
                                     @Param("order_status") OrderStatus orderStatus1,
                                     @Param("sub_service") SubService subService);

    Optional<Orders> findByOrderNumber(String orderNumber);

    @Query("select o from Orders o where o.isDeleted= :isDeleted")
    List<Orders> findAllByDeletedIs(boolean isDeleted);

}
