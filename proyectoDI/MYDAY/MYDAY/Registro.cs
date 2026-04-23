using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
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
            textBox.ForeColor = Color.White;
            if (textBox != null)
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
        private bool validarDatos()
        {
            if (string.IsNullOrWhiteSpace(txtUsuario.Text))
            {
                txtUsuario.ForeColor = Color.Red;
                txtUsuario.Text = "Nombre de usuario no válido";
                return false;
            }
            if (string.IsNullOrWhiteSpace(txtContrasena.Text))
            {

                txtContrasena.ForeColor = Color.Red;
                txtContrasena.Text = "Contraseña no válida";
                txtContrasena.UseSystemPasswordChar = false;
                return false;
            }
            if (txtContrasena.Text.Length < 4)
            {

                txtContrasena.ForeColor = Color.Red;
                txtContrasena.Text = "Debe tener 4 caracteres al menos";
                txtContrasena.UseSystemPasswordChar = false;
                return false;
            }
            if (string.IsNullOrWhiteSpace(txtMail.Text) || !Regex.IsMatch(txtMail.Text, @"^[^@\s]+@[^@\s]+\.[^@\s]+$"))
            {
                txtMail.ForeColor = Color.Red;
                txtMail.Text = "Email no válido";
                return false;
            }
            return true;
        }

        private async void btnRegistro_Click(object sender, EventArgs e)
        {
            if (!validarDatos())
            {
                return;
            }
            Usuario usuario = new Usuario(txtUsuario.Text, txtContrasena.Text, txtMail.Text, "");
            ServicioUsuario servicioUsuario = new ServicioUsuario();
            bool ok = await servicioUsuario.RegistrarUsuario(usuario);
            if (ok)
            {
                MessageBox.Show("Registro exitoso");
                this.Close();
            }
            else
            {
                
            }
        }
    }
}
