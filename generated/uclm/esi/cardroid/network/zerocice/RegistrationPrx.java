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
// Generated from file `Registration.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package uclm.esi.cardroid.network.zerocice;

/**
 * Interface for the registration of new users in the CarDroid platform
 **/
public interface RegistrationPrx extends Ice.ObjectPrx
{
    /**
     * Register a new user in the CarDroid platform
     * @param newUser	The new user data
     * @param password	The new user's password
     * @return			Whether the register process completed
     * successfully
     * @throws RegistrationDeniedException The user registration 
     * did not complete 
     * successfully for some 
     * reason
     **/
    public boolean registerNewUser(uclm.esi.cardroid.data.zerocice.UserTyp newUser, String password)
        throws RegistrationDeniedException;

    /**
     * Register a new user in the CarDroid platform
     * @param newUser	The new user data
     * @param password	The new user's password
     * @param __ctx The Context map to send with the invocation.
     * @return			Whether the register process completed
     * successfully
     * @throws RegistrationDeniedException The user registration 
     * did not complete 
     * successfully for some 
     * reason
     **/
    public boolean registerNewUser(uclm.esi.cardroid.data.zerocice.UserTyp newUser, String password, java.util.Map<String, String> __ctx)
        throws RegistrationDeniedException;

    /**
     * Register a new user in the CarDroid platform
     * @param newUser	The new user data
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_registerNewUser(uclm.esi.cardroid.data.zerocice.UserTyp newUser, String password);

    /**
     * Register a new user in the CarDroid platform
     * @param newUser	The new user data
     * @param __ctx The Context map to send with the invocation.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_registerNewUser(uclm.esi.cardroid.data.zerocice.UserTyp newUser, String password, java.util.Map<String, String> __ctx);

    /**
     * Register a new user in the CarDroid platform
     * @param newUser	The new user data
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_registerNewUser(uclm.esi.cardroid.data.zerocice.UserTyp newUser, String password, Ice.Callback __cb);

    /**
     * Register a new user in the CarDroid platform
     * @param newUser	The new user data
     * @param __ctx The Context map to send with the invocation.
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_registerNewUser(uclm.esi.cardroid.data.zerocice.UserTyp newUser, String password, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    /**
     * Register a new user in the CarDroid platform
     * @param newUser	The new user data
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_registerNewUser(uclm.esi.cardroid.data.zerocice.UserTyp newUser, String password, Callback_Registration_registerNewUser __cb);

    /**
     * Register a new user in the CarDroid platform
     * @param newUser	The new user data
     * @param __ctx The Context map to send with the invocation.
     * @param __cb The asynchronous callback object.
     * @return The asynchronous result object.
     **/
    public Ice.AsyncResult begin_registerNewUser(uclm.esi.cardroid.data.zerocice.UserTyp newUser, String password, java.util.Map<String, String> __ctx, Callback_Registration_registerNewUser __cb);

    /**
     * Register a new user in the CarDroid platform
     * @param __result The asynchronous result object.
     * @return			Whether the register process completed
     * successfully
     * @throws RegistrationDeniedException The user registration 
     * did not complete 
     * successfully for some 
     * reason
     **/
    public boolean end_registerNewUser(Ice.AsyncResult __result)
        throws RegistrationDeniedException;
}