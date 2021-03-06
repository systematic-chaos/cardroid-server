// **********************************************************************
//
// Copyright (c) 2003-2013 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.5.1
//
// <auto-generated>
//
// Generated from file `Session.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package uclm.esi.cardroid.network.zerocice;

public class _SessionFactoryTie extends _SessionFactoryDisp implements Ice.TieBase
{
    public _SessionFactoryTie()
    {
    }

    public _SessionFactoryTie(_SessionFactoryOperations delegate)
    {
        _ice_delegate = delegate;
    }

    public java.lang.Object ice_delegate()
    {
        return _ice_delegate;
    }

    public void ice_delegate(java.lang.Object delegate)
    {
        _ice_delegate = (_SessionFactoryOperations)delegate;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        if(!(rhs instanceof _SessionFactoryTie))
        {
            return false;
        }

        return _ice_delegate.equals(((_SessionFactoryTie)rhs)._ice_delegate);
    }

    public int hashCode()
    {
        return _ice_delegate.hashCode();
    }

    public SessionPrx create(Ice.Current __current)
    {
        return _ice_delegate.create(__current);
    }

    public long getSessionTimeout(Ice.Current __current)
    {
        return _ice_delegate.getSessionTimeout(__current);
    }

    private _SessionFactoryOperations _ice_delegate;

    public static final long serialVersionUID = -472770493L;
}
