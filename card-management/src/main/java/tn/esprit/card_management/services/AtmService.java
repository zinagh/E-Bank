package tn.esprit.card_management.services;

import com.stripe.model.BankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tn.esprit.card_management.dto.Atmdto;
import tn.esprit.card_management.dtoBank.BankAccountDto;
import tn.esprit.card_management.mapper.IAtmmapper;
import tn.esprit.card_management.model.Atm;
import tn.esprit.card_management.model.Card;
import tn.esprit.card_management.repository.AtmRepository;
import tn.esprit.card_management.repository.CardRepository;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AtmService implements IAtmService {
    private final IAtmmapper iAtmmapper;
    private final AtmRepository atmRepository;
    private final CardRepository cardRepository;
    private final WebClient.Builder webClient;
    private SecurityContextHolder securityContextHolder;
    @Value("${principle-attribute}")
    private String principleAttribut;

    @Override
    public List<Atmdto> retrieveAllAtms() {
        List<Atm> atms = atmRepository.findAll();
        List<Atmdto> atmdtos = iAtmmapper.fromListentityTodtos(atms);
        return atmdtos;    }

    @Override
    public Atm addAtm(Atmdto atmdto) {
        Atm atm = iAtmmapper.dtoToEntity(atmdto);
        atmRepository.save(atm);
        return atm;
    }

    @Override
    public void removeAtm(Long Id) {
        atmRepository.deleteById(Id);


    }

    @Override
    public Atmdto retrieveAtm(Long Id) {
        Atm atm = atmRepository.findById(Id).get();
        return iAtmmapper.entityToDto(atm);
    }

    @Override
    public Atm modifyAtm(Atmdto atmdto) {
        Atm atm = iAtmmapper.dtoToEntity(atmdto);
        atmRepository.save(atm);
        return atm;
    }
    @Override
    public boolean validerRetrait(String numeroCard, String codeSecurite, float montant, Atmdto atmdto) {
        if (!atmdto.isActivated() || montant <= 0 || montant > atmdto.getSomme()) {
            return false;
        }
        Optional<Card> optionalCard = cardRepository.findById(numeroCard);
        return optionalCard.map(card -> card.getCodeSecurite().equals(codeSecurite)).orElse(false);
    }

    @Override
    public void effectuerRetrait(String numeroCard, float montant, Atmdto atmdto) {
        atmdto.setSomme(atmdto.getSomme() - montant);
        Atm atm = iAtmmapper.dtoToEntity(atmdto);
        atmRepository.save(atm);
        Optional<Card> optionalCard = cardRepository.findById(numeroCard);
        BankAccountDto bankAccountDto = new BankAccountDto();
        if(optionalCard.isPresent()){
        Card card = optionalCard.get();
            card.setPlafond((float)(card.getPlafond() - (montant + (montant*card.getAssignedFee().getAmount()))));
        card.setSolde((float)(card.getSolde() - (montant + (montant*card.getAssignedFee().getAmount()))));
            bankAccountDto = webClient.build()
                    .get()
                    .uri("http://account-management/account/getbankaccountbyTitulaire/" + getUsername())
                    .retrieve()
                    .bodyToMono(BankAccountDto.class)
                    .block(Duration.ofSeconds(5));
            bankAccountDto.setAccount_balance(bankAccountDto.getAccount_balance() - (montant + (montant*card.getAssignedFee().getAmount())));
        }
        webClient.build()
                .put()
                .uri("http://account-management/account/modifybankaccount", bankAccountDto)
                .retrieve()
                .bodyToMono(void.class)
                .block(Duration.ofSeconds(5));
    }

    public String getUsername() {
        Authentication authentication = securityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication instanceof JwtAuthenticationToken) {
            Jwt jwt = ((JwtAuthenticationToken) authentication).getToken();
            return (String) jwt.getClaim(principleAttribut);
        }
        throw new IllegalStateException("Could not retrieve token from SecurityContext");
    }
}


