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

import java.util.List;

/**
 * 	Next bus information
 *
 * Translink Canada - Vancouver - API reference:
 *   https://developer.translink.ca/ServicesRtti/ApiReference
 */
public class NextBus {
    /** The bus route number */
    public int RouteNo;

    /** The bus route name */
    public String RouteName;

    /** The direction of the route at the specific stop */
    public String Direction;

    /** Containing the route map information */
    public RouteMap RouteMap;

    /** Containing the list of schedules */
    public List<Schedule> Schedules;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NextBus{");
        sb.append("RouteNo=").append(RouteNo);
        sb.append(", RouteName='").append(RouteName).append('\'');
        sb.append(", Direction='").append(Direction).append('\'');
        sb.append(", RouteMap=").append(RouteMap);
        sb.append(", Schedules=").append(Schedules);
        sb.append('}');
        return sb.toString();
    }
}
