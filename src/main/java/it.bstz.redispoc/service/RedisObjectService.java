package it.bstz.redispoc.service;

import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.value.ReactiveValueCommands;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class RedisObjectService {

    private ReactiveKeyCommands<String> keyCommands;
    private ReactiveValueCommands<String, Long> valueCommands;

    public RedisObjectService(ReactiveRedisDataSource dataSource) {
        this.keyCommands = dataSource.key();
        this.valueCommands = dataSource.value(Long.class);
    }

    public Uni<Long> get(String key) {
        Uni<Long> value = valueCommands.get(key);
        if (value == null) {
            return Uni.createFrom().item(0L);
        }
        return value;
    }

    public Uni<Void> set(String key, Long value) {
        return valueCommands.set(key, value);
    }

    public Uni<Long> incrementBy(String key, long incrementBy) {
        return valueCommands.incrby(key, incrementBy);
    }

    public Uni<Void> del(String key) {
        return keyCommands.del(key).replaceWithVoid();
    }

    public Uni<List<String>> keys() {
        return keyCommands.keys("*");
    }
}
