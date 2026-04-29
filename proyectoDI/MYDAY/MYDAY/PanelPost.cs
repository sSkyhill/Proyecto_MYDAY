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
    public partial class PanelPost : UserControl
    {
        [Category("Mis funciones")]
        [Description("Cambiar imagen")]
        public Image ImagenPic
        {
            set { picPost.Image = value; }
            get { return picPost.Image; }
        }
        public PanelPost()
        {
            InitializeComponent();
            this.Height = 420;
            panel1.Padding = new Padding(0);
            this.Margin = new Padding(10);

            RecolocarPanel();
            this.Resize += PanelPost_Resize;

        }



        private void PanelPost_Resize(object sender, EventArgs e)
        {
            RecolocarPanel();
        }
        private void RecolocarPanel()
        {

            int maxAncho = this.Width - 20;
            int maxAlto = 400;
            if (picPost.Image != null)
            {
                float proporcion = (float)picPost.Image.Width / picPost.Image.Height;

                int nuevoAncho = maxAncho;
                int nuevoAlto = (int)(nuevoAncho / proporcion);


                if (nuevoAlto > maxAlto)
                {
                    nuevoAlto = maxAlto;
                    nuevoAncho = (int)(nuevoAlto * proporcion);
                }

                picPost.Width = nuevoAncho;
                picPost.Height = nuevoAlto;
            }
            else
            {
                picPost.Width = maxAncho;
                picPost.Height = maxAlto;
            }

            picPost.SizeMode = PictureBoxSizeMode.Zoom;
            picPost.Location = new Point((this.Width - picPost.Width) / 2, 10);

            lblComentario.Width = this.Width - 20;
            lblComentario.Height = 50;
            lblComentario.Location = new Point(10, picPost.Bottom + 10);


            this.Height = picPost.Height + lblComentario.Height + 30;



        }

        public void CargarImagen(string nombreUsuario, string comentario, string fechaImagen, Image imagen)
        {
            try
            {
                lblComentario.Text = nombreUsuario + ": " + fechaImagen + Environment.NewLine + comentario;
                picPost.Image = imagen;


            }
            catch (Exception ex)
            {
                picPost.Image = null;
            }
            RecolocarPanel();
        }
    }
}
