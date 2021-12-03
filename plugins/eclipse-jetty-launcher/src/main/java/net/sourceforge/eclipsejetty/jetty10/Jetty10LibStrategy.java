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
package net.sourceforge.eclipsejetty.jetty10;

import java.util.Collection;

import net.sourceforge.eclipsejetty.jetty9.Jetty94LibStrategy;

/**
 * Resolve libs for Jetty 8
 * 
 * @author Christian K&ouml;berl
 * @author Manfred Hantschel
 */
public class Jetty10LibStrategy extends Jetty94LibStrategy
{
    @Override
    protected void addServerDependencies(Collection<String> dependencies)
    {
        dependencies.add(".*/jetty-servlet-api-.*\\.jar");
        //dependencies.add(".*/jetty-schemas-.*\\.jar");
        dependencies.add(".*/jetty-http-.*\\.jar");
        dependencies.add(".*/jetty-server-.*\\.jar");
        dependencies.add(".*/jetty-xml-.*\\.jar");
        dependencies.add(".*/jetty-util-.*\\.jar");
        dependencies.add(".*/jetty-io-.*\\.jar");
        dependencies.add(".*/jetty-security-.*\\.jar");
        dependencies.add(".*/jetty-servlet-.*\\.jar");
        dependencies.add(".*/jetty-webapp-.*\\.jar");
        dependencies.add(".*/jetty-deploy-.*\\.jar");
        
        // BasicAuthenticator
        dependencies.add(".*/jetty-security-.*\\.jar");
        
        //sl4j
        dependencies.add(".*/logging/slf4j-api-.*.jar");
        dependencies.add(".*/logging/jetty-slf4j-impl-.*.jar");
    }

    @Override
    protected void addJSPDependencies(Collection<String> dependencies)
    {
    	//jsp.mod:
    	//* servlet 
    	//	addServerDependencies
    	//* annotations
    	addAnnotationsDependencies(dependencies);
    	//* apache-jsp
        dependencies.add(".*/apache-jsp/.*\\.jar");
    }

    @Override
    protected void addJMXDependencies(Collection<String> dependencies)
    {
        dependencies.add(".*/jetty-jmx-.*\\.jar");
    }

    @Override
    protected void addJNDIDependencies(Collection<String> dependencies)
    {
        //dependencies.add(".*/mail/.*\\.jar");
        dependencies.add(".*/jetty-jndi-.*\\.jar");
    }

    @Override
    protected void addAnnotationsDependencies(Collection<String> dependencies)
    {
        dependencies.add(".*/jetty-annotations-.*\\.jar");
        dependencies.add(".*/annotations/.*\\.jar");
        addPlusDependencies(dependencies);
    }
    
    void addPlusDependencies(Collection<String> dependencies)
    {
    	dependencies.add(".*/jetty-plus-.*\\.jar");
    	dependencies.add(".*/jakarta.transaction-api-.*\\.jar");
    	addJNDIDependencies(dependencies);
    }

    @Override
    protected void addWebsocketSupport(Collection<String> dependencies)
    {
        dependencies.add(".*/jetty-client-.*\\.jar");
        dependencies.add(".*/websocket/.*\\.jar");
    }
}
