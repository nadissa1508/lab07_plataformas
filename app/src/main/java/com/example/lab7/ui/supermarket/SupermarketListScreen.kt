package com.example.lab7.ui.supermarket

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SupermarketScreen(viewModel: SupermarketViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Componente para agregar un nuevo artículo
        AddItemScreen(viewModel)

        // Componente para mostrar la lista de artículos
        SupermarketListScreen(viewModel)
    }
}

@Composable
fun AddItemScreen(viewModel: SupermarketViewModel) {
    var itemName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var imageUri: Uri? by remember { mutableStateOf(null) }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success && imageUri != null) {
            val newItem = SupermarketItem(itemName = itemName, quantity = quantity.toInt(), imagePath = imageUri.toString())
            viewModel.addItem(newItem)
            // Resetea los campos después de agregar
            itemName = ""
            quantity = ""
            imageUri = null
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Nombre del Artículo") }
        )
        TextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Cantidad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Asegúrate de usar el tipo correcto para cantidad
        )

        // Botón para capturar la imagen
        Button(onClick = {
            imageUri = createImageFile(context)
            imageUri?.let { launcher.launch(it) }
        }) {
            Text("Capturar Imagen")
        }

        // Mostrar la imagen si está disponible
        imageUri?.let {
            Image(
                painter = rememberImagePainter(it),
                contentDescription = null,
                modifier = Modifier.size(100.dp) // Ajusta el tamaño según sea necesario
            )
        }
    }
}

@Composable
fun SupermarketListScreen(viewModel: SupermarketViewModel) {
    val items by viewModel.items.collectAsState(initial = emptyList())

    LazyColumn {
        items(items) { item ->
            SupermarketItemRow(item)
        }
    }
}

@Composable
fun SupermarketItemRow(item: SupermarketItem) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(item.itemName, Modifier.weight(1f))
        Text("Qty: ${item.quantity}")
        item.imagePath?.let { imagePath ->
            Image(
                painter = rememberImagePainter(imagePath),
                contentDescription = null,
                modifier = Modifier.size(50.dp) // Ajusta el tamaño según sea necesario
            )
        }
    }
}

private fun createImageFile(context: Context): Uri {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val fileName = "JPEG_${timeStamp}_"
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val imageFile = File.createTempFile(
        fileName,  /* prefix */
        ".jpg",    /* suffix */
        storageDir /* directory */
    )
    return FileProvider.getUriForFile(context, "com.example.lab7.fileprovider", imageFile)
}
