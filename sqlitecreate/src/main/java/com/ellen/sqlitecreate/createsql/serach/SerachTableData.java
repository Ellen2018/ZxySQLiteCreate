package com.ellen.sqlitecreate.createsql.serach;

import com.ellen.sqlitecreate.createsql.helper.BaseSql;
import com.ellen.sqlitecreate.createsql.helper.WhereSymbolEnum;
import com.ellen.sqlitecreate.createsql.helper.WhereValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询表数据，查询条件可有可无，有则按照条件找，where语句支持自定义，无则获取表的所有数据
 * 注意的是，此类构建的where语句都会以And进行连接，如果需要or或者NOT需要单独调用Where生成类进行生成，
 * 有提供专门的接口，传入自定义的where语句
 */
public class SerachTableData extends BaseSql {
    private String tableName;
    private List<String> selectFieldNameList;
    private Map<String, WhereValue> whereValueMap;
    private boolean isAddField = true;

    public static SerachTableData getInstance(){
        SerachTableData serachTableData  = new SerachTableData();
        serachTableData.selectFieldNameList = new ArrayList<>();
        serachTableData.whereValueMap = new HashMap<>();
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

    public SerachTableData addEqualWhereValue(String whereFieldName, Object value){
        return addWherValue(whereFieldName, WhereSymbolEnum.EQUAL,value);
    }

    public SerachTableData addNoEqualWhereValue(String whereFieldName, Object value){
        return addWherValue(whereFieldName,WhereSymbolEnum.NO_EQUAl,value);
    }

    public SerachTableData addMoreThanTheWhereValue(String whereFieldName, Object value){
        return addWherValue(whereFieldName,WhereSymbolEnum.MORE_THAN,value);
    }

    public SerachTableData addMoreThanTheEqualWhereValue(String whereFieldName, Object value){
        return addWherValue(whereFieldName,WhereSymbolEnum.MORE_THAN_EQUAL,value);
    }

    public SerachTableData addLessThanWhereValue(String whereFieldName, Object value){
        return addWherValue(whereFieldName,WhereSymbolEnum.LESS_THAN,value);
    }

    public SerachTableData addLessThanEqualWhereValue(String whereFieldName, Object value){
        return addWherValue(whereFieldName,WhereSymbolEnum.LESS_THAN_EQUAL,value);
    }

    public SerachTableData addLikeWhereValue(String whereFieldName,String likeValueString){
        return addWherValue(whereFieldName, WhereSymbolEnum.LIKE,likeValueString);
    }

    private SerachTableData addWherValue(String whereName,WhereSymbolEnum whereSymbolEnum, Object value){
        WhereValue whereValue = new WhereValue(whereName,whereSymbolEnum.getSymbol(),value);
        whereValueMap.put(whereName,whereValue);
        return this;
    }

    /**
     * 生成不带排序效果的sql查询语句
     * @return
     */
    private String createSQL(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        if(isAddField) {
            stringBuilder.append(getStringSQL(selectFieldNameList) + " ");
        }else {
            stringBuilder.append("* ");
        }
        stringBuilder.append("FROM "+tableName);
        if(whereValueMap.size() > 0) {
            stringBuilder.append(" WHERE " + getWhereSQLString(whereValueMap));
        }
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    /**
     * 生成带排序效果的sql查询语句
     * @param orderFieldName
     * @param isDESC
     * @return
     */
    private String createOrderSQL(String orderFieldName,boolean isDESC){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        if(isAddField) {
            stringBuilder.append(getStringSQL(selectFieldNameList) + " ");
        }else {
            stringBuilder.append("* ");
        }
        stringBuilder.append("FROM "+tableName);
        if(whereValueMap.size() > 0) {
            stringBuilder.append(" WHERE " + getWhereSQLString(whereValueMap)+" ");
        }
        stringBuilder.append("ORDER BY "+orderFieldName+" ");
        if(isDESC){
            stringBuilder.append("DESC");
        }else {
            stringBuilder.append("ASC");
        }
        stringBuilder.append(";");
        return stringBuilder.toString();
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
