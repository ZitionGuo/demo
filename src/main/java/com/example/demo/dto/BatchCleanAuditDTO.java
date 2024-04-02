package com.example.demo.dto;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yangjin
 */
public class BatchCleanAuditDTO implements Serializable {

    private static final long serialVersionUID = 8690098016394701501L;
    /**
      *商品编码
     */
    private String productCode;
    /**
     * 门店编码
     */
    private String shopCode;
    /**
     * 鲜度内促销-批次日期
     */
    private String batchDateInternalFreshnessPromotion;

    /**
     * 鲜度内促销-建议价格
     */
    private BigDecimal advicePriceInternalFreshnessPromotion;
    /**
     * 鲜度内促销-建议价格折扣率
     */
    private BigDecimal advicePriceDiscountInternalFreshnessPromotion;
    /**
     * 价格审核开始时间
     */
    private Long auditTime;
    /**
     * 价格生效开始时间
     */
    private Long priceEffectiveStartTime;
    /**
     * 价格生效结束时间
     */
    private Long priceEffectiveEndTime;
    /**
     * 鲜度外出清-最大批次日期
     */
    private String maxBatchDateExternalFreshnessClear;
    /**
     * 鲜度外出清-建议降价
     */
    private BigDecimal advicePriceExternalFreshnessClear;

    /**
     * 鲜度外出清-建议降价折扣
     */
    private BigDecimal advicePriceDiscountExternalFreshnessClear;
    /**
     * 审核人
     */
    private String auditBy;
    /**
     * 交易单号 审核、审核取消生成
     */
    private String transactionNo;
}
