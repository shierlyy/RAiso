package com.example.projectuxlab;

public class GlobalVariable {
    private String email;
    private int quantity;

    public GlobalVariable(String email, int quantity) {
        this.email = email;
        this.quantity = quantity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
