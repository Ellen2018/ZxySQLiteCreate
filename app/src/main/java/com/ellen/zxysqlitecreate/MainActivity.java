package com.ellen.zxysqlitecreate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ellen.sqlitecreate.createsql.add.AddManyRowToTable;
import com.ellen.sqlitecreate.createsql.add.AddSingleRowToTable;
import com.ellen.sqlitecreate.createsql.create.createtable.CreateTable;
import com.ellen.sqlitecreate.createsql.create.createtable.SQLField;
import com.ellen.sqlitecreate.createsql.delete.DeleteTableDataRow;
import com.ellen.sqlitecreate.createsql.helper.Value;
import com.ellen.sqlitecreate.createsql.helper.WhereSymbolEnum;
import com.ellen.sqlitecreate.createsql.order.Order;
import com.ellen.sqlitecreate.createsql.serach.SerachTableData;
import com.ellen.sqlitecreate.createsql.update.UpdateTableDataRow;
import com.ellen.sqlitecreate.createsql.where.Where;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> fieldNameList = new ArrayList<>();
        fieldNameList.add("name");
        fieldNameList.add("phone");
        fieldNameList.add("address");
        List<Object> objects1 = new ArrayList<>();
        objects1.add("Ellen");
        objects1.add("18272167476");
        objects1.add("火星居2813");
        List<Object> objects2 = new ArrayList<>();
        objects2.add("Ellen");
        objects2.add("18272167476");
        objects2.add("火星居2813");
        String addManyDataSql = AddManyRowToTable.getInstance()
                .setTableName("student")
                .addFieldNameList(fieldNameList)
                .addValueList(objects1)
                .addValueList(objects2)
                .createSQL();
        Log.e("Ellen2018","添加多条数据:"+addManyDataSql);
   }
}
