using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MYDAY
{
    public class UsuarioLogin
    {
        public string nombreUsuario { get; set; }
        public string contrasena { get; set; }
        public UsuarioLogin(string nombreUsuario, string contrasena)
        {
            this.nombreUsuario = nombreUsuario;
            this.contrasena = contrasena;
        }
    }
}
