package com.company.enumchoice;

public enum MenuUpdateVehicle {
    NEW_REGISTRATION_NUMBER(1),
    NEW_BRAND(2),
    NEW_MODEL(3),
    NEW_VIN_NUMBER(4),
    NEW_CURRENT_RATE_NETT(5),
    NEW_CURRENT_TAX_LEVEL(6);

    int itemName;

    MenuUpdateVehicle(int itemName) {
        this.itemName = itemName;
    }

    public int getItemName() {
        return itemName;
    }

    public static MenuUpdateVehicle prepareMenuUpdateVehicle(Integer itemName){
        for(MenuUpdateVehicle value : MenuUpdateVehicle.values()){
            if(Integer.valueOf(value.getItemName()).equals(itemName)){
                return value;
            }
        }
        throw new IllegalArgumentException("No supported menu item: " + itemName);
    }

}
