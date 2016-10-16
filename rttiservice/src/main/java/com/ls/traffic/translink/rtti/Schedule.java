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
 * 	Schedule information
 *
 * Translink Canada - Vancouver - API reference:
 *   https://developer.translink.ca/ServicesRtti/ApiReference
 */
public class Schedule {
    /** The pattern of the specific trip */
    public String Pattern;

    /** The destination of the trip */
    public String Destination;

    /** The expected departure time of the trip at the specific stop */
    public String ExpectedLeaveTime;

    /** The expected departure time in minutes */
    public int ExpectedCountdown;

    /** The status of the trip.
     *     * indicates scheduled time.
     *     - indicates delay.
     *     + indicates bus is running ahead of schedule
     */
    public String ScheduleStatus;

    /** Indicates if trip is cancelled */
    public boolean CancelledTrip;

    /** Indicates if stop is cancelled */
    public boolean CancelledStop;

    /** Indicates if trip is added */
    public boolean AddedTrip;

    /** Indicates if stop is added */
    public boolean AddedStop;

    /** The last updated time of the trip */
    public String LastUpdate;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Schedule{");
        sb.append("Pattern='").append(Pattern).append('\'');
        sb.append(", Destination='").append(Destination).append('\'');
        sb.append(", ExpectedLeaveTime='").append(ExpectedLeaveTime).append('\'');
        sb.append(", ExpectedCountdown=").append(ExpectedCountdown);
        sb.append(", ScheduleStatus='").append(ScheduleStatus).append('\'');
        sb.append(", CancelledTrip='").append(CancelledTrip).append('\'');
        sb.append(", CancelledStop='").append(CancelledStop).append('\'');
        sb.append(", AddedTrip='").append(AddedTrip).append('\'');
        sb.append(", AddedStop='").append(AddedStop).append('\'');
        sb.append(", LastUpdate='").append(LastUpdate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
