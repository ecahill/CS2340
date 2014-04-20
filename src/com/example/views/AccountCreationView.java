package com.example.views;

public interface AccountCreationView extends DesignView {
	
	/**
     * Returns the account name.
     *
     * @return the account name based on the string entered in the accName field
     */
	String getAccountName();

    /**
     * Returns the account balance.
     *
     * @return the account balance based on the double entered in the 
     * accBalance field
     */
    double getAccountBalance();

    /**
     * Returns the monthly interest rate.
     *
     * @return the monthly interest rate based on the double entered in the monthlyInterestRate field
     */
    double getMonthlyInterestRate();
    
    boolean validAccountBalance();
    
    boolean validInterestRate();
    
    boolean validAccountName();
}
