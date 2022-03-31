package com.company.enumchoice;

public enum MenuUpdateEmployee {
    NEW_FIRST_NAME(1),
    NEW_LAST_NAME(2),
    NEW_PERSONAL_ID_NUMBER(3),
    NEW_CITY(4),
    NEW_POSTAL_CODE(5),
    NEW_STREET_NAME(6),
    NEW_STREET_NUMBER(7),
    NEW_APARTMENT_NUMBER(8);

    int itemName;

    MenuUpdateEmployee(int itemName) {
        this.itemName = itemName;
    }

    public int getItemName() {
        return itemName;
    }

    public static MenuUpdateEmployee prepareMenuUpdateEmployeeItem(Integer itemNumber) {
        for (MenuUpdateEmployee value : MenuUpdateEmployee.values()) {
            if (Integer.valueOf(value.getItemName()).equals(itemNumber)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No supported menu item: " + itemNumber);
    }
}
