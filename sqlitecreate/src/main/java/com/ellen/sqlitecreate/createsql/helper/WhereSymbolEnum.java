package com.ellen.sqlitecreate.createsql.helper;

public enum WhereSymbolEnum {

    EQUAL("="),
    NO_EQUAl("!="),
    MORE_THAN(">"),
    LESS_THAN("<"),
    MORE_THAN_EQUAL(">="),
    LESS_THAN_EQUAL("<="),
    LIKE("LIKE");

    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    WhereSymbolEnum(String symbol) {
        this.symbol = symbol;
    }
}
