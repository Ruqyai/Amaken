package com.example.afaf.amakenapp.store;

/**
 * Created by sondos on 05/03/2017.
 */

public class Reviews_Report {

    private int id;
    private int review_id;
    private String report_reason;
    private int reporter_id;

 public Reviews_Report(int id, int review_id, String report_reason, int reporter_id){
     this.id=id;
     this.review_id=review_id;
     this.report_reason=report_reason;
     this.reporter_id=reporter_id;
 }

    public int getId() {
        return id;
    }
    public int getReview_id() {
        return review_id;
    }
    public String getReport_reason(){return  report_reason; }
    public int getReporter_id() {
        return reporter_id;
    }


}
