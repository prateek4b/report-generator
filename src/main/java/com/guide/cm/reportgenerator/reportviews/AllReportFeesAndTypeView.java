package com.guide.cm.reportgenerator.reportviews;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
public class AllReportFeesAndTypeView {

    private long id;

    private String name;

    private String gstNo;

    private String scheme;

    private long janFees;

    private String janFeeType = StringUtils.EMPTY;

    private long febFees;

    private String febFeeType = StringUtils.EMPTY;

    private long marFees;

    private String marFeeType = StringUtils.EMPTY;

    private long aprFees;

    private String aprFeeType = StringUtils.EMPTY;

    private long mayFees;

    private String mayFeeType = StringUtils.EMPTY;

    private long juneFees;

    private String juneFeeType = StringUtils.EMPTY;

    private long julyFees;

    private String julyFeeType = StringUtils.EMPTY;

    private long augFees;

    private String augFeeType = StringUtils.EMPTY;

    private long sepFees;

    private String sepFeeType = StringUtils.EMPTY;

    private long octFees;

    private String octFeeType = StringUtils.EMPTY;

    private long novFees;

    private String novFeeType = StringUtils.EMPTY;

    private long decFees;

    private String decFeeType = StringUtils.EMPTY;

    private long annualFees;

    private String annualFeeType = "ANNUAL";

    private long auditFees;

    private String auditFeeType = "AUDIT";

    private long miscFees;

    private String miscFeeType = "MISC";

    private long totalFees;

    private String comments = StringUtils.EMPTY;


    public void evaluateTotalFees() {
        totalFees = janFees + febFees + marFees + aprFees + mayFees + juneFees + julyFees + augFees + sepFees + octFees + novFees + decFees + annualFees + auditFees + miscFees;
    }


}
