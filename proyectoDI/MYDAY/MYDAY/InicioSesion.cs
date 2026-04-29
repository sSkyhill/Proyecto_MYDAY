using Microsoft.VisualBasic.Logging;
using System.Windows.Forms.Design;

namespace MYDAY
{
    public partial class InicioSesion : Form
    {
        public InicioSesion()
        {
            InitializeComponent();
        }

        private void InicioSesion_Load(object sender, EventArgs e)
        {
            CambiarTamanos();
            this.AcceptButton = btnInicio;
            this.ActiveControl = pctLogo;
        }
        private void CambiarTamanos()
        {
            txtUsuario.Left = (this.ClientSize.Width - txtUsuario.Width) / 2;
            txtUsuario.Top = (this.ClientSize.Height - txtUsuario.Height) / 2;
            txtContrasena.Left = (this.ClientSize.Width - txtContrasena.Width) / 2;
            txtContrasena.Top = txtUsuario.Bottom + 20;
            btnInicio.Left = (this.ClientSize.Width - btnInicio.Width) / 2;
            btnInicio.Top = txtContrasena.Bottom + 20;
            pctLogo.Left = (this.ClientSize.Width - pctLogo.Width) / 2;
            pctLogo.Top = (this.ClientSize.Height - pctLogo.Height) / 4;
            lblRegistro.Left = (this.ClientSize.Width - lblRegistro.Width) / 3 + lblCuenta.Width;
            lblCuenta.Left = (this.ClientSize.Width - lblCuenta.Width) / 3;
        }
        private void lblRegistro_Click(object sender, EventArgs e)
        {
            Registro registro = new Registro();
            this.Hide();
            registro.ShowDialog();
            this.Show();

        }
        private void BorrarTextoPlaceholder(object sender, EventArgs e)
        {
            TextBox textBox = sender as TextBox;
            if (textBox != null && (textBox.Text == "Nombre de usuario" || textBox.Text == "Contraseña"))
            {

                textBox.Text = "";
                textBox.ForeColor = Color.White;
                if (textBox == txtContrasena)
                {
                    txtContrasena.UseSystemPasswordChar = true;
                }
            }
        }

        private async void btnInicio_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(txtUsuario.Text))
            {
                MessageBox.Show("Introduce el usuario");
                return;
            }

            if (string.IsNullOrWhiteSpace(txtContrasena.Text))
            {
                MessageBox.Show("Introduce la contraseña");
                return;
            }

            UsuarioLogin login = new UsuarioLogin
                (txtUsuario.Text,
                txtContrasena.Text);



            ServicioUsuario servicioUsuario = new ServicioUsuario();

            string resultado = await servicioUsuario.IniciarSesion(login);

            if (resultado == "OK")
            {
                MessageBox.Show("Login correcto");
                SesionUsuario.NombreUsuario = txtUsuario.Text;
                Feed feed = new Feed();
                this.Hide();
                feed.ShowDialog();
                this.Show();
            }
            else
            {
                MessageBox.Show("Login incorrecto");
            }
        }
    }
}
