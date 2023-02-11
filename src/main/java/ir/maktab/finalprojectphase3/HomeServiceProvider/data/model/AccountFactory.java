package ir.maktab.finalprojectphase3.HomeServiceProvider.data.model;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.AccountType;

public class AccountFactory {
    public Account getAccount(AccountType accountType) {
        if (accountType.equals(AccountType.CUSTOMER))
            return new Customer();
        else if (accountType.equals(AccountType.EXPERT))
            return new Expert();
        else if (accountType.equals(AccountType.ADMIN))
            return new Admin();
        else
            return null;
    }
}
