package ir.maktab.finalprojectphase3.HomeServiceProvider.service;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.OfferRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.UserEmailDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OfferResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Offer;

import java.util.List;

public interface OfferService {
    void add(Offer offer);

    void remove(OfferRequestDTO offerRequestDTO);

    void update(Offer offer);

    List<OfferResponseDTO> selectAllByOrder(Long orderId);

    List<OfferResponseDTO> selectAllByExpert(Long expertId);

    List<OfferResponseDTO> selectAllExpertOffersWaiting(Long expertId);

    List<OfferResponseDTO> selectAllExpertOffersAccepted(Long expertId);

    List<OfferResponseDTO> selectAllExpertOffersRejected(Long expertId);
}
