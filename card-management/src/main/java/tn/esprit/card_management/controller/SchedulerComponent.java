package tn.esprit.card_management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.card_management.model.Card;
import tn.esprit.card_management.model.Fee;
import tn.esprit.card_management.model.FeeType;
import tn.esprit.card_management.model.NomCardType;
import tn.esprit.card_management.repository.CardRepository;

import java.util.List;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerComponent  {
    private final CardRepository cardRepository;
    @Scheduled(cron = "*/1 * * * * *") //testing each minute
    //@Scheduled(cron = "0 0 0 1 */1 *")
    public void resetPlafond() {
        List<Card> cards = cardRepository.findAll();
        for (Card card : cards) {
            if (card.getTypeC().equals(NomCardType.CLASSIC)) {
                card.setPlafond((float) (card.getSolde() * 0.25));
            }
            if (card.getTypeC().equals(NomCardType.PREMIUM)) {
                card.setPlafond((float) (card.getSolde() * 0.6));
            }
            if (card.getTypeC().equals(NomCardType.GOLD)) {
                card.setPlafond((float) (card.getSolde() * 0.45));
            }
        }
        cardRepository.saveAll(cards);
    }
}
