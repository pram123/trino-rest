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

import pl.net.was.rest.apideck.model.ChannelMembers;
import pl.net.was.rest.apideck.model.Channels;
import pl.net.was.rest.apideck.model.Companies;
import pl.net.was.rest.apideck.model.Messages;
import pl.net.was.rest.apideck.model.Users;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

    @GET("companies")
    Call<Companies> listCompaniesWithCursor(
            @Header("x-apideck-consumer-id") String consumerId,
            @Header("x-apideck-service-id") String serviceId,
            @Header("x-apideck-app-id") String appId,
            @Header("Authorization") String auth,
            @Query("cursor") String cursor,
            @Query("limit") int limit);

    /*@GET("opportunities.list")
    Call<Opportunities> listOpportunities(
            @Header("x-apideck-consumer-id") String consumerId,
            @Header("x-apideck-service-id") String serviceId,
            @Header("x-apideck-app-id") String appId,
            @Header("Authorization") String auth,
            @Query("cursor") String cursor,
            @Query("limit") int limit);

     */

    @GET("conversations.members")
    Call<ChannelMembers> listChannelMembers(
            @Header("Authorization") String auth,
            @Query("cursor") String cursor,
            @Query("limit") int limit,
            @Query("channel") String channel);

    @GET("conversations.history")
    Call<Messages> listMessages(
            @Header("Authorization") String auth,
            @Query("cursor") String cursor,
            @Query("limit") int limit,
            @Query("channel") String channel);

    @GET("conversations.replies")
    Call<Messages> listReplies(
            @Header("Authorization") String auth,
            @Query("cursor") String cursor,
            @Query("limit") int limit,
            @Query("channel") String channel,
            @Query("ts") String ts);

    @GET("users.list")
    Call<Users> listUsers(
            @Header("Authorization") String auth,
            @Query("cursor") String cursor,
            @Query("limit") int limit);

    @GET("conversations.list")
    Call<Channels> listChannels(
            @Header("Authorization") String auth,
            @Query("cursor") String cursor,
            @Query("limit") int limit,
            @Query("types") String types);
}
