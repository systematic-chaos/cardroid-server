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

package uclm.esi.cardroid.zerocice;

public abstract class Callback_CardroidManager_getCarFromPlate extends Ice.TwowayCallback
{
    public abstract void response(uclm.esi.cardroid.data.zerocice.CarTypPrx __ret);

    public final void __completed(Ice.AsyncResult __result)
    {
        CardroidManagerPrx __proxy = (CardroidManagerPrx)__result.getProxy();
        uclm.esi.cardroid.data.zerocice.CarTypPrx __ret = null;
        try
        {
            __ret = __proxy.end_getCarFromPlate(__result);
        }
        catch(Ice.LocalException __ex)
        {
            exception(__ex);
            return;
        }
        response(__ret);
    }
}
