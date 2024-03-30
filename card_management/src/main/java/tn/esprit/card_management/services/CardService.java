package tn.esprit.card_management.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.mapper.ICardmapper;
import tn.esprit.card_management.model.Card;
import tn.esprit.card_management.model.Fee;
import tn.esprit.card_management.model.FeeType;
import tn.esprit.card_management.model.NomCardType;
import tn.esprit.card_management.repository.CardRepository;
import tn.esprit.card_management.repository.FeeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor

public class CardService implements ICardService {
    private final ICardmapper iCardmapper;
    private final CardRepository cardRepository;
    private final FeeRepository feeRepository;

    @Override
    public Card demandeCard(Carddto carddto) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime inThreeYears = now.plusYears(3);
        Card card = iCardmapper.dtoToEntity(carddto);
        card.setActivated(false);
        card.setDisableCard(false);
        card.setDateExpiration(inThreeYears);
        if(card.getTypeC().equals(NomCardType.CLASSIC)) {
            card.setPlafond((float) (card.getSolde() * 0.25));
            List<Fee> fees =  feeRepository.findByType(FeeType.FeeForCLASSIC);
            if(!fees.isEmpty()){
                Fee fee = fees.get(0);
                card.setAssignedFee(fee);
            }
        }
        if(card.getTypeC().equals(NomCardType.PREMIUM)) {
            card.setPlafond((float) (card.getSolde() * 0.6));
            List<Fee> fees =  feeRepository.findByType(FeeType.FeeForPREMIUM);
            if(!fees.isEmpty()){
                Fee fee = fees.get(0);
                card.setAssignedFee(fee);
            }
        }
        if(card.getTypeC().equals(NomCardType.GOLD)) {
            card.setPlafond((float) (card.getSolde() * 0.45));
            List<Fee> fees =  feeRepository.findByType(FeeType.FeeForGOLD);
            if(!fees.isEmpty()){
                Fee fee = fees.get(0);
                card.setAssignedFee(fee);
            }

        }
        cardRepository.save(card);
        return card;

    }
@Override
    public List<Carddto> retrieveAllCards() {
        List<Card> cards = cardRepository.findAll();
        List<Carddto> carddtos = iCardmapper.fromListentityTodtos(cards);
        return carddtos;
    }


    @Override
    public void removeCard(String numeroCard ){

        cardRepository.deleteById(numeroCard);
    }
    @Override
    public Carddto retrieveCard(String numeroCard) {
        Card card = cardRepository.findById(numeroCard).get();
        return iCardmapper.entityToDto(card);
    }

    @Override
    public Card modifyCard(Carddto carddto) {
        Card card = iCardmapper.dtoToEntity(carddto);
        cardRepository.save(card);
        return card;
    }


    @Override
    public Card activateCard(String id) {
        Optional<Card> optionnalcard = cardRepository.findById(id);
        if(optionnalcard.isPresent()){
            Card card = optionnalcard.get();
            card.setActivated(true);
            card.setCVV(generateLimitedRandomCode(3));
            card.setNIP(generateLimitedRandomCodeByLimit(4,6));
            card.setCodeSecurite(generateLimitedRandomCode(4));

            cardRepository.save(card);
            return card;
        }
        return null;
    }

    public  String generateLimitedRandomCode(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }
        int maxCode = (int) Math.pow(10, length);
        Random random = new Random();
        int randomint = random.nextInt(maxCode);
        String randomAsString = String.valueOf(randomint);
        return randomAsString;
    }

    public  String generateLimitedRandomCodeByLimit(int minimumLength, int maximumLength) {
        if (minimumLength > maximumLength) {
            throw new IllegalArgumentException("Minimum length cannot be greater than maximum length");
        }
        int randomLength = minimumLength + new Random().nextInt(maximumLength - minimumLength + 1);
        int maxCode = (int) Math.pow(10, randomLength) - 1;
        Random random = new Random();
        int randomCode = random.nextInt(maxCode + 1);
        String randomAsString = String.valueOf(randomCode);
        return randomAsString;
    }


}
