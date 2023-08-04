package com.avada.MyHouse24User.enums;

import java.util.Arrays;
import java.util.List;

public enum Roles {
    AnySpecialist("Любой специалист"), Plumber("Сантехник"), Electric("Электрик");
    private String name;

    Roles(String name) {
        this.name = name;
    }

    public static List<String> getAll(){
        return Arrays.asList(AnySpecialist.name, Plumber.name, Electric.name);
    }
    public static String getNameByRole(String role){
        switch (role){
            case "ROLE_PLUMBER":
                return Plumber.name;
            case "ROLE_ELECTRIC":
                return Electric.name;
        }
        return null;
    }
    public static String getRoleByName(String name){
        switch (name){
            case "Сантехник":
                return "ROLE_PLUMBER";
            case "Электрик":
                return "ROLE_ELECTRIC";
        }
        return null;
    }
}
