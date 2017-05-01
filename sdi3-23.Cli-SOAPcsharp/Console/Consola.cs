using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace ConsolaNamespace
{
    public class Consola
    {
    

        public static void println()
        {
            Console.WriteLine();
        }


        public static void println(Object obj)
        {
            Console.WriteLine(obj.ToString());
        }


        public static void println(String stringe)
        {
            Console.WriteLine(stringe);
        }


        public static void print(String stringe)
        {
            Console.Write(stringe);
        }

      



        public static void printf (String format, params Object[] args)
        {
            Console.Write(format, args);
        }


        public static int? readInt()
        {
            try
            {
                return int.Parse(Console.ReadLine());
            }
            catch (FormatException fe)
            {
                return null;
            }
            catch (System.IO.IOException ioe)
            {
                throw new SystemException(ioe.Message);
            }
        }


        public static long? readLong()
        {
            try
            {
                return long.Parse(Console.ReadLine());
            }catch(FormatException fe)
            {
                return null;
            }catch(System.IO.IOException ioe)
            {
                throw new SystemException(ioe.Message);
            }

        }


        public static Double? readDouble()
        {
            try
            {
                return Double.Parse(Console.ReadLine());
            }
            catch (FormatException fe)
            {
                return null;
            }
            catch (System.IO.IOException ioe)
            {
                throw new SystemException(ioe.Message);
            }
        }  


        public static String readString()
        {
            try
            {
                return Console.ReadLine();
            }catch(System.IO.IOException ioe)
            {
                throw new SystemException(ioe.Message);
            }
        }


        public static String readString (String msg)
        {
            print(msg + ": ");
            return readString();
        }


        public static long? readLong(String msg)
        {
            print(msg + ": ");
            return readLong();
        }


        public static int? readInt(String msg)
        {
            print(msg + ": ");
            return readInt();
        }


        public static double? readDouble(String msg)
        {
            print(msg + ": ");
            return readDouble();
        }


        public static void waitForEnterKey()
        {
            print("Press Enter Key to continue...");
            readString();
        }


    }//end class
}
