package com.guide.cm.reportgenerator.reportviews;

import com.guide.cm.reportgenerator.proxy.FeeEntry;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllReportFeesView {

    private long id;

    private String name;

    private String gstNo;

    private String scheme;

    private long janFees;

    private long febFees;

    private long marFees;

    private long aprFees;

    private long mayFees;

    private long juneFees;

    private long julyFees;

    private long augFees;

    private long sepFees;

    private long octFees;

    private long novFees;

    private long decFees;

    private long annualFees;

    private long auditFees;

    private long miscFees;

    private long totalFees;

    private String comments;

    public void evaluateTotalFees()
    {
        totalFees=janFees+ febFees+marFees+aprFees+mayFees+juneFees+julyFees+augFees+sepFees+octFees+novFees+decFees+annualFees+auditFees+miscFees;
    }
}
