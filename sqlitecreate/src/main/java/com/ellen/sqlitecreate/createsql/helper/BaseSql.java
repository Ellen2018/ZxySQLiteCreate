package com.ellen.sqlitecreate.createsql.helper;

import com.ellen.sqlitecreate.createsql.create.createtable.SQLField;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseSql {

    /**
     * 创建表的时候将Field集合映射为SQL子句
    * @param sqlFieldList
     * @return
     */
    protected String getFieldString(List<SQLField> sqlFieldList){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sqlFieldList.size(); i++) {
            SQLField sqlField = sqlFieldList.get(i);
            stringBuilder.append(sqlField.getName() + " " + sqlField.getFiledType());
            if (sqlField.getAutoEndString() != null) {
                stringBuilder.append(" " + sqlField.getAutoEndString());
            }
            if (i != sqlFieldList.size() - 1) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    protected String getFieldString(SQLField sqlField){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(sqlField.getName() + " " + sqlField.getFiledType());
        if (sqlField.getAutoEndString() != null) {
            stringBuilder.append(" " + sqlField.getAutoEndString());
        }
        return stringBuilder.toString();
    }

    /**
     * 3,"5",6 -> (3,'5',6)
     * @param valueList
     * @return
     */
    protected String getValueString(List<Value> valueList){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<valueList.size();i++){
            if(valueList.get(i).getValue() != null) {
                if (valueList.get(i).getValue() instanceof String || valueList.get(i).getValue() instanceof Character) {
                    stringBuilder.append("'" + valueList.get(i).getValue() + "'");
                } else {
                    stringBuilder.append(valueList.get(i).getValue());
                }
            }else {
                stringBuilder.append("NULL");
            }

            if(i != valueList.size()-1){
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    protected String getFieldNameStringByValue(List<Value> valueList){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<valueList.size();i++){
            stringBuilder.append(valueList.get(i).getFieldName());
            if(i != valueList.size()-1){
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    //1,3,4 -> 1,3,4
    protected String getStringSQL(List<String> stringList){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<stringList.size();i++){
            stringBuilder.append(stringList.get(i));
            if(i != stringList.size()-1){
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    protected String getWhereSQLString(Map<String,WhereValue> whereMap){
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> columnNameSet = whereMap.keySet();
        int i =0;
        for(String columnName:columnNameSet){
            i++;
            WhereValue whereValue = whereMap.get(columnName);
            stringBuilder.append(columnName);
            stringBuilder.append(" "+whereValue.getWhereSymbol()+" ");
            if(whereValue.getValue() != null) {
                if (whereValue.getValue() instanceof String || whereValue.getValue() instanceof Character) {
                    stringBuilder.append("'" + whereValue.getValue() + "'");
                } else {
                    stringBuilder.append(whereValue.getValue());
                }
            }else {
                stringBuilder.append("NULL");
            }
            if(i != whereMap.size()){
                stringBuilder.append(" AND ");
            }

        }
        return stringBuilder.toString();
    }

    protected String getSetValueSQLString(Map<String,Object> setMap){
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> columnNameSet = setMap.keySet();
        int i =0;
        for(String columnName:columnNameSet){
            i++;
            Object value = setMap.get(columnName);
            stringBuilder.append(columnName+"=");
            if(value != null) {
                if (value instanceof String || value instanceof Character) {
                    stringBuilder.append("'" + value + "'");
                } else {
                    stringBuilder.append(value);
                }
            }else {
                stringBuilder.append("NULL");
            }
            if(i != setMap.size()){
                stringBuilder.append(",");
            }

        }
        return stringBuilder.toString();
    }

}
