/**
 * 文件名: HsOrdStatusClient.java
 * 版 权: Copyright © 2013 - 2016 ThinkJF, Inc. All Rights Reserved
 * 描 述: <描述>
 * 修改人: Hao.Lu
 * 修改时间: 2016-3-18
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.chengfeng.kj.webservice.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chengfeng.kj.webservice.domain.OrdStatusVO;
import com.chengfeng.kj.webservice.service.IOrdStausService;
import com.thinkjf.core.config.GlobalConfig;
import com.thinkjf.service.ServiceException;

/**
 * <p>功能描述：</p>
 * <p>Company: 上海丞风智能科技有限公司</p>
 * @author Hao.Lu
 * @version 1.0 2016-3-18
 */
public class HsOrdStatusClient
{

    @Autowired
    private IOrdStausService statusService;

    private final Log log = LogFactory.getLog(getClass());

    // 定义每次发送数据长度
    private final int sendLength = 1000;
    // 接收每5分钟扫描数据库的数据
    private List<OrdStatusVO> queryHsOrdStatus = new ArrayList<OrdStatusVO>();

    public void sendOrdStatus()
    {
        HttpClientParams httpClientParams = new HttpClientParams();
        httpClientParams.setConnectionManagerTimeout(1000);
        HttpClient client = new HttpClient(httpClientParams, new SimpleHttpConnectionManager(true));

        try
        {
            queryHsOrdStatus.addAll(statusService.queryHsOrdStatus());

            int listSize = queryHsOrdStatus.size();
            while (0 < listSize)
            {
                PostMethod postMethod = new PostMethod(
                        GlobalConfig.getPropertyValue("common.kjwebservice.ordStatus.url"));
                postMethod.getParams().clear();
                postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
                List<OrdStatusVO> subList = new ArrayList<OrdStatusVO>();
                int length = this.sendLength;
                if (this.sendLength > queryHsOrdStatus.size())
                {
                    length = queryHsOrdStatus.size();
                }
                subList = queryHsOrdStatus.subList(0, length);
                SerializerFeature feature = SerializerFeature.DisableCircularReferenceDetect;
                JSONArray array = new JSONArray();
                array.addAll(subList);
                postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                postMethod.addRequestHeader("Connection", "close");
                postMethod.addParameter("params", JSONObject.toJSONString(array, feature));

                client.executeMethod(postMethod);

                String responsess = postMethod.getResponseBodyAsString();
                JSONObject result = JSONObject.parseObject(responsess);
                String code = result.getString("code");
                if (!"200".equals(code))
                {
                    log.error("[Error:数据接收失败]" + array.toJSONString());
                }
                else if ("200".equals(code))
                    {
                        queryHsOrdStatus.subList(0, length).clear();
                        listSize = queryHsOrdStatus.size();
                    }
            }
        }
        catch (ServiceException e)
        {
            e.printStackTrace();
            log.error("[Error:扫描数据异常]", e);
        }
        catch (HttpException e)
        {
            e.printStackTrace();
            log.error("[Error:建立连接异常]", e);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            log.error("[Error:数据传输异常]", e);
        }
    }
}
