package com.ztpai.request;

public class IngredientRequest {

    private String name;
    private String unit;
    private int count;

    public IngredientRequest() {
    }

    public IngredientRequest(String name, String unit, int count) {
        this.name = name;
        this.unit = unit;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "IngredientRequest{" +
                "name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", count=" + count +
                '}';
    }
}
