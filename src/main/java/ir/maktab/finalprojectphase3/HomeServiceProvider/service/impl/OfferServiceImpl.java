package ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.OfferRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.UserEmailDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OfferResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.ExpertMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.OfferMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Offer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Orders;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository.OfferRepository;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    @Override
    public void add(OfferRequestDTO offerRequestDTO) {
        Offer offer = OfferMapper.INSTANCE.requestDtoToModel(offerRequestDTO);
        offerRepository.save(offer);
    }

    @Override
    public void remove(OfferRequestDTO offerRequestDTO) {
        Offer offer = OfferMapper.INSTANCE.requestDtoToModel(offerRequestDTO);
        offer.setDeleted(true);
        offerRepository.save(offer);
    }

    @Override
    public void update(OfferRequestDTO offerRequestDTO) {
        Offer offer = OfferMapper.INSTANCE.requestDtoToModel(offerRequestDTO);
        offerRepository.save(offer);
    }

    @Override
    public List<Offer> selectAllByOrder(Orders orders) {
        //TODO
        List<Offer> allOfferForOrder = offerRepository.findAllByOrdersIs(orders);
        Collections.sort(allOfferForOrder, new Comparator() {
            public int compare(Object o1, Object o2) {
                Long x1 = ((Offer) o1).getOfferPrice();
                Long x2 = ((Offer) o2).getOfferPrice();
                int compare = x1.compareTo(x2);
                if (compare != 0) {
                    return compare;
                }

                Double y1 = ((Offer) o1).getExpert().getRate();
                Double y2 = ((Offer) o2).getExpert().getRate();
                return y1.compareTo(y2);
            }
        });
        return allOfferForOrder;
    }

    public List<OfferResponseDTO> selectAllByExpert(UserEmailDTO expertEmail) {
        Expert expert = ExpertMapper.INSTANCE.emailDtoToModel(expertEmail);
        List<Offer> allOfferByExpert = offerRepository.findAllByExpert(expert);
        List<OfferResponseDTO> offerResponseDTOList = new ArrayList<>();
        for (Offer offer : allOfferByExpert)
            offerResponseDTOList.add(OfferMapper.INSTANCE.modelToResponseDto(offer));
        return offerResponseDTOList;
    }
}
