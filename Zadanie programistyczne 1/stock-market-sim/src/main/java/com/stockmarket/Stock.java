package com.stockmarket;

public class Stock {

    private final String code;
    private final String companyName;
    private final double value;

    public Stock(String code, String companyName, double value) {
        this.code = validateCode(code);
        this.companyName = validateCompanyName(companyName);
        this.value = validateCompanyName(value);
    }

    public String getCode() {
        return this.code;
    }

    public String getCompanyName() {
        return this.companyName;
    }
    
    public double getValue() {
        return this.value;
    }

    private String validateCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Kod akcji nie może być pusty. Podano: " + code);
        }
        return code;
    }

    private String validateCompanyName(String companyName) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Nazwa firmy nie może być pusty. Podano: " + companyName);
        }
        return code;
    }

    private double validateCompanyName(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Wartość akcji nie może być ujemna.");
        }
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true; // gdy wskazują na ten sam obiekt zwróć true
        if (other == null) return false; // gdy przekazano null zwróć false
        if (!(other instanceof Stock)) return false; // gdy przekazano obiekt który nie jest instancją Stock zwróć false

        Stock otherAsStock = (Stock) other; // rzutowanie aby dostać dostęp do pól i metod
        
        if (this.code == null) { // jeżeli code tego obiektu jest null sprawdź czy drugiego też
            return otherAsStock.code == null;
        } else { // a jeżeli nie to porównaj stringi z equals
            return code.equals(otherAsStock.code);
        }
    }

    @Override
    public int hashCode() {
        if (this.code == null) return 0;
        return code.hashCode();
    }
    
}
