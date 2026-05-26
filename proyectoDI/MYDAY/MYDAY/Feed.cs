using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net.Http;
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

        private void Feed_Resize(object sender, EventArgs e)
        {
            AjustarPaneles();
        }

       
        private void AjustarPaneles()
        {
            foreach (Control control in panelFlow.Controls)
            {
                if (control is PanelPost)
                {
                    if (this.WindowState == FormWindowState.Maximized)
                        control.Width = (int)(panelFlow.ClientSize.Width * 0.70);
                    else
                        control.Width = (int)(panelFlow.ClientSize.Width * 0.95);

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

        private async Task CargarFeed()
        {
            try
            {
                using HttpClient cliente = new HttpClient();

                string json = await cliente.GetStringAsync(
                    "http://localhost:8080/api-proyecto/rest/publicaciones"
                );

                var opciones = new JsonSerializerOptions
                {
                    PropertyNameCaseInsensitive = true
                };

                List<Publicacion> posts =
                    JsonSerializer.Deserialize<List<Publicacion>>(json, opciones);

                foreach (Control c in panelFlow.Controls)
                {
                    c.Dispose();
                }
                panelFlow.Controls.Clear();

                // FILTRADO HOY
                posts = posts
                    .Where(p => DateTime.Parse(p.fechaImagen).Date == DateTime.Today)
                    .OrderByDescending(p => DateTime.Parse(p.fechaImagen))
                    .Take(60)
                    .ToList();

                foreach (var p in posts)
                {
                    PanelPost nuevoPanel = new PanelPost();

                    if (!string.IsNullOrEmpty(p.imagenBase64))
                    {
                        byte[] bytes = Convert.FromBase64String(
                            p.imagenBase64.Trim().Replace(" ", "")
                        );

                        Image img;

                        using (MemoryStream ms = new MemoryStream(bytes))
                        {
                            using (Image temp = Image.FromStream(ms))
                            {
                                img = new Bitmap(temp);
                            }
                        }

                        nuevoPanel.CargarImagen(
                            p.nombreUsuario,
                            p.comentario,
                            p.fechaImagen,
                            img
                        );
                    }
                    else
                    {
                        nuevoPanel.CargarImagen(
                            p.nombreUsuario,
                            p.comentario,
                            p.fechaImagen,
                            null
                        );
                    }

                    panelFlow.Controls.Add(nuevoPanel);
                }

                AjustarPaneles();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

       
        private async void picPerfil_Click(object sender, EventArgs e)
        {
            try
            {
                using (Perfil perfil = new Perfil())
                {
                    this.Hide();

                    var result = perfil.ShowDialog();

                    this.Show();

                    if (result == DialogResult.OK)
                    {
                        await Task.Delay(100);
                        await CargarFeed();
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        
        private async void lblImagen_Click(object sender, EventArgs e)
        {
            FormSubirPubli formSubirPubli = new FormSubirPubli();

            if (formSubirPubli.ShowDialog() == DialogResult.OK)
            {
                await CargarFeed();
            }
        }
    }
}