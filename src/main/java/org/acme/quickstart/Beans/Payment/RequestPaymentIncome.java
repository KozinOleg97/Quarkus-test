package org.acme.quickstart.Beans.Payment;


import java.time.ZonedDateTime;

public class RequestPaymentIncome {

   private String description;
   private int money;
   private ZonedDateTime dateTime;


    public RequestPaymentIncome() {
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
