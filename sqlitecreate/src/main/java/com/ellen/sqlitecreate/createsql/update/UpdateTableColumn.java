package com.ellen.sqlitecreate.createsql.update;

import com.ellen.sqlitecreate.createsql.create.createtable.SQLField;
import com.ellen.sqlitecreate.createsql.helper.BaseSql;

/**
 * 修改数据库表的字段名 & 类型
 * ALTER TABLE product CHANGE address address1 VARCHAR(20);
 */
public class UpdateTableColumn extends BaseSql {
    private String tableName;
    private String targetFieldName;
    private String newFieldName;
    private SQLField newSqlField;

    public static UpdateTableColumn getInstanc(){
        UpdateTableColumn updateTableColumn = new UpdateTableColumn();
        return updateTableColumn;
    }

    public UpdateTableColumn setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public UpdateTableColumn setTargetFieldName(String targetFieldName) {
        this.targetFieldName = targetFieldName;
        return this;
    }

    public UpdateTableColumn setNewSqlField(SQLField newSqlField) {
        this.newSqlField = newSqlField;
        return this;
    }

    public UpdateTableColumn setNewFieldName(String newFieldName) {
        this.newFieldName = newFieldName;
        return this;
    }

    public String createSQL(){
        if(newSqlField != null) {
            return createSQLBySQLFiled();
        }else {
            return createSQLByNewFieldName();
        }
    }

    private String createSQLBySQLFiled(){
        //ALTER TABLE product CHANGE address address1 VARCHAR(20);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ALTER TABLE ");
        stringBuilder.append(tableName+" CHANGE ");
        stringBuilder.append(targetFieldName+" ");
        stringBuilder.append(getFieldString(newSqlField));
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    private String createSQLByNewFieldName(){
       //EXEC sp_rename 'customers.[contact title]', 'title', 'COLUMN'
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("EXEC sp_rename ");
        stringBuilder.append("'");
        stringBuilder.append(tableName+".");
        stringBuilder.append("["+targetFieldName+"]',");
        stringBuilder.append("'"+newFieldName+"',");
        stringBuilder.append("'COLUMN';");
        return stringBuilder.toString();
    }
}
