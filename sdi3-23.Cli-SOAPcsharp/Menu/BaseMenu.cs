using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using LogNamespace;
using ConsolaNamespace;

namespace MenuNmespace
{
    class BaseMenu : IAction
    {
        public static readonly int EXIT = 0;
        protected Object[][] menuOptions;
        private List<Action> actions = null;

        public void execute()
        {
            int opt;
            do
            {
                showMenu();
                opt = getMenuOption();
                try
                {
                    processOption(opt);
                }catch(Exception ex)
                {
                    Log.error(ex);
                }
            }while (opt != 0) ;          
        }

        protected void processOption(int option) 
        {
            if(option == 0)
            {
                return;
            }
            Action actionClass = actions[option - 1];
            if(actionClass == null)
            {
                return;
            }
            createInstanceOf(actionClass).execute();

        }

        protected int? getMenuOption()
        {
            int? opt;
            do
            {
                Consola.print("Opcion: ");
                opt = Consola.readInt();
            } while ((opt == null)|| (opt < 0));
            
            return opt;
        }

    }
}
