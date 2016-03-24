/**
 * 文件名: HSordStatuVO.java
 * 版 权: Copyright © 2013 - 2016 ThinkJF, Inc. All Rights Reserved
 * 描 述: <描述>
 * 修改人: Hao.Lu
 * 修改时间: 2016-3-18
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.chengfeng.kj.webservice.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>功能描述：</p>
 * <p>Company: 上海丞风智能科技有限公司</p>
 * @author Hao.Lu
 * @version 1.0 2016-3-18
 */
public class OrdStatusVO implements Serializable
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7803390275605269014L;
    
    /**订单ID*/
    private Long orderId;

    /**订单状态
     * 1：撤销
     * 0：等待接单
     * 1：接单成功
     * 2：接单失败
     * 3：揽件成功
     * 4：揽件失败
     * 5：运输中
     * 6：派件成功
     * 7：派件失败
     * 8：派件中
     */
    private Integer orderStatus;

    /**创建网点*/
    private Long siteId;

    /**撤单原因ID*/
    private Long orderCancelId;

    /**创建人*/
    private Long createdBy;

    /**创建时间*/
    private Date createdTime;

    /**
     * @return the orderId
     */
    public Long getOrderId()
    {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    /**
     * @return the orderStatus
     */
    public Integer getOrderStatus()
    {
        return orderStatus;
    }

    /**
     * @param orderStatus the orderStatus to set
     */
    public void setOrderStatus(Integer orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    /**
     * @return the siteId
     */
    public Long getSiteId()
    {
        return siteId;
    }

    /**
     * @param siteId the siteId to set
     */
    public void setSiteId(Long siteId)
    {
        this.siteId = siteId;
    }

    /**
     * @return the orderCancelId
     */
    public Long getOrderCancelId()
    {
        return orderCancelId;
    }

    /**
     * @param orderCancelId the orderCancelId to set
     */
    public void setOrderCancelId(Long orderCancelId)
    {
        this.orderCancelId = orderCancelId;
    }

    /**
     * @return the createdBy
     */
    public Long getCreatedBy()
    {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(Long createdBy)
    {
        this.createdBy = createdBy;
    }

    /**
     * @return the createdTime
     */
    public Date getCreatedTime()
    {
        return createdTime;
    }

    /**
     * @param createdTime the createdTime to set
     */
    public void setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
    }

    
}
