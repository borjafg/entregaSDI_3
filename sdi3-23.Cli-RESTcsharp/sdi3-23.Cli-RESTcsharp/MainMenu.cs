using System;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using alb.util.log;
using System.Collections.Generic;

namespace sdi3_23.Cli_RESTcsharp
{
    class MainMenu
    {

        protected IList<string> opciones = new List<string>();
        string titulo;

        public MainMenu()
        {
            titulo = "Menú cliente Rest";

            opciones.Add("1 -- Listar categorias");
            opciones.Add("2 -- Ver tareas pendientes");
            opciones.Add("3 -- Marcar tarea como completada");
            opciones.Add("4 -- Registrar una tarea");
        }


        public void ejecutarMenu()
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
                        if(opcion == 1)
                        {                          
                            Cliente.findCategoriesByUserId();
                        }else if(opcion ==2)
                        {

                        }
                    }
                }
                catch (NullReferenceException nre)
                {
                    Log.trace("el usuario ha seleccionado una opcion invalida");
                    Log.trace(nre);
                    opcion = -1;
                }

            } while (opcion != 0);
        }

        public void pintarMenu()
        {
            Consola.println(titulo+"\n");
            
            foreach (string st in opciones)
            {
                Consola.println(st);
            }

            Consola.println("\t 0 - Salir");
            Consola.println("\nSeleccione una opcion:");
        }



    }


}



