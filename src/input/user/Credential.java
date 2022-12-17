package input.user;

public final class Credential {
  private String name;
  private String password;
  private String accountType;
  private String country;
  private int balance;

  public Credential() {
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public void setAccountType(final String accountType) {
    this.accountType = accountType;
  }

  public void setCountry(final String country) {
    this.country = country;
  }

  public void setBalance(final int balance) {
    this.balance = balance;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public String getAccountType() {
    return accountType;
  }

  public String getCountry() {
    return country;
  }

  /** Return the balance of the user in String format. */
  public String getBalance() {
    return Integer.toString(balance);
  }

  /** Return the balance of the user in int format. */
  public int findBalanceCount() {
    return balance;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Credential other = (Credential) obj;
    return this.name.equals(other.name) && this.password.equals(other.password);
  }
}
