package dev.almendro.microservices.productcatalog.domain.model.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class DomainEvents {
    private static Map<String, Set<Consumer>> listeners = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(DomainEvents.class);

    public static <T> void subscribe(Consumer<T> listener, Class<T> tClass) {
        String key = tClass.getSimpleName();
        if (!listeners.containsKey(key)) {
            listeners.put(key, new HashSet<Consumer>());
        }
        listeners.get(key).add(listener);
    }

    public static <T> void publish(T args) {
        String key = args.getClass().getSimpleName();
        logger.info("publishing event: " + key);
        if (listeners.containsKey(key)) {
            listeners.get(key).forEach(x -> x.accept(args));
        }
    }
}