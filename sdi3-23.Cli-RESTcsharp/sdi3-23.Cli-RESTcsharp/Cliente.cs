using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using alb.util.log;
using System.Xml;
using System.Xml.Serialization;
using System.Diagnostics;
using System.Xml.Linq;
using System.IO;

namespace sdi3_23.Cli_RESTcsharp
{
    class Cliente
    {
        private static UserNoPasswordDTO usuario;
        private static string password;


        public static void inicializar()
        {
            bool reg = false;
            do
            {
                Console.WriteLine("excriba login");
                string login = Console.ReadLine();
                Console.WriteLine("excriba password");
                string pass = Console.ReadLine();
                reg = GetLogin(login, pass);
                if (reg == false)
                {
                    Consola.println("Usuario incorrecto, intentelo de nuevo");
                }
            } while (reg == false);


        }


        public static bool GetLogin(string login, string pass)
        {


            String credenciales = login + " - " + pass;
            String credencialesCifradas = Encryptor.encrypt(credenciales);

            Console.WriteLine("Se han cifrado las credenciales con exito");

            HttpWebRequest request = WebRequest.Create("http://localhost:8280/sdi3-23.WEB/rest/user/login/" + credencialesCifradas) as HttpWebRequest;
            request.ContentType = "application/xml";
            request.Accept = "application/xml";
            string responseText = null;
            try
            {
                using (HttpWebResponse response = request.GetResponse() as HttpWebResponse)
                {
                    if (response.StatusCode != HttpStatusCode.Accepted)
                    {
                        throw new WebException(String.Format("Server error (HTTP {0} {1}).", response.StatusCode, response.StatusDescription));
                    }

                    password = pass;

                    StreamReader reader = new StreamReader(response.GetResponseStream(), Encoding.UTF8);

                    responseText = reader.ReadToEnd();


                    response.Close();


                    XmlDocument doc = new XmlDocument();
                    doc.LoadXml(responseText);


                    using (XmlReader xmlReader = new XmlNodeReader(doc))
                    {
                        Object objeto = new XmlSerializer(typeof(UserNoPasswordDTO)).Deserialize(xmlReader);
                        usuario = (UserNoPasswordDTO)objeto;
                    }

                    Console.WriteLine(usuario.toString());
                    return true;
                }

            }
            catch (WebException we)
            {

                Log.error(we.ToString());

                return false;
            }
            catch (Exception e)
            {

                Log.error(e.ToString());

                return false;
            }
        }




        public static void findCategoriesByUserId()
        {
            string filtro = new Authenticator(usuario.login, password).filter();

            HttpWebRequest request = WebRequest.Create("http://localhost:8280/sdi3-23.WEB/rest/task/list_categories/"+usuario.id) as HttpWebRequest;
            request.Headers.Add(HttpRequestHeader.Authorization, filtro);

            request.ContentType = "application/xml";
            request.Accept = "application/xml";
            string responseText = null;

            try
            {
                using (HttpWebResponse response = request.GetResponse() as HttpWebResponse)
                {
                    ListCategoriesResponse listaCat;


                    if (response.StatusCode != HttpStatusCode.Accepted)
                    {
                        throw new WebException(String.Format("Server error (HTTP {0} {1}).", response.StatusCode, response.StatusDescription));
                    }

                    StreamReader reader = new StreamReader(response.GetResponseStream(), Encoding.UTF8);

                    responseText = reader.ReadToEnd();

                    Console.WriteLine(responseText);

                    response.Close();


                    XmlDocument doc = new XmlDocument();
                    doc.LoadXml(responseText);


                    using (XmlReader xmlReader = new XmlNodeReader(doc))
                    {
                        Object objeto = new XmlSerializer(typeof(ListCategoriesResponse)).Deserialize(xmlReader);
                        listaCat = (ListCategoriesResponse)objeto;
                    }

                    Console.WriteLine(listaCat.toString());





                }


            }
            catch (WebException we)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine(we.ToString());
                Console.ResetColor();
            }
            catch (Exception e)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine(e.ToString());
                Console.ResetColor();
            }


        }

    }
}
