package de.dentrassi.pm.storage.service;

public class MetaKey
{
    private final String namespace;

    private final String key;

    public MetaKey ( final String namespace, final String key )
    {
        this.namespace = namespace;
        this.key = key;
    }

    public String getKey ()
    {
        return this.key;
    }

    public String getNamespace ()
    {
        return this.namespace;
    }

    @Override
    public int hashCode ()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( this.key == null ? 0 : this.key.hashCode () );
        result = prime * result + ( this.namespace == null ? 0 : this.namespace.hashCode () );
        return result;
    }

    @Override
    public boolean equals ( final Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null )
        {
            return false;
        }
        if ( ! ( obj instanceof MetaKey ) )
        {
            return false;
        }
        final MetaKey other = (MetaKey)obj;
        if ( this.key == null )
        {
            if ( other.key != null )
            {
                return false;
            }
        }
        else if ( !this.key.equals ( other.key ) )
        {
            return false;
        }
        if ( this.namespace == null )
        {
            if ( other.namespace != null )
            {
                return false;
            }
        }
        else if ( !this.namespace.equals ( other.namespace ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString ()
    {
        return "[" + this.namespace + ":" + this.key + "]";
    }
}