using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogNamespace
{
    public  interface ILogger
    {

        void setLogLevel(int paramInt);

        void addSourceClass(String paramString);

        void error(Exception paramThrowable);

        void warn(Exception paramThrowable);

        void info(Exception paramThrowable);

        void debug(Exception paramThrowable);

        void trace(Exception paramThrowable);

        void error(String paramString, Object[] paramArgs);

        void warn(String paramString, Object[] paramArgs);

        void info(String paramString, Object[] paramArgs);

        void debug(String paramString, Object[] paramArgs);

        void trace(String paramString, Object[] paramArgs);

    }
}
