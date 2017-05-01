using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ConsolaNamespace;

namespace LogNamespace
{
    public class ConsoleLogger : BaseLogger
    {
        protected override string getName()
        {
            return "Logger de consola";
        }

        protected override void print(string paramString)
        {
            Consola.println(paramString);
        }
    }
}
