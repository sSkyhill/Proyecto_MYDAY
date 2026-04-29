using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MYDAY
{
    public class Usuario
    {
        public string nombreUsuario { get; set; }
        public string contrasena { get; set; }
        public string email { get; set; }
        
        public Usuario(string nombreUsuario, string contrasena, string email)
        {
            this.nombreUsuario = nombreUsuario;
            this.contrasena = contrasena;
            this.email = email;
            
        }
    }
}
