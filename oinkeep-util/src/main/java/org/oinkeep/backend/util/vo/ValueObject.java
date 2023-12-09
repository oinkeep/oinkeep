package org.oinkeep.backend.util.vo;

import java.io.Serializable;

public abstract class ValueObject<T> implements Serializable{

    public abstract T value();

}
