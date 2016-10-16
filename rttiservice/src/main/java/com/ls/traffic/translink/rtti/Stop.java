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
 * Stop is a locations where buses provide scheduled service.
 *
 * Translink Canada - Vancouver - API reference:
 *   https://developer.translink.ca/ServicesRtti/ApiReference
 */
public class Stop {
    /** The 5-digtal stop number */
    public int StopNo;

    /** The stop name */
    public String Name;

    /** The bay number, if applicable */
    public String BayNo;

    /** The city in which the stop is located */
    public String City;

    /** The street name the stop is located on */
    public String OnStreet;

    /** The intersecting street of the stop */
    public String AtStreet;

    /** The latitude of the stop */
    public double Latitude;

    /** The longitude of the stop */
    public double Longitude;

    /** Specifies wheelchair accessible stop. 1 indicates stop is wheelchair accessible */
    public String WheelchairAccess;

    /** Distance away from the search location */
    public int Distance;

    /** The list of routes that the stop services */
    public String Routes;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Stop{");
        sb.append("StopNo=").append(StopNo);
        sb.append(", Name='").append(Name).append('\'');
        sb.append(", BayNo='").append(BayNo).append('\'');
        sb.append(", City='").append(City).append('\'');
        sb.append(", OnStreet='").append(OnStreet).append('\'');
        sb.append(", AtStreet='").append(AtStreet).append('\'');
        sb.append(", Latitude='").append(Latitude).append('\'');
        sb.append(", Longitude='").append(Longitude).append('\'');
        sb.append(", WheelchairAccess=").append(WheelchairAccess);
        sb.append(", Distance=").append(Distance);
        sb.append(", Routes=").append(Routes);
        sb.append('}');
        return sb.toString();
    }
}
