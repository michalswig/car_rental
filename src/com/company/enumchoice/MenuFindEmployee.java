package com.company.enumchoice;

public enum MenuFindEmployee {
    ID(1, "ID"),
    FIRST_NAME(2, "First Name"),
    LAST_NAME(3, "Last Name"),
    PERSONAL_ID_NUMBER(4, "Personal ID Number"),
    CITY(5, "City"),
    POSTAL_CODE(6, "Postal Code"),
    STREET_NAME(7, "Street Name"),
    STREET_NUMBER(8, "Street Number"),
    APARTMENT_NUMBER(9, "Apartment Number"),
    DEACTIVATION_DATE(10, "Deactivation Date");

    int itemNumber;
    String itemName;

    MenuFindEmployee(int itemNumber, String itemName) {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public static MenuFindEmployee prepareMenuFindEmployeeItem(Integer itemNumber) {
        for (MenuFindEmployee value : MenuFindEmployee.values()) {
            if (Integer.valueOf(value.getItemNumber()).equals(itemNumber)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No supported menu item: " + itemNumber);
    }

}
