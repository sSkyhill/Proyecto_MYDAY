using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MYDAY
{
    public static class ApiDatos
    {
        private static readonly HttpClient client = new HttpClient();

        private static readonly string baseUrl =
            "http://localhost:8080/api-proyecto-1.0-SNAPSHOT/rest/";
    }
}
