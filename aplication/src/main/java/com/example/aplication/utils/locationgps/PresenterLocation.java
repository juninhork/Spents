package com.example.aplication.utils.locationgps;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.example.aplication.utils.base.BasePresenter;
import com.example.aplication.utils.locationgps.interfaces.ILocationCalback;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;


/**
 * Created by juniorbraga on 16/03/17.
 */

public class PresenterLocation extends BasePresenter<ILocationCalback> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private Context mContext;
    private GoogleApiClient mGoogleApiClient;
    private LatLng mLocation;

    public PresenterLocation(Context context, ILocationCalback view) {
        super(context, view);
        this.mContext = context;
    }

    protected PresenterLocation(ILocationCalback view) {
        super(view);
    }

    public synchronized void callConection() {
        // Create an instance of GoogleAPIClient.
        if (this.mGoogleApiClient == null) {
            this.mGoogleApiClient = new GoogleApiClient.Builder(this.mContext)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        this.mGoogleApiClient.connect();
    }

    // Listener
    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this.mContext, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this.mContext,"Habilitar acesso ao GPS no Device", Toast.LENGTH_LONG).show();
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);

        if(mLastLocation != null){
            this.mLocation=new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());
            this.mView.showLocationUser(this.mLocation);
//            new ReverseGeocodingTask(mContext,this).execute(mLocation);
            recorver(this.mLocation);

        }
    }


    public String recorver(LatLng... params) {
        Geocoder geocoder = new Geocoder(this.mContext);
        double latitude = params[0].latitude;
        double longitude = params[0].longitude;

        List<Address> addresses = null;
        String addressText="";

        try {
            addresses = geocoder.getFromLocation(latitude, longitude,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(addresses != null && addresses.size() > 0 ){
            android.location.Address address = addresses.get(0);

            addressText = String.format("%s, %s, %s",
                    address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                    address.getLocality(),
                    address.getCountryName());
        }
        mView.showLocationName(addressText);
        return addressText;
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}