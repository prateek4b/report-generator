package com.guide.cm.reportgenerator.proxy;

import org.apache.commons.lang.StringUtils;

public class FeeEntry {

    FeeCalculation feeCalculation;
    private long id;
    private long fees;
    private String feeType = StringUtils.EMPTY;
    private String feeHead = StringUtils.EMPTY;
    private String date = StringUtils.EMPTY;
    private long receiptNo;
    private String assessMentYear = StringUtils.EMPTY;
    private String gstNo = StringUtils.EMPTY;

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public FeeCalculation getFeeCalculation() {
        return feeCalculation;
    }

    public void setFeeCalculation(FeeCalculation feeCalculation) {
        this.feeCalculation = feeCalculation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFees() {
        return fees;
    }

    public void setFees(long fees) {
        this.fees = fees;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getFeeHead() {
        return feeHead;
    }

    public void setFeeHead(String feeHead) {
        this.feeHead = feeHead;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(long receiptNo) {
        this.receiptNo = receiptNo;
    }


    public String getAssessMentYear() {
        return assessMentYear;
    }

    public void setAssessMentYear(String assessMentYear) {
        this.assessMentYear = assessMentYear;
    }
}
