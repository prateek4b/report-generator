package com.guide.cm.reportgenerator.reportviews;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
public class AllReportView {

    private long id;

    private String name;

    private String gstNo;

    private String scheme;

    private long janFees;

    private String janFeeType = StringUtils.EMPTY;

    private String janDate= StringUtils.EMPTY;

    private long janReceiptNo;

    private int janYear;

    private long febFees;

    private String febFeeType = StringUtils.EMPTY;

    private String febDate= StringUtils.EMPTY;

    private long febReceiptNo;

    private int febYear;

    private long marFees;

    private String marFeeType = StringUtils.EMPTY;

    private String marDate= StringUtils.EMPTY;

    private long marReceiptNo;

    private int marYear;

    private long aprFees;

    private String aprFeeType = StringUtils.EMPTY;

    private String aprDate= StringUtils.EMPTY;

    private long aprReceiptNo;

    private int aprYear;

    private long mayFees;

    private String mayFeeType = StringUtils.EMPTY;

    private String mayDate= StringUtils.EMPTY;

    private long mayReceiptNo;

    private int mayYear;

    private long juneFees;

    private String juneFeeType = StringUtils.EMPTY;

    private String juneDate= StringUtils.EMPTY;

    private long juneReceiptNo;

    private int juneYear;

    private long julyFees;

    private String julyFeeType = StringUtils.EMPTY;

    private String julyDate= StringUtils.EMPTY;

    private long julyReceiptNo;

    private int julyYear;

    private long augFees;

    private String augFeeType = StringUtils.EMPTY;

    private String augDate= StringUtils.EMPTY;

    private long augReceiptNo;

    private int augYear;

    private long sepFees;

    private String sepFeeType = StringUtils.EMPTY;

    private String sepDate= StringUtils.EMPTY;

    private long sepReceiptNo;

    private int sepYear;

    private long octFees;

    private String octFeeType = StringUtils.EMPTY;

    private String octDate= StringUtils.EMPTY;

    private long octReceiptNo;

    private int octYear;

    private long novFees;

    private String novFeeType = StringUtils.EMPTY;

    private String novDate= StringUtils.EMPTY;

    private long novReceiptNo;

    private int novYear;

    private long decFees;

    private String decFeeType = StringUtils.EMPTY;

    private String decDate= StringUtils.EMPTY;

    private long decReceiptNo;

    private int decYear;

    private long annualFees;

    private String annualFeeType = "ANNUAL";

    private String annualDate= StringUtils.EMPTY;

    private long annualReceiptNo;

    private int annualYear;

    private long auditFees;

    private String auditFeeType = "AUDIT";

    private String auditDate= StringUtils.EMPTY;

    private long auditReceiptNo;

    private int auditYear;

    private long miscFees;

    private String miscFeeType = "MISC";

    private String miscDate= StringUtils.EMPTY;

    private long miscReceiptNo;

    private int miscYear;

    private long totalFees;

    private String comments = StringUtils.EMPTY;

    public void evaluateTotalFees() {
        totalFees = janFees + febFees + marFees + aprFees + mayFees + juneFees + julyFees + augFees + sepFees + octFees + novFees + decFees + annualFees + auditFees + miscFees;
    }

}
