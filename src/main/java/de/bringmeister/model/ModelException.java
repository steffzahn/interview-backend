package de.bringmeister.model;

public class ModelException extends RuntimeException
{
    public ModelException(String msg)
    {
        super(msg);
    }
    public ModelException(String msg, Throwable e)
    {
        super(msg, e);
    }
}
