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
    public partial class DatosPerfil : Form
    {
        public DatosPerfil()
        {
            InitializeComponent();
        }
        private void Ajustar()
        {

        }

        private void btnCambiarFoto_Click(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.Title = "Selecciona una imagen";
            openFileDialog.Filter = "Imágenes|*.jpg;*.jpeg;*.png;*.bmp;*.gif";
            openFileDialog.Multiselect = false;
            
            if(openFileDialog.ShowDialog() == DialogResult.OK)
            {
                pictureBox1.Image = Image.FromFile(openFileDialog.FileName);

            }
        }
    }
}
