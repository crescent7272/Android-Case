package com.example.androidcase.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.androidcase.R;
import com.example.androidcase.adapter.PlaceArrayAdapter;
import com.example.androidcase.model.RequestModel;
import com.example.androidcase.model.ResponseModel;
import com.example.androidcase.my_interface.GetNoticeDataService;
import com.example.androidcase.network.RetrofitInstance;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends FragmentActivity {
    private PlaceAutocompleteFragment placeAutocompleteFragment;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    int LAUNCH_MAPS_ACTIVITY = 1;
    int AUTOCOMPLETE_REQUEST_CODE = 123;
    private GoogleApiClient mGoogleApiClient;
    private PlaceArrayAdapter mPlaceArrayAdapter;
    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));
    PlacesClient placesClient;
    RequestModel requestModel;
    Places places;

    EditText origin;
    EditText destination;
    EditText axles;
    EditText avgFuelConsumption;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        origin = findViewById(R.id.origin);
        destination = findViewById(R.id.destination);
        axles = findViewById(R.id.fuelPrice);
        avgFuelConsumption = findViewById(R.id.avgFuelConsumption);
        Button calculate = findViewById(R.id.calculate);

        requestModel = new RequestModel();


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(origin.getText() != null && destination.getText() != null
                        && axles.getText() != null && avgFuelConsumption.getText() != null )
                {
                    //send data to the API

                    List<com.example.androidcase.model.Place> placesList = new ArrayList<>();

                    List<Double> originPoints = new ArrayList<>();
                    List<Double> destinationPoints = new ArrayList<>();



                    originPoints.add(getLocationFromAddress(origin.getText().toString()).longitude);
                    originPoints.add(getLocationFromAddress(origin.getText().toString()).latitude);

                    destinationPoints.add(getLocationFromAddress(destination.getText().toString()).longitude);
                    destinationPoints.add(getLocationFromAddress(destination.getText().toString()).latitude);

                    placesList.add(new com.example.androidcase.model.Place(originPoints));
                    placesList.add(new com.example.androidcase.model.Place(destinationPoints));

                    requestModel.setPlaces(placesList);
                    requestModel.setFuelConsumption(Integer.parseInt(avgFuelConsumption.getText().toString()));
                    requestModel.setFuelPrice(Double.parseDouble(axles.getText().toString()));
                    /** Create handle for the RetrofitInstance interface*/
                    GetNoticeDataService service = RetrofitInstance.getRetrofitInstance(RetrofitInstance.getBaseUrl()).create(GetNoticeDataService.class);

                    Gson gson = new Gson();
                    String requestModelString = gson.toJson(requestModel);

                    requestModel = gson.fromJson(requestModelString, RequestModel.class);
                    /** Call the method with parameter in the interface to get the notice data*/
                    Call<ResponseModel> call = service.getRoute(requestModel);

                    /**Log the URL called*/
                    Log.wtf("URL Called", call.request().url() + " "+ requestModelString);

                    call.enqueue(new Callback<ResponseModel>() {

                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            Log.d("URL Called", call.request().url() + "");

                            if(response.code() == 200)
                            {
                                Gson gson = new Gson();
                                String responseS = gson.toJson(response.body());
                                Log.d("URL Called2", responseS + "");

                                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                                intent.putExtra("response",responseS);
                                intent.putExtra("axis",axles.getText().toString());
                                intent.putExtra("originAddress",origin.getText().toString());
                                intent.putExtra("destinationAddress",destination.getText().toString());
                                startActivity(intent);
                            }



                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            Log.d("onfailure", t.getMessage() + "");
                            Toast.makeText(MainActivity.this, "Ocorreu um erro ... Mensagem de erro: "+t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }

                else
                {
                    Toast.makeText(MainActivity.this, "Por favor, preencha os campos. ", Toast.LENGTH_SHORT).show();


                }








            }
        });




        //origin.setThreshold(3);
        //origin.setAdapter(mPlaceArrayAdapter);
/*
        origin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Set the fields to specify which types of place data to return.
                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);
                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY, fields)
                        .build(MainActivity.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);

            }
        });*/
        //origin.setOnItemClickListener(mAutocompleteClickListener);

        String apiKey = getString(R.string.google_maps_key);

        Places.initialize(getApplicationContext(), apiKey);
        placesClient = Places.createClient(this);
/*
        mPlaceArrayAdapter = new PlaceArrayAdapter(this, android.R.layout.simple_list_item_1,
                BOUNDS_MOUNTAIN_VIEW, null);
*/

        //AutocompleteSupportFragment

    }
    /*
    private void createAutoCompleteIntent() {

        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields)
                .build(MainActivity.this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }
*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i("MainActivity", "Place: " + place.getName() + ", " + place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("MainActivity", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }


        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i("tag", "Place: " + place.getName() + ", " + place.getLatLng());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("tag", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

/*
    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final PlaceArrayAdapter.PlaceAutocomplete item = mPlaceArrayAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);
            Log.i("MainActivity", "Selected: " + item.description);
            List<Place.Field> placeFields = Arrays.asList(Place.Field.LAT_LNG);

            FetchPlaceRequest fetchPlaceRequest = FetchPlaceRequest.newInstance(placeId,placeFields );

            //PendingResult<PlaceBuffer> placeResult = placesClient.fetchPlace(fetchPlaceRequest);
            Task<FetchPlaceResponse> placeResult = placesClient.fetchPlace(fetchPlaceRequest);
            placeResult.addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
                @Override
                public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {
                    final Place place = fetchPlaceResponse.getPlace();
                    CharSequence attributions = place.getAttributions().get(0);
                    origin.setText(attributions);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

            Log.i("MainActivity", "Fetching details for ID: " + item.placeId);
        }
    };
*/
    public LatLng getLocationFromAddress(String strAddress){

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        //GeoPoint p1 = null;
        double lat;
        double lng;
        LatLng latLng = null;
        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            lat= location.getLatitude();
            lng=location.getLongitude();
            latLng = new LatLng(lat, lng);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return latLng;

    }
}
