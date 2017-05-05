using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net.Http;
using System.Xml.Serialization;

namespace sdi3_23.Cli_RESTcsharp
{

    public enum UserStatusDTO {[XmlEnum(Name = "ENABLED")] ENABLED, [XmlEnum(Name = "DISABLED")] DISABLED }


    [XmlRoot("user_no_password")]
    public class UserNoPasswordDTO
    {
        [XmlElement(ElementName = "id")]
        public long id { get; set; }

        [XmlElement(ElementName = "email")]
        public string email { get; set; }

        [XmlElement(ElementName = "login")]
        public string login { get; set; }

        [XmlElement(ElementName = "is_admin")]
        public bool isAdmin { get; set; }

        [XmlElement(ElementName = "status")]
        public UserStatusDTO status { get; set; }

        public UserNoPasswordDTO()
        {
            status = UserStatusDTO.ENABLED;
        }

        public string toString()
        {
            return "UserDTO [id=" + id + ", login=" + login + ", email=" + email +
                ", isAdmin=" + isAdmin + ", status=" + status + "]";
        }

    }
}
