package com.company.enumchoice;

public enum MenuUpdateCustomer {
    NEW_FIRST_NAME(1),
    NEW_LAST_NAME(2),
    NEW_PERSONAL_ID_NUMBER(3),
    NEW_CITY(4),
    NEW_POSTAL_CODE(5),
    NEW_STREET_NAME(6),
    NEW_STREET_NUMBER(7),
    NEW_APARTMENT_NUMBER(8),
    NEW_COMPANY_NAME(9),
    NEW_TAX_ID_NUMBER(10),
    NEW_COMPANY_CITY(11),
    NEW_COMPANY_POSTAL_CODE(12),
    NEW_COMPANY_STREET(13),
    NEW_COMPANY_STREET_NUMBER(14),
    NEW_COMPANY_APARTMENT_NUMBER(15);

    int itemName;

    MenuUpdateCustomer(int itemName) {
        this.itemName = itemName;
    }

    public int getItemName() {
        return itemName;
    }

    public static MenuUpdateCustomer prepareMenuUpdateCustomerItem(Integer itemNumber) {
        for (MenuUpdateCustomer value : MenuUpdateCustomer.values()) {
            if (Integer.valueOf(value.getItemName()).equals(itemNumber)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No supported menu item: " + itemNumber);
    }

}
