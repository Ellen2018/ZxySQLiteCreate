package com.ellen.sqlitecreate.createsql.serach;

import com.ellen.sqlitecreate.createsql.helper.BaseSql;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询表数据，查询条件可有可无，有则按照条件找，where语句支持自定义，无则获取表的所有数据
 * 注意的是，此类构建的where语句都会以And进行连接，如果需要or或者NOT需要单独调用Where生成类进行生成，
 * 有提供专门的接口，传入自定义的where语句
 */
public class SerachTableData extends BaseSql {
    private String tableName;
    private List<String> selectFieldNameList;
    private boolean isAddField = false;

    public static SerachTableData getInstance(){
        SerachTableData serachTableData  = new SerachTableData();
        serachTableData.selectFieldNameList = new ArrayList<>();
        return serachTableData;
    }

    public SerachTableData setTableName(String tableName){
        this.tableName = tableName;
        return  this;
    }

    public SerachTableData setIsAddField(boolean isAddField){
        this.isAddField = isAddField;
        return this;
    }

    public SerachTableData addSelectField(String name){
        selectFieldNameList.add(name);
        return this;
    }

    /**
     * 传入自定义的where语句(不带排序效果)
     * @param whereSQL
     * @return
     */
    public String createSQLAutoWhere(String whereSQL){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        if(isAddField) {
            stringBuilder.append(getStringSQL(selectFieldNameList) + " ");
        }else {
            stringBuilder.append("* ");
        }
        stringBuilder.append("FROM "+tableName);
        stringBuilder.append(" WHERE " + whereSQL);
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    /**
     * 传入自定义的where语句(带排序效果)
     * @param whereSQL
     * @return
     */
    public String createSQLAutoWhere(String whereSQL,String orderFieldName,boolean isDESC){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        if(isAddField) {
            stringBuilder.append(getStringSQL(selectFieldNameList) + " ");
        }else {
            stringBuilder.append("* ");
        }
        stringBuilder.append("FROM "+tableName);
        stringBuilder.append(" WHERE " + whereSQL+" ");
        stringBuilder.append("ORDER BY "+orderFieldName+" ");
        if(isDESC){
            stringBuilder.append("DESC");
        }else {
            stringBuilder.append("ASC");
        }
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    public String createSQLAutoWhere(String whereSQL,String orderSQL){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        if(isAddField) {
            stringBuilder.append(getStringSQL(selectFieldNameList) + " ");
        }else {
            stringBuilder.append("* ");
        }
        stringBuilder.append("FROM "+tableName);
        stringBuilder.append(" WHERE " + whereSQL+" ");
        stringBuilder.append("ORDER BY "+orderSQL);
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    public String getTableAllDataSQL(String orderSQL){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append("* ");
        stringBuilder.append("FROM "+tableName);
        if(orderSQL != null) {
            stringBuilder.append(" ORDER BY " + orderSQL);
        }
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

}
