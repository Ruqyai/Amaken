package com.amakenapp.website.amakenapp.store;

/**
 * Created by sondos on 05/03/2017.
 */

public class Event_Date {

    private int id;
    private long start_date;
    private long end_date;
    private long start_time;
    private long end_time;
    private String days;
    private long time_stamp;

    public Event_Date( int id, long start_date, long end_date,
                       long start_time, long end_time, String days, long time_stamp ){
        this.id=id;
        this.start_date=start_date;
        this.end_date=end_date;
        this.start_time=start_time;
        this.end_time=end_time;
        this.days=days;
        this.time_stamp=time_stamp;
    }
    public int getId(){
        return id;
    }
    public long getStart_date(){return start_date;}
    public long getEnd_date(){return end_date;}
    public long getStart_time(){return start_time; }
    public long getEnd_time(){ return  end_time;}
    public String getDays(){ return  days; }
    public long getTime_stamp(){return time_stamp; }
}
