package com.cache.cachemanager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CacheManager {

    private static Map<Object, Object> cachedMap = new HashMap<>();

    static {

        try {

            Thread threadCleanerUpper = new Thread(
                    new Runnable() {
                        int milliseconds = 500;

                        @Override
                        public void run() {
                            while (true) {
                                System.out.println("Scanning is started");
                                Set keySet = cachedMap.keySet();
                                Iterator keys = keySet.iterator();
                                while (keys.hasNext()){
                                    Object key = keys.next();
                                    Cacheable object = (Cacheable) cachedMap.get(key);
                                    if(object.isExpired()){
                                        cachedMap.remove(key);
                                        System.out.println("found an expired object");
                                    }
                                }
                                try {
                                    Thread.sleep(milliseconds);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }
            );

            threadCleanerUpper.start();
            threadCleanerUpper.setPriority(Thread.MIN_PRIORITY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void putObject(CachedObject object){
        cachedMap.put(object.getIdentifire(),object);
    }
    public static Cacheable getObject(Object identifire){
        Cacheable object = (Cacheable)cachedMap.get(identifire);
        if(object==null){
            return null;
        }
        if(object.isExpired()){
            cachedMap.remove(identifire);
            return null;
        }else
            return object;


    }
}
