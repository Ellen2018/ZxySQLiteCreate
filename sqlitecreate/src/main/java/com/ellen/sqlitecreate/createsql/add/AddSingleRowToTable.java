package com.ellen.sqlitecreate.createsql.add;

import com.ellen.sqlitecreate.createsql.helper.BaseSql;
import com.ellen.sqlitecreate.createsql.helper.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * 单条数据添加
 */
public class AddSingleRowToTable extends BaseSql {
    private String tableName;
    private List<Value> valueList;

    public static AddSingleRowToTable getInstance(){
        AddSingleRowToTable addRowToTable = new AddSingleRowToTable();
        addRowToTable.valueList = new ArrayList<>();
        return addRowToTable;
    }

    public AddSingleRowToTable setTableName(String tableName){
        this.tableName = tableName;
        return this;
    }

    public AddSingleRowToTable addData(Value value){
        valueList.add(value);
        return this;
    }

    public String createSQL(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ");
        stringBuilder.append(tableName+" ");
        stringBuilder.append("(");
        stringBuilder.append(getFieldNameStringByValue(valueList));
        stringBuilder.append(") ");
        stringBuilder.append("VALUES(");
        stringBuilder.append(getValueString(valueList));
        stringBuilder.append(");");
        return stringBuilder.toString();
    }

}
