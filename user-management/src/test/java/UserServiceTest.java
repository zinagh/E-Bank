import com.mfbank.controller.UserController;
import com.mfbank.otherDtos.BankAccountDto;
import com.mfbank.otherDtos.CreditDto;
import com.mfbank.otherDtos.InternationalTransferDto;
import com.mfbank.otherDtos.RepaymentPlanDto;

import com.mfbank.service.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private RepaymentPlanDto plan;

    @InjectMocks
    private UserServiceImp userService;
    @Test
    public void testGetAdditionalDebtForecast() {
        // Set up mock behavior for plan
        when(plan.getStartingBalance()).thenReturn(1000.0);
        when(plan.getRepaymentOfCcapital()).thenReturn(500.0);

        // Call the method to test
        Double result = userService.getAdditionalDebtForecast(plan);

        // Assert the expected result
        assertEquals(-0.5, result);
    }



    @Test
    public void testGetAverageDailyInterestAccrual_ErrorHandlingTest() {
        // Test for null credit
        try {
            userService.getAverageDailyInterestAccrual(null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            assertEquals("CreditDto cannot be null", expected.getMessage());
        }

        // Test for missing repayment plan
        CreditDto credit = new CreditDto(); // Create credit without repayment plan
        try {
            userService.getAverageDailyInterestAccrual(credit);
            fail("Expected IllegalStateException");
        } catch (IllegalStateException expected) {
            assertEquals("CreditDto must have a RepaymentPlanDto", expected.getMessage());
        }
    }


    @Test
    public void testGetProjectedOutstandingBalance() {
        // Test data (adjust as needed)
        double startingBalance = 10000.0;
        double interest = 50.0; // Monthly interest amount
        double repayment = 1000.0; // Monthly repayment of capital
        int periods = 12; // Number of periods (months)

        // Mock setup (using Mockito)
        RepaymentPlanDto mockPlan = Mockito.mock(RepaymentPlanDto.class);
        Mockito.when(mockPlan.getStartingBalance()).thenReturn(startingBalance);
        Mockito.when(mockPlan.getInterest()).thenReturn(interest);
        Mockito.when(mockPlan.getRepaymentOfCcapital()).thenReturn(repayment);

        // Call the method to test
        Double projectedBalance = userService.getProjectedOutstandingBalance(mockPlan, periods);

        // **Debugging suggestions:**
        System.out.println("Starting Balance: " + startingBalance);
        System.out.println("Interest per Month: " + interest);
        System.out.println("Repayment per Month: " + repayment);
        System.out.println("Number of Periods: " + periods);

        // **Expected balance calculation (corrected)**
        double expectedBalance = startingBalance;
        for (int i = 0; i < periods; i++) {
            expectedBalance += interest;
            expectedBalance -= repayment;
            System.out.println("Iteration " + i + ": Expected Balance = " + expectedBalance); // Added for debugging
        }

        // Adjust tolerance slightly to account for potential rounding errors
        assertEquals(expectedBalance, projectedBalance, 1.0); // Adjust expected value if necessary
    }
    @Test
    public void testGetCoverageRatioByPlannedRepayments() {
        // Test data
        double startingBalanceReimbursementAmount = 10000.0;
        double interest = 500.0;

        // Mock setup
        RepaymentPlanDto mockPlan = Mockito.mock(RepaymentPlanDto.class);
        Mockito.when(mockPlan.getStartingBalanceimbursementAount()).thenReturn(startingBalanceReimbursementAmount);
        Mockito.when(mockPlan.getInterest()).thenReturn(interest);

        // Call the method to test
        Double coverageRatio = userService.getCoverageRatioByPlannedRepayments(mockPlan);

        // Assertions
        assertEquals(startingBalanceReimbursementAmount / interest, coverageRatio, 0.01); // Adjust tolerance if needed

        // Verify mock interactions (optional)
        Mockito.verify(mockPlan).getStartingBalanceimbursementAount();
        Mockito.verify(mockPlan).getInterest();
    }



}



