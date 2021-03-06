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
 * 	Route information
 *
 * Translink Canada - Vancouver - API reference:
 *   https://developer.translink.ca/ServicesRtti/ApiReference
 */
public class Route {
    /** The bus route number */
    public int RouteNo;

    /** The name of the route */
    public String Name;

    /** The operating company of the route */
    public String OperatingCompany;

    /** The list of patterns for the route */
    public  List<Pattern> Patterns;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Route{");
        sb.append("RouteNo=").append(RouteNo);
        sb.append(", Name='").append(Name).append('\'');
        sb.append(", OperatingCompany='").append(OperatingCompany).append('\'');
        sb.append(", Patterns=").append(Patterns
            );
        sb.append('}');
        return sb.toString();
    }
}
