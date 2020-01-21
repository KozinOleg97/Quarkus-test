package org.acme.quickstart.Core;

import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WalletScheduling {

    @Scheduled(every = "10s")
    void calcWallets(){
        System.out.println("calcWallets");
    }
}
