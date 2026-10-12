package org.mardon.spring.listener.entity;

import java.util.EventObject;

public class EntityEvent extends EventObject {

    private final AccessType accessType;

    public EntityEvent(Object source, AccessType acessType) {
        super(source);
        this.accessType = acessType;
    }

    public AccessType getAccessType() {
        return accessType;
    }
}
