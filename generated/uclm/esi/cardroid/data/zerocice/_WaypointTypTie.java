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
// Generated from file `Cardroid.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package uclm.esi.cardroid.data.zerocice;

public class _WaypointTypTie extends WaypointTyp implements Ice.TieBase
{
    public _WaypointTypTie()
    {
    }

    public _WaypointTypTie(_WaypointTypOperations delegate)
    {
        _ice_delegate = delegate;
    }

    public java.lang.Object ice_delegate()
    {
        return _ice_delegate;
    }

    public void ice_delegate(java.lang.Object delegate)
    {
        _ice_delegate = (_WaypointTypOperations)delegate;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        if(!(rhs instanceof _WaypointTypTie))
        {
            return false;
        }

        return _ice_delegate.equals(((_WaypointTypTie)rhs)._ice_delegate);
    }

    public int hashCode()
    {
        return _ice_delegate.hashCode();
    }

    public int getNOrder(Ice.Current __current)
    {
        return _ice_delegate.getNOrder(__current);
    }

    public PlaceTyp getWaypointPlace(Ice.Current __current)
    {
        return _ice_delegate.getWaypointPlace(__current);
    }

    public void setNOrder(int nOrder, Ice.Current __current)
    {
        _ice_delegate.setNOrder(nOrder, __current);
    }

    public void setWaypointPlace(PlaceTyp waypointPlace, Ice.Current __current)
    {
        _ice_delegate.setWaypointPlace(waypointPlace, __current);
    }

    public String _toString(Ice.Current __current)
    {
        return _ice_delegate._toString(__current);
    }

    private _WaypointTypOperations _ice_delegate;

    public static final long serialVersionUID = 1952798701L;
}