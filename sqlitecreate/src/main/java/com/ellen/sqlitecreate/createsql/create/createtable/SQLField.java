package com.ellen.sqlitecreate.createsql.create.createtable;

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

    public static SQLField getOrdinaryField(String name, String fieldType){
        return new SQLField(name,fieldType);
    }

    public static SQLField getAutoEndStringField(String name, String fieldType, String autoEndString){
        return new SQLField(name,fieldType,autoEndString);
    }

    public static SQLField getPrimaryKeyField(String name, String fieldType, boolean isAuto){
        return new SQLField(name,fieldType,true,isAuto);
    }

    public static SQLField getContainsDefaultValueField(String name, String fieldType, Object defaultValue){
        return new SQLField(name,fieldType,false,defaultValue);
    }

    public static SQLField getNotNullValueField(String name, String fieldType){
        return new SQLField(name,fieldType,false,null);
    }

    public static SQLField getNotNullContainsDefaultValueField(String name, String fieldType, Object defaultValue){
        return new SQLField(name,fieldType,false,defaultValue);
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

    /**
     *
     * @param name 列名
     * @param filedType 列在SQL中的数据类型
     * @param isPrimaryKey 是否为主键
     * @param isAuto 是否自增
     */
    private SQLField(String name, String filedType, boolean isPrimaryKey, boolean isAuto){
        StringBuilder stringBuilder = new StringBuilder();
        if(isPrimaryKey){
            stringBuilder.append(" PRIMARY KEY");
        }
        if(isAuto){
            stringBuilder.append(" AUTOINCREMENT");
        }
        this.name = name;
        this.filedType = filedType;
        if(stringBuilder.toString().length() > 0)
            this.autoEndString = stringBuilder.toString();
    }

    /**
     * 非主键
     * @param name
     * @param filedType
     * @param isCanNull 是否能为NULL
     * @param defaultValue 默认值
     */
    private SQLField(String name, String filedType, boolean isCanNull, Object defaultValue){
        StringBuilder stringBuilder = new StringBuilder();
        if(defaultValue != null){
            stringBuilder.append(" DEFAULT ");
            if(defaultValue instanceof String){
                stringBuilder.append("'"+defaultValue+"'");
            }else {
                stringBuilder.append(defaultValue);
            }
        }
        if(!isCanNull){
            stringBuilder.append(" NOT NULL");
        }
        this.name = name;
        this.filedType = filedType;
        if(stringBuilder.toString().length() > 0)
            this.autoEndString = stringBuilder.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFiledType() {
        return filedType;
    }

    public void setFiledType(String filedType) {
        this.filedType = filedType;
    }

    public String getAutoEndString() {
        return autoEndString;
    }

    public void setAutoEndString(String autoEndString) {
        this.autoEndString = autoEndString;
    }
}