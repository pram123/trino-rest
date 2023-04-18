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

package pl.net.was.rest.apideck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseMetadata
{
    private final Cursors cursors;
    private final Integer itemsOnPage;

    public ResponseMetadata(

            @JsonProperty("cursors") Cursors cursors,
            //CHECKSTYLE:OFF
            @JsonProperty("items_on_page") Integer itemsOnPage)
            //CHECKSTYLE:ON
    {
        this.cursors = cursors;
        this.itemsOnPage = itemsOnPage;
    }

    // this might fail.. we might have to look at links object and not the cursors object
    public String getNextCursor()
    {
        return cursors.getNextCursor();
    }
}
