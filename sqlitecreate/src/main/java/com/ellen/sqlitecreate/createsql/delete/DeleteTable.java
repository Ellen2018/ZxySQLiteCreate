package com.ellen.sqlitecreate.createsql.delete;

import com.ellen.sqlitecreate.createsql.helper.BaseSql;

import java.util.ArrayList;
import java.util.List;

/**
 * 删除表(支持删除多个表)
 */
public class DeleteTable extends BaseSql {
    private List<String> tableNameList;

    public static DeleteTable getInstance() {
        return new DeleteTable();
    }

    public DeleteTable setTableName(String tableName) {
        if (tableNameList == null) {
            tableNameList = new ArrayList<>();
        }
        tableNameList.add(tableName);
        return this;
    }

    public String createSQL() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DROP TABLE ");
        stringBuilder.append(getStringSQL(tableNameList));
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

}
