package org.acme.quickstart.Core.Scheduling;

import io.quarkus.scheduler.Scheduled;
import org.acme.quickstart.Entity.Deal;
import org.acme.quickstart.Entity.Payment;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;


public class PaymentScheduling {

    private final int STANDART_PAYMENT_VALUE = 1000;


    /**
     * money outcome
     */
    //every hour
    @Scheduled(cron = "0 0 0/1 1/1 * ? *")
    // every minute
    //@Scheduled(cron = "0 0/1 * 1/1 * ? *")
    @Transactional
    public void calcWallets() {

        System.out.println("calc Payments");

        List<Deal> dealList = Deal.list("status", true);

        for (Deal aDeal : dealList) {

            Payment newPaymentDoc = new Payment();
            newPaymentDoc.type = "outcome";
            newPaymentDoc.description = "Auto payment  ";
            newPaymentDoc.date = ZonedDateTime.now();
            newPaymentDoc.deal = aDeal;
            newPaymentDoc.account = aDeal.account;
            newPaymentDoc.money = Math.round(aDeal.box.coefficient * STANDART_PAYMENT_VALUE);

            newPaymentDoc.persist();

        }


        System.out.println("done");

    }
}
