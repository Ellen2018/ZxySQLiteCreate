package com.ellen.sqlitecreate.createsql.where;

import com.ellen.sqlitecreate.createsql.helper.WhereSymbolEnum;

/**
 * 生产普通的WHERE语句，但是不包括WHERE IN
 */
public class Where {

    private StringBuilder stringBuilder;
    //记录添加的条件个数
    private int size = 0;
    private boolean isContainsWhere = true;

    public static Where getInstance(boolean isContainsWhere) {
        Where where = new Where();
        where.stringBuilder = new StringBuilder();
        where.isContainsWhere = isContainsWhere;
        if(where.isContainsWhere) {
            where.stringBuilder.append("WHERE ");
        }
        return where;
    }

    public Where addAndWhereValue(String fieldName, WhereSymbolEnum whereSymbolEnum, Object value) {
        return addWhereValue("AND", fieldName, whereSymbolEnum, value);
    }

    public Where addOrWhereValue(String fieldName, WhereSymbolEnum whereSymbolEnum, Object value) {
        return addWhereValue("OR", fieldName, whereSymbolEnum, value);
    }

    private Where addWhereValue(String connection, String fieldName, WhereSymbolEnum whereSymbolEnum, Object value) {
        if (size != 0) {
            stringBuilder.append(" "+connection + " ");
        }
        stringBuilder.append(fieldName);
        stringBuilder.append(" "+whereSymbolEnum.getSymbol()+" ");
        if(value != null) {
            if (value instanceof String || value instanceof Character) {
                stringBuilder.append("'" + value + "'");
            } else {
                stringBuilder.append(value);
            }
        }else {
            stringBuilder.append("NULL");
        }
        size++;
        return this;
    }

    public String createSQL(){
        return stringBuilder.toString();
    }


}
