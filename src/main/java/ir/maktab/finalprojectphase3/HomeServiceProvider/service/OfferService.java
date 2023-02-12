package ir.maktab.finalprojectphase3.HomeServiceProvider.service;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.OfferRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.UserEmailDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OfferResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Offer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Orders;

import java.util.List;

public interface OfferService {
    void add(OfferRequestDTO offerRequestDTO);

    void remove(OfferRequestDTO offerRequestDTO);

    void update(OfferRequestDTO offerRequestDTO);

    List<Offer> selectAllByOrder(Orders orders);

    List<OfferResponseDTO> selectAllByExpert(UserEmailDTO expertEmail);
}
