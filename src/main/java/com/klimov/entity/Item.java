package com.klimov.entity;

public class Item {
    private String name;
    private Integer price;
    private boolean isBooked;

    public Item() {
    }

    public Item(String name, Integer price, boolean isBooked) {
        this.name = name;
        this.price = price;
        this.isBooked = isBooked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBooked() {
        return isBooked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (isBooked != item.isBooked) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        return price != null ? price.equals(item.price) : item.price == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (isBooked ? 1 : 0);
        return result;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;

    }

}
