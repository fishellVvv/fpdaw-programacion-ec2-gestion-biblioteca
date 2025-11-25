package biblioteca.simple.servicios;

import biblioteca.simple.modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

// Clase de utilidad para guardar y cargar usuarios en formato JSON.
public final class PersistenciaUsuarios {
    // Nombre del archivo donde se guardan los usuarios.
    private static final String ARCHIVO = "usuarios.json";
    // Objeto Gson configurado para escribir el JSON "bonito"
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private PersistenciaUsuarios() {}

    public static void exportar(List<Usuario> usuarios) throws IOException {
        // Convierte la lista de usuarios a JSON y la escribe en el archivo.
        try (Writer w = new OutputStreamWriter(new FileOutputStream(ARCHIVO), StandardCharsets.UTF_8)) {
            gson.toJson(usuarios, w);
        }
    }

    public static List<Usuario> importar() throws IOException {
        File f = new File(ARCHIVO);

        // Si el archivo no existe, devolvemos una lista vacía
        if (!f.exists()) return new ArrayList<>();
        try (Reader r = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8)) {
            Type tipoLista = new TypeToken<ArrayList<Usuario>>() {}.getType();

            // Leemos el JSON y lo convertimos a lista de usuarios
            List<Usuario> lista = gson.fromJson(r, tipoLista);
            return (lista != null) ? lista : new ArrayList<>();
        }
    }
}
