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

public class _CardroidManagerTie extends _CardroidManagerDisp implements Ice.TieBase
{
    public _CardroidManagerTie()
    {
    }

    public _CardroidManagerTie(_CardroidManagerOperations delegate)
    {
        _ice_delegate = delegate;
    }

    public java.lang.Object ice_delegate()
    {
        return _ice_delegate;
    }

    public void ice_delegate(java.lang.Object delegate)
    {
        _ice_delegate = (_CardroidManagerOperations)delegate;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        if(!(rhs instanceof _CardroidManagerTie))
        {
            return false;
        }

        return _ice_delegate.equals(((_CardroidManagerTie)rhs)._ice_delegate);
    }

    public int hashCode()
    {
        return _ice_delegate.hashCode();
    }

    public uclm.esi.cardroid.data.zerocice.CarTypPrx addCar(uclm.esi.cardroid.data.zerocice.CarTyp car, uclm.esi.cardroid.data.zerocice.UserTypPrx usr, Ice.Current __current)
    {
        return _ice_delegate.addCar(car, usr, __current);
    }

    public uclm.esi.cardroid.data.zerocice.CarTypPrx addCarEmail(uclm.esi.cardroid.data.zerocice.CarTyp car, String usrEmail, Ice.Current __current)
    {
        return _ice_delegate.addCarEmail(car, usrEmail, __current);
    }

    public double calculatePriceEstimation(uclm.esi.cardroid.data.zerocice.Fuel f, int distance, Ice.Current __current)
    {
        return _ice_delegate.calculatePriceEstimation(f, distance, __current);
    }

    public uclm.esi.cardroid.data.zerocice.CarTypPrx getCarFromPlate(String plate, uclm.esi.cardroid.data.zerocice.UserTypPrx owner, Ice.Current __current)
    {
        return _ice_delegate.getCarFromPlate(plate, owner, __current);
    }

    public uclm.esi.cardroid.data.zerocice.CarTypPrx getCarFromPlateEmail(String plate, String ownerEmail, Ice.Current __current)
    {
        return _ice_delegate.getCarFromPlateEmail(plate, ownerEmail, __current);
    }

    public void getMessageTalks(uclm.esi.cardroid.data.zerocice.UserTypPrx usr1, uclm.esi.cardroid.data.zerocice.UserTypPrx usr2, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result, Ice.Current __current)
    {
        _ice_delegate.getMessageTalks(usr1, usr2, n, first, nrows, result, __current);
    }

    public void getMessageTalksSpeakers(uclm.esi.cardroid.data.zerocice.UserTypPrx usr, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result, Ice.Current __current)
    {
        _ice_delegate.getMessageTalksSpeakers(usr, n, first, nrows, result, __current);
    }

    public void getPassengerTrips(uclm.esi.cardroid.data.zerocice.UserTypPrx passenger, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result, Ice.Current __current)
    {
        _ice_delegate.getPassengerTrips(passenger, n, first, nrows, result, __current);
    }

    public uclm.esi.cardroid.data.zerocice.TripTypPrx getTripFromId(int tripId, Ice.Current __current)
    {
        return _ice_delegate.getTripFromId(tripId, __current);
    }

    public uclm.esi.cardroid.data.zerocice.TripOfferTypPrx getTripOfferFromId(int tripId, Ice.Current __current)
    {
        return _ice_delegate.getTripOfferFromId(tripId, __current);
    }

    public uclm.esi.cardroid.data.zerocice.TripRequestTypPrx getTripRequestFromId(int tripId, Ice.Current __current)
    {
        return _ice_delegate.getTripRequestFromId(tripId, __current);
    }

    public void getUserActivity(uclm.esi.cardroid.data.zerocice.UserTypPrx usr, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result, Ice.Current __current)
    {
        _ice_delegate.getUserActivity(usr, n, first, nrows, result, __current);
    }

    public uclm.esi.cardroid.data.zerocice.UserTypPrx getUserFromEmail(String email, Ice.Current __current)
    {
        return _ice_delegate.getUserFromEmail(email, __current);
    }

