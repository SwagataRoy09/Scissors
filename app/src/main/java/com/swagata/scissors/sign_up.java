package com.swagata.scissors;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Locale;
import javax.net.ssl.SSLSessionBindingEvent;
public class sign_up extends AppCompatActivity {
    LinearLayout googleLogin;
    ImageView google;
    HashMap<String, Object> map = new HashMap<>();
    ImageView back;
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    TextView register, forgotpassword, login;
    EditText email, password, contact;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("USER");
    static int rc = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), splash2.class);
                startActivity(intent);
            }
        });
        google = findViewById(R.id.google);
        googleLogin = findViewById(R.id.googleLogin);
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        forgotpassword = findViewById(R.id.tv_forgetpassword);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        contact = findViewById(R.id.contact);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        register = findViewById(R.id.tv_registerbutton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), signin_midpage.class);
//                startActivity(intent);
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                String contactno = contact.getText().toString();
                if (mail.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter correct Email and Password", Toast.LENGTH_SHORT).show();
                } else if (pass.length() < 6 || !pass.contains("@") || !pass.contains("&") || !pass.contains("*")) {
                    Toast.makeText(getApplicationContext(), "Password must be of 7 characters and must have *,@,&", Toast.LENGTH_SHORT).show();
                } else {
                    myRef.child("USER").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(contactno)) {
                                Toast.makeText(sign_up.this, "User already exist please login", Toast.LENGTH_SHORT).show();
                            } else {
                                map.put("EMAIL", mail);
                                map.put("PASSWORD", pass);
                                map.put("CONTACT_NO", contactno);
                                myRef.child(contactno).setValue(map);
                                Toast.makeText(sign_up.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), signin_midpage.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });
    }
    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, rc);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == rc) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent i=new Intent(getApplicationContext(),signin_midpage.class);
            startActivity(i);
        } catch (ApiException e) {
            Toast.makeText(this, "Sorry Try Again", Toast.LENGTH_SHORT).show();
        }
    }
}