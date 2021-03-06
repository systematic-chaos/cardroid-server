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

public interface _TripTypOperationsNC
{
    int getTripId();

    void setTripId(int tripId);

    PlaceTyp getPlace1();

    void setPlace1(PlaceTyp fromPlace);

    PlaceTyp getPlace2();

    void setPlace2(PlaceTyp toPlace);

    DateTyp getTripDate();

    void setTripDate(DateTyp tripDate);

    int getNSeats();

    void setNSeats(int nSeats);

    DateTyp getTripReturnDate();

    void setTripReturnDate(DateTyp tripReturnDate);

    boolean hasTripReturnDate();

    String[] getTripWeekDays();

    Periodicity getTripPeriodicity();

    void setTripWeekDaysPeriodicity(String[] tripWeekDays, Periodicity tripPeriodicity);

    boolean hasWeekDaysPeriodicity();

    int getDistance();

    void setDistance(int distance);

    boolean hasDistance();

    String getCharacteristics();

    void setCharacteristics(String characteristics);

    boolean hasCharacteristics();

    void setTripType(int type);

    int getTripType();

    String _toString();
}
