package co.edu.unab.srugeles435.stream;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import co.edu.unab.srugeles435.stream.model.Credenciales;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario, etPass;
    private SharedPreferences sharedPreferences;
    private ImageView imagenes;
    private static final String NOMBRE_COLLECTION = "credenciales";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imagenes = findViewById(R.id.Logo);
        sharedPreferences = getSharedPreferences("informacion_login", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("logeado", false)){
            finish();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        referenciar();

    }

    public void referenciar(){
        etUsuario = findViewById(R.id.Usuario);
        etPass = findViewById(R.id.Contraseña);
    }
    public void clickLogin (View view){
        cargardatosFireBase();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("logueado", true);
        editor.apply();
    }
    public void cargardatosFireBase(){
        String email = etUsuario.getText().toString();
        String pass = etPass.getText().toString();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection(NOMBRE_COLLECTION).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {

                    for (DocumentSnapshot document : task.getResult()) {
                        Credenciales micredencial = document.toObject(Credenciales.class);
                        micredencial.setIdFireStore(document.getId());
                        if (micredencial.getContrasena().equals(pass) && micredencial.getUsuario().equals(email)) {
                            finish();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Usuario ó Contraseña Incor", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    }
                }

            }
        });
    }
    public void AgregarUsuario(View view){
        Intent nuevoUsuario = new Intent(LoginActivity.this, RegistroActivity.class);
        irRegistro.launch(nuevoUsuario);
    }
    ActivityResultLauncher<Intent> irRegistro = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK){
                //cargardatosFireBase();
            }
        }
    });
}