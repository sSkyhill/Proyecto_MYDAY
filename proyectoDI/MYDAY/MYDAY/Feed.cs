using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MYDAY
{
    public partial class Feed : Form
    {
        public Feed()
        {
            InitializeComponent();

            panelPublicacion.Width = (int)(panelFlow.ClientSize.Width * 0.95);
            this.MinimumSize = new Size(600, 400);
            this.Resize += Feed_Resize;
            this.Load += Feed_Load;

        }
        private async void Feed_Load(object sender, EventArgs e)
        {
            await CargarFeed();
        }
        private void AjustarPaneles()
        {
            foreach (Control control in panelFlow.Controls)
            {
                if (control is PanelPost)
                {
                    //Si esta maximizado
                    if (this.WindowState == FormWindowState.Maximized)
                    {
                        control.Width = (int)(panelFlow.ClientSize.Width * 0.70);
                    }
                    //Si esta normal
                    else
                    {
                        control.Width = (int)(panelFlow.ClientSize.Width * 0.95);
                    }


                    int margenIzquierdo = (panelFlow.ClientSize.Width - control.Width) / 2;

                    control.Margin = new Padding(
                        Math.Max(margenIzquierdo, 0),
                        10,
                        0,
                        10
                    );
                }
            }
        }
        private void Feed_Resize(object sender, EventArgs e)
        {
            AjustarPaneles();

        }
        private string imagenBase64 = "";
        private async void lblImagen_Click(object sender, EventArgs e)
        {
            FormSubirPubli formSubirPubli = new FormSubirPubli();
            if (formSubirPubli.ShowDialog() == DialogResult.OK)
            {
                await CargarFeed();
            }
        }

        private async Task CargarFeed()
        {
            try
            {
                using HttpClient cliente =
                    new HttpClient();

                string json =
                await cliente.GetStringAsync(
                "http://localhost:8080/api-proyecto/rest/publicaciones"
                );


                var opciones =
                    new JsonSerializerOptions
                    {
                        PropertyNameCaseInsensitive = true
                    };

                //Meto en la lista de publicaciones las publicaciones que me devuelve el backend
                List<Publicacion> posts =
                 JsonSerializer.Deserialize
                 <List<Publicacion>>
                 (
                    json,
                    opciones
                 );


                panelFlow.Controls.Clear();

                //Coge solo x publicaciones del día de hoy para no sobrecargar
                posts = posts
                    .Where(p => DateTime.Parse(p.fechaImagen).Date == DateTime.Today)
                    .OrderByDescending(p => DateTime.Parse(p.fechaImagen))
                    .Take(60)
                    .ToList();
                foreach (var p in posts)
                {
                    PanelPost nuevoPanel = new PanelPost();

                    //OBSOLETO
                    //nuevoPanel.Width = (int)(panelFlow.ClientSize.Width * 0.95);
                    //int margenIzquierdo = (panelFlow.ClientSize.Width - 600) / 2;
                    //nuevoPanel.Margin = new Padding(Math.Max(margenIzquierdo,10),10,0,10);

                    if (!string.IsNullOrEmpty(p.imagenBase64))
                    {
                        byte[] bytes = Convert.FromBase64String(p.imagenBase64);

                        MemoryStream ms = new MemoryStream(bytes);
                        Image img = Image.FromStream(ms);

                        nuevoPanel.CargarImagen(
                            p.nombreUsuario,
                            p.comentario,
                            p.fechaImagen,
                            img
                        );

                    }
                    else
                    {

                        nuevoPanel.CargarImagen(p.nombreUsuario, p.comentario, p.fechaImagen, null);
                    }

                    panelFlow.Controls.Add(nuevoPanel);
                    AjustarPaneles();
                }

            }
            catch (Exception ex)
            {
                MessageBox.Show(
                  ex.Message
                );
            }
        }

        private async void picPerfil_Click(object sender, EventArgs e)
        {
            Perfil perfil = new Perfil();
            this.Hide();
            if (perfil.ShowDialog() == DialogResult.OK)
            {

                await CargarFeed();
                this.Show();
            }
            else { this.Show(); }
        }
    }
}
