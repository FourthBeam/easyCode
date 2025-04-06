package com.fourthBeam.doubleWrite;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanCache {
    public static Map<String, Pair<Object, Object>> cache = new ConcurrentHashMap<>();
}
