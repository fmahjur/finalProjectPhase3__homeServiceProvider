package ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Long> {
    boolean existsByEmail(String emailAddress);

    Optional<Expert> findByUsername(String username);

    Optional<Expert> findByEmail(String emailAddress);

    List<Expert> findAllByExpertStatus(ExpertStatus expertStatus);

    @Query("select e from Expert e where e.isDeleted= :isDeleted")
    List<Expert> findAllByDeletedIs(boolean isDeleted);

    @Modifying
    @Query("update Expert e set e.credit = :newCredit where e.id = :customerId")
    void updateCredit(Long customerId, Long newCredit);

    @Modifying
    @Query("update Expert e set e.isActive = :isActive where e.id = :customerId")
    void changeActivation(Long customerId, boolean isActive);
}
