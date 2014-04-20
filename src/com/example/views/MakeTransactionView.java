package com.example.views;

public interface MakeTransactionView extends DesignView {
	/**
     * Gets the transaction reason.
     * 
     * @return the reason for the transaction
     */
    public String getTransactionReason();

    /**
     * Gets the transaction amount.
     * 
     * @return the amount of the transaction
     */
    public double getTransactionAmount();
    
    public String getTransactionType();
    
    public boolean verifyTransactionAmount();
}
