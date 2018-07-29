package com.ohraymaster.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.ohraymaster.guava.cache.domain.Profile;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by ray on 18-7-29.
 */
public class ProfileCache {
    private static final long MAX_SIZE = 100;

    private static class CacheHolder {
        private final static LoadingCache cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<String, Profile>() {
                            @Override
                            public Profile load(String key) throws Exception {
                                return loadBykey(key);
                            }

                        }
                );
    }

    private static LoadingCache<String, Profile> getCache() {
        return CacheHolder.cache;
    }

    private static Profile loadBykey(String key) {
        return new Profile(key);
    }

    public static Profile get(String key) throws ExecutionException {
        return getCache().get(key);
    }

    public static void main(String[] args) throws ExecutionException {
        Profile p1 = ProfileCache.get("key3");
        Profile p1_cp = ProfileCache.get("key3");

        System.out.println(p1.getKey());
        System.out.println(p1_cp.getKey());
        System.out.println(p1);
        System.out.println(p1_cp);
        System.out.println(p1 == p1_cp);

    }
}

