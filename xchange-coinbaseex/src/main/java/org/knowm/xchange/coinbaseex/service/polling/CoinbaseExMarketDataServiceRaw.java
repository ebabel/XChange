package org.knowm.xchange.coinbaseex.service.polling;

import java.io.IOException;
import java.util.List;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.coinbaseex.CoinbaseEx;
import org.knowm.xchange.coinbaseex.dto.marketdata.CoinbaseExProduct;
import org.knowm.xchange.coinbaseex.dto.marketdata.CoinbaseExProductBook;
import org.knowm.xchange.coinbaseex.dto.marketdata.CoinbaseExProductStats;
import org.knowm.xchange.coinbaseex.dto.marketdata.CoinbaseExProductTicker;
import org.knowm.xchange.coinbaseex.dto.marketdata.CoinbaseExTrade;
import org.knowm.xchange.currency.CurrencyPair;

/**
 * Created by Yingzhe on 4/6/2015.
 */
public class CoinbaseExMarketDataServiceRaw extends CoinbaseExBasePollingService<CoinbaseEx> {

  public CoinbaseExMarketDataServiceRaw(Exchange exchange) {

    super(CoinbaseEx.class, exchange);
  }

  public CoinbaseExProductTicker getCoinbaseExProductTicker(CurrencyPair currencyPair) throws IOException {

    if (!this.checkProductExists(currencyPair)) {
      return null;
    }

    CoinbaseExProductTicker tickerReturn = this.coinbaseEx.getProductTicker(currencyPair.base.getCurrencyCode(),
        currencyPair.counter.getCurrencyCode());
    return tickerReturn;
  }

  public CoinbaseExProductStats getCoinbaseExProductStats(CurrencyPair currencyPair) throws IOException {

    if (!this.checkProductExists(currencyPair)) {
      return null;
    }

    CoinbaseExProductStats statsReturn = this.coinbaseEx.getProductStats(currencyPair.base.getCurrencyCode(), currencyPair.counter.getCurrencyCode());
    return statsReturn;
  }

  public CoinbaseExProductBook getCoinbaseExProductOrderBook(CurrencyPair currencyPair) throws IOException {

    if (!this.checkProductExists(currencyPair)) {
      return null;
    }

    CoinbaseExProductBook book = this.coinbaseEx.getProductOrderBook(currencyPair.base.getCurrencyCode(), currencyPair.counter.getCurrencyCode(),
        "1");
    return book;
  }

  public CoinbaseExProductBook getCoinbaseExProductOrderBook(CurrencyPair currencyPair, int level) throws IOException {
    CoinbaseExProductBook book = this.coinbaseEx.getProductOrderBook(currencyPair.base.getCurrencyCode(), currencyPair.counter.getCurrencyCode(),
        String.valueOf(level));
    return book;
  }

  public CoinbaseExTrade[] getCoinbaseExTrades(CurrencyPair currencyPair, int limit) throws IOException {

    return this.coinbaseEx.getTrades(currencyPair.base.getCurrencyCode(), currencyPair.counter.getCurrencyCode(), String.valueOf(limit));

  }

  private boolean checkProductExists(CurrencyPair currencyPair) throws IOException {

    boolean currencyPairSupported = false;
    for (CurrencyPair cp : exchange.getExchangeSymbols()) {
      if (cp.base.getCurrencyCode().equalsIgnoreCase(currencyPair.base.getCurrencyCode())
          && cp.counter.getCurrencyCode().equalsIgnoreCase(currencyPair.counter.getCurrencyCode())) {
        currencyPairSupported = true;
        break;
      }
    }

    return currencyPairSupported;
  }

  public List<CoinbaseExProduct> getConbaseExProducts() throws IOException {

    return coinbaseEx.getProducts();
  }
}
