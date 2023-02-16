package ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.OfferRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.UserEmailDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OfferResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OfferStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.ExpertMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.OfferMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Offer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Orders;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository.OfferRepository;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.NotFoundException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final OrderServiceImpl orderService;
    private final ExpertServiceImpl expertService;

    @Override
    public void add(Offer offer) {
        offerRepository.save(offer);
    }

    @Override
    public void remove(OfferRequestDTO offerRequestDTO) {
        Offer offer = OfferMapper.INSTANCE.requestDtoToModel(offerRequestDTO);
        offer.setDeleted(true);
        offerRepository.save(offer);
    }

    @Override
    public void update(Offer offer) {
        offerRepository.save(offer);
    }

    public Offer findById(Long offerId) {
        return offerRepository.findById(offerId).orElseThrow(() -> new NotFoundException("not found!"));
    }

    @Override
    public List<OfferResponseDTO> selectAllByOrder(Long orderId) {
        Orders order = orderService.findById(orderId);
        List<Offer> allOfferForOrder = offerRepository.findByOrdersSortByOfferPriceAndExpertRate(order);
        List<OfferResponseDTO> offerResponseDTOList = new ArrayList<>();
        for (Offer offer : allOfferForOrder)
            offerResponseDTOList.add(OfferMapper.INSTANCE.modelToResponseDto(offer));
        return offerResponseDTOList;
    }

    @Override
    public List<OfferResponseDTO> selectAllByExpert(Long expertId) {
        Expert expert = expertService.findById(expertId);
        List<Offer> allOfferByExpert = offerRepository.findAllByExpert(expert);
        List<OfferResponseDTO> offerResponseDTOList = new ArrayList<>();
        for (Offer offer : allOfferByExpert)
            offerResponseDTOList.add(OfferMapper.INSTANCE.modelToResponseDto(offer));
        return offerResponseDTOList;
    }

    @Override
    public List<OfferResponseDTO> selectAllExpertOffersWaiting(Long expertId) {
        Expert expert = expertService.findById(expertId);
        List<Offer> allExpertOffersAccepted = offerRepository.findOffersByExpertAndOfferStatus(expert, OfferStatus.WAITING);
        List<OfferResponseDTO> offerResponseDTOList = new ArrayList<>();
        for (Offer offer : allExpertOffersAccepted)
            offerResponseDTOList.add(OfferMapper.INSTANCE.modelToResponseDto(offer));
        return offerResponseDTOList;
    }

    @Override
    public List<OfferResponseDTO> selectAllExpertOffersAccepted(Long expertId) {
        Expert expert = expertService.findById(expertId);
        List<Offer> allExpertOffersAccepted = offerRepository.findOffersByExpertAndOfferStatus(expert, OfferStatus.ACCEPTED);
        List<OfferResponseDTO> offerResponseDTOList = new ArrayList<>();
        for (Offer offer : allExpertOffersAccepted)
            offerResponseDTOList.add(OfferMapper.INSTANCE.modelToResponseDto(offer));
        return offerResponseDTOList;
    }

    @Override
    public List<OfferResponseDTO> selectAllExpertOffersRejected(Long expertId) {
        Expert expert = expertService.findById(expertId);
        List<Offer> allExpertOffersAccepted = offerRepository.findOffersByExpertAndOfferStatus(expert, OfferStatus.REJECTED);
        List<OfferResponseDTO> offerResponseDTOList = new ArrayList<>();
        for (Offer offer : allExpertOffersAccepted)
            offerResponseDTOList.add(OfferMapper.INSTANCE.modelToResponseDto(offer));
        return offerResponseDTOList;
    }
}
