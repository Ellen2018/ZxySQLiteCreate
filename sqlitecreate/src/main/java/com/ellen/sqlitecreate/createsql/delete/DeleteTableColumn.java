package com.ellen.sqlitecreate.createsql.delete;

/**
 * 删除表的某一列(Android SQLite不支持)
 */
public class DeleteTableColumn {
    private String tableName;
    private String columnName;

    public static DeleteTableColumn getInstance(){
        return new DeleteTableColumn();
    }

    public DeleteTableColumn addColumnName(String columnName){
        this.columnName = columnName;
        return this;
    }

    public DeleteTableColumn setTableName(String tableName){
        this.tableName = tableName;
        return this;
    }

    public String createSQL(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ALTER TABLE ");
        stringBuilder.append(tableName+" ");
        stringBuilder.append("DROP COLUMN ");
        stringBuilder.append(columnName+";");
        return stringBuilder.toString();
    }
}
