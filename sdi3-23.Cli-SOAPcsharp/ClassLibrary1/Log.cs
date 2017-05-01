using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogNamespace
{
    public class Log 
    {
        private static ILogger logger;

        private Log() { }

        private static ILogger createInstance() { return new ConsoleLogger(); }

        public static void setLogLevel(int paramInt)
        {
            instance().setLogLevel(paramInt);
        }

        public static void addSourceClass(string paramString)
        {
            instance().addSourceClass(paramString);
        }

        public static void debug(Exception paramThrowable)
        {
            instance().debug(paramThrowable);
        }

        public static void debug(string paramString, object[] paramArgs)
        {
            instance().debug(paramString,paramArgs);
        }

        public static void error(Exception paramThrowable)
        {
            instance().error(paramThrowable);
        }

        public static void error(string paramString, object[] paramArgs)
        {
            instance().error(paramString, paramArgs);
        }

        public static void info(Exception paramThrowable)
        {
            instance().info(paramThrowable);
        }

        public static void info(string paramString, object[] paramArgs)
        {
            instance().info(paramString, paramArgs);
        }

        public static void trace(Exception paramThrowable)
        {
            instance().trace(paramThrowable);
        }

        public static void trace(string paramString, object[] paramArgs)
        {
            instance().trace(paramString, paramArgs);
        }

        public static void warn(Exception paramThrowable)
        {
            instance().warn(paramThrowable);
        }

        public static void warn(string paramString, object[] paramArgs)
        {
            instance().warn(paramString, paramArgs);
        }

        private static ILogger instance()
        {
            if(logger == null)
            {
                logger = createInstance();
            }
            return logger;
        }
    }
}
