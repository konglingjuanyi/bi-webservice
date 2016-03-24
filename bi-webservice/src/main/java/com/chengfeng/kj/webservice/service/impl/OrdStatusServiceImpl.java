/**
 * 文件名: StatusServiceImpl.java
 * 版    权: Copyright © 2013 - 2016 ThinkJF, Inc. All Rights Reserved
 * 描    述: <描述>
 * 修改人: Hao.Lu
 * 修改时间: 2016-3-18
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.chengfeng.kj.webservice.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chengfeng.kj.webservice.dao.IOrdStatuDao;
import com.chengfeng.kj.webservice.domain.OrdStatusVO;
import com.chengfeng.kj.webservice.service.IOrdStausService;
import com.thinkjf.service.ServiceException;

/**
 * <p>功能描述：</p>
 * <p>Company: 上海丞风智能科技有限公司</p>
 * @author Hao.Lu
 * @version 1.0 2016-3-18
 */
@Service("ordStatusService")
public class OrdStatusServiceImpl implements IOrdStausService
{

    @Resource(name="ordStatusDao")
    private IOrdStatuDao ordStatusDao;
    /**
     * 获取订单状态信息
     *
     * @return
     * @throws ServiceException
     */
    @Override
    public List<OrdStatusVO> queryHsOrdStatus() throws ServiceException
    {
        //return ordStatusDao.queryHsOrdStatus();
        return ordStatusDao.queryList();
    }

}
