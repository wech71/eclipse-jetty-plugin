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
package net.sourceforge.eclipsejetty.jetty11;

import java.util.Collection;

import net.sourceforge.eclipsejetty.jetty.JettyVersionType;
import net.sourceforge.eclipsejetty.jetty10.Jetty10ServerConfiguration;
import net.sourceforge.eclipsejetty.jetty7.Jetty7ServerConfiguration;
import net.sourceforge.eclipsejetty.jetty9.Jetty9ServerConfiguration;

public class Jetty11ServerConfiguration extends Jetty10ServerConfiguration
{

    public Jetty11ServerConfiguration()
    {
        super();
    }

    /**
     * {@inheritDoc}
     * 
     * @see net.sourceforge.eclipsejetty.jetty.AbstractConfiguration#getJettyVersionType()
     */
    @Override
    protected JettyVersionType getJettyVersionType()
    {
        return JettyVersionType.JETTY_11;
    }

    @Override
    protected void collectDefaultHandlerConfigurations(Collection<String> configurations)
    {
        if (isAnnotationsEnabled())
        {
            configurations.add("org.eclipse.jetty.webapp.WebInfConfiguration");
            configurations.add("org.eclipse.jetty.webapp.WebXmlConfiguration");
            configurations.add("org.eclipse.jetty.webapp.MetaInfConfiguration");
            configurations.add("org.eclipse.jetty.webapp.FragmentConfiguration");

            if (isAnnoationConfigurationWorkaroundAllowed())
            {
                configurations.add(getExtendedAnnotationConfigurationClassName());
            }

            configurations.add("org.eclipse.jetty.webapp.JettyWebXmlConfiguration");
        }

        if (isJndiEnabled())
        {
            configurations.add("org.eclipse.jetty.webapp.WebInfConfiguration");
            configurations.add("org.eclipse.jetty.webapp.WebXmlConfiguration");
            configurations.add("org.eclipse.jetty.webapp.MetaInfConfiguration");
            configurations.add("org.eclipse.jetty.webapp.FragmentConfiguration");
            configurations.add("org.eclipse.jetty.plus.webapp.EnvConfiguration");
            configurations.add("org.eclipse.jetty.plus.webapp.PlusConfiguration");

            if (isAnnoationConfigurationWorkaroundAllowed())
            {
                configurations.add(getExtendedAnnotationConfigurationClassName());
            }

            configurations.add("org.eclipse.jetty.webapp.JettyWebXmlConfiguration");
            configurations.add("org.eclipse.jetty.webapp.TagLibConfiguration");
        }
    }

    protected String getExtendedAnnotationConfigurationClassName()
    {
        return "net.sourceforge.eclipsejetty.starter.jetty8.ExtendedAnnotationConfiguration";
    }

    protected boolean isAnnoationConfigurationWorkaroundAllowed()
    {
        if ((getMinorVersion() == null) || (getMicroVersion() == null))
        {
            return false;
        }

        if (getMinorVersion().intValue() > 1)
        {
            return true;
        }
       
        if (getMinorVersion().intValue() == 1) {
            return (getMicroVersion().intValue() >= 8);
        }
        
        return false;
    }
}
