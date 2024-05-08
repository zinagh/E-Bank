package com.mfbank.account_managment.service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfbank.account_managment.dto.QuestionDto;
import com.mfbank.account_managment.dtouser.Userdto;
import com.mfbank.account_managment.mapper.QuestionMapper;
import com.mfbank.account_managment.model.*;
import com.mfbank.account_managment.repository.BankAccountRepository;
import com.mfbank.account_managment.repository.FeeRepository;
import com.mfbank.account_managment.repository.InternationalTransferRepository;
import com.mfbank.account_managment.repository.QuestionDao;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import com.mfbank.account_managment.dto.BankAccountDto;
import com.mfbank.account_managment.mapper.IBankAccountMapper;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.*;


import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements  IBankAccountService{
    private final BankAccountRepository bankAccountRepository;
    private final InternationalTransferRepository internationalTransferRepository;

    private final FeeRepository feeRepository;
    private final IBankAccountMapper iBankAccountMapper;
    private final WebClient.Builder webClient;
    private SecurityContextHolder securityContextHolder;

    @Value("${principle-attribute}")
    private String principleAttribut;
    private String openaiApiKey;
    @Autowired
    private final QuestionDao  questionDao;

    private RestTemplate restTemplate=new RestTemplate();

    private final QuestionMapper mapper;



    @Override
    public QuestionDto addQuestion(QuestionDto questionD) {
        Question question = mapper.toEntity(questionD);



        System.out.println(question.getQuestion());
        // Associate question with level
        questionDao.save(question); // Persist the question
        return questionD;
    }

    @Override
    public List<QuestionDto> getQuestions() {

        List<Question> questions = questionDao.findAll(); // Retrieve questions from the level
        return mapper.toDtoList(questions);
    }
    @Override
    public List<BankAccountDto> retrieveAllBankAccounts() {
        List<BankAccount> bankaccounts = bankAccountRepository.findAll();
        List<BankAccountDto> bankAccountDtos = iBankAccountMapper.toDtoList(bankaccounts);
        for(BankAccountDto bankAccountDto :  bankAccountDtos){
            Userdto userdto = webClient.build()
                    .get()
                    .uri("http://user-management/user/retrieve-user/" + bankAccountDto.getTitulaire())
                    .retrieve()
                    .bodyToMono(Userdto.class)
                    .block(Duration.ofSeconds(5));
            bankAccountDto.setUserdto(userdto);
        }
         return bankAccountDtos;
    }


    @Override
    public BankAccountDto retrieveBankAccount(String bankAccountId) {
        BankAccount bankAccount =  bankAccountRepository.findById(bankAccountId).get();
        BankAccountDto bankAccountdto = iBankAccountMapper.toDto(bankAccount);
        Userdto userdto = webClient.build()
                        .get()
                        .uri("http://user-management/user/retrieve-user/" + bankAccountdto.getTitulaire())
                        .retrieve()
                        .bodyToMono(Userdto.class)
                        .block(Duration.ofSeconds(5));
        bankAccountdto.setUserdto(userdto);

        return bankAccountdto;

    }

    @Override
    public Double retreiveAccountBalance(String bankAccountTitulaire){
        Optional<BankAccount>  opBankAccount =  bankAccountRepository.findByTitulaire(bankAccountTitulaire);

        if (opBankAccount.isPresent()){
            BankAccount bankAccount = opBankAccount.get();
            Double  balance =bankAccount.getAccount_balance();
            return balance;
        } else {
            return 0.0;
        }
    }

    @Override
    public BankAccountDto retrieveBankAccountByTitulaire(String bankAccountTitulaire) {
        BankAccount bankAccount =  bankAccountRepository.findByTitulaire(bankAccountTitulaire).get();
        BankAccountDto bankAccountdto = iBankAccountMapper.toDto(bankAccount);
        return bankAccountdto;

    }

      @Override
    public void addBankAccount(BankAccountDto bankAccountDto) {
          BankAccount bankAccount = iBankAccountMapper.toEntity(bankAccountDto);
          String typeBankAccount = bankAccountDto.getType().toString();
           Fee fee = getFeeFromBankAccount(typeBankAccount);
           if(fee != null) {
               bankAccount.setDefaultFees(fee);
           }
           Date currentDate = new Date();
           bankAccount.setCreation_date(currentDate);
           bankAccount.setActivated(false);
           bankAccount.setNegativeSoldeAllowed(true);
           bankAccount.setNegativeSoldeDepassement(false);
           bankAccount.setNegativeSoldeAmount(200.0f);

           bankAccountRepository.save(bankAccount);


    }


    public Fee getFeeFromBankAccount(String typeBankAccount) {
        if(typeBankAccount.equals("currentAccount")){
            List<Fee> fees = feeRepository.findByFeeType(FeeType.TRANSACTION_FEE_currentAccount);
            if(!fees.isEmpty()){
                Fee fee = fees.get(0);
                return fee;
            }
        } else if (typeBankAccount.equals("savingsAccount")) {
            List<Fee> fees = feeRepository.findByFeeType(FeeType.TRANSACTION_FEE_savingsAccount);
            if(!fees.isEmpty()){
                Fee fee = fees.get(0);
                return fee;
            }
        }
        return null;
    }

    @Override
    public void removeBankAccount(String bankAccountId) {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(bankAccountId);
        if(bankAccountOptional.isPresent()){
            BankAccount bankAccount = bankAccountOptional.get();
            List<InternationalTransfer> internationalTransfers = bankAccount.getInternationalTransfers();
            if(!internationalTransfers.isEmpty()){
                for(InternationalTransfer internationalTransfer: internationalTransfers){
                    internationalTransferRepository.delete(internationalTransfer);
                }
            }
            bankAccountRepository.deleteById(bankAccountId);
        }
    }
    @Override
    public void modifyBankAccount(BankAccountDto bankAccountDto) {
        BankAccount bankAccount = iBankAccountMapper.toEntity(bankAccountDto);
         bankAccountRepository.save(bankAccount);
    }

    @SneakyThrows
    @Override
    public QuestionDto generateQuestion(String topic, int numberOfQuestions) {

        String chatGptUrl = "http://localhost:3040/v1/chat/completions";
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer "+ openaiApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String prompt = "Generate a quiz question with one correct answer and three incorrect answers about.";



        String requestBody = "{" +
                "\"model\": \"gpt-3.5-turbo\"," +
                "\"max_tokens\": \"150\"," +
                "\"messages\": [" +

                "{\"role\": \"user\", \"content\": \"" + prompt + " "+topic +"and please return the reponse in json format ,question , reponse1,reponse2,reponse3,correct-reponse and please do'nt add anything "+"\"}" +
                "]" +
                "}" ;
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);



        ResponseEntity<String> responseEntity = restTemplate.postForEntity(chatGptUrl, requestEntity, String.class);



        String responseBody = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = null;
        System.out.println(responseBody);

        responseJson = objectMapper.readTree(responseBody);

        System.out.println(responseJson);
        String generatedText = responseJson.get("choices").get(0).get("message").get("content").asText();
        System.out.println(generatedText);


// Parse the generatedText to extract the question and answers
// For example, assuming the format is "Question? Answer1. Answer2. Answer3. CorrectAnswer."
        //   String pattern = "(?<question>.*?)\\.(?<response1>.*?)\\.(?<response2>.*?)\\.(?<response3>.*?)\\.(?<correctResponse>.*)";
        // Matcher matcher = Pattern.compile(pattern, Pattern.DOTALL).matcher(generatedText); // Include DOTALL flag to match n

        JsonNode jsonObject = objectMapper.readTree(generatedText);

        // Extracting question
        String question = jsonObject.get("question").asText();

        // Extracting responses
        String response1 = jsonObject.get("response1").asText();
        String response2 = jsonObject.get("response2").asText();
        String response3 = jsonObject.get("response3").asText();

        // Extracting correct response
        String correctResponse = jsonObject.get("correct_response").asText();
        if(correctResponse==null)
            correctResponse = jsonObject.get("correct").asText();

        // Print the extracted question, responses, and correct response
        System.out.println("Question: " + question);
        System.out.println("Response 1: " + response1);
        System.out.println("Response 2: " + response2);
        System.out.println("Response 3: " + response3);

        System.out.println("Correct Response: " + correctResponse);
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestion(question);
        questionDto.setResponse1(response1);
        questionDto.setResponse2(response2);
        questionDto.setResponse3(response3);

        questionDto.setCorrect(correctResponse);

        this.addQuestion(questionDto);
        return questionDto;



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
