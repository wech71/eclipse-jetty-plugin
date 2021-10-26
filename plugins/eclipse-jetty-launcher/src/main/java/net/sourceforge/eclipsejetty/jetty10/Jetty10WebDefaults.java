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

import net.sourceforge.eclipsejetty.jetty9.Jetty9WebDefaults;
import net.sourceforge.eclipsejetty.util.DOMBuilder;

public class Jetty10WebDefaults extends Jetty9WebDefaults
{

    public Jetty10WebDefaults()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /* {@inheritDoc}
    * 
    * @see net.sourceforge.eclipsejetty.jetty.AbstractWebDefaults#appendListeners(net.sourceforge.eclipsejetty.util.DOMBuilder)
    */
   @Override
   protected void appendListeners(DOMBuilder builder)
   {    
	   //super.appendListeners(builder);
   }

}
