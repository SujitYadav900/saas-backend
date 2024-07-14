package com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao;

public class AccountTransactionReportDao {
    private long balance;
    private long accountId;
    private long credit;
    private long debit;

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

    public long getDebit() {
        return debit;
    }

    public void setDebit(long debit) {
        this.debit = debit;
    }

    @Override
    public String toString() {
        return "AccountTransactionReportDao{" +
                "balance=" + balance +
                ", accountId=" + accountId +
                ", credit=" + credit +
                ", debit=" + debit +
                '}';
    }
}
