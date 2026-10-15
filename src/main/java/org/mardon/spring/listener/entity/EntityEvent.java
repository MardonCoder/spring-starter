package org.mardon.spring.listener.entity;

import lombok.Getter;

import java.util.EventObject;

public class EntityEvent extends EventObject {

    @Getter
    private final AccessType accessType;

    public EntityEvent(Object source, AccessType acessType) {
        super(source);
        this.accessType = acessType;
    }
}
