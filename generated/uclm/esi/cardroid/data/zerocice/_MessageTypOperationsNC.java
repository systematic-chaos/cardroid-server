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

public interface _MessageTypOperationsNC
{
    UserTypPrx getUser1();

    void setUser1(UserTypPrx user1);

    UserTypPrx getUser2();

    void setUser2(UserTypPrx user2);

    String getMessageText();

    void setMessageText(String msg);

    long getTimeStampInMillis();

    void setTimeStampInMillis(long timeStampMillis);

    String _toString();
}
