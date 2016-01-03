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

public class _Glacier2SessionTie extends _Glacier2SessionDisp implements Ice.TieBase
{
    public _Glacier2SessionTie()
    {
    }

    public _Glacier2SessionTie(_Glacier2SessionOperations delegate)
    {
        _ice_delegate = delegate;
    }

    public java.lang.Object ice_delegate()
    {
        return _ice_delegate;
    }

    public void ice_delegate(java.lang.Object delegate)
    {
        _ice_delegate = (_Glacier2SessionOperations)delegate;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        if(!(rhs instanceof _Glacier2SessionTie))
        {
            return false;
        }

        return _ice_delegate.equals(((_Glacier2SessionTie)rhs)._ice_delegate);
    }

    public int hashCode()
    {
        return _ice_delegate.hashCode();
    }

    public void destroy(Ice.Current __current)
    {
        _ice_delegate.destroy(__current);
    }

    public uclm.esi.cardroid.zerocice.CardroidManagerPrx getCardroidManager(Ice.Current __current)
    {
        return _ice_delegate.getCardroidManager(__current);
    }

    public IceStorm.TopicPrx getTopic(Ice.Current __current)
    {
        return _ice_delegate.getTopic(__current);
    }

    public void refresh(Ice.Current __current)
    {
        _ice_delegate.refresh(__current);
    }

    private _Glacier2SessionOperations _ice_delegate;

    public static final long serialVersionUID = 199603753L;
}