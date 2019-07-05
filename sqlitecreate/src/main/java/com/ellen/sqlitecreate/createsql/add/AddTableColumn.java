package com.ellen.sqlitecreate.createsql.add;

import com.ellen.sqlitecreate.createsql.create.createtable.SQLField;

/**
 * 动态添加表的列
 */
public class AddTableColumn {
    private String tableName;
    private SQLField SQLField;

    public static AddTableColumn getInstance(){
        return new AddTableColumn();
    }

    public AddTableColumn setTableColumn(String tableName){
        this.tableName = tableName;
        return this;
    }

    public AddTableColumn addField(com.ellen.sqlitecreate.createsql.create.createtable.SQLField SQLField){
        this.SQLField = SQLField;
        return this;
    }

    public String createSQL(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ALTER TABLE ");
        stringBuilder.append(tableName);
        stringBuilder.append(" ADD ");
        stringBuilder.append(SQLField.getName()+" ");
        stringBuilder.append(SQLField.getFiledType());
        if(SQLField.getAutoEndString() != null)
        stringBuilder.append(" "+ SQLField.getAutoEndString());
        stringBuilder.append(";");
        return stringBuilder.toString();
    }
}
