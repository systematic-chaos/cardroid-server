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

public interface _CardroidManagerOperationsNC
{
    void getUserPlaces(uclm.esi.cardroid.data.zerocice.UserTypPrx usr, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result);

    void searchTrips(uclm.esi.cardroid.data.zerocice.TripRequestTyp tRequest, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result);

    uclm.esi.cardroid.data.zerocice.TripTypPrx getTripFromId(int tripId);

    uclm.esi.cardroid.data.zerocice.TripOfferTypPrx getTripOfferFromId(int tripId);

    uclm.esi.cardroid.data.zerocice.TripRequestTypPrx getTripRequestFromId(int tripId);

    void joinTrip(uclm.esi.cardroid.data.zerocice.TripOfferTypPrx trip, uclm.esi.cardroid.data.zerocice.UserTypPrx passenger, int nSeats);

    uclm.esi.cardroid.data.zerocice.TripOfferTypPrx organizeTrip(uclm.esi.cardroid.data.zerocice.TripRequestTypPrx tripRequest, uclm.esi.cardroid.data.zerocice.TripOfferTyp tripOffer);

    void getUserTrips(uclm.esi.cardroid.data.zerocice.UserTypPrx usr, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result);

    void getPassengerTrips(uclm.esi.cardroid.data.zerocice.UserTypPrx passenger, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result);

    boolean userTripRegistered(uclm.esi.cardroid.data.zerocice.UserTypPrx usr, uclm.esi.cardroid.data.zerocice.TripTypPrx trip);

    double calculatePriceEstimation(uclm.esi.cardroid.data.zerocice.Fuel f, int distance);

    uclm.esi.cardroid.data.zerocice.TripOfferTypPrx newTripOffer(uclm.esi.cardroid.data.zerocice.TripOfferTyp tOffer);

    uclm.esi.cardroid.data.zerocice.TripRequestTypPrx newTripRequest(uclm.esi.cardroid.data.zerocice.TripRequestTyp tRequest);

    void getMessageTalksSpeakers(uclm.esi.cardroid.data.zerocice.UserTypPrx usr, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result);

    void getMessageTalks(uclm.esi.cardroid.data.zerocice.UserTypPrx usr1, uclm.esi.cardroid.data.zerocice.UserTypPrx usr2, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result);

    uclm.esi.cardroid.data.zerocice.MessageTypPrx newMessage(uclm.esi.cardroid.data.zerocice.UserTypPrx usr1, uclm.esi.cardroid.data.zerocice.UserTypPrx usr2, String message);

    void getUserActivity(uclm.esi.cardroid.data.zerocice.UserTypPrx usr, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result);

    uclm.esi.cardroid.data.zerocice.UserTypPrx getUserFromEmail(String email);

    uclm.esi.cardroid.data.zerocice.CarTypPrx getCarFromPlate(String plate, uclm.esi.cardroid.data.zerocice.UserTypPrx owner);

    uclm.esi.cardroid.data.zerocice.CarTypPrx getCarFromPlateEmail(String plate, String ownerEmail);

    uclm.esi.cardroid.data.zerocice.UserTypPrx updateUserData(uclm.esi.cardroid.data.zerocice.UserTyp usr);

    uclm.esi.cardroid.data.zerocice.CarTypPrx updateCarData(uclm.esi.cardroid.data.zerocice.CarTyp car, uclm.esi.cardroid.data.zerocice.UserTyp usr);

    uclm.esi.cardroid.data.zerocice.CarTypPrx updateCarDataEmail(uclm.esi.cardroid.data.zerocice.CarTyp car, String usrEmail);

    uclm.esi.cardroid.data.zerocice.CarTypPrx addCar(uclm.esi.cardroid.data.zerocice.CarTyp car, uclm.esi.cardroid.data.zerocice.UserTypPrx usr);

    uclm.esi.cardroid.data.zerocice.CarTypPrx addCarEmail(uclm.esi.cardroid.data.zerocice.CarTyp car, String usrEmail);

    void removeCar(uclm.esi.cardroid.data.zerocice.CarTypPrx car, uclm.esi.cardroid.data.zerocice.UserTypPrx usr);

    void removeCarPlateEmail(String plate, String ownerEmail);
}
