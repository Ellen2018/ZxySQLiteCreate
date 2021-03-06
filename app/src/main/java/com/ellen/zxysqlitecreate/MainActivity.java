package com.ellen.zxysqlitecreate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ellen.sqlitecreate.createsql.add.AddManyRowToTable;
import com.ellen.sqlitecreate.createsql.add.AddSingleRowToTable;
import com.ellen.sqlitecreate.createsql.create.createtable.CreateTable;
import com.ellen.sqlitecreate.createsql.create.createtable.SQLField;
import com.ellen.sqlitecreate.createsql.delete.DeleteTable;
import com.ellen.sqlitecreate.createsql.delete.DeleteTableColumn;
import com.ellen.sqlitecreate.createsql.delete.DeleteTableDataRow;
import com.ellen.sqlitecreate.createsql.helper.SQLFieldType;
import com.ellen.sqlitecreate.createsql.helper.Value;
import com.ellen.sqlitecreate.createsql.helper.WhereSymbolEnum;
import com.ellen.sqlitecreate.createsql.order.Order;
import com.ellen.sqlitecreate.createsql.serach.SerachTableData;
import com.ellen.sqlitecreate.createsql.serach.SerachTableExist;
import com.ellen.sqlitecreate.createsql.update.UpdateTableColumn;
import com.ellen.sqlitecreate.createsql.update.UpdateTableDataRow;
import com.ellen.sqlitecreate.createsql.update.UpdateTableName;
import com.ellen.sqlitecreate.createsql.where.Between;
import com.ellen.sqlitecreate.createsql.where.Where;
import com.ellen.sqlitecreate.createsql.where.WhereIn;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String sql = AddSingleRowToTable.getInstance()
                .setTableName("student")
                .addData(new Value("id",1))
                .addData(new Value("name","Ellen2018"))
                .addData(new Value("age",23))
                .createSQL();

        SQLFieldType sqlFieldType = new SQLFieldType("BIGINT",23);
        Log.e("Ellen2018","自定义数据类型:"+sqlFieldType.getSQLFieldTypeString());

        String createSql = CreateTable.getInstance().setTableName("sdsad").addField(
                //通过全约束构建模式进行构建
                SQLField.getInstance("a","text(20)")
                        .setMajorKey(true)
                        .setAuto(true)
                        .setUnique(true)
                        .setCheckWhereSql("a > 8")
                        .setDefaultValue("4")
                        .createSqlFiled()

        ).createSQL();
        Log.e("Ellen2018", "添加多条数据:" + createSql);
    }
}
