package com.example.geoprofesor;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;
    TextView textoLAT;
    TextView textoLON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (status == ConnectionResult.SUCCESS) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } else {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, (Activity) getApplicationContext(), 10);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        miUbicacion();
    }

    private void agregarMarcador(double lat, double lng) {

        LatLng coordenadas = new LatLng(lat, lng);

        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 18);
        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).
                title("Posicion actual")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.maker3)));
        mMap.animateCamera(miUbicacion);
    }

    private void actualizarUbicacion(Location location) {

        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            float resultado[] = new float[1];

            Location.distanceBetween(lat, lng, 19.1654923, -96.1142007, resultado);

            //  Toast.makeText(this, "Reultado" + resultado, Toast.LENGTH_LONG).show();

            Circle circle = mMap.addCircle(new CircleOptions()
                    .center(new LatLng(19.1654923, -96.1142007))
                    .radius(80)
                    .strokeColor(Color.RED));

            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(lat, lng), new LatLng(19.1654923, -96.1142007))
                    .width(5)
                    .color(Color.GREEN));

            //Toast.makeText(this, "Reultado: " + resultado[0], Toast.LENGTH_LONG).show();

            if (resultado[0] < 80) {
                Toast.makeText(this, "Dentro: " + resultado[0], Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Fuera: " + resultado[0], Toast.LENGTH_LONG).show();
            }

            agregarMarcador(lat, lng);

              /*
            if (((resultado[1])*-1) < ) {
                //Toast.makeText(this, "Dentro" + (resultado[1])*-1, Toast.LENGTH_LONG).show();
            } else {
               // Toast.makeText(this, "Fuera" + (resultado[1])*-1, Toast.LENGTH_LONG).show();
            }

            if (resultado[0] > circle.getRadius()) {
                Toast.makeText(this, "Dentro" + resultado[1], Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Fuera" + resultado[3], Toast.LENGTH_LONG).show();
            }





            /*

            Location locationA = new Location("Este eres tu Eduardo");
            locationA.setLatitude(lat);
            locationA.setLongitude(lng);

            Location locationB = new Location("Adonde quieres llegar");
            locationB.setLatitude(19.205598);
            locationB.setLatitude(-96.191610);

            double distance = locationA.distanceTo(locationB);





            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(lat, lng), new LatLng(19.1654923, -96.1142007))
                    .width(5)
                    .color(Color.GREEN));


        /*Polygon poligone = mMap.addPolygon(new PolygonOptions()
                    .add(new LatLng(19.1648453, -96.1139714), new LatLng(19.1646834, -96.1139285), new LatLng(19.1647224, -96.1137266), new LatLng(19.1649175, -96.1137974))
                    .strokeColor(Color.RED)

            );*/



           /* if (lat > 19.1648453 && lng > -96.1139714) {
                Toast.makeText(this, "Dentro del rango a" + distance + "mts.", Toast.LENGTH_LONG).show();
                if (lat < 19.1647224 && lng > -96.1137266) {
                    Toast.makeText(this, "Dentro del rango b" + distance + "mts.", Toast.LENGTH_LONG).show();
                    if (lat > 19.1646834 && lng > -96.1139285) {
                        Toast.makeText(this, "Dentro del rango c" + distance + "mts.", Toast.LENGTH_LONG).show();
                        if (lat < 19.1649175 && lng > -96.1137974){
                            Toast.makeText(this, "Dentro del rango d" + distance + "mts.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            if (lat < 19.1648453 || lat > 19.1647224 || lat < 19.1646834 || lat > 19.1649175 && lng > -96.1139714 || lng > -96.1137266 || lng < -96.1139285 || lng > -96.1137974)
                Toast.makeText(this, "Dentro del rango" + distance + "mts.", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, " No Estas Dentro del rango" + distance + "mts.", Toast.LENGTH_LONG).show();*/

            // Toast.makeText(this, "Estas afuera del rango" + distance + "mts.", Toast.LENGTH_LONG).show();

            //  Toast.makeText(this, "Metros al punto deseado: " + distance + "mts.", Toast.LENGTH_LONG).show();
            //  Toast.makeText(this, "Lat: " + lat + "." + " Long: " + lng + ".", Toast.LENGTH_LONG).show();


        }
    }



/*
    public void Location(double latnew, double longnew) {
        Location locationA = new Location("Este eres tu Eduardo");
        locationA.setLatitude(latnew);
        locationA.setLongitude(longnew);

        Location locationB = new Location("Adonde quieres llegar");
        locationB.setLatitude(19.205276);
        locationB.setLatitude(-96.191520);

        double distance = locationA.distanceTo(locationB);

        Toast.makeText(this,"Metros a al punto deseado: "+distance+"mts.", Toast.LENGTH_LONG);

    }
*/

    LocationListener locListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            miUbicacion();
            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
//            Toast.makeText(MapsActivity.this, "Activando el GPS", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onProviderDisabled(String provider) {
            // Toast.makeText(MapsActivity.this, "Desactivando el GPS", Toast.LENGTH_SHORT).show();
        }
    };

    private void miUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locListener);
    }
        /*mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        // Marcador del reco

        LatLng RECO = new LatLng(19.164895, -96.113976);
        mMap.addMarker(new MarkerOptions().position(RECO).title("Eduardo esta por el RECO"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(RECO));

        float zoomlevel = 16;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(RECO,zoomlevel));*/

}
