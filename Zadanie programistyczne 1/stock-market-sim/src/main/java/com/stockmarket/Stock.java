package com.stockmarket;

public class Stock {

    private final String code;
    private final String companyName;
    private final double value;

    public Stock(String code, String companyName, double value) {
        this.code = code;
        this.companyName = companyName;
        this.value = value;
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

    @Override
    public boolean equals(Object other) {
        // gdy wskazują na ten sam obiekt zwróć true
        if (this == other) return true;
        // gdy przekazano null zwróć false
        if (other == null) return false;
        // gdy przekazano obiekt który nie jest instancją Stock zwróć false
        if (!(other instanceof Stock)) return false;

        // rzutowanie aby dostać dostęp do pól i metod
        Stock otherAsStock = (Stock) other;
        // jeżeli code tego obiektu jest null sprawdź czy drugiego też
        // a jeżeli nie to porównaj stringi z equals
        if (this.code == null) {
            return otherAsStock.code == null;
        } else {
            return code.equals(otherAsStock.code);
        }
    }

    @Override
    public int hashCode() {
        if (this.code == null) return 0;
        return code.hashCode();
    }
    
}
