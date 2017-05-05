using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sdi3_23.Cli_SOAPcsharp
{
    class Printer
    {
        public static void printUsers(IList<ServiceSoap.userInfoDTO> users)
        {
            Consola.println("Listado de usuarios:\n");

            foreach (ServiceSoap.userInfoDTO user in users)
            {
                String mensaje = "\tid: %s - login: %s - email: %s - "
            + "estado: %s - admin: %s\n";

                String isAdmin;
                if (user.is_admin)
                {
                    isAdmin = "si";
                }
                else
                {
                    isAdmin = "no";
                }


                mensaje += "\t\t--> Número de tareas planeadas (sin finalizar): %s\n";


                mensaje += "\t\t--> Número de tareas no planeadas (sin finalizar): %s\n";


                mensaje += "\t\t--> Número de tareas retrasadas y finalizadas: %s\n";


                mensaje += "\t\t--> Número de tareas finalizadas: %s\n\n";

                Consola.printf(mensaje, user.id.ToString(), user.login.ToString(), user.email.ToString(),
                    user.status.ToString().ToLower()
                    , isAdmin, user.num_planned_tasks.ToString(), user.num_not_planned_tasks.ToString()
                    , user.num_finished_delayed_tasks.ToString(), user.num_finished_tasks.ToString());

            }
            Consola.println("--- Fin del listado ---");

        }

    }
}
