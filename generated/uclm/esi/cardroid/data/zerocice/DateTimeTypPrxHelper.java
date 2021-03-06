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

public final class DateTimeTypPrxHelper extends Ice.ObjectPrxHelperBase implements DateTimeTypPrx
{
    private static final String __getTimeInMillis_name = "getTimeInMillis";

    public long getTimeInMillis()
    {
        return getTimeInMillis(null, false);
    }

    public long getTimeInMillis(java.util.Map<String, String> __ctx)
    {
        return getTimeInMillis(__ctx, true);
    }

    private long getTimeInMillis(java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        final Ice.Instrumentation.InvocationObserver __observer = IceInternal.ObserverHelper.get(this, "getTimeInMillis", __ctx);
        int __cnt = 0;
        try
        {
            while(true)
            {
                Ice._ObjectDel __delBase = null;
                try
                {
                    __checkTwowayOnly("getTimeInMillis");
                    __delBase = __getDelegate(false);
                    _DateTimeTypDel __del = (_DateTimeTypDel)__delBase;
                    return __del.getTimeInMillis(__ctx, __observer);
                }
                catch(IceInternal.LocalExceptionWrapper __ex)
                {
                    __cnt = __handleExceptionWrapperRelaxed(__delBase, __ex, null, __cnt, __observer);
                }
                catch(Ice.LocalException __ex)
                {
                    __cnt = __handleException(__delBase, __ex, null, __cnt, __observer);
                }
            }
        }
        finally
        {
            if(__observer != null)
            {
                __observer.detach();
            }
        }
    }

    public Ice.AsyncResult begin_getTimeInMillis()
    {
        return begin_getTimeInMillis(null, false, null);
    }

    public Ice.AsyncResult begin_getTimeInMillis(java.util.Map<String, String> __ctx)
    {
        return begin_getTimeInMillis(__ctx, true, null);
    }

    public Ice.AsyncResult begin_getTimeInMillis(Ice.Callback __cb)
    {
        return begin_getTimeInMillis(null, false, __cb);
    }

    public Ice.AsyncResult begin_getTimeInMillis(java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_getTimeInMillis(__ctx, true, __cb);
    }

    public Ice.AsyncResult begin_getTimeInMillis(Callback_DateTyp_getTimeInMillis __cb)
    {
        return begin_getTimeInMillis(null, false, __cb);
    }

    public Ice.AsyncResult begin_getTimeInMillis(java.util.Map<String, String> __ctx, Callback_DateTyp_getTimeInMillis __cb)
    {
        return begin_getTimeInMillis(__ctx, true, __cb);
    }

