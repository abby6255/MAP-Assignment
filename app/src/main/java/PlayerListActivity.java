import com.example.namibiahockey.Player;
import com.example.namibiahockey.PlayerAdapter;
import com.example.namibiahockey.R;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import java.util.ArrayList;
import java.util.List;

public class PlayerListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PlayerAdapter adapter;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize adapter with empty list
        adapter = new PlayerAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Load players
        loadPlayers();
    }

    private void loadPlayers() {
        db.collection("players")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        Toast.makeText(this, "Error loading players", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (value != null && !value.isEmpty()) {
                        List<Player> players = new ArrayList<>();
                        for (DocumentSnapshot doc : value.getDocuments()) {
                            Player player = doc.toObject(Player.class);
                            if (player != null) {
                                player.setId(doc.getId());
                                players.add(player);
                            }
                        }
                        adapter.updateData(players);
                    }
                });
    }
}
