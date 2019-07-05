package com.ellen.sqlitecreate.createsql.create.createtable;

import com.ellen.sqlitecreate.createsql.helper.BaseSql;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建表
 */
public class CreateTable extends BaseSql {

    private String tableName;
    private List<SQLField> SQLFieldList;

    public static CreateTable getInstance() {
        CreateTable createTable = new CreateTable();
        createTable.SQLFieldList = new ArrayList<>();
        return createTable;
    }

    public CreateTable setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public CreateTable addField(SQLField SQLField) {
        SQLFieldList.add(SQLField);
        return this;
    }

    public CreateTable addField(List<SQLField> sqlFieldList) {
        SQLFieldList.addAll(sqlFieldList);
        return this;
    }

    public String createSQL() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE " + tableName + "(");
        stringBuilder.append(getFieldString(SQLFieldList));
        stringBuilder.append(");");
        return stringBuilder.toString();
    }

    public String createSQLIfNotExists() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS " + tableName + "(");
        stringBuilder.append(getFieldString(SQLFieldList));
        stringBuilder.append(");");
        return stringBuilder.toString();
    }

}
