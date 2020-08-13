package edu.miu.ebuy.common.enums;

public enum RoleEnum {
    ADMIN(1),
    VENDOR(2),
    REGESTERED(3);

    public final int id;

    private RoleEnum(int id) {
        this.id = id;
    }
}
