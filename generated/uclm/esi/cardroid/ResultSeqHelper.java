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

package uclm.esi.cardroid;

public final class ResultSeqHelper
{
    public static void
    write(IceInternal.BasicStream __os, java.util.List<Ice.ObjectPrx> __v)
    {
        if(__v == null)
        {
            __os.writeSize(0);
        }
        else
        {
            __os.writeSize(__v.size());
            for(Ice.ObjectPrx __elem : __v)
            {
                __os.writeProxy(__elem);
            }
        }
    }

    public static java.util.List<Ice.ObjectPrx>
    read(IceInternal.BasicStream __is)
    {
        java.util.List<Ice.ObjectPrx> __v;
        __v = new java.util.LinkedList<Ice.ObjectPrx>();
        final int __len0 = __is.readAndCheckSeqSize(2);
        for(int __i0 = 0; __i0 < __len0; __i0++)
        {
            Ice.ObjectPrx __elem;
            __elem = __is.readProxy();
            __v.add(__elem);
        }
        return __v;
    }
}
