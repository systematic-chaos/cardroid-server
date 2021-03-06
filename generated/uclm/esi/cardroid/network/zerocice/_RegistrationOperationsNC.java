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
public interface _RegistrationOperationsNC
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
    boolean registerNewUser(uclm.esi.cardroid.data.zerocice.UserTyp newUser, String password)
        throws RegistrationDeniedException;
}
