package com.insanecraft.core.ui;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * Generic action response for UI interactions.
 */
public record UiActionResult<T>(boolean success, String message, Optional<T> payload, OptionalInt xpDelta) {
    public static <T> UiActionResult<T> success(String message, T payload) {
        return new UiActionResult<>(true, message, Optional.ofNullable(payload), OptionalInt.empty());
    }

    public static <T> UiActionResult<T> successWithXp(String message, T payload, int xpDelta) {
        return new UiActionResult<>(true, message, Optional.ofNullable(payload), OptionalInt.of(xpDelta));
    }

    public static <T> UiActionResult<T> failure(String message) {
        return new UiActionResult<>(false, message, Optional.empty(), OptionalInt.empty());
    }
}
