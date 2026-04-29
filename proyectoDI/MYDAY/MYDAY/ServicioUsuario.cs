using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace MYDAY
{
    public class ServicioUsuario
    {
        private readonly HttpClient _http;

        public ServicioUsuario()
        {
            _http = new HttpClient();
        }

        public async Task<bool> RegistrarUsuario(Usuario user)
        {
            var json = JsonSerializer.Serialize(user);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            var response = await _http.PostAsync("http://localhost:8080/api-proyecto/rest/usuarios", content);

            if (!response.IsSuccessStatusCode)
            {
                string error = await response.Content.ReadAsStringAsync();
                if (error.Contains("PRIMARY"))
                {
                    error = "El nombre de usuario ya existe. Por favor, elige otro.";
                }
                else if (error.Contains("email_UNIQUE"))
                {
                    error = "El correo electrónico ya está registrado. Por favor, utiliza otro.";
                }
                MessageBox.Show(error);
                return false;
            }
            else
            {
                return true;
            }

        }
        public async Task<string> IniciarSesion(UsuarioLogin login)
        {

            var json = JsonSerializer.Serialize(login);

            var content = new StringContent(
                json,
                Encoding.UTF8,
                "application/json"
            );

            var response = await _http.PostAsync(
                "http://localhost:8080/api-proyecto/rest/usuarios/login",
                content
            );

            if (response.IsSuccessStatusCode)
            {
                return "OK";
            }

            return await response.Content.ReadAsStringAsync();

        }
    }
}
