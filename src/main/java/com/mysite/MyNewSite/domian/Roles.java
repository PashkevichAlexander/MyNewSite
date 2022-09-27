package com.mysite.MyNewSite.domian;

public enum Roles {
    USER("ROLE_USER",2),

    ADMIN("ROLE_ADMIN",1),

    EDITOR("ROLE_EDITOR",3);



    private final String name;
    private final int id;

    Roles(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
