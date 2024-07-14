package com.whatsappbuisness.wsbuissness.CombinePackadge.Notification;

public class AccountBalance {
    private long id;
    private double balance;
    private double balanceUnofficial;

    @Override
    public String toString() {
        return "AccountBalance{" +
                "id=" + id +
                ", balance=" + balance +
                ", balanceUnofficial=" + balanceUnofficial +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalanceUnofficial() {
        return balanceUnofficial;
    }

    public void setBalanceUnofficial(double balanceUnofficial) {
        this.balanceUnofficial = balanceUnofficial;
    }

    public AccountBalance() {
    }

    public AccountBalance(long id, double balance, double balanceUnofficial) {
        this.id = id;
        this.balance = balance;
        this.balanceUnofficial = balanceUnofficial;
    }
}
