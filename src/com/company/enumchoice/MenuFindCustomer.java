package com.company.enumchoice;

public enum MenuFindCustomer {
    ID(1, "ID"),
    FIRST_NAME(2, "First Name"),
    LAST_NAME(3, "Last Name"),
    PERSONAL_ID_NUMBER(4, "Personal ID Number"),
    CITY(5, "City"),
    POSTAL_CODE(6, "Postal Code"),
    STREET_NAME(7, "Street Name"),
    STREET_NUMBER(8, "Street Number"),
    APARTMENT_NUMBER(9, "Apartment Number"),
    COMPANY_NAME(10, "Company Name"),
    TAX_ID_NUMBER(11, "Tax ID number"),
    COMPANY_CITY(12, "Company City"),
    COMPANY_POSTAL_CODE(13, "Tax ID number"),
    COMPANY_STREET(14, "Company Street"),
    COMPANY_STREET_NUMBER(15, "Company Street Number"),
    COMPANY_APARTMENT_NUMBER(16, "Company Apartment Number"),
    DEACTIVATION_DATE(17, "Deactivation Date");

    int itemNumber;
    String itemName;

    MenuFindCustomer(int itemNumber, String itemName) {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public static MenuFindCustomer prepareMenuFindCustomerItem(Integer itemNumber) {
        for (MenuFindCustomer value : MenuFindCustomer.values()) {
            if (Integer.valueOf(value.getItemNumber()).equals(itemNumber)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No supported menu item: " + itemNumber);
    }

}


