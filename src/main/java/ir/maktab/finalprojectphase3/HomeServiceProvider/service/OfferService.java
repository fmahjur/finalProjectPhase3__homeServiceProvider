package ir.maktab.finalprojectphase3.HomeServiceProvider.service;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.OfferRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OfferResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Offer;

import java.util.List;

public interface OfferService {
    void add(Offer offer);

    void remove(OfferRequestDTO offerRequestDTO);

    void update(Offer offer);

    List<OfferResponseDTO> selectAllByOrder(Long orderId);

    List<OfferResponseDTO> selectAllByExpert(Expert expert);

    List<OfferResponseDTO> selectAllExpertOffersWaiting(Expert expert);

    List<OfferResponseDTO> selectAllExpertOffersAccepted(Expert expert);

    List<OfferResponseDTO> selectAllExpertOffersRejected(Expert expert);
}
