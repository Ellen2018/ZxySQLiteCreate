package com.ellen.sqlitecreate.createsql.update;

/**
 * 重命名表的名字
 */
public class UpdateTableName {
    private String oldTableName;
    private String newTableName;

    public static UpdateTableName getInstance(){
        return new UpdateTableName();
    }

    public UpdateTableName setOldTableName(String oldTableName){
        this.oldTableName = oldTableName;
        return this;
    }

    public UpdateTableName setNewTableName(String newTableName){
        this.newTableName = newTableName;
        return this;
    }

    public String createSQL(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ALTER TABLE ");
        stringBuilder.append("'"+oldTableName+"' RENAME TO ");
        stringBuilder.append("'"+newTableName+"';");
        return stringBuilder.toString();
    }
}
