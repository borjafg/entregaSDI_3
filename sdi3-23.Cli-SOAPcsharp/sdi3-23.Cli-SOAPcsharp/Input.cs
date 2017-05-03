using alb.util.log;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sdi3_23.Cli_SOAPcsharp
{
    class Input
    {
        public static long pedirLong(String mensaje)
        {
            long valor = -1;
            do
            {
                try
                {
                    valor = Consola.readLong(mensaje).longValue();
                }
                catch (NullReferenceException nre)
                {
                    Log.trace("problema a la hora de sacar el long");
                    Log.trace(nre);
                    valor = -1;
                }
            } while (valor < 0);
            return valor;
        }
    }
}
