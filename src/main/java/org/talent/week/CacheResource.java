package org.talent.week;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.redis.client.Response;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/cache")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CacheResource {

  @Inject
  CacheRepository repository;

  @GET
  public Uni<List<String>> keys() {
    return repository.keys();
  }

  @GET
  @Path("/{key}")
  public Uni<Integer> get(@PathParam("key") String key) {
    return repository.get(key);
  }

  @PUT
  @Path("/{key}")
  public Uni<String> set(@PathParam("key") String key, Integer value) {
    return repository.set(key, value).map(Response::toString);
  }

  @DELETE
  @Path("/{key}")
  public Uni<String> delete(@PathParam("key") String key) {
    return repository.del(key).map(Response::toString);
  }
}