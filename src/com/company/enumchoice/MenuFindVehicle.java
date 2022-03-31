package com.company.enumchoice;

public enum MenuFindVehicle {
    ID(1, "ID"),
    REGISTRATION_NUMBER(2, "Registration Number"),
    BRAND(3, "Brand"),
    MODEL(4, "Model"),
    VIN_NUMBER(5, "Vin Number"),
    RATE_NETT(6, "Current rate Nett"),
    RATE_GROSS(7, "Current rate Gross"),
    TAX_LEVEL(8, "Tax Level"),
    DEACTIVATION_DATE(9, "Deactivation Date");

    int menuItem;
    String itemName;

    MenuFindVehicle(int menuItem, String itemName) {
        this.menuItem = menuItem;
        this.itemName = itemName;
    }

    public int getMenuItem() {
        return menuItem;
    }

    public String getItemName() {
        return itemName;
    }

    public static MenuFindVehicle prepareMenuFindVehicleItem(Integer menuItem) {
        for (MenuFindVehicle value : MenuFindVehicle.values()) {
            if (Integer.valueOf(value.getMenuItem()).equals(menuItem)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No supported menu item: " + menuItem);
    }

}
