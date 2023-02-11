package ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Long> {
    boolean existsByEmailAddress(String emailAddress);

    Optional<Expert> findByUsername(String username);

    Optional<Expert> findByEmailAddress(String emailAddress);

    List<Expert> findAllByExpertStatus(ExpertStatus expertStatus);

    @Modifying
    @Query("select e from Expert e where e.isDeleted=?1")
    List<Expert> findAllByDeletedIs(@Param("is_deleted") boolean isDeleted);
}
