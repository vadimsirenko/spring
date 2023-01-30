package ru.vasire.models;

public enum Mood {
    HAPPY ("Счастливый"),
    SAD ("Грустный"),
    UNGRY("Злой"),
    WORRIED("Взволнованный"),
    CALM ("Спокойный");

    private final String displayValue;

    private Mood(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
