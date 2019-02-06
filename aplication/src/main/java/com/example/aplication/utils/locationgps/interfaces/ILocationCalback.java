package com.example.aplication.utils.locationgps.interfaces;

import com.example.aplication.utils.base.BaseView;
import com.google.android.gms.maps.model.LatLng;


/**
 * Created by juniorbraga on 16/03/17.
 */

public interface ILocationCalback extends BaseView {
    void showLocationUser(LatLng locationUser);
    void showLocationName(String locationName);
}