package org.acme.quickstart.Core.Scheduling;

import io.quarkus.scheduler.Scheduled;

import javax.transaction.Transactional;


public class WalletScheduling {

    @Scheduled(every = "60s")
    @Transactional
    public void calcWallets() {

        System.out.println("calcWallets");


        /*List<Deal> dealList = Deal.list("status", true);

        for (Deal aDeal : dealList) {
            Wallet wallet = aDeal.account.wallet;

            wallet.outlay = wallet.outlay - 1;
            wallet.persist();
        }
*/

        System.out.println("done");

    }
}
