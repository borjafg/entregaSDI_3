using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace sdi3_23.Cli_RESTcsharp
{
    [XmlRoot("category")]
   public class CategoryDTO
    {
        [XmlElement(ElementName = "id")]
        public long id { get; set; }

        [XmlElement(ElementName = "name")]
        public string name { get; set; }

        [XmlElement(ElementName = "created")]
        public DateTime created { get; set; }

        [XmlElement(ElementName = "user_id")]
        public long userId { get; set; }

        public String toString()
        {
            return "CategoryDTO [id=" + id + ", name=" + name + ", created="
                    + created + ", userId=" + userId + "]";
        }


    }
}
