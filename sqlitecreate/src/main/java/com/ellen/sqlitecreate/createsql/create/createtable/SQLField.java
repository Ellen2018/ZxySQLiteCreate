package com.ellen.sqlitecreate.createsql.create.createtable;

import android.text.TextUtils;

/**
 * 用于创建表的字段（列）
 */
public class SQLField {
    //字段的名称
    private String name;
    //字段的数据类型
    private String filedType;
    //字段的末尾设置，例如:主键，不为NULL,默认值
    private String autoEndString;

    /**
     * Not Null 约束
     */
    private boolean isNotNull;
    /**
     * DEFAULT 约束
     */
    private Object defaultValue;
    /**
     * UNIQUE约束
     */
    private boolean isUnique;
    /**
     * 主键约束
     */
    private boolean isMajorKey;
    /**
     * 是否自增
     */
    private boolean isAuto;
    /**
     * CHECK 约束
     * 只用传入where sql语句即可
     */
    private String checkWhereSql;

    public static SQLField getOrdinaryField(String name, String fieldType){
        return new SQLField(name,fieldType);
    }

    public static SQLField getAutoEndStringField(String name, String fieldType, String autoEndString){
        return new SQLField(name,fieldType,autoEndString);
    }

    public static SQLField getPrimaryKeyField(String name, String fieldType, boolean isAuto){
        return SQLField.getInstance(name,fieldType).setMajorKey(true).setAuto(isAuto).createSqlFiled();
    }

    public static SQLField getContainsDefaultValueField(String name, String fieldType, Object defaultValue){
        return SQLField.getInstance(name,fieldType).setDefaultValue(defaultValue).createSqlFiled();
    }

    public static SQLField getNotNullValueField(String name, String fieldType){
        return SQLField.getInstance(name,fieldType).setNotNull(true).createSqlFiled();
    }

    public static SQLField getNotNullContainsDefaultValueField(String name, String fieldType, Object defaultValue){
        return  SQLField.getInstance(name,fieldType).setNotNull(true).setDefaultValue(defaultValue).createSqlFiled();
    }

    public static SQLField getInstance(String name, String filedTyp){
        return new SQLField(name, filedTyp);
    }

    /**
     * 普通，啥都不能设置
     * @param name
     * @param filedType
     */
    private SQLField(String name, String filedType) {
        this.name = name;
        this.filedType = filedType;
    }

    /**
     * 自定义尾部
     * @param name
     * @param filedType
     * @param autoEndString
     */
    private SQLField(String name, String filedType, String autoEndString) {
        this.name = name;
        this.filedType = filedType;
        this.autoEndString = autoEndString;
    }

    public SQLField setNotNull(boolean notNull) {
        isNotNull = notNull;
        return this;
    }

    public SQLField setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public SQLField setUnique(boolean unique) {
        isUnique = unique;
        return this;
    }

    public SQLField setMajorKey(boolean majorKey) {
        isMajorKey = majorKey;
        return this;
    }

    public SQLField setAuto(boolean auto) {
        isAuto = auto;
        return this;
    }

    public SQLField setCheckWhereSql(String checkWhereSql) {
        this.checkWhereSql = checkWhereSql;
        return this;
    }

    public SQLField createSqlFiled(){
        //主键
        StringBuilder stringBuilder = new StringBuilder();
        if(isMajorKey){
            stringBuilder.append(" PRIMARY KEY");
            if(isAuto){
                stringBuilder.append(" AUTOINCREMENT");
            }
        }
        //Not null
        if(isNotNull){
            stringBuilder.append(" NOT NULL");
        }
        // default
        if(defaultValue != null){
            stringBuilder.append(" DEFAULT ");
            if(defaultValue instanceof String){
                stringBuilder.append("'"+defaultValue+"'");
            }else {
                stringBuilder.append(defaultValue);
            }
        }
        //check
        if(!TextUtils.isEmpty(checkWhereSql)){
            stringBuilder.append(" CHECK(");
            stringBuilder.append(checkWhereSql);
            stringBuilder.append(")");
        }
        // unique
        if(isUnique){
            stringBuilder.append(" UNIQUE");
        }
        autoEndString = stringBuilder.toString();
        return this;
    }

    public String getName() {
        return name;
    }

    public String getFiledType() {
        return filedType;
    }

    public String getAutoEndString() {
        return autoEndString;
    }
}