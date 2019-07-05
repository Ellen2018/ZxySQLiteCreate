package com.ellen.sqlitecreate.createsql.helper;

public class Value<T> {
    private String fieldName;
    private T value;

    public Value(String fieldName,T value){
        this.fieldName = fieldName;
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public String getFieldName() {
        return fieldName;
    }
}
