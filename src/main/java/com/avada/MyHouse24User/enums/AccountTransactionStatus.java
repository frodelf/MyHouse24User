package com.avada.MyHouse24User.enums;

import java.util.Arrays;
import java.util.List;

public enum AccountTransactionStatus {
    COMPLETED("Проведена"),
    NOT_COMPLETED("Не проведена");

    private String status;

    AccountTransactionStatus(String status) {
        this.status = status;
    }
    public static List<String> getAll(){
        return Arrays.asList(COMPLETED.status, NOT_COMPLETED.status);
    }
}
