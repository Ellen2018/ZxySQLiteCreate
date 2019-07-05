package com.ellen.sqlitecreate.createsql.serach;

/**
 * 判断表是否存在
 */
public class SerachTableExist {

    private String tableName;

    public static SerachTableExist getInstance(){
        return new SerachTableExist();
    }

    public SerachTableExist setTableName(String tableName){
        this.tableName = tableName;
        return this;
    }

    public String createSQL(){
        return String.format("SELECT * FROM sqlite_master WHERE type='table' AND name='%s';",tableName);
    }

}
