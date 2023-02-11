package ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
