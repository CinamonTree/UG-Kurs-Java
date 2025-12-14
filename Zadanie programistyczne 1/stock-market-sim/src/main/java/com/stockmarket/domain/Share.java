package com.stockmarket.domain;

public class Share extends Asset {

    private String companyName;
    private double manipulationFee = 5.0;

    public Share(String code, String companyName, Money price, double manipulationFee) {
        super(code, price);
        this.companyName = validateCompanyName(companyName);
        this.manipulationFee = validateManipulationFee(manipulationFee);
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getManipulationFee() {
        return manipulationFee;
    }

    public void setCompanyName(String companyName) {
        this.companyName = validateCompanyName(companyName);
    }

    public void setManipulationFee(double manipulationFee) {
        this.manipulationFee = validateManipulationFee(manipulationFee);
    }

    @Override
    public Money getRealPrice(int shareQuantity) {
        Money sharePrice = getPrice();
        double totalPriceAmount = sharePrice.getAmount() * shareQuantity + manipulationFee;
        return new Money(sharePrice.getCurrency(), totalPriceAmount);
    }

    private String validateCompanyName(String companyName) {
        if (companyName == null || companyName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa firmy nie może być pusta ani pustym wskaźnikiem.");
        }
        return companyName;
    }

    private double validateManipulationFee(double manipulationFee) {
        if (manipulationFee < 0) {
            throw new IllegalArgumentException("Opłata manipulacyjna nie może być ujemna.");
        }
        return manipulationFee;
    }
    
}
