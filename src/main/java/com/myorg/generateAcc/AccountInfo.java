package com.myorg.generateAcc;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by huyan on 2016/9/18.
 */
public class AccountInfo {

    private Collection<String> onlyAccount;
    private Collection<String> accountInfo;
    private Map<String, Integer> attClickCount;
    private int allClickCount;
    String baseAccountFilePath;

    public Collection<String> getOnlyAccount() {
        return onlyAccount;
    }

    public void setOnlyAccount(Collection<String> onlyAccount) {
        this.onlyAccount = onlyAccount;
    }

    public Collection<String> getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(Collection<String> accountInfo) {
        this.accountInfo = accountInfo;
    }

    public Map<String, Integer> getAttClickCount() {
        return attClickCount;
    }

    public void setAttClickCount(Map<String, Integer> attClickCount) {
        this.attClickCount = attClickCount;
    }

    public int getAllClickCount() {
        return allClickCount;
    }

    public void setAllClickCount(int allClickCount) {
        this.allClickCount = allClickCount;
    }

    public String getBaseAccountFilePath() {
        return baseAccountFilePath;
    }

    public void setBaseAccountFilePath(String baseAccountFilePath) {
        this.baseAccountFilePath = baseAccountFilePath;
    }
}
