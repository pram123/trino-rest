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

import io.trino.testing.AbstractTestQueryFramework;
import io.trino.testing.QueryRunner;
import org.testng.annotations.Test;

public class TestAPIDeckCRMOpportunitiesQueries
        extends AbstractTestQueryFramework
{
    @Override
    protected QueryRunner createQueryRunner()
            throws Exception
    {
        return SlackQueryRunner.createQueryRunner();
    }

    @Test
    public void showTables()
    {
        assertQuery("SHOW SCHEMAS FROM slack", "VALUES 'default', 'information_schema'");
        assertQuery("SHOW TABLES FROM slack.default", "VALUES 'users', 'channels', 'channel_members', 'messages', 'replies'");
    }

    @Test
    public void selectFromGeneral()
    {
        assertQuery("SELECT name FROM channels WHERE is_member ORDER BY name",
                "VALUES 'general', 'trino_rest'");
        assertQuery("SELECT name FROM slack.default.users ORDER BY name",
                "VALUES 'jan', 'test', 'slackbot'");
        assertQuery("SELECT member FROM channel_members WHERE channel = 'C024ZB0UMBJ' ORDER BY member",
                "VALUES 'U0242PCE55L', 'U0249TNJUDR'");
        computeActual("SELECT * FROM messages WHERE channel = 'C024ZB0UMBJ'");
        computeActual("SELECT * FROM replies WHERE channel = 'C024ZB0UMBJ' AND ts = '1623095713.000400'");
    }

    @Test
    public void selectFromIm()
    {
        computeActual("SELECT id FROM channels WHERE type = 'im' ORDER BY id");
    }
}
