package com.ellen.sqlitecreate.createsql.where;

/**
 * 生产Between语句
 *
 * example:
 * BETWEEN 3 AND 8
 *
 */
public class Between {
    private Object leftValue;
    private Object rightValue;
    private String filedName;
    private boolean isContainsWhere = false;

    private Between(boolean isContainsWhere){
        this.isContainsWhere = isContainsWhere;
    }

    public static Between getInstance(boolean isContainsWhere){
        Between between = new Between(isContainsWhere);
        return between;
    }

    public Between setLeftValue(Object leftValue){
        this.leftValue = leftValue;
        return this;
    }

    public Between setRightValue(Object rightValue){
        this.rightValue = rightValue;
        return this;
    }

    public Between setFiledName(String filedName) {
        this.filedName = filedName;
        return this;
    }

    public String createSQL(){
        StringBuilder stringBuilder = new StringBuilder();
        if(isContainsWhere){
            stringBuilder.append("WHERE ");
        }
        stringBuilder.append(filedName);
        stringBuilder.append(" BETWEEN ");
        if(leftValue instanceof String){
            stringBuilder.append("'"+leftValue+"'");
        }else {
            stringBuilder.append(leftValue);
        }
        stringBuilder.append(" AND ");
        if(rightValue instanceof String){
            stringBuilder.append("'"+rightValue+"'");
        }else {
            stringBuilder.append(rightValue);
        }
        return stringBuilder.toString();
    }
}
