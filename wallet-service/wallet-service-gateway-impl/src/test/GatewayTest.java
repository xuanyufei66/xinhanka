import bo.ReqPaymentStatusBO;
import bo.ReqPaymentSubmitBO;
import bo.RspResultBO;
import com.payease.wallet.gateway.impl.serviceImpl.PaymentServiceImpl;

import java.io.UnsupportedEncodingException;

/**
 * Created by lch on 2017/11/7.
 */

public class GatewayTest {

    public static void main(String[] args) throws UnsupportedEncodingException {

        //        ReqPaymentStatusBO bo = new ReqPaymentStatusBO();
        //        bo.setMid("20027");
        //        bo.setKey("test");
        //        bo.setUrl("https://pay.yizhifubj
        // .com/merchant/virement/mer_payment_status_utf8.jsp");
        //        bo.setRequestId("1232131231231231");
        //        IPaymentStatusService iPaymentStatusService = new PaymentStatusServiceImpl();
        //        RspResultBO rspResultBO = iPaymentStatusService.PaymentStatus(bo);
        //        System.out.println(rspResultBO);


        ReqPaymentSubmitBO bo = new ReqPaymentSubmitBO();
        bo.setMid("20027");
        bo.setKey("test");
        //bo.setUrl("http://210.73.90.235/merchant/virement/mer_payment_submit_utf8.jsp");
        bo.setUrl("https://pay.yizhifubj.com/merchant/virement/mer_payment_submit_utf8.jsp");
        bo.setAccountName("李长宏");
        bo.setAccountNo("6217000060000540854");
        bo.setBankCode("105100000017");
        bo.setBankName("中国建设银行股份有限公司天津津门湖支行");
        bo.setCity("天津市");
        bo.setProvince("天津市");
        bo.setMoney("0.01");
        bo.setUserID("1232131231231231");
        PaymentServiceImpl iPaymentSubmitService = new PaymentServiceImpl();
        RspResultBO rspResultBO = iPaymentSubmitService.paymentSubmit(bo);
        System.out.println(rspResultBO);



    }
}
