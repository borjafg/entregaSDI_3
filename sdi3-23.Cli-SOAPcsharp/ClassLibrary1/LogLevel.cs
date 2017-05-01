using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogNamespace
{
    public class LogLevel
    {

        public static readonly int OFF = 10;
        public static readonly int ERROR = 5;
        public static readonly int WARN = 4;
        public static readonly int INFO = 3;
        public static readonly int DEBUG = 2;
        public static readonly int TRACE = 1;
        public static readonly int ALL = 0;


        public LogLevel() { }

        public static String toString(int level)
        {
            switch (level)
            {
                case 0: return "ALL";
                case 1: return "TRACE";
                case 2: return "DEBUG";
                case 3: return "INFO";
                case 4: return "WARN";
                case 5: return "ERROR";
                case 10: return "OFF";
            }

            throw new System.InvalidOperationException("LogLevel.toString() not supported value");
        }

        public static void assertValidValue(int level)
        {
            if ((level <0)||(level >10))
            {
                throw new System.InvalidOperationException("Invalid LogLevel " + level);
            }
        }

    }
}
