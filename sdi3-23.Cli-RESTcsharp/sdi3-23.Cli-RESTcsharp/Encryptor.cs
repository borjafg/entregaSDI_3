using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Security.Cryptography;
using alb.util.log;

namespace sdi3_23.Cli_RESTcsharp
{
    class Encryptor
    {
        private static readonly string KEY = "%190_CLAVE_SDI_?";


       public static string encrypt(string text)
        {      

            AesManaged aesCipher = new AesManaged();

            aesCipher.KeySize = 128;
            aesCipher.BlockSize = 128;

            aesCipher.Mode = CipherMode.ECB;
            aesCipher.Padding = PaddingMode.PKCS7;

            System.Text.UTF8Encoding encoding = new System.Text.UTF8Encoding();
            byte[] bytesClave = encoding.GetBytes(KEY);

            aesCipher.Key = bytesClave;

            ICryptoTransform encryptor = aesCipher.CreateEncryptor();


            byte[] textByte = System.Text.Encoding.UTF8.GetBytes(text);

            byte[] encryptedText = encryptor.TransformFinalBlock(textByte,0,textByte.Length);

            string textoEncyptString = Convert.ToBase64String(encryptedText);

            Console.WriteLine("Texto encriptado: "+ textoEncyptString);

            return textoEncyptString;
        }

    }
}
