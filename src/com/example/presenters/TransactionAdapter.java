package com.example.presenters;

public class TransactionAdapter {

    private double afterDeposit;
    private double afterWithdraw;
    private String transactionType;
    private static final String DEPOSIT = "Deposit";
    private static final String WITHDRAW = "Withdraw";

    public TransactionAdapter() {

    }

    public TransactionAdapter(String transactionType) {
        this.transactionType = transactionType;
    }

    // public boolean isWithdraw() {
    // if (transactionType.equals(WITHDRAW)) {
    // return true;
    // } else {
    // return false;
    // }
    // }

    public void setFinalDepositAmount(double depositAmount, double accBalance) {
        afterDeposit = depositAmount + accBalance;
    }

    public double getFinalDepositAmount() {
        return afterDeposit;
    }

    public void setFinalWithdrawAmount(double withdrawAmount, double accBalance) {
        afterWithdraw = accBalance - withdrawAmount;
    }

    public double getFinalWithdrawAmount() {
        return afterWithdraw;
    }
}
