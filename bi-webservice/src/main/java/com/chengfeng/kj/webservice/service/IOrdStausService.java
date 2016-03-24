/**
 * 文件名: IStausService.java
 * 版    权: Copyright © 2013 - 2016 ThinkJF, Inc. All Rights Reserved
 * 描    述: <描述>
 * 修改人: Hao.Lu
 * 修改时间: 2016-3-18
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.chengfeng.kj.webservice.service;

import java.util.List;

import com.chengfeng.kj.webservice.domain.OrdStatusVO;
import com.thinkjf.service.ServiceException;

/**
 * <p>功能描述：</p>
 * <p>Company: 上海丞风智能科技有限公司</p>
 * @author Hao.Lu
 * @version 1.0 2016-3-18
 */
public interface IOrdStausService
{
    /**
     * 获取订单状态信息
     *
     * @return
     * @throws ServiceException
     */
    public List<OrdStatusVO> queryHsOrdStatus() throws ServiceException;
}
