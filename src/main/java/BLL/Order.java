package BLL;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable, Comparable<Order> {

    private Integer orderID;
    private Calendar orderDate;
    private String clientName;
    private static final long serialVersionUID = 1L;


    public Order(Integer orderID,String clientName) {
        this.orderID = orderID;
        this.orderDate = Calendar.getInstance();
        this.clientName = clientName;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public Calendar getOrderDate() {
        return orderDate;
    }

    public String getClientName() {
        return clientName;
    }

    public Date getDate() {
        return orderDate.getTime();
    }

    public int getHour() {
        return orderDate.get(Calendar.HOUR_OF_DAY);
    }

    public int getDayOfMonth() {
        return orderDate.get(Calendar.DAY_OF_MONTH);
    }

    public int getMonth() {
        return orderDate.get(Calendar.MONTH);
    }

    public int getYear() {
        return orderDate.get(Calendar.YEAR);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientName, orderDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Order order = (Order) o;

        return Objects.equals(clientName, order.clientName) && Objects.equals(orderDate, order.orderDate);
    }


    @Override
    public int compareTo(Order o) {
        return this.getDate().compareTo(o.getDate());
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", orderHour=" + getHour() +
                ", clientName='" + clientName + '\'' +
                '}' + "\n";
    }
}
