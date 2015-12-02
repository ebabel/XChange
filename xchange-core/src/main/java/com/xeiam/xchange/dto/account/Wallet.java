package com.xeiam.xchange.dto.account;

import java.math.BigDecimal;
import java.util.*;

import com.xeiam.xchange.currency.Currencies;
import com.xeiam.xchange.dto.trade.Balance;

/**
 * <p>
 * DTO representing a wallet
 * </p>
 * <p>
 * A wallet has a set of current balances in various currencies held on the exchange.
 * </p>
 */
public final class Wallet {

  /**
   * A unique identifier for this wallet
   */
  private String id;

  /**
   * A descriptive name for this wallet.  Defaults to {@link #id}
   */
  private String name;

  /**
   * The keys represent the currency of the wallet.
   */
  private final Map<String, Balance> balances;

  /**
   * Constructs a {@link Wallet}.
   *
   * @param id the wallet id
   * @param name a descriptive name for the wallet
   * @param balances the balances, the currencies of the balances should not be duplicated.
   */
  public Wallet(String id, String name, Collection<Balance> balances) {

    this.id = id;
    if (name == null) {
      this.name = id;
    } else {
      this.name = name;
    }
    if (balances.size() == 0) {
      this.balances = Collections.emptyMap();
    } else if (balances.size() == 1) {
      Balance balance = balances.iterator().next();
      this.balances = Collections.singletonMap(balance.getCurrency(), balance);
    } else {
      this.balances = new HashMap<String, Balance>();
      for (Balance balance : balances) {
        if (this.balances.containsKey(balance.getCurrency()))
          // this class could merge balances, but probably better to catch mistakes and let the exchange merge them
          throw new IllegalArgumentException("duplicate balances in wallet");
        this.balances.put(balance.getCurrency(), balance);
      }
    }
  }

  /**
   * @see #Wallet(String,String,Collection)
   */
  public Wallet(String id, Collection<Balance> balances) {

    this(id, null, balances);
  }

  /**
   * @see #Wallet(String,String,Collection)
   */
  public Wallet(String id, Balance... balances) {

    this(id, null, (Collection<Balance>)Arrays.asList(balances));
  }

  /**
   * @see #Wallet(String,String,Collection)
   */
  public Wallet(Collection<Balance> balances) {

    this(null, null, balances);
  }

  /**
   * @see #Wallet(String,String,Collection)
   */
  public Wallet(Balance... balances) {

    this(null, balances);
  }

  /**
   * @return The wallet id
   */
  public String getId() {

    return id;
  }

  /**
   * @return A descriptive name for the wallet
   */
  public String getName() {

    return name;
  }

  /**
   * @return The available balances (amount and currency)
   */
  public Map<String,Balance> getBalances() {

    return Collections.unmodifiableMap(balances);
  }

  /**
   * Returns the balance for the specified currency.
   *
   * @param currency one of the {@link Currencies}.
   * @return the balance of the specified currency, or a zero balance if currency not present
   */
  public Balance getBalance(String currency) {

    Balance balance = this.balances.get(currency);
    return balance == null ? Balance.zero(currency) : balance;
  }

  @Override
  public String toString() {

    return "Wallet [id=" + id + ", name=" + name + ", balances=" + balances.values() + "]";
  }

}
