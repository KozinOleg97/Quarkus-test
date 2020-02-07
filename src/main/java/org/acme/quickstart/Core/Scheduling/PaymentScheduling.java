package org.acme.quickstart.Core.Scheduling;

import io.quarkus.scheduler.Scheduled;
import org.acme.quickstart.Beans.Payment.RequestPaymentIncome;
import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.Deal;
import org.acme.quickstart.Entity.Payment;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.ZonedDateTime;
import java.util.List;


public class PaymentScheduling {

    private final int STANDART_PAYMENT_VALUE = 100;

    @Scheduled(every = "60s")
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
