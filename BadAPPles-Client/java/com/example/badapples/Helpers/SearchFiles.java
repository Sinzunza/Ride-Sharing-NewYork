package com.example.badapples.Helpers;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SearchFiles extends AppCompatActivity  {

    public static void search( Context context, int searchType, TextView result) {
        String url;
        RequestQueue queue;
        StringRequest stringRequest;
        queue = Volley.newRequestQueue(context);

        switch (searchType) {
            case 1:
                url = "http://192.168.29.207:8080//BadAPPles/RidesOnDate";


                // Request a string response from the provided URL.
                stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the response
                                result.setText(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        result.setText("That didn't work!");
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
                break;
            case 2:
                url = "http://192.168.29.207:8080//BadAPPles/RidesInBurrough";


                // Request a string response from the provided URL.
                stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the response
                                result.setText(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        result.setText("That didn't work!");
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
                break;
            case 3:
                url = "http://192.168.29.207:8080//BadAPPles/HowManyPast10Servlet";


                // Request a string response from the provided URL.
                stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the response
                                result.setText(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        result.setText("That didn't work!");
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
                break;
            case 4:
                url = "http://192.168.29.207:8080//BadAPPles/FirstClassServlet";


                // Request a string response from the provided URL.
                stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the response
                                result.setText(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        result.setText("That didn't work!");
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
                break;
            case 5:
                url = "http://192.168.29.207:8080//BadAPPles/ActiveVehiclesOver1000Servlet";


                // Request a string response from the provided URL.
                stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the response
                                result.setText(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        result.setText("That didn't work!");
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
                break;
            case 6:
                url = "http://192.168.29.207:8080//BadAPPles/StreetServlet";


                // Request a string response from the provided URL.
                stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the response
                                result.setText(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        result.setText("That didn't work!");
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
                break;
            default:

        }

    }
    public static void postNewComment(Context context, JSONObject jsonBody, int file ) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);


        final String requestBody = jsonBody.toString();


        switch (file) {
            case 1:
                String url1 = "http://192.168.29.207:8080//BadAPPles/RidesOnDate";

                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("VOLLEY", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.e("VOLLEY", error.toString());
                    }
                }) {
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        final Map<String, String> headers = new HashMap<>();
                        headers.put("Authorization", "Basic " + "My_auth_key");
                        headers.put("Content-Type", "application/json");

                        return headers;
                    }

                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        String responseString = "";
                        if (response != null) {
                            responseString = String.valueOf(response.statusCode);
                            // can get more details such as response.headers
                        }
                        return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                    }

                };
                requestQueue.add(stringRequest1);
                break;
            case 2:
                String url2 = "http://192.168.29.207:8080//BadAPPles/RidesInBurrough";

                StringRequest stringRequest2 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("VOLLEY", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.e("VOLLEY", error.toString());
                    }
                }) {
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        final Map<String, String> headers = new HashMap<>();
                        headers.put("Authorization", "Basic " + "My_auth_key");
                        headers.put("Content-Type", "application/json");

                        return headers;
                    }

                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        String responseString = "";
                        if (response != null) {
                            responseString = String.valueOf(response.statusCode);
                            // can get more details such as response.headers
                        }
                        return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                    }

                };


                requestQueue.add(stringRequest2);
                break;
            case 3:
                String url3 = "http://192.168.29.207:8080//BadAPPles/HowManyPast10Servlet";

                StringRequest stringRequest3 = new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("VOLLEY", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.e("VOLLEY", error.toString());
                    }
                }) {
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        final Map<String, String> headers = new HashMap<>();
                        headers.put("Authorization", "Basic " + "My_auth_key");
                        headers.put("Content-Type", "application/json");

                        return headers;
                    }

                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        String responseString = "";
                        if (response != null) {
                            responseString = String.valueOf(response.statusCode);
                            // can get more details such as response.headers
                        }
                        return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                    }

                };
                requestQueue.add(stringRequest3);
                break;
            case 4:
                String url4 = "http://192.168.29.207:8080//BadAPPles/HowManyPast10Servlet1";
                StringRequest stringRequest4 = new StringRequest(Request.Method.POST, url4, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("VOLLEY", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.e("VOLLEY", error.toString());
                    }
                }) {
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        final Map<String, String> headers = new HashMap<>();
                        headers.put("Authorization", "Basic " + "My_auth_key");
                        headers.put("Content-Type", "application/json");

                        return headers;
                    }

                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        String responseString = "";
                        if (response != null) {
                            responseString = String.valueOf(response.statusCode);
                            // can get more details such as response.headers
                        }
                        return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                    }

                };
                requestQueue.add(stringRequest4);
                break;

        }
    }

}

