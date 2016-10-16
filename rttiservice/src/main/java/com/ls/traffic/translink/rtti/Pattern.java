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
 * The pattern of the specific trip
 *
 * Translink Canada - Vancouver - API reference:
 *   https://developer.translink.ca/ServicesRtti/ApiReference
 */
public class Pattern {
    /** The pattern number */
    public String PatternNo;

    /** The destination of the pattern */
    public String Destination;

    /** The element containing the route map information */
    public RouteMap RouteMap;

    /** The direction of the pattern */
    public String Direction;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pattern{");
        sb.append("PatternNo='").append(PatternNo).append('\'');
        sb.append(", Destination='").append(Destination).append('\'');
        sb.append(", RouteMap=").append(RouteMap);
        sb.append(", Direction='").append(Direction).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
