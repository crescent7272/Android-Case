package com.example.androidcase.activity;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.androidcase.R;
import com.example.androidcase.model.AnttPricesRequestModel;
import com.example.androidcase.model.AnttPricesModel;
import com.example.androidcase.model.ResponseModel;
import com.example.androidcase.my_interface.GetNoticeDataService;
import com.example.androidcase.network.RetrofitInstance;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.gson.Gson;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private PlacesClient mPlacesClient;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    ResponseModel responseModel = null;
    AnttPricesRequestModel anttPricesRequestModel = null;
    PolylineOptions options;
    TextView originAddress;
    TextView destinationAddress;
    TextView axes;
    TextView distance;
    TextView duration;
    TextView toll;
    TextView fuelRequired;
    TextView fuelTotal;
    TextView total;
    TextView general;
    TextView bulk;
    TextView neogranel;
    TextView refrigerated;
    TextView dangerous;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        originAddress      = findViewById(R.id.originAddress);
        destinationAddress = findViewById(R.id.destinationAddress);
        axes               = findViewById(R.id.axes);
        distance           = findViewById(R.id.distance);
        duration           = findViewById(R.id.duration);
        toll               = findViewById(R.id.toll);
        fuelRequired       = findViewById(R.id.fuelRequired);
        fuelTotal          = findViewById(R.id.fuelTotal);
        total              = findViewById(R.id.total);
        general            = findViewById(R.id.general);
        bulk               = findViewById(R.id.bulk);
        neogranel          = findViewById(R.id.neogranel);
        refrigerated       = findViewById(R.id.refrigerated);
        dangerous          = findViewById(R.id.dangerous);



        String responseData = "";
        String axles = "";
        String originAddressText = "";
        String destinationAddressText = "";
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            responseData = null;
        } else {
            responseData= extras.getString("response");
            axles = extras.getString("axis");
            originAddressText = extras.getString("originAddress");
            destinationAddressText = extras.getString("destinationAddress");
        }

        gson = new Gson();
        Log.d("MapsActivity", responseData+"");

        responseModel = gson.fromJson(responseData, ResponseModel.class);

        originAddress.setText(originAddressText);
        destinationAddress.setText(destinationAddressText);
        axes.setText(axles);
        distance.setText(responseModel.getDistance()+"");
        duration.setText(responseModel.getDuration()+"");
        toll.setText(responseModel.getTollCount()+"");
        fuelRequired.setText(responseModel.getTollCostUnit()+ " "+ responseModel.getTollCost() );
        fuelTotal.setText(responseModel.getFuelCostUnit()+ " "+ responseModel.getFuelCost());
        total.setText(""+responseModel.getTotalCost());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String apiKey = getString(R.string.google_maps_key);
        Places.initialize(getApplicationContext(), apiKey);
        mPlacesClient = Places.createClient(this);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //if(responseModel!= null && responseModel.getPoints() != null)


        anttPricesRequestModel = new AnttPricesRequestModel((int)(Double.parseDouble(axles)), Double.valueOf(responseModel.getDistance()), true);

        GetNoticeDataService service = RetrofitInstance.getRetrofitInstance(RetrofitInstance.getBaseUrl2()).create(GetNoticeDataService.class);

        String anttPricesRequestModelString = gson.toJson(anttPricesRequestModel);

        Call<AnttPricesModel> call = service.getAnttPrices(anttPricesRequestModel);


        /**Log the URL called*/
       Log.wtf("URL Called", call.request().url() + " ");
       Log.wtf("URL Called2", " "+ anttPricesRequestModelString);

        call.enqueue(new Callback<AnttPricesModel>() {

            @Override
            public void onResponse(Call<AnttPricesModel> call, Response<AnttPricesModel> response) {

                Log.wtf("URL Called", gson.toJson(response));

                general.setText(response.body().getGeneral()+"");
                bulk.setText(response.body().getBulk()+"");
                neogranel.setText(response.body().getNeogranel()+"");
                refrigerated.setText(response.body().getRefrigerated()+"");
                dangerous.setText(response.body().getDangerous()+"");

            }

            @Override
            public void onFailure(Call<AnttPricesModel> call, Throwable t) {

            }
        });


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng destination = new LatLng( responseModel.getRoute().get(0).get(responseModel.getRoute().get(0).size()-1).get(1),
                responseModel.getRoute().get(0).get(responseModel.getRoute().get(0).size()-1).get(0));

        drawPrimaryLinePath(responseModel.getRoute().get(0));
        mMap.addMarker(new MarkerOptions().position(destination).title("Target"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination,12.0f));


        /*
        if(options!= null) {
            Log.d("MapsActivity", "options!= null");

            mMap.addPolyline(options);
        }*/
        // Add a marker in Sydney and move the camera

        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));



    }

    private void drawPrimaryLinePath( List<List<Double>> responsePoints )
    {
        if ( responsePoints.size() < 2 )
        {
            return;
        }

        options = new PolylineOptions();

        options.color( Color.BLUE ).geodesic(true);
        options.width( 5 );

        for ( List<Double> points : responsePoints )
        {
            Log.d("MapsActivity", "points[0] "+ points.get(0) + "points[1] "+ points.get(1));

            options.add( new LatLng( points.get(1), points.get(0) ));
        }

        if(mMap!= null ) {

            Log.d("MapsActivity", "mMap!= null");

            mMap.addPolyline(options);
        }


    }
}
