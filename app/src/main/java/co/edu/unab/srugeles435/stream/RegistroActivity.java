package co.edu.unab.srugeles435.stream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

import co.edu.unab.srugeles435.stream.model.Credenciales;

public class RegistroActivity extends AppCompatActivity {

    EditText usuarioET;
    EditText contrasenaET;
    Button registrarse;
    private static final String NOMBRE_COLLECTION = "credenciales";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuarioET = findViewById(R.id.r_usuarioET);
        contrasenaET = findViewById(R.id.r_contrasenaET);
        registrarse = findViewById(R.id.registro_btn);

    }

    public void clickGuardar(View view){
        String email = usuarioET.getText().toString();
        String pass = contrasenaET.getText().toString();

        if ("".equals(email)){
            usuarioET.setError("Usuario Vacio");
            return;
        }
        if ("".equals(pass)){
            contrasenaET.setError("Contraseña Vacia");
            return;
        }

        Credenciales nuevoUsuario = new Credenciales(email, pass);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection(NOMBRE_COLLECTION).add(nuevoUsuario);
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();

    }


}
