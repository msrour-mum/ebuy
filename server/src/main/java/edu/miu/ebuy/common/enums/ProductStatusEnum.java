package edu.miu.ebuy.common.enums;

public enum ProductStatusEnum {
    Pending(1),
    Approved(2),
    Rejected(3);

    public final int id;

    private ProductStatusEnum(int id) {
        this.id = id;
    }
}
