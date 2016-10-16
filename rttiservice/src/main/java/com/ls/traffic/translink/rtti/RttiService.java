/**
 * Created by Leonardo Simberg on 4/11/2016.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.ls.traffic.translink.rtti;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * 	Endpoints to access the Real-Time Transit Information - RTTI Open API
 *
 * Translink Canada - Vancouver - API reference:
 *   https://developer.translink.ca/ServicesRtti/ApiReference
 */
public interface RttiService {
    //-------------------------------------------------------------------------------------//
    //                          Stops
    //-------------------------------------------------------------------------------------//

    /**
     * Returns stop details for stop <i>stopNo</i>
     *
     * @param stopNo A five-digit stop number
     * @return
     */
    @GET("rttiapi/v1/stops/{stopNo}")
    Call<Stop> loadStop(@Path("stopNo") int stopNo);

    /**
     *  Returns stops near latitude/longitude coordinates, radius is defaulted to 500 meters
     *
     * @param latitude The latitude of your search (must be combined with long)
     * @param longitude The longitude of your search (must be combined with lat)
     * @return
     */
    @GET("rttiapi/v1/stops")
    Call<List<Stop>> loadStops(@Query("lat") double latitude, @Query("long") double longitude);

    /**
     *  Returns stops near latitude/longitude coordinates, filtered by 500 meters radius or specified <i>radius</i>
     *  and filtered by <i>routeNo</i> if it is not null.
     *
     * @param latitude The latitude of your search (must be combined with long)
     * @param longitude The longitude of your search (must be combined with lat)
     * @param routeNo if not null, Will search for stops specific to route
     * @param radius If not null, will search a radius for stops (must be combined with lat and long).
     *               Default 500. Maximum 2000.
     * @return
     */
    @GET("rttiapi/v1/stops")
    Call<List<Stop>> loadStops(@Query("lat") double latitude,
                               @Query("long") double longitude,
                               @Query("routeNo") Integer routeNo,
                               @Query("radius") Integer radius);


    //-------------------------------------------------------------------------------------//
    //                          Stop Estimates
    //-------------------------------------------------------------------------------------//

    /**
     * Returns the next 6 buses for each route to service the stop in the next 24 hours
     *
     * @param stopNo A five-digit stop number
     * @return
     */
    @GET("rttiapi/v1/stops/{stopNo}/estimates")
    Call<List<NextBus>> loadNextBuses(@Path("stopNo") int stopNo);

    /**
     * Returns the next 6 buses for <i>routeNo</i> service the stop in the next 24 hours
     *
     * @param stopNo A five-digit stop number
     * @param routeNo Stops specific to route
     * @return
     */
    @GET("rttiapi/v1/stops/{stopNo}/estimates")
    Call<List<NextBus>> loadNextBuses(@Path("stopNo") int stopNo, @Query("routeNo") int routeNo);

    /**
     *
     * @param stopNo A five-digit stop number
     * @param routeNo Stops specific to route. If null -> all routes
     * @param count The search time frame in minutes. If null -> Default 1440
     * @param timeframe The number of buses to return. If null -> Default 6
     * @return
     */
    @GET("rttiapi/v1/stops/{stopNo}/estimates")
    Call<List<NextBus>> loadNextBuses(@Path("stopNo") int stopNo,
                                      @Query("routeNo") Integer routeNo,
                                      @Query("count") Integer count,
                                      @Query("timeframe") Integer timeframe);


    //-------------------------------------------------------------------------------------//
    //                           Buses
    //-------------------------------------------------------------------------------------//

    /**
     * Returns details for bus id <i>busNo</i> if active, otherwise a 404 will be returned
     * @param busNo A vehicle id
     *
     * @return Bus details
     */
    @GET("rttiapi/v1/buses/{busNo}")
    Call<Bus> loadBus(@Path("busNo") int busNo);

    /**
     * Returns details for all active buses
     *
     * @return List of Buses
     */
    @GET("rttiapi/v1/buses")
    Call<List<Bus>> loadActiveBuses();

    /**
     * Returns all active buses serving stop <i>stopNo</i>
     *
     * @param stopNo Stop id
     * @return List of Buses
     */
    @GET("rttiapi/v1/buses")
    Call<List<Bus>> loadActiveBusesByStopNo(@Query("stopNo") int stopNo);

    /**
     * Returns all active buses serving stop <i>stopNo</i>
     *
     * @param routeNo routeNo id
     * @return List of Buses
     */
    @GET("rttiapi/v1/buses")
    Call<List<Bus>> loadActiveBusesByRouteNo(@Query("routeNo") int routeNo);

    /**
     * Returns all active buses serving stop <i>stopNo</i> and <i>routeNo</i>
     *
     * @param routeNo Route id
     * @param stopNo Stop id
     * @return List of Buses
     */
    @GET("rttiapi/v1/buses")
    Call<List<Bus>> loadActiveBuses(@Query("routeNo") int routeNo,
                                    @Query("stopNo") int stopNo);


    //-------------------------------------------------------------------------------------//
    //                      Routes
    //-------------------------------------------------------------------------------------//

    /**
     * Returns details for <i>routeNo</i>
     *
     * @param routeNo 	A bus route number
     * @return
     */
    @GET("rttiapi/v1/routes/{routeNo}")
    Call<Route> loadRoute(@Path("routeNo") int routeNo);

    /**
     * Returns routes serving <i>stopNo</i>
     *
     * @param stopNo  search for routes passing through this stop
     * @return
     */
    @GET("rttiapi/v1/routes")
    Call<List<Route>> loadRouteByStop(@Query("stopNo") int stopNo);


    //-------------------------------------------------------------------------------------//
    //                      Status
    //-------------------------------------------------------------------------------------//

    /**
     * Returns status of location updates
     *
     * @return
     */
    @GET("rttiapi/v1/status/location")
    Call<List<Status>> loadStatusLocation();

    /**
     * Returns status of schedule updates
     *
     * @return
     */
    @GET("rttiapi/v1/status/schedule")
    Call<List<Status>> loadStatusSchedule();

    /**
     * Returns status of location and schedule updates
     *
     * @return
     */
    @GET("rttiapi/v1/status/all")
    Call<List<Status>> loadStatusAll();
}


