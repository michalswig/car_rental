package com.company.enumchoice;

public enum MenuFindRentalDetails {
    ID(1, "ID"),
    VEHICLE_ID(2, "Vehicle ID"),
    CUSTOMER_ID(3, "Customer ID"),
    EMPLOYEE_ID(4, "Employee ID"),
    PICK_UP_DATE(5, "Pick up date"),
    DROP_OFF_DATE(6, "Drop Off date"),
    TOTAL_RATE_NETT(7, "Total rate nett"),
    TOTAL_RATE_GROSS(8, "Total rate gross"),
    TOTAL_TAX(9, "Total tax"),
    CREATE_TIME(10, "Create time");

    int menuItem;
    String itemName;

    MenuFindRentalDetails(int menuItem, String itemName) {
        this.menuItem = menuItem;
        this.itemName = itemName;
    }

    public int getMenuItem() {
        return menuItem;
    }

    public String getItemName() {
        return itemName;
    }

    public static MenuFindRentalDetails prepareMenuFindRentalDetailsItem(Integer menuItem) {
        for (MenuFindRentalDetails value : MenuFindRentalDetails.values()) {
            if (Integer.valueOf(value.getMenuItem()).equals(menuItem)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No supported menu item: " + menuItem);
    }

}
