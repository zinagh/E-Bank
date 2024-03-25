package com.mfbank.service;

import com.mfbank.dto.Userdto;
import com.mfbank.model.User;
import com.mfbank.otherDtos.BankAccountDto;
import com.mfbank.otherDtos.CreditDto;
import com.mfbank.otherDtos.InternationalTransferDto;
import com.mfbank.otherDtos.RepaymentPlanDto;

import java.util.Date;
import java.util.List;

public interface IUserService {
    List<Userdto> retrieveAllUsers();
    Userdto retrieveUser(String userName);
    void addUser(Userdto userdto);
    void removeUser(String userName);
    User modifyUser(Userdto userdto);
    String updatepassword(String username, String newwpass ,String verifpass);
    Double getAdditionalDebtForecast(RepaymentPlanDto plan);
    Integer getDaysToPayoffByPrincipal(RepaymentPlanDto plan);
    Double getAverageDailyInterestAccrual(CreditDto credit);
    Double getProjectedOutstandingBalance(RepaymentPlanDto plan, int periods);
    Double getCoverageRatioByPlannedRepayments(RepaymentPlanDto plan);
    Double getAccountActivityRatio(BankAccountDto account, Date startDate, Date endDate);


}
