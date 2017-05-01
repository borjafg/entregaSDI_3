using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;


namespace LogNamespace
{
   public abstract class BaseLogger : ILogger
    {

        private int logLevel = 4;
        
        private IList<String> validClassSources = new List<String>();
        private DateTime fecha;
       
        public void addSourceClass(string paramString)
        {

            validClassSources.Add(paramString);

        }

        public void setLogLevel(int paramInt)
        {
            LogLevel.assertValidValue(paramInt);
            logLevel = paramInt;
        }

        public void debug(Exception paramThrowable)
        {
            if(logLevel > 2) return;
            log(2, paramThrowable);
        }

        public void debug(string paramString, object[] paramArgs)
        {
            if (logLevel > 2) return;
            log(2, String.Format(paramString,paramArgs));
        }

        public void error(Exception paramThrowable)
        {
            if (logLevel > 5) return;
            log(5, paramThrowable);
        }

        public void error(string paramString, object[] paramArgs)
        {
            if (logLevel > 5) return;
            log(5, String.Format(paramString, paramArgs));
        }

        public void info(Exception paramThrowable)
        {
            if (logLevel > 3) return;
            log(3, paramThrowable);
        }

        public void info(string paramString, object[] paramArgs)
        {
            if (logLevel > 3) return;
            log(3, String.Format(paramString, paramArgs));
        }


        public void trace(Exception paramThrowable)
        {
            if (logLevel > 1) return;
            log(1, paramThrowable);
        }

        public void trace(string paramString, object[] paramArgs)
        {
            if (logLevel > 1) return;
            log(1, String.Format(paramString, paramArgs));
        }

        public void warn(Exception paramThrowable)
        {
            if (logLevel > 4) return;
            log(4, paramThrowable);
        }

        public void warn(string paramString, object[] paramArgs)
        {
            if (logLevel > 4) return;
            log(4, String.Format(paramString, paramArgs));
        }


        protected void log(int level, Exception e)
        {
            log(level, stackTraceAsString(e));
        }

        protected String stackTraceAsString(Exception e)
        {
            return e.ToString();                 
        }

        protected void log (int level,String msg)
        {      
            fecha = DateTime.Now;
            String stringFecha = fecha.ToString("u"); 

            Console.WriteLine();
            Console.ForegroundColor = ConsoleColor.Green;
            Console.Write("{0:S}", getName());
            Console.ResetColor();
            Console.Write(" -- [ ");
            Console.ForegroundColor = ConsoleColor.Cyan;
            Console.Write("{0:d}", LogLevel.toString(level));
            Console.ResetColor();
            Console.Write(" ] - {0:S}: ", stringFecha);
            Console.ForegroundColor = ConsoleColor.Yellow;
            Console.Write("{0:S}", msg);
        }


        private bool isValidClassSource(String clazz)
        {
            if(validClassSources.Count == 0)
            {
                return true;
            }
            foreach(String classSource in validClassSources)
            {
                if (clazz.StartsWith(classSource)) { }
                {
                    return true;

                }
            }
            return false;
        }

        protected abstract void print(String paramString);


        protected abstract String getName();
       




    }
}
