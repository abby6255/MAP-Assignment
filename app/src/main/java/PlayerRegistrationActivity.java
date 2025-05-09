import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.namibiahockey.databinding.ActivityPlayerRegistrationBinding;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class PlayerRegistrationActivity extends AppCompatActivity {

    private ActivityPlayerRegistrationBinding binding;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding
        binding = ActivityPlayerRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Set click listener
        binding.btnRegister.setOnClickListener(v -> registerPlayer());
    }

    private void registerPlayer() {
        String name = binding.etName.getText().toString().trim();
        String age = binding.etAge.getText().toString().trim();
        String position = binding.etPosition.getText().toString().trim();
        String contact = binding.etContact.getText().toString().trim();

        if (name.isEmpty() || age.isEmpty() || position.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> player = new HashMap<>();
        player.put("name", name);
        player.put("age", age);
        player.put("position", position);
        player.put("contact", contact);
        player.put("timestamp", FieldValue.serverTimestamp());

        db.collection("players")
                .add(player)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Player registered!", Toast.LENGTH_SHORT).show();
                    clearForm();
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void clearForm() {
        binding.etName.setText("");
        binding.etAge.setText("");
        binding.etPosition.setText("");
        binding.etContact.setText("");
    }
}