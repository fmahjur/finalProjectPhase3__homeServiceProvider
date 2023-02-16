package ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.BaseService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubServiceRepository extends JpaRepository<SubService, Long> {
    Optional<SubService> findByName(String subService);

    List<SubService> findAllByBaseService(BaseService baseService);

    @Query("select s from SubService s where s.isDeleted= :isDeleted")
    List<SubService> findAllByDeletedIs(boolean isDeleted);
}
