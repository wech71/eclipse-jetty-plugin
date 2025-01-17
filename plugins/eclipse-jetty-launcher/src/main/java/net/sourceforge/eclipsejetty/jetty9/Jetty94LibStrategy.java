// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package net.sourceforge.eclipsejetty.jetty9;

import java.util.Collection;

import net.sourceforge.eclipsejetty.jetty8.Jetty8LibStrategy;

/**
 * Resolve libs for Jetty 9.4.x
 * 
 * @author Christian K&ouml;berl
 * @author Manfred Hantschel
 * @author Andreas Stubenrauch
 */
public class Jetty94LibStrategy extends Jetty8LibStrategy
{
    @Override
    protected void addServerDependencies(Collection<String> dependencies)
    {
        dependencies.add(".*/servlet-api-.*\\.jar");
        dependencies.add(".*/jetty-schemas-.*\\.jar");
        dependencies.add(".*/jetty-http-.*\\.jar");
        dependencies.add(".*/jetty-server-.*\\.jar");
        dependencies.add(".*/jetty-xml-.*\\.jar");
        dependencies.add(".*/jetty-util-.*\\.jar");
        dependencies.add(".*/jetty-io-.*\\.jar");
        dependencies.add(".*/jetty-security-.*\\.jar");
        dependencies.add(".*/jetty-servlet-.*\\.jar");
        dependencies.add(".*/jetty-webapp-.*\\.jar");
        dependencies.add(".*/jetty-deploy-.*\\.jar");
    }

    @Override
    protected void addJSPDependencies(Collection<String> dependencies)
    {
        dependencies.add(".*/apache-jsp\\/.*\\.jar");
        dependencies.add(".*/apache-jstl\\/.*\\.jar");
    }

    @Override
    protected void addJMXDependencies(Collection<String> dependencies)
    {
        dependencies.add(".*/jetty-jmx-.*\\.jar");
    }

    @Override
    protected void addJNDIDependencies(Collection<String> dependencies)
    {
        dependencies.add(".*/mail/.*\\.jar");
        dependencies.add(".*/jetty-jndi-.*\\.jar");
        dependencies.add(".*/transactions/.*\\.jar");
        dependencies.add(".*/jetty-plus-.*\\.jar");
    }

    @Override
    protected void addAnnotationsDependencies(Collection<String> dependencies)
    {
        dependencies.add(".*/jetty-annotations-.*\\.jar");
        dependencies.add(".*/annotations/.*\\.jar");
        dependencies.add(".*/jetty-plus-.*\\.jar");
    }

    @Override
    protected void addWebsocketSupport(Collection<String> dependencies)
    {
        dependencies.add(".*/jetty-client-.*\\.jar");
        dependencies.add(".*/websocket/.*\\.jar");
    }
}
