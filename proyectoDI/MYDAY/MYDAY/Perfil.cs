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
        }
        private async void Perfil_Load(object sender, EventArgs e)
        {
            Grids();
            await CargarPublicacionesPerfil();
        }
        private async void Perfil_Resize(
            object sender,
            EventArgs e)
        {
            await CargarPublicacionesPerfil();
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

            int columnas = 3;
            int margen = 10;

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
    }
}
