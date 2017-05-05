using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sdi3_23.Cli_SOAPcsharp
{
    class ListarUsuariosAction : Accion
    {
        public void execute()
        {           

            ServiceSoap.AdminServiceImplService servicio = new ServiceSoap.AdminServiceImplService();
            Printer.printUsers(servicio.findAllUsersInfo());


        }



    }
}
