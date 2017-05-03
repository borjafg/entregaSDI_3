using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Security.Cryptography;


namespace sdi3_23.Cli_RESTcsharp
{
    class Encryptor
    {
        private static readonly string KEY = "%190_CLAVE_SDI_?";


        static string encrypt(string text)
        {

            Aes encriptadorAES = new AesCryptoServiceProvider();
            
            System.Text.UTF8Encoding encoding = new System.Text.UTF8Encoding();
            byte[] bytesClave = encoding.GetBytes(KEY);
            encriptadorAES.Key = bytesClave;

            encriptadorAES.Mode = CipherMode.ECB;

            encriptadorAES.Padding = PaddingMode.PKCS7;

            ICryptoTransform encryptor = encriptadorAES.CreateEncryptor();

            

        }

    }
}
