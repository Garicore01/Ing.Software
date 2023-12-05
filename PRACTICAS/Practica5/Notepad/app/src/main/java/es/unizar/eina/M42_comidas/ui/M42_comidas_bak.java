// package es.unizar.eina.M42_comidas.ui;

// import androidx.appcompat.app.AppCompatActivity;
// import androidx.lifecycle.ViewModelProvider;
// import androidx.recyclerview.widget.LinearLayoutManager;
// import androidx.recyclerview.widget.RecyclerView;

// import android.content.Intent;
// import android.os.Bundle;
// import android.view.Menu;
// import android.view.MenuItem;
// import android.widget.Toast;

// import com.google.android.material.floatingactionbutton.FloatingActionButton;

// import es.unizar.eina.M42_comidas.database.Plato;
// import es.unizar.eina.M42_comidas.database.Pedido;
// import es.unizar.eina.M42_comidas.R;

// /** Pantalla principal de la aplicacion M42_comidas */
// public class M42_comidas extends AppCompatActivity {
//     private PlatoViewModel mPlatoViewModel;

//     public static final int ACTIVITY_CREATE = 1;

//     public static final int ACTIVITY_EDIT = 2;

//     static final int INSERT_ID = Menu.FIRST;
//     static final int DELETE_ID = Menu.FIRST + 1;
//     static final int EDIT_ID = Menu.FIRST + 2;

//     // RecyclerView mPlatoRecyclerView;
//     // RecyclerView mPedidoRecyclerView;



//     // PlatoListAdapter mPlatoAdapter;
//     // PedidoListAdapter mPedidoAdapter;

//     // FloatingActionButton mPlatoFab;
//     // FloatingActionButton mPedidoFab;


//     @Override
//     protected void onCreate(Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.detalle_plato);
        
        


//         // mPlatoRecyclerView = findViewById(R.id.recyclerview);
//         // mPedidoRecyclerView = findViewById(R.id.recyclerview);

//         // mPlatoAdapter = new PlatoListAdapter(new PlatoListAdapter.PlatoDiff());
//         // mPedidoAdapter = new PedidoListAdapter(new PedidoListAdapter.PedidoDiff());

//         // mPlatoRecyclerView.setAdapter(mPlatoAdapter);
//         // mPlatoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//         // mPedidoRecyclerView.setAdapter(mPedidoAdapter);
//         // mPedidoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//         // mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);
//         // mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);

//         // mPlatoViewModel.getAllDishes().observe(this, platos -> {
//         //     // Update the cached copy of the dishes in the adapter.
//         //     mPlatoAdapter.submitList(platos);
//         // });

//         // mPedidoViewModel.getAllPedidos().observe(this, pedidos -> {
//         //     // Update the cached copy of the orders in the adapter.
//         //     mPedidoAdapter.submitList(pedidos);
//         // });

//         // mPlatoFab = findViewById(R.id.fab);
//         // mPlatoFab.setOnClickListener(view -> {
//         //     createDish();
//         // });

//         // mPedidoFab = findViewById(R.id.fab);
//         // mPedidoFab.setOnClickListener(view -> {
//         //     createPedido();
//         // });

//         // registerForContextMenu(mPlatoRecyclerView);
//         // registerForContextMenu(mPedidoRecyclerView);
//     }


//     // public boolean onCreateOptionsMenu(Menu menu) {
//     //     boolean result = super.onCreateOptionsMenu(menu);
//     //     menu.add(Menu.NONE, INSERT_ID, Menu.NONE, R.string.add_note);
//     //     return result;
//     // }

//     // @Override
//     // public boolean onOptionsItemSelected(MenuItem item) {
//     //     switch (item.getItemId()) {
//     //         case INSERT_ID:
//     //             createNote();
//     //             return true;
//     //     }
//     //     return super.onOptionsItemSelected(item);
//     // }

//     // public void onActivityResult(int requestCode, int resultCode, Intent data) {
//     //     super.onActivityResult(requestCode, resultCode, data);

//     //     Bundle extras = data.getExtras();

//     //     if (resultCode != RESULT_OK) {
//     //         Toast.makeText(
//     //                 getApplicationContext(),
//     //                 R.string.empty_not_saved,
//     //                 Toast.LENGTH_LONG).show();
//     //     } else {

//     //         switch (requestCode) {
//     //             case ACTIVITY_CREATE:
//     //                 Note newNote = new Note(extras.getString(PlatoEdit.NOTE_TITLE)
//     //                         , extras.getString(PlatoEdit.NOTE_BODY));
//     //                 mNoteViewModel.insert(newNote);
//     //                 break;
//     //             case ACTIVITY_EDIT:

//     //                 int id = extras.getInt(PlatoEdit.NOTE_ID);
//     //                 Note updatedNote = new Note(extras.getString(PlatoEdit.NOTE_TITLE)
//     //                         , extras.getString(PlatoEdit.NOTE_BODY));
//     //                 updatedNote.setId(id);
//     //                 mNoteViewModel.update(updatedNote);
//     //                 break;
//     //         }
//     //     }
//     // }


//     // public boolean onContextItemSelected(MenuItem item) {
//     //     Note current = mAdapter.getCurrent();
//     //     switch (item.getItemId()) {
//     //         case DELETE_ID:
//     //             Toast.makeText(
//     //                     getApplicationContext(),
//     //                     "Deleting " + current.getTitle(),
//     //                     Toast.LENGTH_LONG).show();
//     //             mNoteViewModel.delete(current);
//     //             return true;
//     //         case EDIT_ID:
//     //             editNote(current);
//     //             return true;
//     //     }
//     //     return super.onContextItemSelected(item);
//     // }

//     // private void createNote() {
//     //     Intent intent = new Intent(this, PlatoEdit.class);
//     //     startActivityForResult(intent, ACTIVITY_CREATE);
//     // }


//     // private void editNote(Note current) {
//     //     Intent intent = new Intent(this, PlatoEdit.class);
//     //     intent.putExtra(PlatoEdit.NOTE_TITLE, current.getTitle());
//     //     intent.putExtra(PlatoEdit.NOTE_BODY, current.getBody());
//     //     intent.putExtra(PlatoEdit.NOTE_ID, current.getId());
//     //     startActivityForResult(intent, ACTIVITY_EDIT);
//     // }

// }