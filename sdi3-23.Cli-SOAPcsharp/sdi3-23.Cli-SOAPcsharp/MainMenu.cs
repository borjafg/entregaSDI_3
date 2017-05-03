using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using alb.util.console;
using alb.util.menu;
using alb.util.log;

namespace sdi3_23.Cli_SOAPcsharp
{
    class MainMenu
    {
        protected List<string> opciones = new List<string>();
        protected List<Accion> acciones = new List<Accion>();
        String titulo;
        public MainMenu()
        {
            
            titulo = "Menu administrador EJB";            

            opciones.Add("Listar usuarios");
            acciones.Add(new ListarUsuariosAction());

            opciones.Add("Eliminar usuarios");
            acciones.Add(new EliminarUsuariosAccion());

            opciones.Add("Deshabilitar usuarios");
            acciones.Add(new DeshabilitarUsuariosAction());

        }
        
        static void Main(string[] args)
        {
            Log.setLogLevel(LogLevel.DEBUG);

            new MainMenu().execute();
          
        }

        public void execute()
        {
            int opcion = -1;
            do
            {
                pintarMenu();
                try
                {
                    opcion = Consola.readInt().intValue();
                    if (opcion > 0)
                    {
                        acciones[opcion - 1].execute();
                    }
                }catch(NullReferenceException nre)
                {
                    Log.trace("el usuario ha seleccionado una opcion invalida");
                    Log.trace(nre);
                    opcion = -1;
                }

            } while (opcion != 0);
        }

        private void pintarMenu()
        {
            Consola.println(titulo);
            int i = 1;
            foreach(string opcion in opciones)
            {
                
                Consola.printf("\t %s - %s \n",i.ToString(),opcion);
                ++i;
            }

            Consola.println("\t 0 - Salir");
            Consola.println("\nSelecciones una opcion:");
        }
    }
}
