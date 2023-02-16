package ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaseServiceRepository extends JpaRepository<BaseService, Long> {
    Optional<BaseService> findByName(String baseServiceName);

    @Query("select b from BaseService b where b.isDeleted= :isDeleted")
    List<BaseService> findAllByDeletedIs(boolean isDeleted);

    @Modifying
    @Query("update BaseService set isDeleted=true where id = :baseServiceId")
    void updateIsDeletedFlag(Long baseServiceId);
}
