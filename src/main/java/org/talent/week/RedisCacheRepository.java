package org.talent.week;

import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.redis.client.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RedisCacheRepository implements CacheRepository {

  @Inject
  ReactiveRedisClient reactiveRedisClient;

  @Override
  public Uni<Response> del(String key) {
    return reactiveRedisClient.del(Arrays.asList(key));
  }

  @Override
  public Uni<Integer> get(String key) {
    return reactiveRedisClient.get(key).map(response -> response.toInteger());
  }

  @Override
  public Uni<Response> set(String key, Integer value) {
    return reactiveRedisClient.set(Arrays.asList(key, value.toString()));
  }

  @Override
  public Uni<List<String>> keys() {
    return reactiveRedisClient.keys("*").map(response -> {
      var list = new ArrayList<String>();
      response.spliterator().forEachRemaining(r -> list.add(r.toString()));
      return list;
    });
  }
}
