package input.user;

public class Credential {
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

  public int getBalance() {
    return balance;
  }

  @Override
  public boolean equals(Object obj) {
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
