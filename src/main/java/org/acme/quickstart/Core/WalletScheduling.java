package org.acme.quickstart.Core;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.scheduler.Scheduled;
import org.acme.quickstart.Entity.Deal;
import org.acme.quickstart.Entity.Test;
import org.acme.quickstart.Entity.Wallet;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;


public class WalletScheduling {

    @Scheduled(every = "10s")
    @Transactional
    public void calcWallets() {

        System.out.println("calcWallets");


        List<Deal> dealList = Deal.list("status", true);
        for (Deal aDeal: dealList   ) {
            Wallet wallet = aDeal.account.wallet;
            ///aDeal.account.wallet.outlay = aDeal.account.wallet.outlay - 5;
            wallet.outlay = wallet.outlay - 5;
            wallet.persist();
            ///aDeal.account.wallet.persist();
        }


        System.out.println("done");

    }
}
