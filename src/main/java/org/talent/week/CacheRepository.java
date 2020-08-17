package org.talent.week;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.redis.client.Response;
import java.util.List;

public interface CacheRepository {

  Uni<Response> del(String key);

  Uni<Integer> get(String key);

  Uni<Response> set(String key, Integer value);

  Uni<List<String>> keys();
}
