package com.google.movessample;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.movessample.addressModels.AddressModel;
import com.google.movessample.models.ApixuWeatherModel;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;

    TextView location, device_time, region_time, region_temp;
    EditText search_location;
    Button btn_go;
    ImageView nightMood, dayMood;

    // api key for getting  lat lon address  =https://api.opencagedata.com/geocode/v1/json?q=PLACENAME&key=3b4c0949431646d2a72538c0bc1c6d07


    String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the night is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        bind();
        device_time.setText("Device time : "+mydate);
        getPermissions();

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (search_location.length()!=0){
                    String value = search_location.getText().toString().trim();
                    searchLocation(value);
                }else {
                    search_location.setError("Enter address");
                }

            }
        });

        nightMood.setOnClickListener(this);
        dayMood.setOnClickListener(this);
    }

    void bind() {
        location = findViewById(R.id.location);
        search_location = findViewById(R.id.search_location);
        btn_go = findViewById(R.id.btn_go);
        device_time = findViewById(R.id.device_time);
        region_time = findViewById(R.id.region_time);
        nightMood = findViewById(R.id.nightMood);
        dayMood = findViewById(R.id.dayMood);
        region_temp = findViewById(R.id.region_temp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nightMood:
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.night));
                search_location.setHintTextColor(Color.rgb(255, 255, 255));
                location.setTextColor(Color.rgb(255,255,255));
                break;

            case R.id.dayMood:
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map));
                search_location.setHintTextColor(Color.rgb(0, 0, 0));
                break;
        }
    }

    private void searchLocation(String value) {
        String url = "https://api.opencagedata.com/geocode/v1/json?q=" + value + "&key=3b4c0949431646d2a72538c0bc1c6d07";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                showLocationOnMAp(responseString);
            }
        });

    }

    private void showLocationOnMAp(String responseString) {
        Gson gson = new Gson();
        AddressModel addressModel = gson.fromJson(responseString, AddressModel.class);

        if (addressModel.getStatus().getMessage().equals("OK")) {

            double lat = addressModel.getResults().
                    get(0).getGeometry().getLat();
            double lon = addressModel.getResults().
                    get(0).getGeometry().getLng();

            LatLng point = new LatLng(lat, lon);
            CameraUpdate location = CameraUpdateFactory.newLatLngZoom(point, 15);
            mMap.animateCamera(location);

        } else {
            Toast.makeText(MapsActivity.this, "Address Not Found !", Toast.LENGTH_LONG).show();
            Log.e("StatusCode:", String.valueOf(addressModel.getStatus().getCode()));
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map));
        getPermissions();

        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                double lat = mMap.getCameraPosition().target.latitude;
                double lon = mMap.getCameraPosition().target.longitude;
                //    location.setText(lat + " " + lon);
                location.setText(getCompleteAddressString(lat, lon));
                setRegionTime(lat, lon);
                showTemp(lat, lon);
            }
        });

    }


    void getMyLocation() {
        SmartLocation.with(this).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        double lat = location.getLatitude();
                        double lon = location.getLongitude();
                        LatLng myLocation = new LatLng(lat, lon);
                        mMap.addMarker(new MarkerOptions().position(myLocation).title("I am here"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));

                        LatLng a = new LatLng(lat + 0.003, lon + 0.003);
                        mMap.addMarker(new MarkerOptions().position(a).title("I am in A"));

                        LatLng b = new LatLng(lat + 0.006, lon + 0.006);
                        mMap.addMarker(new MarkerOptions().position(b).title("I am in B"));

                        Marker sample = mMap.addMarker(new MarkerOptions()
                                .position(b)
                                .title("You are here")
                                .rotation(90)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));
                    }
                });
    }

    void getPermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION, LOCATION_SERVICE
                ).withListener(new MultiplePermissionsListener() {

            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                getMyLocation();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                getMyLocation();
            }
        }).check();
    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current location ", strReturnedAddress.toString());
            } else {
                Log.w("My Current location ", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current location ", "Cant get Address!");
        }
        return strAdd;

    }

    //https://api.apixu.com/v1/current.json?key=c4662836cc5848bfa8784116190903&q=35,50
    public void setRegionTime(double lat, double lon) {

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.apixu.com/v1/current.json?key=c4662836cc5848bfa8784116190903&q=" + lat + "," + lon;
        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                showData(responseString);
            }

        });

    }

    private void showData(String responseString) {
        Gson gson = new Gson();
        ApixuWeatherModel apixuWeatherModel = gson.fromJson(responseString, ApixuWeatherModel.class);
        String regTime = apixuWeatherModel.getLocation().getLocaltime();
        region_time.setText("Local time :" + regTime);

    }

    // https://api.apixu.com/v1/current.json?key=c4662836cc5848bfa8784116190903&q=35,50

    private void showTemp(final double lat, double lon) {
        String url = "https://api.apixu.com/v1/current.json?key=c4662836cc5848bfa8784116190903&q=" + lat + "," + lon;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                parseData(responseString);
            }
        });

    }

    private void parseData(String response) {
        Gson gson = new Gson();
        ApixuWeatherModel apixuWeatherModel = gson.fromJson(response, ApixuWeatherModel.class);
        Double TempC1 = apixuWeatherModel.getCurrent().getTempC();
        String TempC2 = String.format("%.0f", TempC1);
        region_temp.setText("Here is " + TempC2 + " °C");
    }

}
