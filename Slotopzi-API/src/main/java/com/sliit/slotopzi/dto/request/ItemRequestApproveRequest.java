package com.sliit.slotopzi.dto.request;
import com.sliit.slotopzi.enums.ItemRequestStatus;

import java.math.BigDecimal;
import java.util.Date;
public class ItemRequestApproveRequest {

    private long requestId;
    private BigDecimal quantity;
    private Date issuedDateTime;
    private ItemRequestStatus status;
    private long itemNo;

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Date getIssuedDateTime() {
        return issuedDateTime;
    }

    public void setIssuedDateTime(Date issuedDateTime) {
        this.issuedDateTime = issuedDateTime;
    }

    public ItemRequestStatus getStatus() {
        return status;
    }

    public void setStatus(ItemRequestStatus status) {
        this.status = status;
    }

    public long getItemNo() {
        return itemNo;
    }

    public void setItemNo(long itemNo) {
        this.itemNo = itemNo;
    }
}

