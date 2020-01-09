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

### 2.1 增加相关

#### 2.1.1 创建表

1.如何创建一个名字为Student的表？

     String createTableSql = CreateTable.getInstance().setTableName("Student")
                .addField(SQLField.getOrdinaryField("name","text"))
                .addField(SQLField.getPrimaryKeyField("id","integer(10)",true))
                .createSQLIfNotExists();

     输出的SQL语句：
     CREATE TABLE IF NOT EXISTS Student(name text,id integer(10)  PRIMARY KEY AUTOINCREMENT);

     String createTableSql = CreateTable.getInstance().setTableName("Student")
                .addField(SQLField.getOrdinaryField("name","text"))
                .addField(SQLField.getPrimaryKeyField("id","integer(10)",true))
                .createSQL();

    输出的SQL语句:
    CREATE TABLE Student(name text,id integer(10)  PRIMARY KEY AUTOINCREMENT);

方式非常简单，通过CreateTable类进行构建,SQLField是指数据库中的Field属性，你可以自己查看SQLField类查看如何构建对应的数据库字段类型，笔者已经封装好了。

#### 2.1.2 增加数据到数据库

1.如何添加一条数据到Student表？

    String addSQL = AddSingleRowToTable.getInstance()
                .setTableName("Student")
                .addData(new Value("name","Ellen2018"))
                .createSQL();

    输出的SQL语句：
    INSERT INTO Student (name) VALUES('Ellen2018');

2.如何增加多条数据到student表中？

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

    输出的SQL语句：
    INSERT INTO student (name,phone,address) VALUES ('Ellen','18272167476','火星居2813'),('Ellen','18272167476','火星居2813');

### 2.2 删除相关

#### 2.2.1 删除表中的数据

1.如何删除name="Ellen2018" 且 age < 23的数据？

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
    DELETE FROM Student WHERE name = 'Ellen2018' AND age < 23;

#### 2.2.2 删除表中的字段

1.删除student表中的age字段

    String sql = DeleteTableColumn.getInstance()
                .setTableName("student")
                .addColumnName("age")
                .createSQL();

    输出的SQL语句：
    ALTER TABLE student DROP COLUMN age;

#### 2.2.3 删除表

1.删除表student

    String sql = DeleteTable.getInstance()
                .setTableName("student")
                .createSQL();

    输出的SQL语句：
    DROP TABLE student;

### 2.3 修改相关

#### 2.3.1 修改表的名字

1.将表名为student的表修改为student_new

    String sql = UpdateTableName.getInstance()
                .setOldTableName("student")
                .setNewTableName("student_new")
                .createSQL();

    输出的SQL语句：
    ALTER TABLE 'student' RENAME TO 'student_new';

#### 2.3.2 修改表中的数据

1.将 age >= 25 且 name="Ellen2018"的name修改为"未知"

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

#### 2.3.3 修改表中字段的名字

貌似没有封装，待笔者有时间加上

### 2.4 查询相关

#### 2.4.1 查询表是否存在

    String sql = SerachTableExist.getInstance()
                .setTableName("student")
                .createSQL();

    输出的SQL语句：
    SELECT * FROM sqlite_master WHERE type='table' AND name='student';

注意这段语句如果能获取到数据，则表明表是存在的，反之则不存在。

#### 2.4.2 查询表中的数据

1.查询Student表中所有的数据

    String serachAll = SerachTableData.getInstance()
             .setTableName("Student")
             .getTableAllDataSQL(null);//这里面传入null代表按照表的顺序排列，传入Order语句则按照Order语句进行排列

    输出的SQL语句：
    SELECT * FROM Student;

2.查询Student表中age > 20的所有学生的name和id,且查询的顺序按照id进行升序排序

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

### 2.5 Where语句构建

#### 2.5.1 Where语句使用

1.当age大于20且名字(name字段)姓李？

    String sql = Where.getInstance(false)//这里的false代表构建的SQL不带有Where,反之，则带
                .addAndWhereValue("age", WhereSymbolEnum.MORE_THAN_EQUAL, 20)
                .addAndWhereValue("name", WhereSymbolEnum.LIKE, "李%")
                .createSQL();

    输出的SQL语句：
    age >= 20 AND name LIKE '李%'

#### 2.5.2 WhereIn语句使用

1.选择学校(字段为school_name)在"a","b","c"的数据？

    String sql = WhereIn.getInstance(false)
                .setFieldName("school_name")
                .addInValue("a")
                .addInValue("b")
                .addInValue("c")
                .createSQLWhereIn();

    输出的SQL语句：
    school_name IN ('a','b','c')

1.选择学校(字段为school_name)不在"a","b","c"的数据？

    String sql = WhereIn.getInstance(false)
                .setFieldName("school_name")
                .addInValue("a")
                .addInValue("b")
                .addInValue("c")
                .createSQLWhereNotIn();

    输出的SQL语句：
    school_name NOT IN ('a','b','c')

#### 2.5.3 Between语句使用

1.选择age在18~25的数据

    String sql = Between.getInstance(true)
                .setFiledName("age")
                .setLeftValue(18)
                .setRightValue(25)
                .createSQL();

    输出的SQL语句：
    WHERE age BETWEEN 18 AND 25

### 2.6 Order语句使用

1.第一属性按照年龄，第二属性按照名字进行升序

     String sql = Order.getInstance(true)
                .setFirstOrderFieldName("age")
                .setSecondOrderFieldName("name")
                .setIsDesc(false)
                .createSQL();

    输出的SQL语句：
    ORDER BY age,name ASC
