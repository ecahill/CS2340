package com.example.presenters;

/**
 * Handles transactions made in the MakeTransactionActivity.
 * 
 * @author Kristian Zhelyazkov
 *
 */
public class TransactionAdapter {

	/**
	 * @param the account balance after the deposit.
	 */
    private double afterDeposit;
    
    /**
     * @param afterWithdraw the account balance after the withdraw.
     */
    private double afterWithdraw;
    
    /**
     * @param transactionType the type of transaction.
     */
    private String transactionType;
//    private static final String DEPOSIT = "Deposit";
//    private static final String WITHDRAW = "Withdraw";

    /**
     * Default constructor for TransactionAdapter.
     */
    public TransactionAdapter() {

    }

    /**
     * Constructor takes in a transaction type: Deposit or Withdraw.
     * 
     * @param type the type of transaction (Deposit or Withdraw)
     */
    public TransactionAdapter(String type) {
        this.transactionType = type;
    }

    /**
     * Calculates the final account balance after the deposit.
     * 
     * @param depositAmount the deposit amount
     * @param accBalance the starting account balance
     */
    public void setFinalDepositAmount(double depositAmount, double accBalance) {
        afterDeposit = depositAmount + accBalance;
    }

    /**
     * Returns the account balance after the deposit.
     * 
     * @return account balance after the deposit
     */
    public double getAfterDeposit() {
        return afterDeposit;
    }

    /**
     * Calculates the account balance after the withdraw.
     * 
     * @param withdrawAmount the withdraw amount
     * @param accBalance the starting account balance
     */
    public void setFinalWithdrawAmount(double withdrawAmount, double accBalance) {
        afterWithdraw = accBalance - withdrawAmount;
    }

    /**
     * Returns the account balance after the withdraw.
     * 
     * @return the account balance after the withdraw
     */
    public double getAfterWithdraw() {
        return afterWithdraw;
    }
}
