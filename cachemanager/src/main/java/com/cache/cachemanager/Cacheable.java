package com.cache.cachemanager;

public interface Cacheable {

    boolean isExpired();
    Object getIdentifire();
}
