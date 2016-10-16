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

/**
 * Bus information
 *
 * Translink Canada - Vancouver - API reference:
 *   https://developer.translink.ca/ServicesRtti/ApiReference
 */
public class Bus {
    /** The vehicle number of the bus */
    public int VehicleNo;

    /** The id of the trip the bus currently running */
    public long TripId;

    /** The route number of the vehicle */
    public String RouteNo;

    /** The direction of the trip */
    public String Direction;

    /** The pattern of the trip */
    public String Pattern;

    /** The latitude of the vehicle location */
    public double Latitude;

    /** The longitude of the vehicle location */
    public double Longitude;

    /** The recorded time of the last location of the vehicle */
    public String RecordedTime;

    /** The route map information */
    public RouteMap RouteMap;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bus{");
        sb.append("VehicleNo='").append(VehicleNo).append('\'');
        sb.append(", TripId='").append(TripId).append('\'');
        sb.append(", RouteNo='").append(RouteNo).append('\'');
        sb.append(", Direction='").append(Direction).append('\'');
        sb.append(", Pattern='").append(Pattern).append('\'');
        sb.append(", Latitude='").append(Latitude).append('\'');
        sb.append(", Longitude='").append(Longitude).append('\'');
        sb.append(", RecordedTime='").append(RecordedTime).append('\'');
        sb.append(", RouteMap='").append(RouteMap).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