    public void getUserPlaces(uclm.esi.cardroid.data.zerocice.UserTypPrx usr, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result, Ice.Current __current)
    {
        _ice_delegate.getUserPlaces(usr, n, first, nrows, result, __current);
    }

    public void getUserTrips(uclm.esi.cardroid.data.zerocice.UserTypPrx usr, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result, Ice.Current __current)
    {
        _ice_delegate.getUserTrips(usr, n, first, nrows, result, __current);
    }

    public void joinTrip(uclm.esi.cardroid.data.zerocice.TripOfferTypPrx trip, uclm.esi.cardroid.data.zerocice.UserTypPrx passenger, int nSeats, Ice.Current __current)
    {
        _ice_delegate.joinTrip(trip, passenger, nSeats, __current);
    }

    public uclm.esi.cardroid.data.zerocice.MessageTypPrx newMessage(uclm.esi.cardroid.data.zerocice.UserTypPrx usr1, uclm.esi.cardroid.data.zerocice.UserTypPrx usr2, String message, Ice.Current __current)
    {
        return _ice_delegate.newMessage(usr1, usr2, message, __current);
    }

    public uclm.esi.cardroid.data.zerocice.TripOfferTypPrx newTripOffer(uclm.esi.cardroid.data.zerocice.TripOfferTyp tOffer, Ice.Current __current)
    {
        return _ice_delegate.newTripOffer(tOffer, __current);
    }

    public uclm.esi.cardroid.data.zerocice.TripRequestTypPrx newTripRequest(uclm.esi.cardroid.data.zerocice.TripRequestTyp tRequest, Ice.Current __current)
    {
        return _ice_delegate.newTripRequest(tRequest, __current);
    }

    public uclm.esi.cardroid.data.zerocice.TripOfferTypPrx organizeTrip(uclm.esi.cardroid.data.zerocice.TripRequestTypPrx tripRequest, uclm.esi.cardroid.data.zerocice.TripOfferTyp tripOffer, Ice.Current __current)
    {
        return _ice_delegate.organizeTrip(tripRequest, tripOffer, __current);
    }

    public void removeCar(uclm.esi.cardroid.data.zerocice.CarTypPrx car, uclm.esi.cardroid.data.zerocice.UserTypPrx usr, Ice.Current __current)
    {
        _ice_delegate.removeCar(car, usr, __current);
    }

    public void removeCarPlateEmail(String plate, String ownerEmail, Ice.Current __current)
    {
        _ice_delegate.removeCarPlateEmail(plate, ownerEmail, __current);
    }

    public void searchTrips(uclm.esi.cardroid.data.zerocice.TripRequestTyp tRequest, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result, Ice.Current __current)
    {
        _ice_delegate.searchTrips(tRequest, n, first, nrows, result, __current);
    }

    public uclm.esi.cardroid.data.zerocice.CarTypPrx updateCarData(uclm.esi.cardroid.data.zerocice.CarTyp car, uclm.esi.cardroid.data.zerocice.UserTyp usr, Ice.Current __current)
    {
        return _ice_delegate.updateCarData(car, usr, __current);
    }

    public uclm.esi.cardroid.data.zerocice.CarTypPrx updateCarDataEmail(uclm.esi.cardroid.data.zerocice.CarTyp car, String usrEmail, Ice.Current __current)
    {
        return _ice_delegate.updateCarDataEmail(car, usrEmail, __current);
    }

    public uclm.esi.cardroid.data.zerocice.UserTypPrx updateUserData(uclm.esi.cardroid.data.zerocice.UserTyp usr, Ice.Current __current)
    {
        return _ice_delegate.updateUserData(usr, __current);
    }

    public boolean userTripRegistered(uclm.esi.cardroid.data.zerocice.UserTypPrx usr, uclm.esi.cardroid.data.zerocice.TripTypPrx trip, Ice.Current __current)
    {
        return _ice_delegate.userTripRegistered(usr, trip, __current);
    }

    private _CardroidManagerOperations _ice_delegate;

    public static final long serialVersionUID = 1734848224L;
}