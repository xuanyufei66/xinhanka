package com.payease.wallet.scheduling.timer;

import com.payease.wallet.entity.pojo.TWithdrawalHistory;
import com.payease.wallet.orm.inter.TWithdrawalHistoryMapper;
import com.payease.wallet.scheduling.AccountThread.CheckWithdrawalThread;
import com.payease.wallet.scheduling.service.CheckWithdrawalServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangzhili on 2017/11/20.
 */
@Component
public class CheckWithdrawalTimer {

    private static final Logger log = LoggerFactory.getLogger(CheckWithdrawalTimer.class);

    @Autowired
    TWithdrawalHistoryMapper tWithdrawalHistoryMapper;

    @Autowired
    CheckWithdrawalServiceImpl checkWithdrawalServiceImpl;

    private BlockingDeque<Runnable> queue = new LinkedBlockingDeque<>();
    private ThreadPoolExecutor poolExecutor =
        new ThreadPoolExecutor(10, 15, 5L, TimeUnit.HOURS, queue);

    //    @Scheduled(cron = "0 0/1 * * * ?") //每分钟执行一次
    //    public void statusCheck() {
    //        System.out.println("开始");
    //    }
    //@Scheduled(fixedRate = 1000 * 10)
    //    @Scheduled(cron = "*/5 * * * * ?")
    //    public void testFsfd(){
    //        String s = UUID.randomUUID().toString();
    //        System.out.println(s);
    //        try {
    //            Thread.sleep(10001);
    //            System.out.println("睡眠过后"+s);
    //        } catch (InterruptedException e) {
    //            e.printStackTrace();
    //        }
    //    }

    //@Scheduled(fixedRate = 1000 * 60 * 10)
    @Scheduled(cron = "0 0/10 * * * ?")
    public void testTasks() {
        boolean flag = true;
        Long id = 0L;
        while (flag) {
            //查询提现记录表中前100条数据
            List<TWithdrawalHistory> tWithdrawalHistoryList =
                tWithdrawalHistoryMapper.selectLimit(id);
            log.info("提现记录共有={}", tWithdrawalHistoryList.size());
            if (!tWithdrawalHistoryList.isEmpty()) {
                for (TWithdrawalHistory tWithdrawalHistory : tWithdrawalHistoryList) {
                    poolExecutor.execute(new CheckWithdrawalThread(tWithdrawalHistory,
                        checkWithdrawalServiceImpl));
                }
                id = tWithdrawalHistoryList.get(tWithdrawalHistoryList.size() - 1).getId();
            } else {
                flag = false;
            }
        }
    }


}
