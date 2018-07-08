package com.cache.cachemanager;

public class CacheManagerTestProgram {
    public static void main(String[] args) {
        String str= new String("AVNFJJFJFJFJF");
        CachedObject object = new CachedObject(str,"1234",30);
        CacheManager.putObject(object);

        CachedObject returnObject = (CachedObject)CacheManager.getObject("1234");
        if(returnObject==null){
            System.out.println("Object is not found");
        }else
        {
            System.out.println("Object is found");
        }


    }
}
