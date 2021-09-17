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
package net.sourceforge.eclipsejetty.starter.jetty11;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.channels.ReadableByteChannel;

import net.sourceforge.eclipsejetty.starter.common.AbstractJettyLauncherMain;
import net.sourceforge.eclipsejetty.starter.common.ServerAdapter;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.xml.XmlConfiguration;

/**
 * Main for Jetty 10
 * 
 * @author Christian K&ouml;berl
 * @author Manfred Hantschel
 */
public class Jetty11LauncherMain extends AbstractJettyLauncherMain
{

    /**
     * Calls the {@link #launch(String[])} method
     * 
     * @param args the arguments
     * @throws Exception on occasion
     */
    public static void main(String[] args) throws Exception
    {
        new Jetty11LauncherMain().launch(args);
    }

    /**
     * {@inheritDoc}
     * 
     * @see net.sourceforge.eclipsejetty.starter.common.AbstractJettyLauncherMain#printLogo(java.io.PrintStream)
     */
    @Override
    protected void printLogo(PrintStream out)
    {
        out.println("   ____    ___                   __    __  __         ___");
        out.println("  / __/___/ (_)__  ___ ___   __ / /__ / /_/ /___ __  / _ \\");
        out.println(" / _// __/ / / _ \\(_-</ -_) / // / -_) __/ __/ // /  \\_, /");
        out.println("/___/\\__/_/_/ .__/___/\\__/  \\___/\\__/\\__/\\__/\\_, /  /___/");
        out.println("           /_/                              /___/");
    }

    /**
     * {@inheritDoc}
     * 
     * @see net.sourceforge.eclipsejetty.starter.common.AbstractJettyLauncherMain#createAdapter(java.io.File[], boolean)
     */
    @Override
    protected ServerAdapter createAdapter(File[] configurationFiles, boolean showInfo) throws Exception
    {
        return new Jetty11Adapter(new Server());
    }


    static class FileInputStreamResource extends org.eclipse.jetty.util.resource.Resource
    {
    	FileInputStream in;
    	
		public FileInputStreamResource(FileInputStream in)
		{
			this.in = in;
		}

		@Override
		public boolean isContainedIn(Resource r) throws MalformedURLException
		{
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void close()
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean exists()
		{
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isDirectory()
		{
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public long lastModified()
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public long length()
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public URI getURI()
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public File getFile() throws IOException
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getName()
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public InputStream getInputStream() throws IOException
		{
			return in;
		}

		@Override
		public ReadableByteChannel getReadableByteChannel() throws IOException
		{
			return null;
		}

		@Override
		public boolean delete() throws SecurityException
		{
			return false;
		}

		@Override
		public boolean renameTo(Resource dest) throws SecurityException
		{
			return false;
		}

		@Override
		public String[] list()
		{
			return null;
		}

		@Override
		public Resource addPath(String path) throws IOException, MalformedURLException
		{
			return null;
		}
    }
    
    
    /**
     * {@inheritDoc}
     * 
     * @see net.sourceforge.eclipsejetty.starter.common.AbstractJettyLauncherMain#configure(java.io.FileInputStream,
     *      java.lang.Class, net.sourceforge.eclipsejetty.starter.common.ServerAdapter)
     */
    @Override
    protected void configure(FileInputStream in, Class<?> type, ServerAdapter adapter) throws Exception
    {
        Server server = (Server) adapter.getServer();
        XmlConfiguration configuration = new XmlConfiguration(new FileInputStreamResource(in));

        if (type.isInstance(server))
        {
            configuration.configure(server);

            return;
        }

        boolean success = false;

        for (Handler handler : server.getHandlers())
        {
            if (type.isInstance(handler))
            {
                configuration.configure(handler);

                success = true;
            }
        }

        if (success)
        {
            return;
        }

        throw new IllegalArgumentException(String.format(
            "Failed to run configuration for %s. No matching object found in server.", type));
    }
}
