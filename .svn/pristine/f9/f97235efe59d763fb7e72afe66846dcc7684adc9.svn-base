package com.payease.wallet.app.controller;

import com.alibaba.fastjson.JSON;
import com.payease.wallet.app.common.utils.FastDFSClient;
import com.payease.wallet.dto.bean.ResultBo;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhili on 2018/1/9.
 */
@Controller
public class UploadController {
    private final static Logger log = LoggerFactory.getLogger(UploadController.class);


    @Value("${trackerService}")
    String trackerService;

    @PostMapping(value = "/uploadpic")
    public ResultBo uploadMerchantInfo(MultipartHttpServletRequest request) {
        log.info("图片上传*******");
        ResultBo resultBo = ResultBo.build();
        MultiValueMap<String, MultipartFile> map = request.getMultiFileMap();// 为了获取文件，这个类是必须的
        MultipartFile multipartFile = map.getFirst("filedata");
        String url = null;
        try {
            url = uploadPic(multipartFile);
            resultBo.getResultBody().put("imgUrl", url);
            log.info("图片上传成功返回：==={}", JSON.toJSONString(resultBo));
        } catch (Exception e) {
            log.error("图片上传失败", e);
            resultBo.fail();
        }
        return resultBo;
    }



    public String uploadPic(MultipartFile picFile) throws Exception {
        String url = null;
        //上传到图片服务器
        //取图片扩展名
        String originaFilename = picFile.getOriginalFilename();
        //扩展名不要“.”
        String extName = originaFilename.substring(originaFilename.indexOf(".") + 1);
        FastDFSClient client =
            new FastDFSClient(trackerService);
        //            FastDFSClient client = new FastDFSClient("classpath:client.conf");
        url = client.uploadFile(picFile.getBytes(), extName);
        return url;
    }
}
