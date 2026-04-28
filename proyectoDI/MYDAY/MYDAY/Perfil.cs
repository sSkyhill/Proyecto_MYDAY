using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Collections.Specialized.BitVector32;

namespace MYDAY
{
    public partial class Perfil : Form
    {
        public Perfil()
        {
            InitializeComponent();
            this.Load += Perfil_Load;
        }
        private async void Perfil_Load(object sender, EventArgs e)
        {
            
            this.MinimumSize = new Size(500, 400);
            Grids();
            await CargarPublicacionesPerfil();
            this.Width = 500;
        }
        
        private void Grids()
        {
            flowPerfil.WrapContents = true;
            flowPerfil.FlowDirection = FlowDirection.LeftToRight;
            flowPerfil.AutoScroll = true;
        }
        private void PintarGrid(List<Publicacion> publicaciones)
        {
            flowPerfil.Controls.Clear();

            int columnas = 4;
            int margen = 5;

            int anchoCelda =
                (flowPerfil.ClientSize.Width -
                ((columnas + 1) * margen))
                / columnas;

            foreach (var p in publicaciones)
            {
                PictureBox miniatura =
                    CrearMiniatura(p, anchoCelda, margen);

                flowPerfil.Controls.Add(miniatura);
            }
        }
        private PictureBox CrearMiniatura(
            Publicacion p,
            int tamaño,
            int margen)
        {
            PictureBox foto = new PictureBox();

            foto.Width = tamaño;
            foto.Height = tamaño;

            foto.SizeMode = PictureBoxSizeMode.Zoom;
            foto.Margin = new Padding(margen);

            if (!string.IsNullOrEmpty(p.imagenBase64))
            {
                byte[] bytes =
                    Convert.FromBase64String(p.imagenBase64);

                using (MemoryStream ms =
                    new MemoryStream(bytes))
                {
                    Image img = Image.FromStream(ms);
                    foto.Image = (Image)img.Clone();
                }
            }

            return foto;
        }
        private async Task CargarPublicacionesPerfil()
        {
            try
            {
                using HttpClient cliente = new HttpClient();

                string json = await cliente.GetStringAsync(
                "http://localhost:8080/api-proyecto-1.0-SNAPSHOT/rest/publicaciones"
                );
                MessageBox.Show(json);
                var opciones = new JsonSerializerOptions
                {
                    PropertyNameCaseInsensitive = true
                };

                List<Publicacion> posts =
                    JsonSerializer.Deserialize<List<Publicacion>>
                    (json, opciones);

                string usuario = SesionUsuario.NombreUsuario;

                var misPosts = posts
                    .Where(p => p.nombreUsuario == usuario)
                    .ToList();

                PintarGrid(misPosts);
                
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private async void picSubir_Click(object sender, EventArgs e)
        {
            FormSubirPubli formSubirPubli = new FormSubirPubli();
            if (formSubirPubli.ShowDialog() == DialogResult.OK)
            {
                await CargarPublicacionesPerfil();
            }
        }
    }
}
