package com.mfbank.controller;
import com.mfbank.configuration.KeycloakSecurity;
import com.mfbank.dto.Userdto;
import com.mfbank.mapper.Imapper;
import com.mfbank.model.Static;
import com.mfbank.model.User;
import com.mfbank.otherDtos.BankAccountDto;
import com.mfbank.otherDtos.CreditDto;
import com.mfbank.otherDtos.InternationalTransferDto;
import com.mfbank.otherDtos.RepaymentPlanDto;
import com.mfbank.service.IUserService;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    KeycloakSecurity keycloakSecurity;
    @Autowired
    Imapper imapper;
    @Value("${realm}")
    private String realm ;

    @PostMapping("/add-user")
    public Response addUser(@RequestBody Userdto u) {
        userService.addUser(u);
        return Response.ok().build();
    }

    @PutMapping("/modify-user")
    public Response modifyUser(@RequestBody Userdto u) {
        User user = userService.modifyUser(u);
        return Response.ok(u).build();
    }
    @PutMapping("/updatepass")
    public Response updatepassword(@RequestParam String username,
                                   @RequestParam String newpass ,
                                   @RequestParam String veripass) {
        String message= userService.updatepassword(username,newpass,veripass);
        return Response.ok(message).build();
    }



    @GetMapping("/retrieve-all-users")
   // @PreAuthorize("hasRole('EMPLOYEE')")
    public List<Userdto> retrieveAllUsers() {
        List<Userdto> listUserdtos = userService.retrieveAllUsers();
        return listUserdtos;
    }

    @GetMapping("/retrieve-user/{userName}")
    public Userdto retrieveUser(@PathVariable("userName") String userName) {
        Userdto userdto = userService.retrieveUser(userName);
        return userdto;
    }

   // @PreAuthorize("hasRole('EMPLOYEE')")
    @DeleteMapping("/remove-user/{userName}")
    public void removeUser(@PathVariable("userName") String userName) {
        userService.removeUser(userName);
    }


    @GetMapping("/getAdditionalDebtForecast")
    public Double getAdditionalDebtForecast(@RequestBody RepaymentPlanDto plan) {
       return userService.getAdditionalDebtForecast(plan);

    }
    @GetMapping("/getDaysToPayoffByPrincipal")
    public Integer getDaysToPayoffByPrincipal(@RequestBody RepaymentPlanDto plan) {
        return userService.getDaysToPayoffByPrincipal(plan);

    }
    @GetMapping("/getAverageDailyInterestAccrual")
    public Double getAverageDailyInterestAccrual(@RequestBody CreditDto credit) {
        return userService.getAverageDailyInterestAccrual(credit);

    }
    @GetMapping("/getProjectedOutstandingBalance")
    public Double getProjectedOutstandingBalance(@RequestBody RepaymentPlanDto plan ,
                                                 @RequestParam int periods) {
        return userService.getProjectedOutstandingBalance(plan ,periods);

    }
    @GetMapping("/getCoverageRatioByPlannedRepayments")
    public Double getCoverageRatioByPlannedRepayments(@RequestBody RepaymentPlanDto plan) {
        return userService.getCoverageRatioByPlannedRepayments(plan);

    }




    @GetMapping("/getAccountActivityRatio")
    public  Double getAccountActivityRatio(@RequestBody BankAccountDto account ){
        return userService.getAccountActivityRatio(account );
    }


  //  @PreAuthorize("hasRole('EMPLOYEE' , 'STUDENT')")
    @GetMapping("/getFeeIncomePerAccount")
    public Map<Double,List<Double>>  getFeeIncomePerAccount(@RequestParam String username) {
        return userService.getFeeIncomePerAccount(username);

    }

  //  @PreAuthorize("hasRole('EMPLOYEE' , 'STUDENT')")
    @GetMapping("/getAccountUtilizationRatio")
    public Double getAccountUtilizationRatio() {
        return userService.getAccountUtilizationRatio();

    }
   // @PreAuthorize("hasRole('EMPLOYEE' , 'STUDENT')")
    @GetMapping("/getPercentageOutgoingTransfers")
    public List<Double> getPercentageOutgoingTransfers(@RequestParam String username) {
        return userService.getMonthlyOutgoingTransfers(username);

    }

}
