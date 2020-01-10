package com.ellen.sqlitecreate.createsql.order;

public class Order {

    private String firstOrderFieldName;
    private String secondOrderFieldName;
    private boolean isDESC = true;
    private boolean isContainsOrder = true;

    private Order(boolean isContainsOrder){
        this.isContainsOrder = isContainsOrder;
    }

    public static Order getInstance(boolean isContainsOrder){
        Order order = new Order(isContainsOrder);
        return order;
    }

    public Order setFirstOrderFieldName(String firstOrderFieldName){
        this.firstOrderFieldName = firstOrderFieldName;
        return this;
    }

    public Order setSecondOrderFieldName(String secondOrderFieldName) {
        this.secondOrderFieldName = secondOrderFieldName;
        return this;
    }

    public Order setIsDesc(boolean isDesc){
        this.isDESC = isDesc;
        return this;
    }

    public String createSQL(){
        StringBuilder stringBuilder = new StringBuilder();
        if(isContainsOrder){
            stringBuilder.append("ORDER BY ");
        }
        stringBuilder.append(firstOrderFieldName);
        if(secondOrderFieldName != null){
            stringBuilder.append(","+secondOrderFieldName+" ");
        }else {
            stringBuilder.append(" ");
        }
        if(isDESC){
            stringBuilder.append("DESC");
        }else {
            stringBuilder.append("ASC");
        }
        return stringBuilder.toString();
    }
}
