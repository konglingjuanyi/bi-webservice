/**
 * 文件名: HsordStatuDaoImpl.java
 * 版 权: Copyright © 2013 - 2016 ThinkJF, Inc. All Rights Reserved
 * 描 述: <描述>
 * 修改人: Hao.Lu
 * 修改时间: 2016-3-18
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.chengfeng.kj.webservice.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chengfeng.common.dao.BaseNeDaoImpl;
import com.chengfeng.kj.webservice.dao.IOrdStatuDao;
import com.chengfeng.kj.webservice.domain.OrdStatusVO;
import com.thinkjf.dao.DaoException;

/**
 * <p>功能描述：</p>
 * <p>Company: 上海丞风智能科技有限公司</p>
 * @author Hao.Lu
 * @version 1.0 2016-3-18
 */
@Repository("ordStatusDao")
public class OrdStatusDaoImpl extends BaseNeDaoImpl implements IOrdStatuDao
{

    /**
     * 获取订单状态信息
     *
     * @return
     * @throws DaoException
     */
    public List<OrdStatusVO> queryHsOrdStatus() throws DaoException
    {
        // Map<String,Date> parameter =new HashMap<String, Date>();
        // Date currentTime = this.getCurrentTime();
        // parameter.put("", currentTime);
        // parameter.put("", new Date(currentTime.getTime()-5*60*1000));
        return super.queryForList("OrdStatusVO.queryOrdStatus");
    }

    public List<OrdStatusVO> queryList() throws DaoException
    {
        return super.queryForList("OrdStatusVO.queryList");
    }

}
