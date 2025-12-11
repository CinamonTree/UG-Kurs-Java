package com.stockmarket.domain;

public class Currency {
    
    private String code;
    private String name;

    public Currency(String code, String name) {
        this.code = validateCode(code);
        this.name = validateName(name);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = validateCode(code);
    }

    public void setName(String name) {
        this.name = validateName(name);
    }

    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Currency other = (Currency) obj;
        return code.equals(other.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    private String validateCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Kod waluty nie może być pustym ciągiem znaków lub nullem.");
        }
        return code;
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa waluty nie może być pustym ciągiem znaków lub nullem.");
        }
        return name;
    }
    
}
