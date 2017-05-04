using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace sdi3_23.Cli_RESTcsharp
{
    [XmlRoot("categories_list")]
    class ListCategoriesResponse
    {
        [XmlElement(ElementName = "categories")]
        public IList<CategoryDTO> categories;


        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            sb.Append("Listado de categorias : \n");
            foreach (CategoryDTO ct in categories)
            {
                sb.Append("\t id="+ct.id+", nombre="+ct.name+", Fecha="+ct.created.ToShortDateString());
            }

            return sb.ToString();
        }

    }
}
