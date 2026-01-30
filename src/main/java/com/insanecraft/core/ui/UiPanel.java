package com.insanecraft.core.ui;

import java.util.List;

/**
 * Lightweight UI summary model for the demo systems.
 */
public record UiPanel(String title, String description, List<UiLine> lines, List<String> actions) {
    public UiPanel(String title, String description, List<UiLine> lines, List<String> actions) {
        this.title = title;
        this.description = description;
        this.lines = List.copyOf(lines);
        this.actions = List.copyOf(actions);
    }
}
