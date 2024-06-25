package Database.Remoto;

import Modelo.Cliente;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Insertar {
    public void insertarServicio(Cliente cliente) throws IOException, ExecutionException, InterruptedException {
        inicializarFirebase();
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference nuevoClienteRef = db.collection("clientes").document();

        Map<String, Object> datosCliente = new HashMap<>();
        datosCliente.put("nombre", cliente.getNombre());
        datosCliente.put("apellido", cliente.getApellido());
        datosCliente.put("fNacimiento", cliente.getfNacimiento());
        datosCliente.put("telefono", cliente.getTelefono());
        datosCliente.put("email", cliente.getEmail());
        datosCliente.put("cp", cliente.getCp());

        ApiFuture<WriteResult> resultado = nuevoClienteRef.set(datosCliente);
        resultado.get();
    }

    private void inicializarFirebase() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            FileInputStream refreshToken = new FileInputStream("parabeusticketgenerator-firebase-adminsdk-z8fuh-b8d9833ff9.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setProjectId("parabeusticketgenerator")
                    .build();

            FirebaseApp.initializeApp(options);
        }
    }
}
