package com.xeiam.xchange.examples.cointrader;

import java.io.IOException;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.cointrader.service.polling.CointraderAccountServiceRaw;
import com.xeiam.xchange.dto.account.Wallet;

public class CointraderAccountDemo {

  public static void main(String[] args) throws IOException {
    Exchange cointraderExchange = CointraderExampleUtils.createTestExchange();

    generic(cointraderExchange);
    raw(cointraderExchange);
  }

  private static void generic(Exchange cointraderExchange) throws IOException {
    Wallet wallet = cointraderExchange.getPollingAccountService().getAccountInfo();
    System.out.println("Account Info: " + wallet);
  }

  private static void raw(Exchange cointraderExchange) throws IOException {
    CointraderAccountServiceRaw rawCointraderAcctService = (CointraderAccountServiceRaw) cointraderExchange.getPollingAccountService();
    System.out.println("Balance Info: " + rawCointraderAcctService.getCointraderBalance());
  }
}
