package com.ellen.zxysqlitecreate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建表Studnet
        String createTableSql = CreateTable.getInstance().setTableName("Student")
                .addField(SQLField.getOrdinaryField("name","text"))
                .addField(SQLField.getPrimaryKeyField("id","integer(10)",true))
                .createSQLIfNotExists();

        Log.e("建表SQL语句",createTableSql);

        //添加数据到Student表中
        String addSQL = AddSingleRowToTable.getInstance()
                .setTableName("Student")
                .addData(new Value("name","Ellen2018"))
                .createSQL();

        Log.e("建表SQL语句",addSQL);

        //删除name="Ellen2018" 且 age<23的数据
        //首先构建where语句
        String whereSQL = Where.getInstance(false)
                .addAndWhereValue("name", WhereSymbolEnum.EQUAL,"Ellen2018")
                .addAndWhereValue("age",WhereSymbolEnum.LESS_THAN,23)
                .createSQL();
        String deleteSQL = DeleteTableDataRow.getInstance()
                .setTableName("Student")
                .createSQLAutoWhere(whereSQL);

        Log.e("建表SQL语句",deleteSQL);

        //将 age >= 25 且 name="Ellen2018"的name修改为"未知"
        String whereSQLUpdate = Where.getInstance(false)
                .addAndWhereValue("name", WhereSymbolEnum.EQUAL,"Ellen2018")
                .addAndWhereValue("age",WhereSymbolEnum.MORE_THAN_EQUAL,25)
                .createSQL();
        String updateSQL = UpdateTableDataRow.getInstance()
                .setTableName("Student")
                .addSetValue("name","未知")
                .createSQLAutoWhere(whereSQLUpdate);

        Log.e("建表SQL语句",updateSQL);


        String serachAll = SerachTableData.getInstance()
                .setTableName("Student")
                .getTableAllDataSQL(null);//这里面传入null代表按照表的顺序排列，床褥Order语句则按照Order语句进行排列

        Log.e("建表SQL语句",serachAll);

        String whereSQLSerach = Where.getInstance(false)
                .addAndWhereValue("age",WhereSymbolEnum.MORE_THAN,20)
                .createSQL();
        String orderSQL = Order.getInstance(false)
                .setFirstOrderFieldName("id")
                .setIsDesc(true)
                .createSQL();

        String searcSQL  = SerachTableData.getInstance()
                .setTableName("Student")
                .setIsAddField(true)
                .addSelectField("name")
                .addSelectField("id")
                .createSQLAutoWhere(whereSQLSerach,orderSQL);

        Log.e("建表SQL语句",searcSQL);
   }
}
