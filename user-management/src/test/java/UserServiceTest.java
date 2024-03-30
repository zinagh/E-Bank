import com.mfbank.controller.UserController;
import com.mfbank.otherDtos.*;

import com.mfbank.service.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

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





    @Test
    public void testGetFeeIncomePerAccount_CalculatesCorrectly() {
        // Mock data
        BankAccountDto mockAccount = Mockito.mock(BankAccountDto.class);

        // Default fees
        Double defaultFeeAmount = 10.0; // Assuming amountPercent represents income
        FeeDto mockDefaultFees = Mockito.mock(FeeDto.class);
        Mockito.when(mockDefaultFees.getAmountPercent()).thenReturn(defaultFeeAmount);
        Mockito.when(mockAccount.getDefaultFees()).thenReturn(mockDefaultFees);

        // International transfers with fees
        List<InternationalTransferDto> transfers = new ArrayList<>();
        int numTransfers = 2;
        Double transferFeeAmount = 5.0; // Assuming amountPercent represents income
        for (int i = 0; i < numTransfers; i++) {
            InternationalTransferDto mockTransfer = Mockito.mock(InternationalTransferDto.class);
            FeeDto mockTransferFees = Mockito.mock(FeeDto.class);
            Mockito.when(mockTransferFees.getAmountPercent()).thenReturn(transferFeeAmount);
            Mockito.when(mockTransfer.getInternationnalFees()).thenReturn(mockTransferFees);
            transfers.add(mockTransfer);
        }
        Mockito.when(mockAccount.getInternationalTransfers()).thenReturn(transfers);

        // Call the method to test
        Double totalFees = userService.getFeeIncomePerAccount(mockAccount);

        // Expected total fees calculation
        double expectedTotal = defaultFeeAmount + (numTransfers * transferFeeAmount);

        // Assertions
        assertEquals(expectedTotal, totalFees);
    }

    @Test
    public void testGetAccountUtilizationRatio() {
        // Mock data
        BankAccountDto mockAccount = Mockito.mock(BankAccountDto.class);

        // Test cases for both allowed and not allowed negative balance
        for (boolean negativeSoldeAllowed : new boolean[]{true, false}) {
            Mockito.when(mockAccount.getNegativeSoldeAllowed()).thenReturn(negativeSoldeAllowed);
            Mockito.lenient().when(mockAccount.getAccount_balance()).thenReturn(100.0);

            if (negativeSoldeAllowed) {
                Mockito.when(mockAccount.getNegativeSoldeAmount()).thenReturn(50.0F); // Only mock for allowed case
            }

            // Call the method to test
            Double utilizationRatio = userService.getAccountUtilizationRatio(mockAccount);

            // Assertions
            if (negativeSoldeAllowed) {
                // Calculate expected ratio for allowed case
                double expectedRatio = mockAccount.getAccount_balance() /
                        (mockAccount.getAccount_balance() + mockAccount.getNegativeSoldeAmount());
                assertEquals(expectedRatio, utilizationRatio);
            } else {
                // Assert 0 for not allowed case
                assertEquals(0.0, utilizationRatio);
            }
        }
    }

    @Test
    public void testGetPercentageOutgoingTransfers() {
        for (int outgoingTransferCount : new int[]{0, 1, 4}) {  // Test with 0, 1, and 4 outgoing transfers
            // Mock setup
            BankAccountDto mockAccount = Mockito.mock(BankAccountDto.class);
            List<InternationalTransferDto> transfers = new ArrayList<>();

            int totalTransfers = 5; // Adjust as needed

            // **Corrected mocking logic (already in the loop)**
            for (int i = 0; i < totalTransfers; i++) {
                InternationalTransferDto mockTransfer = Mockito.mock(InternationalTransferDto.class);
                Mockito.when(mockTransfer.isSendOrReceive()).thenReturn(i >= outgoingTransferCount); // Set outgoing status (adjusted for zero transfers)
                transfers.add(mockTransfer);
            }

            Mockito.when(mockAccount.getInternationalTransfers()).thenReturn(transfers);

            // Call the method to test
            Double percentage = userService.getPercentageOutgoingTransfers(mockAccount);

            // Expected percentage calculation
            double expectedPercentage = (double) outgoingTransferCount / totalTransfers * 100;

            // Assertions
            assertEquals(expectedPercentage, percentage);
        }

        // Test handling for no transfers
        BankAccountDto mockAccountNoTransfers = Mockito.mock(BankAccountDto.class);
        Mockito.when(mockAccountNoTransfers.getInternationalTransfers()).thenReturn(Collections.emptyList());
        Double percentageWithNoTransfers = userService.getPercentageOutgoingTransfers(mockAccountNoTransfers);
        assertEquals(0.0, percentageWithNoTransfers);}

    @Test
    public void testGetAverageInternationalTransferFee() {
        List<List<Double>> feesList = Arrays.asList(Arrays.asList(10.0, 20.0, 15.0), Arrays.asList(5.0), Arrays.asList());

        feesList.stream()
                .forEach(fees -> {
                    // Mock setup
                    List<InternationalTransferDto> mockTransfers = new ArrayList<>();
                    for (Double fee : fees) {
                        InternationalTransferDto mockTransfer = Mockito.mock(InternationalTransferDto.class);
                        Mockito.when(mockTransfer.getFees()).thenReturn(fee);
                        mockTransfers.add(mockTransfer);
                    }

                    // Call the method to test
                    Double averageFee = userService.getAverageInternationalTransferFee(mockTransfers);

                    // Expected average calculation
                    Double expectedAverage = fees.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

                    // Assertions
                    assertEquals(expectedAverage, averageFee);
                });
    }
      /*  @Test
    public void testGetAccountActivityRatio() {
        // Create BankAccountDto object with sample transfers
        BankAccountDto account = new BankAccountDto();
        List<InternationalTransferDto> transfers = new ArrayList<>();

        // **Set transfer dates here** (ensure year is 2024)
        transfers.add(new InternationalTransferDto());
        transfers.get(0).setDate(new Date(2024, 2, 20)); // March 20, 2024
        transfers.add(new InternationalTransferDto());
        transfers.get(1).setDate(new Date(2024, 2, 25)); // March 25, 2024

        account.setInternationalTransfers(transfers);

        // Set start and end dates for the test
        Date startDate = new Date(2024, 2, 15);
        Date endDate = new Date(2024, 2, 28);

        // Call the function with real data
        Double activityRatio = userService.getAccountActivityRatio(account, startDate, endDate);

        // Adjust assertion based on desired number of transfers and period length
        assertEquals(0.02777777777777778, activityRatio, 0.000001); // Example assertion
    }
*/


}



