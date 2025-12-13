package com.stockmarket.domain;

public class Comodity extends Asset {
    
    private String name;
    private double handlingFee;

    public Comodity(String code, String name, Price price, double handlingFee) {
        super(code, price);
        this.name = validateName(name);
        this.handlingFee = validateHandlingFee(handlingFee);
    }

    public String getName() {
        return name;
    }

    public double getHandlingFee() {
        return handlingFee;
    }

    public void setName(String name) {
        this.name = validateName(name);
    }

    public void setHandlingFee(double handlingFee) {
        this.handlingFee = validateHandlingFee(handlingFee);
    }

    @Override
    public Price getRealPrice(int assetQuantity) {
        double unitPriceAmount = getPrice().getAmount();
        double totalAmount = unitPriceAmount * assetQuantity + (handlingFee * assetQuantity);
        return new Price(getPrice().getCurrency(), totalAmount);
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa zasobu nie powinna być pustym napisem lub wskaźnimiem null.");            
        }
        return name;
    }

    private double validateHandlingFee(double handlingFee) {
        if (handlingFee < 0) {
            throw new IllegalArgumentException("Handling fee nie może być ujemne.");
        }
        return handlingFee;
    }

}
