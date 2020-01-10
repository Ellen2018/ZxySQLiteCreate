package com.ellen.sqlitecreate.createsql.create.createtable;

import com.ellen.sqlitecreate.createsql.helper.BaseSql;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建表
 */
public class CreateTable extends BaseSql {

    private String tableName;
    private List<SQLField> sqlFieldList;

    private CreateTable(){
        sqlFieldList = new ArrayList<>();
    }

    public static CreateTable getInstance() {
        CreateTable createTable = new CreateTable();
        return createTable;
    }

    public CreateTable setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public CreateTable addField(SQLField SQLField) {
        sqlFieldList.add(SQLField);
        return this;
    }

    public CreateTable addField(List<SQLField> sqlFieldList) {
        this.sqlFieldList.addAll(sqlFieldList);
        return this;
    }

    public String createSQL() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE " + tableName + "(");
        stringBuilder.append(getFieldString(sqlFieldList));
        stringBuilder.append(");");
        return stringBuilder.toString();
    }

    public String createSQLIfNotExists() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS " + tableName + "(");
        stringBuilder.append(getFieldString(sqlFieldList));
        stringBuilder.append(");");
        return stringBuilder.toString();
    }

}
