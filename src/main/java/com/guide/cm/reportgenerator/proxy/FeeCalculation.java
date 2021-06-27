package com.guide.cm.reportgenerator.proxy;

import java.util.ArrayList;
import java.util.List;


public class FeeCalculation {

    List<FeeEntry> feeEntries = new ArrayList<>();

    private long id;

    private String gstNo;

    public List<FeeEntry> getFeeEntries() {
        return feeEntries;
    }

    public void setFeeEntries(List<FeeEntry> feeEntries) {
        this.feeEntries = feeEntries;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }
}
