using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sdi3_23.Cli_SOAPcsharp
{
    class DeshabilitarUsuariosAction : Accion
    {
        public void execute()
        {
            Consola.println("probando el menu deshabilitar usuarios");
            long id = Input.pedirLong("Escriba el identificador del usuario que hay que deshabilitar");

            ServiceSoap.AdminServiceImplService servicio = new ServiceSoap.AdminServiceImplService();
            servicio.disableUser(id,true);
            Consola.println("Exito al deshabilitar a un usuario");
        }
    }
}
