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

public abstract class WaypointTyp extends Ice.ObjectImpl
                                  implements _WaypointTypOperations,
                                             _WaypointTypOperationsNC
{
    public WaypointTyp()
    {
    }

    public WaypointTyp(int nOrder, PlaceTyp waypointPlace)
    {
        this.nOrder = nOrder;
        this.waypointPlace = waypointPlace;
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::cardroid::data::zerocice::WaypointTyp"
    };

    public boolean ice_isA(String s)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean ice_isA(String s, Ice.Current __current)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[] ice_ids()
    {
        return __ids;
    }

    public String[] ice_ids(Ice.Current __current)
    {
        return __ids;
    }

    public String ice_id()
    {
        return __ids[1];
    }

    public String ice_id(Ice.Current __current)
    {
        return __ids[1];
    }

    public static String ice_staticId()
    {
        return __ids[1];
    }

    public final int getNOrder()
    {
        return getNOrder(null);
    }

    public final PlaceTyp getWaypointPlace()
    {
        return getWaypointPlace(null);
    }

    public final void setNOrder(int nOrder)
    {
        setNOrder(nOrder, null);
    }

    public final void setWaypointPlace(PlaceTyp waypointPlace)
    {
        setWaypointPlace(waypointPlace, null);
    }

    public final String _toString()
    {
        return _toString(null);
    }

    public static Ice.DispatchStatus ___getNOrder(WaypointTyp __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        __inS.readEmptyParams();
        int __ret = __obj.getNOrder(__current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeInt(__ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___setNOrder(WaypointTyp __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        int nOrder;
        nOrder = __is.readInt();
        __inS.endReadParams();
        __obj.setNOrder(nOrder, __current);
        __inS.__writeEmptyParams();
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getWaypointPlace(WaypointTyp __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        __inS.readEmptyParams();
        PlaceTyp __ret = __obj.getWaypointPlace(__current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeObject(__ret);
        __os.writePendingObjects();
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___setWaypointPlace(WaypointTyp __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        PlaceTypHolder waypointPlace = new PlaceTypHolder();
        __is.readObject(waypointPlace);
        __is.readPendingObjects();
        __inS.endReadParams();
        __obj.setWaypointPlace(waypointPlace.value, __current);
        __inS.__writeEmptyParams();
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___toString(WaypointTyp __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        __inS.readEmptyParams();
        String __ret = __obj._toString(__current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeString(__ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
        "getNOrder",
        "getWaypointPlace",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "setNOrder",
        "setWaypointPlace",
        "toString"
    };

    public Ice.DispatchStatus __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
        int pos = java.util.Arrays.binarySearch(__all, __current.operation);
        if(pos < 0)
        {
            throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return ___getNOrder(this, in, __current);
            }
            case 1:
            {
                return ___getWaypointPlace(this, in, __current);
            }
            case 2:
            {
                return ___ice_id(this, in, __current);
            }
            case 3:
            {
                return ___ice_ids(this, in, __current);
            }
            case 4:
            {
                return ___ice_isA(this, in, __current);
            }
            case 5:
            {
                return ___ice_ping(this, in, __current);
            }
            case 6:
            {
                return ___setNOrder(this, in, __current);
            }
            case 7:
            {
                return ___setWaypointPlace(this, in, __current);
            }
            case 8:
            {
                return ___toString(this, in, __current);
            }
        }

        assert(false);
        throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
    }

    protected void __writeImpl(IceInternal.BasicStream __os)
    {
        __os.startWriteSlice(ice_staticId(), -1, true);
        __os.writeInt(nOrder);
        __os.writeObject(waypointPlace);
        __os.endWriteSlice();
    }

    private class Patcher implements IceInternal.Patcher
    {
        public void
        patch(Ice.Object v)
        {
            if(v == null || v instanceof PlaceTyp)
            {
                waypointPlace = (PlaceTyp)v;
            }
            else
            {
                IceInternal.Ex.throwUOE(type(), v);
            }
        }

        public String
        type()
        {
            return "::cardroid::data::zerocice::PlaceTyp";
        }
    }

    protected void __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        nOrder = __is.readInt();
        __is.readObject(new Patcher());
        __is.endReadSlice();
    }

    public int nOrder;

    public PlaceTyp waypointPlace;

    public static final long serialVersionUID = 1952798701L;
}
