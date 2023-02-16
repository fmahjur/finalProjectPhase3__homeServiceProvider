package ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);

    Optional<Customer> findByUsername(String email);

    Optional<Customer> findByEmail(String emailAddress);
    @Query("select c from Customer c where c.isDeleted= :isDeleted")
    List<Customer> findAllByDeletedIs(boolean isDeleted);

    @Modifying
    @Query("update Customer c set c.credit = :newCredit where c.id = :customerId")
    void updateCredit(Long customerId, Long newCredit);
}
