using alb.util.log;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sdi3_23.Cli_RESTcsharp
{
    class Principal
    {

        static void Main()
        {
            Log.setLogLevel(LogLevel.DEBUG);
            Cliente.inicializar();
            new MainMenu().ejecutarMenu();
            while (true) ;
        }
    }
}
