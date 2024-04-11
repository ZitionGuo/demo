package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author guozixuan
 * @date 2023/12/18 13:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBatchPickingParams implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<BatchPickingItem> itemList;

    private Integer type;

    @Data
    public static class BatchPickingItem implements Serializable {

        private static final long serialVersionUID = 1L;

        private String shopId;

        private String skuCode;
    }
}
