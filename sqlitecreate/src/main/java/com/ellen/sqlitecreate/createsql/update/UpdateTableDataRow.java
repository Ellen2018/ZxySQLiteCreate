package com.ellen.sqlitecreate.createsql.update;

import com.ellen.sqlitecreate.createsql.helper.BaseSql;

import java.util.HashMap;
import java.util.Map;

/**
 * 更新表的数据
 */
public class UpdateTableDataRow extends BaseSql {

    private String tableName;
    private Map<String,Object> setValueMap;

    private UpdateTableDataRow(){
        setValueMap = new HashMap<>();
    }

    public static UpdateTableDataRow getInstance(){
        UpdateTableDataRow updateTableColumn = new UpdateTableDataRow();
        return updateTableColumn;
    }

    public UpdateTableDataRow setTableName(String tableName){
        this.tableName = tableName;
        return this;
    }

    public UpdateTableDataRow addSetValue(String columnName, Object value){
        setValueMap.put(columnName,value);
        return this;
    }

    public String createSQLAutoWhere(String whereSQL){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE "+tableName);
        stringBuilder.append(" SET ");
        stringBuilder.append(getSetValueSQLString(setValueMap));
        stringBuilder.append(" WHERE ");
        stringBuilder.append(whereSQL+";");
        return stringBuilder.toString();
    }
}
