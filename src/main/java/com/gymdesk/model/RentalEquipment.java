package com.gymdesk.model;

public enum RentalEquipment {
    TOWEL("Towel"), SHOES("Shoes");

    private String label;

    RentalEquipment(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
