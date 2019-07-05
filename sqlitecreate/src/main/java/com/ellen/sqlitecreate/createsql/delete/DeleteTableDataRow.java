package com.ellen.sqlitecreate.createsql.delete;

import com.ellen.sqlitecreate.createsql.helper.BaseSql;
import com.ellen.sqlitecreate.createsql.helper.WhereSymbolEnum;
import com.ellen.sqlitecreate.createsql.helper.WhereValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 删除数据行
 */
public class DeleteTableDataRow extends BaseSql {

    private String tableName;
    private Map<String, WhereValue> whereValueMap;

    public static DeleteTableDataRow getInstance(){
        DeleteTableDataRow deleteTableDataColumn = new DeleteTableDataRow();
        deleteTableDataColumn.whereValueMap = new HashMap<>();
        return deleteTableDataColumn;
    }

    public DeleteTableDataRow setTableName(String tableName){
        this.tableName = tableName;
        return this;
    }

    public DeleteTableDataRow addEqualWhereValue(String whereName, Object value){
        return addWherValue(whereName, WhereSymbolEnum.EQUAL,value);
    }

    public DeleteTableDataRow addNoEqualWhereValue(String whereName, Object value){
        return addWherValue(whereName,WhereSymbolEnum.NO_EQUAl,value);
    }

    public DeleteTableDataRow addMoreThanTheWhereValue(String whereName, Object value){
        return addWherValue(whereName,WhereSymbolEnum.MORE_THAN,value);
    }

    public DeleteTableDataRow addMoreThanTheEqualWhereValue(String whereName, Object value){
        return addWherValue(whereName,WhereSymbolEnum.MORE_THAN_EQUAL,value);
    }

    public DeleteTableDataRow addLessThanWhereValue(String whereName, Object value){
        return addWherValue(whereName,WhereSymbolEnum.LESS_THAN,value);
    }

    public DeleteTableDataRow addLessThanEqualWhereValue(String whereName, Object value){
        return addWherValue(whereName,WhereSymbolEnum.LESS_THAN_EQUAL,value);
    }

    public DeleteTableDataRow addLikeWhereValue(String whereName, Object value){
        return addWherValue(whereName,WhereSymbolEnum.LIKE,value);
    }

    private DeleteTableDataRow addWherValue(String whereName, WhereSymbolEnum whereSymbolEnum, Object value){
        WhereValue whereValue = new WhereValue(whereName,whereSymbolEnum.getSymbol(),value);
        whereValueMap.put(whereName,whereValue);
        return this;
    }

    //暂时不提供
    private String createSQL(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE FROM "+tableName+" WHERE ");
        stringBuilder.append(getWhereSQLString(whereValueMap));
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    public String createSQLAutoWhere(String whereString){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE FROM "+tableName+" WHERE ");
        stringBuilder.append(whereString);
        return stringBuilder.toString();
    }

    //清空表中所有数据
    public String createDeleteAllDataSQL(){
        return String.format("DELETE FROM %s;",tableName);
    }
}
