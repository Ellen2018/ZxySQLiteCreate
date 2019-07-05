package com.ellen.sqlitecreate.createsql.where;

import com.ellen.sqlitecreate.createsql.helper.BaseSql;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产WHERE IN语句
 */
public class WhereIn extends BaseSql {

    private String fieldName;
    private List<String> valueList;
    //是否生产出的WhereIN语句包含WHERE
    private boolean isContainsWhere = true;

    public static WhereIn getInstance(boolean isContainsWhere){
        WhereIn whereIn = new WhereIn();
        whereIn.valueList = new ArrayList();
        whereIn.isContainsWhere = isContainsWhere;
        return whereIn;
    }

    public WhereIn setFieldName(String fieldName){
        this.fieldName = fieldName;
        return this;
    }

    public WhereIn addInValue(Object object){
        if(object != null) {
            if (object instanceof String || object instanceof Character) {
                valueList.add("'" + object + "'");
            } else {
                valueList.add(String.valueOf(object));
            }
        }else {
            valueList.add("NULL");
        }
        return this;
    }

    public String createSQLWhereIn(){
        StringBuilder stringBuilder = new StringBuilder();
        if(isContainsWhere) {
            stringBuilder.append("WHERE ");
        }
        stringBuilder.append(fieldName);
        stringBuilder.append(" IN ");
        stringBuilder.append("(");
        stringBuilder.append(getStringSQL(valueList));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public String createSQLWhereNotIn(){
        StringBuilder stringBuilder = new StringBuilder();
        if(isContainsWhere) {
            stringBuilder.append("WHERE ");
        }
        stringBuilder.append(fieldName);
        stringBuilder.append(" NOT IN ");
        stringBuilder.append("(");
        stringBuilder.append(getStringSQL(valueList));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
