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

import net.sourceforge.eclipsejetty.jetty.JettyConfigBuilder;
import net.sourceforge.eclipsejetty.jetty.JettyVersionType;
import net.sourceforge.eclipsejetty.jetty9.Jetty9ServerConfiguration;
import net.sourceforge.eclipsejetty.util.DOMBuilder;

public class Jetty10ServerConfiguration extends Jetty9ServerConfiguration
{

    public Jetty10ServerConfiguration()
    {
        super();
    }

    protected void buildBody(DOMBuilder builder)
    {
    	super.buildBody(builder);    	
    	builder.setDoctype("-//Jetty//Configure//EN", "https://www.eclipse.org/jetty/configure_10_0.dtd");
    }

    @Override
    protected void buildContent(JettyConfigBuilder builder)
    {
    	super.buildContent(builder);

//    	builder.beginCallClass("org.eclipse.jetty.webapp.WebAppContext", "addServerClasses")
//			.argRef("Server")
//			.argArray("-org.eclipse.jetty");
    	
		builder.end();
    }
    
    @Override
    protected void buildDefaultHandlerSetters(JettyConfigBuilder builder)
    {
    	super.buildDefaultHandlerSetters(builder);
    	//builder.set("parentLoaderPriority", true);
    }
    

    /**
     * {@inheritDoc}
     * 
     * @see net.sourceforge.eclipsejetty.jetty7.Jetty7ServerConfiguration#buildHttpConfig(net.sourceforge.eclipsejetty.jetty.JettyConfigBuilder)
     */
    @Override
    protected void buildHttpConfig(JettyConfigBuilder builder)
    {
        if (getPort() == null)
        {
            return;
        }

        builder.comment("HTTP Config");

        builder.beginNew("httpConfig", "org.eclipse.jetty.server.HttpConfiguration");
        {
            builder.set("secureScheme", "https");

            if (getSslPort() != null)
            {
                builder.set("securePort", getSslPort());
            }

            builder.set("outputBufferSize", 32768);
            builder.set("requestHeaderSize", 8192);
            builder.set("responseHeaderSize", 8192);
            builder.set("sendServerVersion", true);
            builder.set("sendDateHeader", false);
            builder.set("headerCacheSize", 512);
            
            String uriCompliance = System.getProperty("jetty.httpConfig.uriCompliance");
            if (uriCompliance == null)
            	uriCompliance = "LEGACY";
                        
            builder.beginSet("uriCompliance");
            {
            	 builder.beginCallClass("org.eclipse.jetty.http.UriCompliance", "from");
                 {
                	 builder.arg(uriCompliance);
                 }
                 builder.end();
            }
            builder.end();
        }
        builder.end();
    }

    
    /**
     * {@inheritDoc}
     * 
     * @see net.sourceforge.eclipsejetty.jetty.AbstractConfiguration#getJettyVersionType()
     */
    @Override
    protected JettyVersionType getJettyVersionType()
    {
        return JettyVersionType.JETTY_10;
    }

    @Override
    protected void collectDefaultHandlerConfigurations(Collection<String> configurations)
    {
    	configurations.add("org.eclipse.jetty.webapp.WebInfConfiguration");
		configurations.add("org.eclipse.jetty.webapp.WebXmlConfiguration");
		configurations.add("org.eclipse.jetty.webapp.MetaInfConfiguration");
		configurations.add("org.eclipse.jetty.webapp.FragmentConfiguration");		
		configurations.add("org.eclipse.jetty.plus.webapp.EnvConfiguration");
    	
    	
		//JNDI depends on Annotation support
        if (isAnnotationsEnabled() || isJndiEnabled())
        {
    		configurations.add("org.eclipse.jetty.plus.webapp.PlusConfiguration");
    		configurations.add("org.eclipse.jetty.annotations.AnnotationConfiguration");
    		configurations.add("org.eclipse.jetty.webapp.JettyWebXmlConfiguration");
    		configurations.add("org.eclipse.jetty.webapp.WebAppConfiguration");
    		configurations.add("org.eclipse.jetty.webapp.JspConfiguration");
        }

        if (isJndiEnabled())
        {
            configurations.add("org.eclipse.jetty.webapp.JndiConfiguration");
        }
    }
    

    /**
     * {@inheritDoc}
     * 
     * @see net.sourceforge.eclipsejetty.jetty7.Jetty7ServerConfiguration#buildAnnotations(net.sourceforge.eclipsejetty.jetty.JettyConfigBuilder)
     */
    @Override
    protected void buildAnnotations(JettyConfigBuilder builder)
    {
        if (!isAnnotationsEnabled())
        {
            return;
        }

        builder.comment("Annotations");

        //don't call super method: jetty 10 does no longer have org.eclipse.jetty.webapp.Configuration$ClassList and does not need append
    	//super.buildAnnotations(builder);
    }
    
    @Override
    protected void buildJNDI(JettyConfigBuilder builder)
    {
        if (!isAnnotationsEnabled())
        {
            return;
        }

        builder.comment("JNDI");

        //don't call super method: jetty 10 does no longer have org.eclipse.jetty.webapp.Configuration$ClassList and does not need append
        //super.buildJNDI(builder);
    }
}
