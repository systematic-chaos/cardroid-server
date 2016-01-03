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

/**
 * The session object. This is used to retrieve a CarDroid session
 * on behalf of the client. If the session is not refreshed on a
 * periodic basis, it will be automatically destroyed.
 * 
 **/
public interface SessionPrx extends Ice.ObjectPrx
{
    /**
     * Get the CarDroid manager object.
     * 
     * @return A proxy for the new CarDroid manager.
     * 
     **/
    public uclm.esi.cardroid.zerocice.CardroidManagerPrx getCardroidManager();

    /**
     * Get the CarDroid manager object.
     * 
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for the new CarDroid manager.
     * 
     **/
    public uclm.esi.cardroid.zerocice.CardroidManagerPrx getCardroidManager(java.util.Map<String, String> __ctx);

    /**
     * Get the CarDroid manager object.
     * 
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_getCardroidManager();

    /**
     * Get the CarDroid manager object.
     * 
     * @param __ctx The Context map to send with the invocation.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_getCardroidManager(java.util.Map<String, String> __ctx);

    /**
     * Get the CarDroid manager object.
     * 
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_getCardroidManager(Ice.Callback __cb);

    /**
     * Get the CarDroid manager object.
     * 
     * @param __ctx The Context map to send with the invocation.
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_getCardroidManager(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    /**
     * Get the CarDroid manager object.
     * 
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_getCardroidManager(Callback_Session_getCardroidManager __cb);

    /**
     * Get the CarDroid manager object.
     * 
     * @param __ctx The Context map to send with the invocation.
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_getCardroidManager(java.util.Map<String, String> __ctx, Callback_Session_getCardroidManager __cb);

    /**
     * Get the CarDroid manager object.
     * 
     * @param __result The asynchronous result object.
     * @return A proxy for the new CarDroid manager.
     * 
     **/
    public uclm.esi.cardroid.zerocice.CardroidManagerPrx end_getCardroidManager(Ice.AsyncResult __result);

    /**
     * Get the topic object used to subscribe to user events via 
     * the IceStorm service.
     * 
     * @return A proxy to the topic referring to the session's 
     * user, used by the server to publish events.
     * 
     **/
    public IceStorm.TopicPrx getTopic();

    /**
     * Get the topic object used to subscribe to user events via 
     * the IceStorm service.
     * 
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy to the topic referring to the session's 
     * user, used by the server to publish events.
     * 
     **/
    public IceStorm.TopicPrx getTopic(java.util.Map<String, String> __ctx);

    /**
     * Get the topic object used to subscribe to user events via 
     * the IceStorm service.
     * 
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_getTopic();

    /**
     * Get the topic object used to subscribe to user events via 
     * the IceStorm service.
     * 
     * @param __ctx The Context map to send with the invocation.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_getTopic(java.util.Map<String, String> __ctx);

    /**
     * Get the topic object used to subscribe to user events via 
     * the IceStorm service.
     * 
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_getTopic(Ice.Callback __cb);

    /**
     * Get the topic object used to subscribe to user events via 
     * the IceStorm service.
     * 
     * @param __ctx The Context map to send with the invocation.
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_getTopic(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    /**
     * Get the topic object used to subscribe to user events via 
     * the IceStorm service.
     * 
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_getTopic(Callback_Session_getTopic __cb);

    /**
     * Get the topic object used to subscribe to user events via 
     * the IceStorm service.
     * 
     * @param __ctx The Context map to send with the invocation.
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_getTopic(java.util.Map<String, String> __ctx, Callback_Session_getTopic __cb);

    /**
     * Get the topic object used to subscribe to user events via 
     * the IceStorm service.
     * 
     * @param __result The asynchronous result object.
     * @return A proxy to the topic referring to the session's 
     * user, used by the server to publish events.
     * 
     **/
    public IceStorm.TopicPrx end_getTopic(Ice.AsyncResult __result);

    /**
     * Refresh a session. If a session is not refreshed on a regular
     * basis by the client, it will be automatically destroyed.
     * 
     **/
    public void refresh();

    /**
     * Refresh a session. If a session is not refreshed on a regular
     * basis by the client, it will be automatically destroyed.
     * 
     * @param __ctx The Context map to send with the invocation.
     **/
    public void refresh(java.util.Map<String, String> __ctx);

    /**
     * Refresh a session. If a session is not refreshed on a regular
     * basis by the client, it will be automatically destroyed.
     * 
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_refresh();

    /**
     * Refresh a session. If a session is not refreshed on a regular
     * basis by the client, it will be automatically destroyed.
     * 
     * @param __ctx The Context map to send with the invocation.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_refresh(java.util.Map<String, String> __ctx);

    /**
     * Refresh a session. If a session is not refreshed on a regular
     * basis by the client, it will be automatically destroyed.
     * 
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_refresh(Ice.Callback __cb);

    /**
     * Refresh a session. If a session is not refreshed on a regular
     * basis by the client, it will be automatically destroyed.
     * 
     * @param __ctx The Context map to send with the invocation.
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_refresh(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    /**
     * Refresh a session. If a session is not refreshed on a regular
     * basis by the client, it will be automatically destroyed.
     * 
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_refresh(Callback_Session_refresh __cb);

    /**
     * Refresh a session. If a session is not refreshed on a regular
     * basis by the client, it will be automatically destroyed.
     * 
     * @param __ctx The Context map to send with the invocation.
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_refresh(java.util.Map<String, String> __ctx, Callback_Session_refresh __cb);

    /**
     * Refresh a session. If a session is not refreshed on a regular
     * basis by the client, it will be automatically destroyed.
     * 
     * @param __result The asynchronous result object.
     **/
    public void end_refresh(Ice.AsyncResult __result);

    /**
     * Destroy the session.
     * 
     **/
    public void destroy();

    /**
     * Destroy the session.
     * 
     * @param __ctx The Context map to send with the invocation.
     **/
    public void destroy(java.util.Map<String, String> __ctx);

    /**
     * Destroy the session.
     * 
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_destroy();

    /**
     * Destroy the session.
     * 
     * @param __ctx The Context map to send with the invocation.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_destroy(java.util.Map<String, String> __ctx);

    /**
     * Destroy the session.
     * 
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_destroy(Ice.Callback __cb);

    /**
     * Destroy the session.
     * 
     * @param __ctx The Context map to send with the invocation.
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_destroy(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    /**
     * Destroy the session.
     * 
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_destroy(Callback_Session_destroy __cb);

    /**
     * Destroy the session.
     * 
     * @param __ctx The Context map to send with the invocation.
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_destroy(java.util.Map<String, String> __ctx, Callback_Session_destroy __cb);

    /**
     * Destroy the session.
     * 
     * @param __result The asynchronous result object.
     **/
    public void end_destroy(Ice.AsyncResult __result);
}