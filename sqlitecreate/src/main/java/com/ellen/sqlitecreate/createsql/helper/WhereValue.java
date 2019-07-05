package com.ellen.sqlitecreate.createsql.helper;

public class WhereValue {
    private String whereName;
    //=,>=,<=...
    private String whereSymbol;
    private Object value;

    public WhereValue(String whereName, String whereSymbol, Object value) {
        this.whereName = whereName;
        this.whereSymbol = whereSymbol;
        this.value = value;
    }

    public String getWhereName() {
        return whereName;
    }

    public void setWhereName(String whereName) {
        this.whereName = whereName;
    }

    public String getWhereSymbol() {
        return whereSymbol;
    }

    public void setWhereSymbol(String whereSymbol) {
        this.whereSymbol = whereSymbol;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
