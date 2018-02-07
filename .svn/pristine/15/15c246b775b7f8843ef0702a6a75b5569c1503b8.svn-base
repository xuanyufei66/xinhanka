package com.payease.wallet.scheduling.AccountThread;

import com.payease.wallet.entity.pojo.TWithdrawalHistory;
import com.payease.wallet.scheduling.service.CheckWithdrawalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by zhangzhili on 2017/11/30.
 */
public class CheckWithdrawalThread implements Runnable {



    private TWithdrawalHistory tWithdrawalHistory;
    private CheckWithdrawalServiceImpl checkWithdrawalServiceImpl;



    @Override
    public void run() {
        checkWithdrawalServiceImpl.handler(tWithdrawalHistory);
    }


    public CheckWithdrawalThread(TWithdrawalHistory tWithdrawalHistory,
                                 CheckWithdrawalServiceImpl checkWithdrawalServiceImpl) {
        this.tWithdrawalHistory = tWithdrawalHistory;
        this.checkWithdrawalServiceImpl = checkWithdrawalServiceImpl;

    }


    public TWithdrawalHistory gettWithdrawalHistory() {
        return tWithdrawalHistory;
    }

    public void settWithdrawalHistory(TWithdrawalHistory tWithdrawalHistory) {
        this.tWithdrawalHistory = tWithdrawalHistory;
    }


}
