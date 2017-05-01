using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ConsolaNamespace;

namespace sdi3_23.Cli_SOAPcsharp
{
    class Input
    {
        public static long? pedirLong(string mensaje)
        {
            long? valor = null;
            do
            {
                try
                {
                    valor = Consola.readLong(mensaje);
                }
                catch(NullReferenceException ne)
                {
                    valor = null;
                }

            } while (valor == null || valor < 0);



            return valor;     
        }
    }
}
