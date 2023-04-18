/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.net.was.rest.apideck;

import pl.net.was.rest.apideck.modeln.companies.ACompany;
import pl.net.was.rest.apideck.modeln.companies.Companies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIDeckService
{
    @GET("companies")
    Call<Companies> listCompanies(
            @Header("x-apideck-consumer-id") String consumerId,
            @Header("x-apideck-service-id") String serviceId,
            @Header("x-apideck-app-id") String appId,
            @Header("Authorization") String auth,
            @Query("limit") int limit);

    @GET("company/{id}")
    ACompany getCompany(
            @Header("x-apideck-consumer-id") String consumerId,
            @Header("x-apideck-service-id") String serviceId,
            @Header("x-apideck-app-id") String appId,
            @Header("Authorization") String auth,
            @Path("id") String id);
}
