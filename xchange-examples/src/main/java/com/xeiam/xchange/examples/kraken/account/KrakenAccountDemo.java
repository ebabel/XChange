package com.xeiam.xchange.examples.kraken.account;

import java.io.IOException;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.dto.account.Wallet;
import com.xeiam.xchange.examples.kraken.KrakenExampleUtils;
import com.xeiam.xchange.kraken.service.polling.KrakenAccountServiceRaw;

/**
 * <p>
 * Example showing the following:
 * </p>
 * <ul>
 * <li>Connect to Kraken exchange with authentication</li>
 * <li>View account balance</li>
 * </ul>
 */
public class KrakenAccountDemo {

  public static void main(String[] args) throws IOException {

    Exchange krakenExchange = KrakenExampleUtils.createTestExchange();

    generic(krakenExchange);
    raw(krakenExchange);
  }

  private static void generic(Exchange krakenExchange) throws IOException {

    Wallet wallet = krakenExchange.getPollingAccountService().getAccountInfo();
    System.out.println("Account Info: " + wallet.toString());
  }

  private static void raw(Exchange krakenExchange) throws IOException {

    KrakenAccountServiceRaw rawKrakenAcctService = (KrakenAccountServiceRaw) krakenExchange.getPollingAccountService();
    System.out.println("Balance Info: " + rawKrakenAcctService.getKrakenBalance());
  }
}
