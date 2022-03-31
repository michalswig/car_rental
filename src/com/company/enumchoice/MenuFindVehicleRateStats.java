package com.company.enumchoice;

public enum MenuFindVehicleRateStats {
    ID(1, "ID"),
    VEHICLE_ID(2, "Vehicle ID"),
    RATE_CHANGE_DATE(3, "Rate change date"),
    RATE_NETT_BEFORE_CHANGE(4, "Rate nett before change"),
    RATE_GROSS_BEFORE_CHANGE(5, "Rate gross before change"),
    TAX_BEFORE_CHANGE(6, "Tax before change");

    int itemNumber;
    String itemName;

    MenuFindVehicleRateStats(int itemNumber, String itemName) {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public static MenuFindVehicleRateStats prepareMenuFindVehicleRateStatsItem(Integer itemNumber) {
        for (MenuFindVehicleRateStats value : MenuFindVehicleRateStats.values()) {
            if (Integer.valueOf(value.getItemNumber()).equals(itemNumber)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No supported menu item: " + itemNumber);
    }

}
