package com.ellen.sqlitecreate.createsql.delete;

import com.ellen.sqlitecreate.createsql.helper.BaseSql;

/**
 * 删除数据行
 */
public class DeleteTableDataRow extends BaseSql {

    private String tableName;

    public static DeleteTableDataRow getInstance(){
        DeleteTableDataRow deleteTableDataColumn = new DeleteTableDataRow();
        return deleteTableDataColumn;
    }

    public DeleteTableDataRow setTableName(String tableName){
        this.tableName = tableName;
        return this;
    }

    public String createSQLAutoWhere(String whereString){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE FROM "+tableName+" WHERE ");
        stringBuilder.append(whereString);
        return stringBuilder.toString();
    }

    //清空表中所有数据
    public String createDeleteAllDataSQL(){
        return String.format("DELETE FROM %s;",tableName);
    }
}
