package Database.Remoto;

import Modelo.Cliente;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Consulta {
    private static List<Cliente> clientesList;

    public Consulta() {
        clientesList = new ArrayList<>();
    }

    public void consultarClientes() throws ExecutionException, InterruptedException, IOException {
        inicializarFirebase();
        Firestore db = FirestoreClient.getFirestore();
        clientesList.clear();

        ApiFuture<QuerySnapshot> future = db.collection("clientes").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            String nombre = document.getString("nombre");
            String apellido = document.getString("apellido");
            Date fNacimiento = document.getDate("fNacimiento");
            String telefono = document.getString("telefono");
            String email = document.getString("email");
            String cp = document.getString("cp");

            Cliente cliente = new Cliente(nombre, apellido, fNacimiento, telefono, email, cp);
            clientesList.add(cliente);
        }
    }

    public List<Cliente> getServiciosList() {
        return clientesList;
    }

    private void inicializarFirebase() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            FileInputStream refreshToken = new FileInputStream("C:\\Users\\Celeste\\Downloads\\ticketGenerator\\src\\Database\\Remoto\\parabeusticketgenerator-firebase-adminsdk-z8fuh-b8d9833ff9.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setProjectId("parabeusticketgenerator")
                    .build();

            FirebaseApp.initializeApp(options);
        }
    }

}
