package com.braingames.sdk.numbersflow.helpers;

public enum GameModesEnum {
	CLASSIC ("Classic"),
	RAINBOW ("Rainbow");
	
    private final String name;       

    private GameModesEnum(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}
