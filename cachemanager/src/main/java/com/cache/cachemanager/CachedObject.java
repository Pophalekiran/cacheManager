package com.cache.cachemanager;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;
@Data
@AllArgsConstructor
public class CachedObject implements Cacheable {

    private Date dateofexpiration;
    private Object value;
    private Object identifire;

    public CachedObject(Object value, Object identifire, int minutesToLive) {
        this.value = value;
        this.identifire = identifire;
        if(minutesToLive!=0){
            dateofexpiration = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateofexpiration);
            calendar.add(calendar.SECOND,minutesToLive);
            dateofexpiration = calendar.getTime();
        }

        this.minutesToLive = minutesToLive;
    }

    private int minutesToLive;
    @Override
    public boolean isExpired() {
       if(dateofexpiration!=null){
            if(dateofexpiration.before(new Date())){
                System.out.println("Object is expired");
                return true;
            }else{
                System.out.println("Object is not expired");
                return false;
            }
       }

       return false;
    }

    @Override
    public Object getIdentifire() {
        return identifire;
    }
}
