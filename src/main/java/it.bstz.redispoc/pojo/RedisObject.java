package it.bstz.redispoc.pojo;

import io.smallrye.mutiny.Uni;

public class RedisObject {
    private String key;
    private long value;

    public RedisObject(String key, long value) {
        this.key = key;
        this.value = value;
    }

    public RedisObject() {
    }

    public RedisObject(String key, Uni<Long> longUni) {
        this.key = key;
        this.value = longUni.await().indefinitely();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RedisObject{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
