package com.avada.MyHouse24User.enums;

import java.util.Arrays;
import java.util.List;

public enum MasterRequestStatus {
    NEW("Новое"), InProgress("В работе"), Completed("Выполнено");
    private String name;
    MasterRequestStatus(String name) {
        this.name = name;
    }
    public static List<String> getAll(){
        return Arrays.asList(NEW.name, InProgress.name, InProgress.name);
    }

    public String getName() {
        return name;
    }
}
