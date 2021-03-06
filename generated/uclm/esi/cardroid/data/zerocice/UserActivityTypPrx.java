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

public interface UserActivityTypPrx extends Ice.ObjectPrx
{
    public UserTypPrx getActivityUser();

    public UserTypPrx getActivityUser(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getActivityUser();

    public Ice.AsyncResult begin_getActivityUser(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getActivityUser(Ice.Callback __cb);

    public Ice.AsyncResult begin_getActivityUser(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getActivityUser(Callback_UserActivityTyp_getActivityUser __cb);

    public Ice.AsyncResult begin_getActivityUser(java.util.Map<String, String> __ctx, Callback_UserActivityTyp_getActivityUser __cb);

    public UserTypPrx end_getActivityUser(Ice.AsyncResult __result);

    public void setActivityUser(UserTypPrx activityUser);

    public void setActivityUser(UserTypPrx activityUser, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_setActivityUser(UserTypPrx activityUser);

    public Ice.AsyncResult begin_setActivityUser(UserTypPrx activityUser, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_setActivityUser(UserTypPrx activityUser, Ice.Callback __cb);

    public Ice.AsyncResult begin_setActivityUser(UserTypPrx activityUser, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_setActivityUser(UserTypPrx activityUser, Callback_UserActivityTyp_setActivityUser __cb);

    public Ice.AsyncResult begin_setActivityUser(UserTypPrx activityUser, java.util.Map<String, String> __ctx, Callback_UserActivityTyp_setActivityUser __cb);

    public void end_setActivityUser(Ice.AsyncResult __result);

    public TripOfferTypPrx getActivityTrip();

    public TripOfferTypPrx getActivityTrip(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getActivityTrip();

    public Ice.AsyncResult begin_getActivityTrip(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getActivityTrip(Ice.Callback __cb);

    public Ice.AsyncResult begin_getActivityTrip(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getActivityTrip(Callback_UserActivityTyp_getActivityTrip __cb);

    public Ice.AsyncResult begin_getActivityTrip(java.util.Map<String, String> __ctx, Callback_UserActivityTyp_getActivityTrip __cb);

    public TripOfferTypPrx end_getActivityTrip(Ice.AsyncResult __result);

    public void setActivityTrip(TripOfferTypPrx activityTrip);

    public void setActivityTrip(TripOfferTypPrx activityTrip, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_setActivityTrip(TripOfferTypPrx activityTrip);

    public Ice.AsyncResult begin_setActivityTrip(TripOfferTypPrx activityTrip, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_setActivityTrip(TripOfferTypPrx activityTrip, Ice.Callback __cb);

    public Ice.AsyncResult begin_setActivityTrip(TripOfferTypPrx activityTrip, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_setActivityTrip(TripOfferTypPrx activityTrip, Callback_UserActivityTyp_setActivityTrip __cb);

    public Ice.AsyncResult begin_setActivityTrip(TripOfferTypPrx activityTrip, java.util.Map<String, String> __ctx, Callback_UserActivityTyp_setActivityTrip __cb);

    public void end_setActivityTrip(Ice.AsyncResult __result);

    public ActivityType getUserActivityType();

    public ActivityType getUserActivityType(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getUserActivityType();

    public Ice.AsyncResult begin_getUserActivityType(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getUserActivityType(Ice.Callback __cb);

    public Ice.AsyncResult begin_getUserActivityType(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getUserActivityType(Callback_UserActivityTyp_getUserActivityType __cb);

    public Ice.AsyncResult begin_getUserActivityType(java.util.Map<String, String> __ctx, Callback_UserActivityTyp_getUserActivityType __cb);

    public ActivityType end_getUserActivityType(Ice.AsyncResult __result);

    public void setUserActivityType(ActivityType userActivityType);

    public void setUserActivityType(ActivityType userActivityType, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_setUserActivityType(ActivityType userActivityType);

    public Ice.AsyncResult begin_setUserActivityType(ActivityType userActivityType, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_setUserActivityType(ActivityType userActivityType, Ice.Callback __cb);

    public Ice.AsyncResult begin_setUserActivityType(ActivityType userActivityType, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_setUserActivityType(ActivityType userActivityType, Callback_UserActivityTyp_setUserActivityType __cb);

    public Ice.AsyncResult begin_setUserActivityType(ActivityType userActivityType, java.util.Map<String, String> __ctx, Callback_UserActivityTyp_setUserActivityType __cb);

    public void end_setUserActivityType(Ice.AsyncResult __result);

    public long getTimeStampInMillis();

    public long getTimeStampInMillis(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getTimeStampInMillis();

    public Ice.AsyncResult begin_getTimeStampInMillis(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getTimeStampInMillis(Ice.Callback __cb);

    public Ice.AsyncResult begin_getTimeStampInMillis(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getTimeStampInMillis(Callback_UserActivityTyp_getTimeStampInMillis __cb);

    public Ice.AsyncResult begin_getTimeStampInMillis(java.util.Map<String, String> __ctx, Callback_UserActivityTyp_getTimeStampInMillis __cb);

    public long end_getTimeStampInMillis(Ice.AsyncResult __result);

    public void setTimeStampInMillis(long timeStampMillis);

    public void setTimeStampInMillis(long timeStampMillis, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_setTimeStampInMillis(long timeStampMillis);

    public Ice.AsyncResult begin_setTimeStampInMillis(long timeStampMillis, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_setTimeStampInMillis(long timeStampMillis, Ice.Callback __cb);

    public Ice.AsyncResult begin_setTimeStampInMillis(long timeStampMillis, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_setTimeStampInMillis(long timeStampMillis, Callback_UserActivityTyp_setTimeStampInMillis __cb);

    public Ice.AsyncResult begin_setTimeStampInMillis(long timeStampMillis, java.util.Map<String, String> __ctx, Callback_UserActivityTyp_setTimeStampInMillis __cb);

    public void end_setTimeStampInMillis(Ice.AsyncResult __result);

    public String _toString();

    public String _toString(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_toString();

    public Ice.AsyncResult begin_toString(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_toString(Ice.Callback __cb);

    public Ice.AsyncResult begin_toString(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_toString(Callback_UserActivityTyp_toString __cb);

    public Ice.AsyncResult begin_toString(java.util.Map<String, String> __ctx, Callback_UserActivityTyp_toString __cb);

    public String end_toString(Ice.AsyncResult __result);
}
