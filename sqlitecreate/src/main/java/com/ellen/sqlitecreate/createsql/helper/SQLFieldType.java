package com.ellen.sqlitecreate.createsql.helper;

import android.support.annotation.Nullable;

public class SQLFieldType {

    private String typeString;
    private Integer length;

    public SQLFieldType(@Nullable SQLFieldTypeEnum sqlFieldTypeEnum, Integer length) {
        this.typeString = sqlFieldTypeEnum.getTypeName();
        this.length = length;
    }

    public SQLFieldType(String sqlType,Integer length){
        this.typeString = sqlType;
        this.length = length;
    }

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getSQLFieldTypeString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(typeString);
        if(length != null) {
            stringBuilder.append("(");
            stringBuilder.append(length);
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }
}
