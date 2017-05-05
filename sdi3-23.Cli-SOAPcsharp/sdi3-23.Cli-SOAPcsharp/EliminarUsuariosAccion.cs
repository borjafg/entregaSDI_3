using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sdi3_23.Cli_SOAPcsharp
{
    class EliminarUsuariosAccion : Accion
    {
        public void execute()
        {
            Consola.println("probando el menu eliminar usuarios");
            long id = Input.pedirLong("Escriba el identificador del usuario que hay que eliminar");

           ServiceSoap.AdminServiceImplService servicio = new ServiceSoap.AdminServiceImplService();
            servicio.deepDeleteUser(id,true);
            Consola.println("Exito al eliminar al usuario");
        }
    }
}
