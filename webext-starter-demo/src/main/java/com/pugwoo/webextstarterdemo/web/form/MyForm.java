package com.pugwoo.webextstarterdemo.web.form;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class MyForm {

    private Date myDate;

    private LocalDate myLocalDate;

    private LocalDateTime myLocalDateTime;

    private LocalTime myLocalTime;

    public Date getMyDate() {
        return myDate;
    }

    public void setMyDate(Date myDate) {
        this.myDate = myDate;
    }

    public LocalDate getMyLocalDate() {
        return myLocalDate;
    }

    public void setMyLocalDate(LocalDate myLocalDate) {
        this.myLocalDate = myLocalDate;
    }

    public LocalDateTime getMyLocalDateTime() {
        return myLocalDateTime;
    }

    public void setMyLocalDateTime(LocalDateTime myLocalDateTime) {
        this.myLocalDateTime = myLocalDateTime;
    }

    public LocalTime getMyLocalTime() {
        return myLocalTime;
    }

    public void setMyLocalTime(LocalTime myLocalTime) {
        this.myLocalTime = myLocalTime;
    }
}
