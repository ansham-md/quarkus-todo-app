package com.quarkus.todo.constants;

public enum ProvidersConstants {

    HSBC("HSBC Bank"),
    BCY("Barclays Bank"),
    NW("NatWest");

    private final String displayName;

    ProvidersConstants(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}

