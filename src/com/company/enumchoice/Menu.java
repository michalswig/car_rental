package com.company.enumchoice;

public enum Menu {
    CUSTOMER_CREATE(1),
    CUSTOMER_UPDATE(2),
    CUSTOMER_FIND(3),
    CUSTOMER_DEACTIVATE(4),
    VEHICLE_CREATE(5),
    VEHICLE_UPDATE(6),
    VEHICLE_FIND(7),
    VEHICLE_DEACTIVATE(8),
    EMPLOYEE_CREATE(9),
    EMPLOYEE_UPDATE(10),
    EMPLOYEE_FIND(11),
    EMPLOYEE_DEACTIVATE(12),
    RENTAL_CREATE(13),
    RENTAL_FIND(14),
    VEHICLE_RATE_STATS_FIND(15),
    TOP_EARNERS_REPORT_MONTH(16),
    TOP_EARNERS_REPORT_YEAR(17),
    TOP_EARNERS_REPORT_RENTED_DAYS(18),
    APPLICATION_EXIT(19);

    int itemNumber;

    Menu(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public static Menu prepareMenuItem(Integer menuItemText) {
        for (Menu value : Menu.values()) {
            if (value.getItemNumber() == menuItemText) {
                return value;
            }
        }
        throw new IllegalArgumentException("No supported menu item: " + menuItemText);
    }

}