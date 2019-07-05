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
    private boolean isContainsWhere = false;

    public static Between getInstance(boolean isContainsWhere){
        Between between = new Between();
        between.isContainsWhere = isContainsWhere;
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

    public String createSQL(){
        StringBuilder stringBuilder = new StringBuilder();
        if(isContainsWhere){
            stringBuilder.append("WHERE ");
        }
        stringBuilder.append("BETWEEN ");
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
