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

public abstract class _CardroidManagerDisp extends Ice.ObjectImpl implements CardroidManager
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
        throws java.lang.CloneNotSupportedException
    {
        throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::cardroid::zerocice::CardroidManager"
    };

    public boolean ice_isA(String s)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean ice_isA(String s, Ice.Current __current)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[] ice_ids()
    {
        return __ids;
    }

    public String[] ice_ids(Ice.Current __current)
    {
        return __ids;
    }

    public String ice_id()
    {
        return __ids[1];
    }

    public String ice_id(Ice.Current __current)
    {
        return __ids[1];
    }

    public static String ice_staticId()
    {
        return __ids[1];
    }

    public final uclm.esi.cardroid.data.zerocice.CarTypPrx addCar(uclm.esi.cardroid.data.zerocice.CarTyp car, uclm.esi.cardroid.data.zerocice.UserTypPrx usr)
    {
        return addCar(car, usr, null);
    }

    public final uclm.esi.cardroid.data.zerocice.CarTypPrx addCarEmail(uclm.esi.cardroid.data.zerocice.CarTyp car, String usrEmail)
    {
        return addCarEmail(car, usrEmail, null);
    }

    public final double calculatePriceEstimation(uclm.esi.cardroid.data.zerocice.Fuel f, int distance)
    {
        return calculatePriceEstimation(f, distance, null);
    }

    public final uclm.esi.cardroid.data.zerocice.CarTypPrx getCarFromPlate(String plate, uclm.esi.cardroid.data.zerocice.UserTypPrx owner)
    {
        return getCarFromPlate(plate, owner, null);
    }

    public final uclm.esi.cardroid.data.zerocice.CarTypPrx getCarFromPlateEmail(String plate, String ownerEmail)
    {
        return getCarFromPlateEmail(plate, ownerEmail, null);
    }

    public final void getMessageTalks(uclm.esi.cardroid.data.zerocice.UserTypPrx usr1, uclm.esi.cardroid.data.zerocice.UserTypPrx usr2, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result)
    {
        getMessageTalks(usr1, usr2, n, first, nrows, result, null);
    }

    public final void getMessageTalksSpeakers(uclm.esi.cardroid.data.zerocice.UserTypPrx usr, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result)
    {
        getMessageTalksSpeakers(usr, n, first, nrows, result, null);
    }

    public final void getPassengerTrips(uclm.esi.cardroid.data.zerocice.UserTypPrx passenger, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result)
    {
        getPassengerTrips(passenger, n, first, nrows, result, null);
    }

    public final uclm.esi.cardroid.data.zerocice.TripTypPrx getTripFromId(int tripId)
    {
        return getTripFromId(tripId, null);
    }

    public final uclm.esi.cardroid.data.zerocice.TripOfferTypPrx getTripOfferFromId(int tripId)
    {
        return getTripOfferFromId(tripId, null);
    }

    public final uclm.esi.cardroid.data.zerocice.TripRequestTypPrx getTripRequestFromId(int tripId)
    {
        return getTripRequestFromId(tripId, null);
    }

    public final void getUserActivity(uclm.esi.cardroid.data.zerocice.UserTypPrx usr, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result)
    {
        getUserActivity(usr, n, first, nrows, result, null);
    }

    public final uclm.esi.cardroid.data.zerocice.UserTypPrx getUserFromEmail(String email)
    {
        return getUserFromEmail(email, null);
    }

    public final void getUserPlaces(uclm.esi.cardroid.data.zerocice.UserTypPrx usr, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result)
    {
        getUserPlaces(usr, n, first, nrows, result, null);
    }

    public final void getUserTrips(uclm.esi.cardroid.data.zerocice.UserTypPrx usr, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result)
    {
        getUserTrips(usr, n, first, nrows, result, null);
    }

    public final void joinTrip(uclm.esi.cardroid.data.zerocice.TripOfferTypPrx trip, uclm.esi.cardroid.data.zerocice.UserTypPrx passenger, int nSeats)
    {
        joinTrip(trip, passenger, nSeats, null);
    }

    public final uclm.esi.cardroid.data.zerocice.MessageTypPrx newMessage(uclm.esi.cardroid.data.zerocice.UserTypPrx usr1, uclm.esi.cardroid.data.zerocice.UserTypPrx usr2, String message)
    {
        return newMessage(usr1, usr2, message, null);
    }

    public final uclm.esi.cardroid.data.zerocice.TripOfferTypPrx newTripOffer(uclm.esi.cardroid.data.zerocice.TripOfferTyp tOffer)
    {
        return newTripOffer(tOffer, null);
    }

    public final uclm.esi.cardroid.data.zerocice.TripRequestTypPrx newTripRequest(uclm.esi.cardroid.data.zerocice.TripRequestTyp tRequest)
    {
        return newTripRequest(tRequest, null);
    }

    public final uclm.esi.cardroid.data.zerocice.TripOfferTypPrx organizeTrip(uclm.esi.cardroid.data.zerocice.TripRequestTypPrx tripRequest, uclm.esi.cardroid.data.zerocice.TripOfferTyp tripOffer)
    {
        return organizeTrip(tripRequest, tripOffer, null);
    }

    public final void removeCar(uclm.esi.cardroid.data.zerocice.CarTypPrx car, uclm.esi.cardroid.data.zerocice.UserTypPrx usr)
    {
        removeCar(car, usr, null);
    }

    public final void removeCarPlateEmail(String plate, String ownerEmail)
    {
        removeCarPlateEmail(plate, ownerEmail, null);
    }

    public final void searchTrips(uclm.esi.cardroid.data.zerocice.TripRequestTyp tRequest, int n, uclm.esi.cardroid.ResultSeqHolder first, Ice.IntHolder nrows, uclm.esi.cardroid.QueryResultPrxHolder result)
    {
        searchTrips(tRequest, n, first, nrows, result, null);
    }

    public final uclm.esi.cardroid.data.zerocice.CarTypPrx updateCarData(uclm.esi.cardroid.data.zerocice.CarTyp car, uclm.esi.cardroid.data.zerocice.UserTyp usr)
    {
        return updateCarData(car, usr, null);
    }

    public final uclm.esi.cardroid.data.zerocice.CarTypPrx updateCarDataEmail(uclm.esi.cardroid.data.zerocice.CarTyp car, String usrEmail)
    {
        return updateCarDataEmail(car, usrEmail, null);
    }

    public final uclm.esi.cardroid.data.zerocice.UserTypPrx updateUserData(uclm.esi.cardroid.data.zerocice.UserTyp usr)
    {
        return updateUserData(usr, null);
    }

    public final boolean userTripRegistered(uclm.esi.cardroid.data.zerocice.UserTypPrx usr, uclm.esi.cardroid.data.zerocice.TripTypPrx trip)
    {
        return userTripRegistered(usr, trip, null);
    }

    public static Ice.DispatchStatus ___getUserPlaces(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.UserTypPrx usr;
        int n;
        usr = uclm.esi.cardroid.data.zerocice.UserTypPrxHelper.__read(__is);
        n = __is.readInt();
        __inS.endReadParams();
        uclm.esi.cardroid.ResultSeqHolder first = new uclm.esi.cardroid.ResultSeqHolder();
        Ice.IntHolder nrows = new Ice.IntHolder();
        uclm.esi.cardroid.QueryResultPrxHolder result = new uclm.esi.cardroid.QueryResultPrxHolder();
        __obj.getUserPlaces(usr, n, first, nrows, result, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.ResultSeqHelper.write(__os, first.value);
        __os.writeInt(nrows.value);
        uclm.esi.cardroid.QueryResultPrxHelper.__write(__os, result.value);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___searchTrips(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.TripRequestTypHolder tRequest = new uclm.esi.cardroid.data.zerocice.TripRequestTypHolder();
        int n;
        __is.readObject(tRequest);
        n = __is.readInt();
        __is.readPendingObjects();
        __inS.endReadParams();
        uclm.esi.cardroid.ResultSeqHolder first = new uclm.esi.cardroid.ResultSeqHolder();
        Ice.IntHolder nrows = new Ice.IntHolder();
        uclm.esi.cardroid.QueryResultPrxHolder result = new uclm.esi.cardroid.QueryResultPrxHolder();
        __obj.searchTrips(tRequest.value, n, first, nrows, result, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.ResultSeqHelper.write(__os, first.value);
        __os.writeInt(nrows.value);
        uclm.esi.cardroid.QueryResultPrxHelper.__write(__os, result.value);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getTripFromId(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        int tripId;
        tripId = __is.readInt();
        __inS.endReadParams();
        uclm.esi.cardroid.data.zerocice.TripTypPrx __ret = __obj.getTripFromId(tripId, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.data.zerocice.TripTypPrxHelper.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getTripOfferFromId(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        int tripId;
        tripId = __is.readInt();
        __inS.endReadParams();
        uclm.esi.cardroid.data.zerocice.TripOfferTypPrx __ret = __obj.getTripOfferFromId(tripId, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.data.zerocice.TripOfferTypPrxHelper.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getTripRequestFromId(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        int tripId;
        tripId = __is.readInt();
        __inS.endReadParams();
        uclm.esi.cardroid.data.zerocice.TripRequestTypPrx __ret = __obj.getTripRequestFromId(tripId, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.data.zerocice.TripRequestTypPrxHelper.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___joinTrip(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.TripOfferTypPrx trip;
        uclm.esi.cardroid.data.zerocice.UserTypPrx passenger;
        int nSeats;
        trip = uclm.esi.cardroid.data.zerocice.TripOfferTypPrxHelper.__read(__is);
        passenger = uclm.esi.cardroid.data.zerocice.UserTypPrxHelper.__read(__is);
        nSeats = __is.readInt();
        __inS.endReadParams();
        __obj.joinTrip(trip, passenger, nSeats, __current);
        __inS.__writeEmptyParams();
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___organizeTrip(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.TripRequestTypPrx tripRequest;
        uclm.esi.cardroid.data.zerocice.TripOfferTypHolder tripOffer = new uclm.esi.cardroid.data.zerocice.TripOfferTypHolder();
        tripRequest = uclm.esi.cardroid.data.zerocice.TripRequestTypPrxHelper.__read(__is);
        __is.readObject(tripOffer);
        __is.readPendingObjects();
        __inS.endReadParams();
        uclm.esi.cardroid.data.zerocice.TripOfferTypPrx __ret = __obj.organizeTrip(tripRequest, tripOffer.value, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.data.zerocice.TripOfferTypPrxHelper.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getUserTrips(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.UserTypPrx usr;
        int n;
        usr = uclm.esi.cardroid.data.zerocice.UserTypPrxHelper.__read(__is);
        n = __is.readInt();
        __inS.endReadParams();
        uclm.esi.cardroid.ResultSeqHolder first = new uclm.esi.cardroid.ResultSeqHolder();
        Ice.IntHolder nrows = new Ice.IntHolder();
        uclm.esi.cardroid.QueryResultPrxHolder result = new uclm.esi.cardroid.QueryResultPrxHolder();
        __obj.getUserTrips(usr, n, first, nrows, result, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.ResultSeqHelper.write(__os, first.value);
        __os.writeInt(nrows.value);
        uclm.esi.cardroid.QueryResultPrxHelper.__write(__os, result.value);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getPassengerTrips(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.UserTypPrx passenger;
        int n;
        passenger = uclm.esi.cardroid.data.zerocice.UserTypPrxHelper.__read(__is);
        n = __is.readInt();
        __inS.endReadParams();
        uclm.esi.cardroid.ResultSeqHolder first = new uclm.esi.cardroid.ResultSeqHolder();
        Ice.IntHolder nrows = new Ice.IntHolder();
        uclm.esi.cardroid.QueryResultPrxHolder result = new uclm.esi.cardroid.QueryResultPrxHolder();
        __obj.getPassengerTrips(passenger, n, first, nrows, result, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.ResultSeqHelper.write(__os, first.value);
        __os.writeInt(nrows.value);
        uclm.esi.cardroid.QueryResultPrxHelper.__write(__os, result.value);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___userTripRegistered(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.UserTypPrx usr;
        uclm.esi.cardroid.data.zerocice.TripTypPrx trip;
        usr = uclm.esi.cardroid.data.zerocice.UserTypPrxHelper.__read(__is);
        trip = uclm.esi.cardroid.data.zerocice.TripTypPrxHelper.__read(__is);
        __inS.endReadParams();
        boolean __ret = __obj.userTripRegistered(usr, trip, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeBool(__ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___calculatePriceEstimation(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.Fuel f;
        int distance;
        f = uclm.esi.cardroid.data.zerocice.Fuel.__read(__is);
        distance = __is.readInt();
        __inS.endReadParams();
        double __ret = __obj.calculatePriceEstimation(f, distance, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeDouble(__ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___newTripOffer(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.TripOfferTypHolder tOffer = new uclm.esi.cardroid.data.zerocice.TripOfferTypHolder();
        __is.readObject(tOffer);
        __is.readPendingObjects();
        __inS.endReadParams();
        uclm.esi.cardroid.data.zerocice.TripOfferTypPrx __ret = __obj.newTripOffer(tOffer.value, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.data.zerocice.TripOfferTypPrxHelper.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___newTripRequest(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.TripRequestTypHolder tRequest = new uclm.esi.cardroid.data.zerocice.TripRequestTypHolder();
        __is.readObject(tRequest);
        __is.readPendingObjects();
        __inS.endReadParams();
        uclm.esi.cardroid.data.zerocice.TripRequestTypPrx __ret = __obj.newTripRequest(tRequest.value, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.data.zerocice.TripRequestTypPrxHelper.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getMessageTalksSpeakers(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.UserTypPrx usr;
        int n;
        usr = uclm.esi.cardroid.data.zerocice.UserTypPrxHelper.__read(__is);
        n = __is.readInt();
        __inS.endReadParams();
        uclm.esi.cardroid.ResultSeqHolder first = new uclm.esi.cardroid.ResultSeqHolder();
        Ice.IntHolder nrows = new Ice.IntHolder();
        uclm.esi.cardroid.QueryResultPrxHolder result = new uclm.esi.cardroid.QueryResultPrxHolder();
        __obj.getMessageTalksSpeakers(usr, n, first, nrows, result, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.ResultSeqHelper.write(__os, first.value);
        __os.writeInt(nrows.value);
        uclm.esi.cardroid.QueryResultPrxHelper.__write(__os, result.value);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getMessageTalks(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.UserTypPrx usr1;
        uclm.esi.cardroid.data.zerocice.UserTypPrx usr2;
        int n;
        usr1 = uclm.esi.cardroid.data.zerocice.UserTypPrxHelper.__read(__is);
        usr2 = uclm.esi.cardroid.data.zerocice.UserTypPrxHelper.__read(__is);
        n = __is.readInt();
        __inS.endReadParams();
        uclm.esi.cardroid.ResultSeqHolder first = new uclm.esi.cardroid.ResultSeqHolder();
        Ice.IntHolder nrows = new Ice.IntHolder();
        uclm.esi.cardroid.QueryResultPrxHolder result = new uclm.esi.cardroid.QueryResultPrxHolder();
        __obj.getMessageTalks(usr1, usr2, n, first, nrows, result, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.ResultSeqHelper.write(__os, first.value);
        __os.writeInt(nrows.value);
        uclm.esi.cardroid.QueryResultPrxHelper.__write(__os, result.value);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___newMessage(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.UserTypPrx usr1;
        uclm.esi.cardroid.data.zerocice.UserTypPrx usr2;
        String message;
        usr1 = uclm.esi.cardroid.data.zerocice.UserTypPrxHelper.__read(__is);
        usr2 = uclm.esi.cardroid.data.zerocice.UserTypPrxHelper.__read(__is);
        message = __is.readString();
        __inS.endReadParams();
        uclm.esi.cardroid.data.zerocice.MessageTypPrx __ret = __obj.newMessage(usr1, usr2, message, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.data.zerocice.MessageTypPrxHelper.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getUserActivity(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.UserTypPrx usr;
        int n;
        usr = uclm.esi.cardroid.data.zerocice.UserTypPrxHelper.__read(__is);
        n = __is.readInt();
        __inS.endReadParams();
        uclm.esi.cardroid.ResultSeqHolder first = new uclm.esi.cardroid.ResultSeqHolder();
        Ice.IntHolder nrows = new Ice.IntHolder();
        uclm.esi.cardroid.QueryResultPrxHolder result = new uclm.esi.cardroid.QueryResultPrxHolder();
        __obj.getUserActivity(usr, n, first, nrows, result, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.ResultSeqHelper.write(__os, first.value);
        __os.writeInt(nrows.value);
        uclm.esi.cardroid.QueryResultPrxHelper.__write(__os, result.value);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getUserFromEmail(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String email;
        email = __is.readString();
        __inS.endReadParams();
        uclm.esi.cardroid.data.zerocice.UserTypPrx __ret = __obj.getUserFromEmail(email, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.data.zerocice.UserTypPrxHelper.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getCarFromPlate(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String plate;
        uclm.esi.cardroid.data.zerocice.UserTypPrx owner;
        plate = __is.readString();
        owner = uclm.esi.cardroid.data.zerocice.UserTypPrxHelper.__read(__is);
        __inS.endReadParams();
        uclm.esi.cardroid.data.zerocice.CarTypPrx __ret = __obj.getCarFromPlate(plate, owner, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.data.zerocice.CarTypPrxHelper.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___getCarFromPlateEmail(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String plate;
        String ownerEmail;
        plate = __is.readString();
        ownerEmail = __is.readString();
        __inS.endReadParams();
        uclm.esi.cardroid.data.zerocice.CarTypPrx __ret = __obj.getCarFromPlateEmail(plate, ownerEmail, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.data.zerocice.CarTypPrxHelper.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___updateUserData(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.UserTypHolder usr = new uclm.esi.cardroid.data.zerocice.UserTypHolder();
        __is.readObject(usr);
        __is.readPendingObjects();
        __inS.endReadParams();
        uclm.esi.cardroid.data.zerocice.UserTypPrx __ret = __obj.updateUserData(usr.value, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.data.zerocice.UserTypPrxHelper.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___updateCarData(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.CarTypHolder car = new uclm.esi.cardroid.data.zerocice.CarTypHolder();
        uclm.esi.cardroid.data.zerocice.UserTypHolder usr = new uclm.esi.cardroid.data.zerocice.UserTypHolder();
        __is.readObject(car);
        __is.readObject(usr);
        __is.readPendingObjects();
        __inS.endReadParams();
        uclm.esi.cardroid.data.zerocice.CarTypPrx __ret = __obj.updateCarData(car.value, usr.value, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.data.zerocice.CarTypPrxHelper.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___updateCarDataEmail(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Idempotent, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.CarTypHolder car = new uclm.esi.cardroid.data.zerocice.CarTypHolder();
        String usrEmail;
        __is.readObject(car);
        usrEmail = __is.readString();
        __is.readPendingObjects();
        __inS.endReadParams();
        uclm.esi.cardroid.data.zerocice.CarTypPrx __ret = __obj.updateCarDataEmail(car.value, usrEmail, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.data.zerocice.CarTypPrxHelper.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___addCar(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.CarTypHolder car = new uclm.esi.cardroid.data.zerocice.CarTypHolder();
        uclm.esi.cardroid.data.zerocice.UserTypPrx usr;
        __is.readObject(car);
        usr = uclm.esi.cardroid.data.zerocice.UserTypPrxHelper.__read(__is);
        __is.readPendingObjects();
        __inS.endReadParams();
        uclm.esi.cardroid.data.zerocice.CarTypPrx __ret = __obj.addCar(car.value, usr, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.data.zerocice.CarTypPrxHelper.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___addCarEmail(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.CarTypHolder car = new uclm.esi.cardroid.data.zerocice.CarTypHolder();
        String usrEmail;
        __is.readObject(car);
        usrEmail = __is.readString();
        __is.readPendingObjects();
        __inS.endReadParams();
        uclm.esi.cardroid.data.zerocice.CarTypPrx __ret = __obj.addCarEmail(car.value, usrEmail, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        uclm.esi.cardroid.data.zerocice.CarTypPrxHelper.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___removeCar(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        uclm.esi.cardroid.data.zerocice.CarTypPrx car;
        uclm.esi.cardroid.data.zerocice.UserTypPrx usr;
        car = uclm.esi.cardroid.data.zerocice.CarTypPrxHelper.__read(__is);
        usr = uclm.esi.cardroid.data.zerocice.UserTypPrxHelper.__read(__is);
        __inS.endReadParams();
        __obj.removeCar(car, usr, __current);
        __inS.__writeEmptyParams();
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___removeCarPlateEmail(CardroidManager __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        String plate;
        String ownerEmail;
        plate = __is.readString();
        ownerEmail = __is.readString();
        __inS.endReadParams();
        __obj.removeCarPlateEmail(plate, ownerEmail, __current);
        __inS.__writeEmptyParams();
        return Ice.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
        "addCar",
        "addCarEmail",
        "calculatePriceEstimation",
        "getCarFromPlate",
        "getCarFromPlateEmail",
        "getMessageTalks",
        "getMessageTalksSpeakers",
        "getPassengerTrips",
        "getTripFromId",
        "getTripOfferFromId",
        "getTripRequestFromId",
        "getUserActivity",
        "getUserFromEmail",
        "getUserPlaces",
        "getUserTrips",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "joinTrip",
        "newMessage",
        "newTripOffer",
        "newTripRequest",
        "organizeTrip",
        "removeCar",
        "removeCarPlateEmail",
        "searchTrips",
        "updateCarData",
        "updateCarDataEmail",
        "updateUserData",
        "userTripRegistered"
    };

    public Ice.DispatchStatus __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
        int pos = java.util.Arrays.binarySearch(__all, __current.operation);
        if(pos < 0)
        {
            throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return ___addCar(this, in, __current);
            }
            case 1:
            {
                return ___addCarEmail(this, in, __current);
            }
            case 2:
            {
                return ___calculatePriceEstimation(this, in, __current);
            }
            case 3:
            {
                return ___getCarFromPlate(this, in, __current);
            }
            case 4:
            {
                return ___getCarFromPlateEmail(this, in, __current);
            }
            case 5:
            {
                return ___getMessageTalks(this, in, __current);
            }
            case 6:
            {
                return ___getMessageTalksSpeakers(this, in, __current);
            }
            case 7:
            {
                return ___getPassengerTrips(this, in, __current);
            }
            case 8:
            {
                return ___getTripFromId(this, in, __current);
            }
            case 9:
            {
                return ___getTripOfferFromId(this, in, __current);
            }
            case 10:
            {
                return ___getTripRequestFromId(this, in, __current);
            }
            case 11:
            {
                return ___getUserActivity(this, in, __current);
            }
            case 12:
            {
                return ___getUserFromEmail(this, in, __current);
            }
            case 13:
            {
                return ___getUserPlaces(this, in, __current);
            }
            case 14:
            {
                return ___getUserTrips(this, in, __current);
            }
            case 15:
            {
                return ___ice_id(this, in, __current);
            }
            case 16:
            {
                return ___ice_ids(this, in, __current);
            }
            case 17:
            {
                return ___ice_isA(this, in, __current);
            }
            case 18:
            {
                return ___ice_ping(this, in, __current);
            }
            case 19:
            {
                return ___joinTrip(this, in, __current);
            }
            case 20:
            {
                return ___newMessage(this, in, __current);
            }
            case 21:
            {
                return ___newTripOffer(this, in, __current);
            }
            case 22:
            {
                return ___newTripRequest(this, in, __current);
            }
            case 23:
            {
                return ___organizeTrip(this, in, __current);
            }
            case 24:
            {
                return ___removeCar(this, in, __current);
            }
            case 25:
            {
                return ___removeCarPlateEmail(this, in, __current);
            }
            case 26:
            {
                return ___searchTrips(this, in, __current);
            }
            case 27:
            {
                return ___updateCarData(this, in, __current);
            }
            case 28:
            {
                return ___updateCarDataEmail(this, in, __current);
            }
            case 29:
            {
                return ___updateUserData(this, in, __current);
            }
            case 30:
            {
                return ___userTripRegistered(this, in, __current);
            }
        }

        assert(false);
        throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
    }

    protected void __writeImpl(IceInternal.BasicStream __os)
    {
        __os.startWriteSlice(ice_staticId(), -1, true);
        __os.endWriteSlice();
    }

    protected void __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        __is.endReadSlice();
    }

    public static final long serialVersionUID = 0L;
}
