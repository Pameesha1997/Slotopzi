package com.sliit.slotopzi.dto.request;

public class SubCatCompleteRequest {
    private long repairId;
    private long subCatId;

    public long getRepairId() {
        return repairId;
    }

    public void setRepairId(long repairId) {
        this.repairId = repairId;
    }

    public long getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(long subCatId) {
        this.subCatId = subCatId;
    }
}
