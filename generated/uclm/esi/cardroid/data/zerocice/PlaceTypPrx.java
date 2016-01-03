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

public interface PlaceTypPrx extends Ice.ObjectPrx
{
    public String getName();

    public String getName(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getName();

    public Ice.AsyncResult begin_getName(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getName(Ice.Callback __cb);

    public Ice.AsyncResult begin_getName(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getName(Callback_PlaceTyp_getName __cb);

    public Ice.AsyncResult begin_getName(java.util.Map<String, String> __ctx, Callback_PlaceTyp_getName __cb);

    public String end_getName(Ice.AsyncResult __result);

    public void setName(String name);

    public void setName(String name, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_setName(String name);

    public Ice.AsyncResult begin_setName(String name, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_setName(String name, Ice.Callback __cb);

    public Ice.AsyncResult begin_setName(String name, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_setName(String name, Callback_PlaceTyp_setName __cb);

    public Ice.AsyncResult begin_setName(String name, java.util.Map<String, String> __ctx, Callback_PlaceTyp_setName __cb);

    public void end_setName(Ice.AsyncResult __result);

    public LatLngTyp getCoords();

    public LatLngTyp getCoords(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getCoords();

    public Ice.AsyncResult begin_getCoords(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getCoords(Ice.Callback __cb);

    public Ice.AsyncResult begin_getCoords(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getCoords(Callback_PlaceTyp_getCoords __cb);

    public Ice.AsyncResult begin_getCoords(java.util.Map<String, String> __ctx, Callback_PlaceTyp_getCoords __cb);

    public LatLngTyp end_getCoords(Ice.AsyncResult __result);

    public void setCoords(LatLngTyp coords);

    public void setCoords(LatLngTyp coords, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_setCoords(LatLngTyp coords);

    public Ice.AsyncResult begin_setCoords(LatLngTyp coords, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_setCoords(LatLngTyp coords, Ice.Callback __cb);

    public Ice.AsyncResult begin_setCoords(LatLngTyp coords, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_setCoords(LatLngTyp coords, Callback_PlaceTyp_setCoords __cb);

    public Ice.AsyncResult begin_setCoords(LatLngTyp coords, java.util.Map<String, String> __ctx, Callback_PlaceTyp_setCoords __cb);

    public void end_setCoords(Ice.AsyncResult __result);

    public String getDescription();

    public String getDescription(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getDescription();

    public Ice.AsyncResult begin_getDescription(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getDescription(Ice.Callback __cb);

    public Ice.AsyncResult begin_getDescription(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getDescription(Callback_PlaceTyp_getDescription __cb);

    public Ice.AsyncResult begin_getDescription(java.util.Map<String, String> __ctx, Callback_PlaceTyp_getDescription __cb);

    public String end_getDescription(Ice.AsyncResult __result);

    public void setDescription(String description);

    public void setDescription(String description, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_setDescription(String description);

    public Ice.AsyncResult begin_setDescription(String description, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_setDescription(String description, Ice.Callback __cb);

    public Ice.AsyncResult begin_setDescription(String description, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_setDescription(String description, Callback_PlaceTyp_setDescription __cb);

    public Ice.AsyncResult begin_setDescription(String description, java.util.Map<String, String> __ctx, Callback_PlaceTyp_setDescription __cb);

    public void end_setDescription(Ice.AsyncResult __result);

    public boolean hasDescription();

    public boolean hasDescription(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_hasDescription();

    public Ice.AsyncResult begin_hasDescription(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_hasDescription(Ice.Callback __cb);

    public Ice.AsyncResult begin_hasDescription(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_hasDescription(Callback_PlaceTyp_hasDescription __cb);

    public Ice.AsyncResult begin_hasDescription(java.util.Map<String, String> __ctx, Callback_PlaceTyp_hasDescription __cb);

    public boolean end_hasDescription(Ice.AsyncResult __result);

    public byte[] getSnapshotBytes();

    public byte[] getSnapshotBytes(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getSnapshotBytes();

    public Ice.AsyncResult begin_getSnapshotBytes(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getSnapshotBytes(Ice.Callback __cb);

    public Ice.AsyncResult begin_getSnapshotBytes(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getSnapshotBytes(Callback_PlaceTyp_getSnapshotBytes __cb);

    public Ice.AsyncResult begin_getSnapshotBytes(java.util.Map<String, String> __ctx, Callback_PlaceTyp_getSnapshotBytes __cb);

    public byte[] end_getSnapshotBytes(Ice.AsyncResult __result);

    public void setSnapshotBytes(byte[] snapshotBytes);

    public void setSnapshotBytes(byte[] snapshotBytes, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_setSnapshotBytes(byte[] snapshotBytes);

    public Ice.AsyncResult begin_setSnapshotBytes(byte[] snapshotBytes, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_setSnapshotBytes(byte[] snapshotBytes, Ice.Callback __cb);

    public Ice.AsyncResult begin_setSnapshotBytes(byte[] snapshotBytes, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_setSnapshotBytes(byte[] snapshotBytes, Callback_PlaceTyp_setSnapshotBytes __cb);

    public Ice.AsyncResult begin_setSnapshotBytes(byte[] snapshotBytes, java.util.Map<String, String> __ctx, Callback_PlaceTyp_setSnapshotBytes __cb);

    public void end_setSnapshotBytes(Ice.AsyncResult __result);

    public boolean hasSnapshot();

    public boolean hasSnapshot(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_hasSnapshot();

    public Ice.AsyncResult begin_hasSnapshot(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_hasSnapshot(Ice.Callback __cb);

    public Ice.AsyncResult begin_hasSnapshot(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_hasSnapshot(Callback_PlaceTyp_hasSnapshot __cb);

    public Ice.AsyncResult begin_hasSnapshot(java.util.Map<String, String> __ctx, Callback_PlaceTyp_hasSnapshot __cb);

    public boolean end_hasSnapshot(Ice.AsyncResult __result);

    public String _toString();

    public String _toString(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_toString();

    public Ice.AsyncResult begin_toString(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_toString(Ice.Callback __cb);

    public Ice.AsyncResult begin_toString(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_toString(Callback_PlaceTyp_toString __cb);

    public Ice.AsyncResult begin_toString(java.util.Map<String, String> __ctx, Callback_PlaceTyp_toString __cb);

    public String end_toString(Ice.AsyncResult __result);
}