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
        String sql = UpdateTableColumn.getInstanc()
                .setTableName("student")
                .setTargetFieldName("age")
                .setNewSqlField(SQLField.getNotNullValueField("name_new","text(20)"))
                .createSQL();
        Log.e("Ellen2018", "添加多条数据:" + sql);
    }
}