    private Ice.AsyncResult begin_getTimeInMillis(java.util.Map<String, String> __ctx, boolean __explicitCtx, IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__getTimeInMillis_name);
        IceInternal.OutgoingAsync __result = new IceInternal.OutgoingAsync(this, __getTimeInMillis_name, __cb);
        try
        {
            __result.__prepare(__getTimeInMillis_name, Ice.OperationMode.Idempotent, __ctx, __explicitCtx);
            __result.__writeEmptyParams();
            __result.__send(true);
        }
        catch(Ice.LocalException __ex)
        {
            __result.__exceptionAsync(__ex);
        }
        return __result;
    }

    public long end_getTimeInMillis(Ice.AsyncResult __result)
    {
        Ice.AsyncResult.__check(__result, this, __getTimeInMillis_name);
        boolean __ok = __result.__wait();
        try
        {
            if(!__ok)
            {
                try
                {
                    __result.__throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.__startReadParams();
            long __ret;
            __ret = __is.readLong();
            __result.__endReadParams();
            return __ret;
        }
        catch(Ice.LocalException ex)
        {
            Ice.Instrumentation.InvocationObserver __obsv = __result.__getObserver();
            if(__obsv != null)
            {
                __obsv.failed(ex.ice_name());
            }
            throw ex;
        }
    }

    private static final String __setTimeInMillis_name = "setTimeInMillis";

    public void setTimeInMillis(long datetime)
    {
        setTimeInMillis(datetime, null, false);
    }

    public void setTimeInMillis(long datetime, java.util.Map<String, String> __ctx)
    {
        setTimeInMillis(datetime, __ctx, true);
    }

    private void setTimeInMillis(long datetime, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        final Ice.Instrumentation.InvocationObserver __observer = IceInternal.ObserverHelper.get(this, "setTimeInMillis", __ctx);
        int __cnt = 0;
        try
        {
            while(true)
            {
                Ice._ObjectDel __delBase = null;
                try
                {
                    __delBase = __getDelegate(false);
                    _DateTimeTypDel __del = (_DateTimeTypDel)__delBase;
                    __del.setTimeInMillis(datetime, __ctx, __observer);
                    return;
                }
                catch(IceInternal.LocalExceptionWrapper __ex)
                {
                    __cnt = __handleExceptionWrapperRelaxed(__delBase, __ex, null, __cnt, __observer);
                }
                catch(Ice.LocalException __ex)
                {
                    __cnt = __handleException(__delBase, __ex, null, __cnt, __observer);
                }
            }
        }
        finally
        {
            if(__observer != null)
            {
                __observer.detach();
            }
        }
    }

    public Ice.AsyncResult begin_setTimeInMillis(long datetime)
    {
        return begin_setTimeInMillis(datetime, null, false, null);
    }

    public Ice.AsyncResult begin_setTimeInMillis(long datetime, java.util.Map<String, String> __ctx)
    {
        return begin_setTimeInMillis(datetime, __ctx, true, null);
    }

    public Ice.AsyncResult begin_setTimeInMillis(long datetime, Ice.Callback __cb)
    {
        return begin_setTimeInMillis(datetime, null, false, __cb);
    }

    public Ice.AsyncResult begin_setTimeInMillis(long datetime, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_setTimeInMillis(datetime, __ctx, true, __cb);
    }

    public Ice.AsyncResult begin_setTimeInMillis(long datetime, Callback_DateTyp_setTimeInMillis __cb)
    {
        return begin_setTimeInMillis(datetime, null, false, __cb);
    }

    public Ice.AsyncResult begin_setTimeInMillis(long datetime, java.util.Map<String, String> __ctx, Callback_DateTyp_setTimeInMillis __cb)
    {
        return begin_setTimeInMillis(datetime, __ctx, true, __cb);
    }

    private Ice.AsyncResult begin_setTimeInMillis(long datetime, java.util.Map<String, String> __ctx, boolean __explicitCtx, IceInternal.CallbackBase __cb)
    {
        IceInternal.OutgoingAsync __result = new IceInternal.OutgoingAsync(this, __setTimeInMillis_name, __cb);
        try
        {
            __result.__prepare(__setTimeInMillis_name, Ice.OperationMode.Idempotent, __ctx, __explicitCtx);
            IceInternal.BasicStream __os = __result.__startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeLong(datetime);
            __result.__endWriteParams();
            __result.__send(true);
        }
        catch(Ice.LocalException __ex)
        {
            __result.__exceptionAsync(__ex);
        }
        return __result;
    }

    public void end_setTimeInMillis(Ice.AsyncResult __result)
    {
        __end(__result, __setTimeInMillis_name);
    }

    private static final String __toString_name = "toString";

    public String _toString()
    {
        return _toString(null, false);
    }

    public String _toString(java.util.Map<String, String> __ctx)
    {
        return _toString(__ctx, true);
    }

    private String _toString(java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        final Ice.Instrumentation.InvocationObserver __observer = IceInternal.ObserverHelper.get(this, "_toString", __ctx);
        int __cnt = 0;
        try
        {
            while(true)
            {
                Ice._ObjectDel __delBase = null;
                try
                {
                    __checkTwowayOnly("_toString");
                    __delBase = __getDelegate(false);
                    _DateTimeTypDel __del = (_DateTimeTypDel)__delBase;
                    return __del._toString(__ctx, __observer);
                }
                catch(IceInternal.LocalExceptionWrapper __ex)
                {
                    __cnt = __handleExceptionWrapperRelaxed(__delBase, __ex, null, __cnt, __observer);
                }
                catch(Ice.LocalException __ex)
                {
                    __cnt = __handleException(__delBase, __ex, null, __cnt, __observer);
                }
            }
        }
        finally
        {
            if(__observer != null)
            {
                __observer.detach();
            }
        }
    }

    public Ice.AsyncResult begin_toString()
    {
        return begin_toString(null, false, null);
    }

    public Ice.AsyncResult begin_toString(java.util.Map<String, String> __ctx)
    {
        return begin_toString(__ctx, true, null);
    }

    public Ice.AsyncResult begin_toString(Ice.Callback __cb)
    {
        return begin_toString(null, false, __cb);
    }

    public Ice.AsyncResult begin_toString(java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_toString(__ctx, true, __cb);
    }

    public Ice.AsyncResult begin_toString(Callback_DateTyp_toString __cb)
    {
        return begin_toString(null, false, __cb);
    }

    public Ice.AsyncResult begin_toString(java.util.Map<String, String> __ctx, Callback_DateTyp_toString __cb)
    {
        return begin_toString(__ctx, true, __cb);
    }

    private Ice.AsyncResult begin_toString(java.util.Map<String, String> __ctx, boolean __explicitCtx, IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__toString_name);
        IceInternal.OutgoingAsync __result = new IceInternal.OutgoingAsync(this, __toString_name, __cb);
        try
        {
            __result.__prepare(__toString_name, Ice.OperationMode.Idempotent, __ctx, __explicitCtx);
            __result.__writeEmptyParams();
            __result.__send(true);
        }
        catch(Ice.LocalException __ex)
        {
            __result.__exceptionAsync(__ex);
        }
        return __result;
    }

    public String end_toString(Ice.AsyncResult __result)
    {
        Ice.AsyncResult.__check(__result, this, __toString_name);
        boolean __ok = __result.__wait();
        try
        {
            if(!__ok)
            {
                try
                {
                    __result.__throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.__startReadParams();
            String __ret;
            __ret = __is.readString();
            __result.__endReadParams();
            return __ret;
        }
        catch(Ice.LocalException ex)
        {
            Ice.Instrumentation.InvocationObserver __obsv = __result.__getObserver();
            if(__obsv != null)
            {
                __obsv.failed(ex.ice_name());
            }
            throw ex;
        }
    }

    public static DateTimeTypPrx checkedCast(Ice.ObjectPrx __obj)
    {
        DateTimeTypPrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof DateTimeTypPrx)
            {
                __d = (DateTimeTypPrx)__obj;
            }
            else
            {
                if(__obj.ice_isA(ice_staticId()))
                {
                    DateTimeTypPrxHelper __h = new DateTimeTypPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static DateTimeTypPrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        DateTimeTypPrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof DateTimeTypPrx)
            {
                __d = (DateTimeTypPrx)__obj;
            }
            else
            {
                if(__obj.ice_isA(ice_staticId(), __ctx))
                {
                    DateTimeTypPrxHelper __h = new DateTimeTypPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static DateTimeTypPrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        DateTimeTypPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA(ice_staticId()))
                {
                    DateTimeTypPrxHelper __h = new DateTimeTypPrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static DateTimeTypPrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        DateTimeTypPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA(ice_staticId(), __ctx))
                {
                    DateTimeTypPrxHelper __h = new DateTimeTypPrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static DateTimeTypPrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        DateTimeTypPrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof DateTimeTypPrx)
            {
                __d = (DateTimeTypPrx)__obj;
            }
            else
            {
                DateTimeTypPrxHelper __h = new DateTimeTypPrxHelper();
                __h.__copyFrom(__obj);
                __d = __h;
            }
        }
        return __d;
    }

    public static DateTimeTypPrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        DateTimeTypPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            DateTimeTypPrxHelper __h = new DateTimeTypPrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::cardroid::data::zerocice::DateTimeTyp",
        "::cardroid::data::zerocice::DateTyp"
    };

    public static String ice_staticId()
    {
        return __ids[1];
    }

    protected Ice._ObjectDelM __createDelegateM()
    {
        return new _DateTimeTypDelM();
    }

    protected Ice._ObjectDelD __createDelegateD()
    {
        return new _DateTimeTypDelD();
    }

    public static void __write(IceInternal.BasicStream __os, DateTimeTypPrx v)
    {
        __os.writeProxy(v);
    }

    public static DateTimeTypPrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            DateTimeTypPrxHelper result = new DateTimeTypPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}
