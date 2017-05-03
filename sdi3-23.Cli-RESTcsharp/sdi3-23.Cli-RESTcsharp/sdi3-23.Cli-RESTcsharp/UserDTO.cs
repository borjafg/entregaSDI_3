using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace sdi3_23.Cli_RESTcsharp
{

    enum Status {[XmlEnum(Name = "ENABLED")]ENABLED, [XmlEnum(Name = "DISABLED")] DISABLED };
    [XmlRoot(ElementName = "user_no_password")]
    class UserDTO
    {
        string login { get; set; }
        long id { get; set; }
        string email { get; set; }
        Status status { get; set; }
        bool isAdmin { get; set; }




    }
}
