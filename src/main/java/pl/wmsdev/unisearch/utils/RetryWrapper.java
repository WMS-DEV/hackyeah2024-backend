package pl.wmsdev.unisearch.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;
import java.util.function.Supplier;

@Slf4j
public final class RetryWrapper {

    public static  <T> T retryOrElse(int count, Supplier<T> actionSupplier, Supplier<T> orElseSupplier) {
        for(int i = 0; i < count; i++) {
            try {
                return actionSupplier.get();
            } catch (Throwable t) {
                log.warn("Retry wrapper failed, retrying for the %d time".formatted(i+1), t);
            }
        }
        return orElseSupplier.get();
    }

    public static <T> T retryIf(int count, Supplier<T> actionSupplier, Supplier<T> orElseSupplier,
                                Predicate<T> acceptancePredicate) {
        for(int i = 0; i < count; i++) {
            try {
                T result = actionSupplier.get();
                if(acceptancePredicate.test(result)) {
                    return result;
                } else {
                    log.warn("Acceptance predicate failed - throwing exception...");
                    throw new RuntimeException("Failed to test a predicate");
                }
            } catch (Throwable t) {
                log.warn("Retry wrapper failed, retrying for the %d time".formatted(i+1), t);
            }
        }
        return orElseSupplier.get();
    }


}
