## 0.如何导入?

[![](https://jitpack.io/v/Ellen2018/ZxySQLiteCreate.svg)](https://jitpack.io/#Ellen2018/ZxySQLiteCreate)

&emsp;&emsp;首先你需要在项目的build.gradle中配置以下代码：  

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }//加上这句即可
		}
	}

&emsp;&emsp;然后你在要使用该库的module中添加以下依赖:  

    implementation 'com.github.Ellen2018:ZxySQLiteCreate:x.y.z'

&emsp;&emsp;x,y,z是笔者库的版本值，例如：1.0.0

## 1.介绍

&emsp;&emsp;此库是专门用于SQL语句构建，基本常用的SQL语句笔者已经封装在此库中,你可以轻轻松松构建一条建表语句，where语句等。

## 2.基本用法：

1.如何创建一个名字为Student的表？

     String createTableSql = CreateTable.getInstance().setTableName("Student")
                .addField(SQLField.getOrdinaryField("name","text"))
                .addField(SQLField.getPrimaryKeyField("id","integer(10)",true))
                .createSQLIfNotExists();

     输出的SQL语句：
     CREATE TABLE IF NOT EXISTS Student(name text,id integer(10)  PRIMARY KEY AUTOINCREMENT);

2.如何添加一条数据到Student表？

    String addSQL = AddSingleRowToTable.getInstance()
                .setTableName("Student")
                .addData(new Value("name","Ellen2018"))
                .createSQL();

    输出的SQL语句：
    INSERT INTO Student (name) VALUES('Ellen2018');

不仅仅可以单条数据插入，还可以多条数据插入，那就要通过AddManyRowToTable来构建，笔者这里就不演示了。 

3.如何删除name="Ellen2018" 且 age < 23的数据？

        //删除name="Ellen2018" 且 age<23的数据
        //首先构建where语句
        String whereSQL = Where.getInstance(false)
                .addAndWhereValue("name", WhereSymbolEnum.EQUAL,"Ellen2018")
                .addAndWhereValue("age",WhereSymbolEnum.LESS_THAN,23)
                .createSQL();
        String deleteSQL = DeleteTableDataRow.getInstance()
                .setTableName("Student")
                .createSQLAutoWhere(whereSQL);

    输出的SQL语句：
    DELETE FROM Student WHERE name = 'Ellen2018' AND age < 23

4.将 age >= 25 且 name="Ellen2018"的name修改为"未知"

        //将 age >= 25 且 name="Ellen2018"的name修改为"未知"
        String whereSQLUpdate = Where.getInstance(false)
                .addAndWhereValue("name", WhereSymbolEnum.EQUAL,"Ellen2018")
                .addAndWhereValue("age",WhereSymbolEnum.MORE_THAN_EQUAL,25)
                .createSQL();
        String updateSQL = UpdateTableDataRow.getInstance()
                .setTableName("Student")
                .addSetValue("name","未知")
                .createSQLAutoWhere(whereSQLUpdate);

    输出的SQL语句：
    UPDATE Student SET name='未知' WHERE name = 'Ellen2018' AND age >= 25;

5.查询Student表中所有的数据

    String serachAll = SerachTableData.getInstance()
             .setTableName("Student")
             .getTableAllDataSQL(null);//这里面传入null代表按照表的顺序排列，传入Order语句则按照Order语句进行排列

    输出的SQL语句：
    SELECT * FROM Student;

6.查询Student表中age > 20的所有学生的name和id,且查询的顺序按照id进行升序排序

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

    输出的SQL语句：
    SELECT name,id FROM Student WHERE age > 20 ORDER BY id DESC;

&emsp;&emsp;此库不止可以构建以上SQL语句，还可以构建很多SQL语句哦，具体读者可以自行看笔者的封装的代码，当然笔者也会不断更新，欢迎提出意见。