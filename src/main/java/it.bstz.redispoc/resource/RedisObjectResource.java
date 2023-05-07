package it.bstz.redispoc.resource;

import io.smallrye.mutiny.Uni;
import it.bstz.redispoc.pojo.RedisObject;
import it.bstz.redispoc.service.RedisObjectService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/redis-object")
public class RedisObjectResource {
    @Inject
    RedisObjectService redisObjectService;

    @GET
    public Uni<List<String>> keys() {
        return redisObjectService.keys();
    }

    @POST
    public RedisObject create(RedisObject object) {
        redisObjectService.set(object.getKey(), object.getValue());
        return object;
    }

    @GET
    @Path("/{key}")
    public RedisObject get(@PathParam("key") String key) {
        return new RedisObject(key, redisObjectService.get(key));
    }

    @PUT
    @Path("/{key}")
    public Uni<Long> increment(String key, long value) {
        return redisObjectService.incrementBy(key, value);
    }

    @DELETE
    @Path("/{key}")
    public Uni<Void> delete(String key) {
        return redisObjectService.del(key);
    }

}
