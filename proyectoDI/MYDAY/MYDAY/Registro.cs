using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MYDAY
{
    public partial class Registro : Form
    {
        public Registro()
        {
            InitializeComponent();

        }
        private void Registro_Load(object sender, EventArgs e)
        {
            CambiarTamano();
        }
        private void CambiarTamano()
        {
            lblRegistro.Left = (this.ClientSize.Width - lblRegistro.Width) / 2;
            lblRegistro.Top = (this.ClientSize.Height - lblRegistro.Height) / 6;
            txtUsuario.Left = (this.ClientSize.Width - txtUsuario.Width) / 2;
            txtUsuario.Top = lblRegistro.Bottom + 20;
            txtContrasena.Left = (this.ClientSize.Width - txtContrasena.Width) / 2;
            txtContrasena.Top = txtUsuario.Bottom + 20;
            txtMail.Left = (this.ClientSize.Width - txtMail.Width) / 2;
            txtMail.Top = txtContrasena.Bottom + 20;
            btnRegistro.Left = (this.ClientSize.Width - btnRegistro.Width) / 2;
        }
        private void borrarTextoPlaceholder(object sender, EventArgs e)
        {
            TextBox textBox = sender as TextBox;
            if (textBox != null && (textBox.Text == "Nombre de usuario" || textBox.Text == "Contraseña" || textBox.Text == "E-Mail"))
            {
                textBox.Text = "";
                textBox.ForeColor = Color.White;
                if (textBox == txtContrasena)
                {
                    txtContrasena.UseSystemPasswordChar = true;
                }
            }
        }

        private void textBox3_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
