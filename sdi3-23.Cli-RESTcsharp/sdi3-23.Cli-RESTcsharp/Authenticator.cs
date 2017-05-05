using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web;


namespace sdi3_23.Cli_RESTcsharp
{
    class Authenticator
    {


        private string user;
        private string password;

        public Authenticator(string user, string password)
        {
            this.user = user;
            this.password = password;
        }


        public string filter()
        {
            return Encryptor.encrypt(user + " - " + password);
        }

    }
}
