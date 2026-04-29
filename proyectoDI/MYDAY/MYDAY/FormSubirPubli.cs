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
using System.IO;

namespace MYDAY
{
    public partial class FormSubirPubli : Form
    {
        public FormSubirPubli()
        {
            InitializeComponent();
        }
        string imagenBase64 = string.Empty;
        private void pictureBox1_Click(object sender, EventArgs e)
        {
            OpenFileDialog ofd = new OpenFileDialog();
            ofd.Filter = "Imágenes|*.jpg;*.png;*.jpeg";

            if (ofd.ShowDialog() == DialogResult.OK)
            {
                byte[] bytes = File.ReadAllBytes(ofd.FileName);
                imagenBase64 = Convert.ToBase64String(bytes);
                picImagen.Image = Image.FromFile(ofd.FileName);
            }
            else
            {
                MessageBox.Show("No se ha seleccionado ninguna imagen");
            }

        }

        private async void btnSubir_Click(object sender, EventArgs e)
        {
            try
            {
                if (string.IsNullOrWhiteSpace(txtComentario.Text) || txtComentario.Text.Contains("Comentario"))
                {
                    MessageBox.Show("Por favor, ingresa un comentario");
                    return;
                }
                if (string.IsNullOrEmpty(imagenBase64))
                {
                    MessageBox.Show("Por favor, selecciona una imagen");
                    return;
                }
                if (txtComentario.Text.Length > 100)
                {
                    MessageBox.Show("El comentario no puede exceder los 100 caracteres");
                    return;
                }

                var publicacion = new Publicacion
                {
                    nombreUsuario = SesionUsuario.NombreUsuario,
                    comentario = txtComentario.Text,
                    fechaImagen = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss"),
                    imagenBase64 = imagenBase64
                };

                using HttpClient client = new HttpClient();

                string json = JsonSerializer.Serialize(publicacion);
                Console.WriteLine(json);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                HttpResponseMessage response =
                    await client.PostAsync(
                        "http://localhost:8080/api-proyecto/rest/publicaciones",
                        content
                    );

                if (response.IsSuccessStatusCode)
                {
                    MessageBox.Show("Publicación subida");

                    this.DialogResult = DialogResult.OK;
                    this.Close();
                }
                else
                {
                    MessageBox.Show("Error al subir");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
    }
}
