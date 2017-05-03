using System;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;


namespace sdi3_23.Cli_RESTcsharp
{
    class Program
    {
        static HttpClient client = new HttpClient();
        static void Main()
        {
            RunAsync().Wait();
        }

        static async Task RunAsync()
        {
            client.BaseAddress = new Uri("HttpClient://localhost:8280/");
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/xml"));

            Console.ReadLine();
        }

       static async Task<UserDTO> GetLogin(string login, string password)
        {
            UserDTO user = null;
            HttpResponseMessage response = await client.GetAsync("http://localhost:8280/sdi3-23.WEB/rest/");
            String credenciales = login + " - " + password;
            String


            if (response.IsSuccessStatusCode)
            {
                
            }
            return user;
        }
    }
}
