package com.imc_lg


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMCCalculatorApp()
        }
    }
}

@Composable
fun IMCCalculatorApp() {
    var isDarkMode by remember { mutableStateOf(false) }

    // Couleurs pour le thème clair
    val lightPrimary = Color(0xFF1A554E)
    val lightBackground = Color(0xFFFFFFFF)
    val lightSurface = Color(0xFFF5F5F5)
    val lightOnPrimary = Color(0xFFFFFFFF)
    val lightOnSurface = Color(0xFF000000)

    // Couleurs pour le thème sombre
    val darkPrimary = Color(0xFF0B221F)
    val darkBackground = Color(0xFFFFFFFF)
    val darkSurface = Color(0xFFF5F5F5)
    val darkOnPrimary = Color(0xFFFFFFFF)
    val darkOnSurface = Color(0xFF000000)

    // Sélection des couleurs en fonction du mode
    val primaryColor = if (isDarkMode) darkPrimary else lightPrimary
    val backgroundColor = if (isDarkMode) darkBackground else lightBackground
    val surfaceColor = if (isDarkMode) darkSurface else lightSurface
    val onPrimaryColor = if (isDarkMode) darkOnPrimary else lightOnPrimary
    val onSurfaceColor = if (isDarkMode) darkOnSurface else lightOnSurface



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(primaryColor)
                        .padding(vertical = 16.dp)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Calculateur d'IMC",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                            .padding(vertical = 10.dp)
                    )
                    Text(
                        text = "Louvinx PIERRE & Jenoph ETIENNE",
                        color = Color.White,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Formulaire principal
                IMCForm(
                    primaryColor = primaryColor,
                    backgroundColor = backgroundColor,
                    surfaceColor = surfaceColor,
                    onPrimaryColor = onPrimaryColor,
                    onSurfaceColor = onSurfaceColor,
                    isDarkMode = isDarkMode
                )
            }

            // Bouton pour changer de thème
            IconButton(
                onClick = { isDarkMode = !isDarkMode },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(48.dp)

            ) {
                Text(
                    text = if (isDarkMode) "☀️" else "🌙",
                    color = onPrimaryColor,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun IMCForm(
    primaryColor: Color,
    backgroundColor: Color,
    surfaceColor: Color,
    onPrimaryColor: Color,
    onSurfaceColor: Color,
    isDarkMode: Boolean
) {
    var nom by remember { mutableStateOf("") }
    var prenom by remember { mutableStateOf("") }
    var dateNaissance by remember { mutableStateOf("") }
    var sexe by remember { mutableStateOf("Homme") }
    var poids by remember { mutableFloatStateOf(70f) }
    var taille by remember { mutableFloatStateOf(170f) }
    var typeActivite by remember { mutableStateOf("Sédentaire") }
    var showResults by remember { mutableStateOf(false) }
    var age by remember { mutableIntStateOf(0) }
    var imc by remember { mutableFloatStateOf(0f) }
    var categorie by remember { mutableStateOf("") }
    var besoinsCaloriques by remember { mutableIntStateOf(0) }

    val typesActivite = listOf("Sédentaire", "Faible", "Actif", "Sportif", "Athlète")
    var showDatePicker by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Informations Personnelles",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Champ Nom
            OutlinedTextField(
                value = nom,
                onValueChange = { nom = it },
                label = { Text("Nom") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = primaryColor.copy(alpha = 0.5f),
                    focusedLabelColor = primaryColor,
                    cursorColor = primaryColor
                )
            )

            // Champ Prénom
            OutlinedTextField(
                value = prenom,
                onValueChange = { prenom = it },
                label = { Text("Prénom") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = primaryColor.copy(alpha = 0.5f),
                    focusedLabelColor = primaryColor,
                    cursorColor = primaryColor
                )
            )

            // Champ Date de naissance avec sélecteur
            OutlinedTextField(
                value = dateNaissance,
                onValueChange = { dateNaissance = it },
                label = { Text("Date de naissance") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { showDatePicker = true },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = primaryColor.copy(alpha = 0.5f),
                    focusedLabelColor = primaryColor,
                    cursorColor = primaryColor
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                readOnly = true,
                trailingIcon = {
                    Icon(imageVector = Icons.Filled.DateRange,
                        contentDescription = "Calendar",
                        tint = Color(0xFF15413C),
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { showDatePicker = true }

                    )
                }
            )

            if (showDatePicker) {
                DatePickerDialog(
                    onDismiss = { showDatePicker = false },
                    onDateSelected = { date ->
                        dateNaissance = date
                        // Calculer l'âge à partir de la date de naissance
                        try {
                            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                            val birthDate = LocalDate.parse(date, formatter)
                            val currentDate = LocalDate.now()
                            age = Period.between(birthDate, currentDate).years
                        } catch (e: Exception) {
                            age = 0
                        }
                    },
                    primaryColor = primaryColor,
                    onSurfaceColor = onSurfaceColor,
                    backgroundColor = backgroundColor
                )
            }

            // Sélection du sexe
            Text(
                text = "Sexe",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp),
                color = onSurfaceColor
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SelectableButton(
                    text = "Homme",
                    isSelected = sexe == "Homme",
                    onClick = { sexe = "Homme" },
                    primaryColor = primaryColor,
                    onPrimaryColor = onPrimaryColor,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                SelectableButton(
                    text = "Femme",
                    isSelected = sexe == "Femme",
                    onClick = { sexe = "Femme" },
                    primaryColor = primaryColor,
                    onPrimaryColor = onPrimaryColor,
                    modifier = Modifier.weight(1f)
                )
            }

            // Affichage de l'âge calculé
            if (age > 0) {
                Text(
                    text = "Âge: $age ans",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    fontWeight = FontWeight.Medium,
                    color = primaryColor
                )
            }

            // Sélection du poids avec incrémenteur/décrémenteur
            Text(
                text = "Poids (kg): ${poids.toInt()}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp),
                color = onSurfaceColor
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { if (poids > 30f) poids -= 1f },
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = primaryColor.copy(alpha = 0.8f),
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = "-",
                        color = onPrimaryColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Slider(
                    value = poids,
                    onValueChange = { poids = it },
                    valueRange = 30f..200f,
                    steps = 170,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = primaryColor,
                        activeTrackColor = primaryColor,
                        inactiveTrackColor = primaryColor.copy(alpha = 0.3f)
                    )
                )

                IconButton(
                    onClick = { if (poids < 200f) poids += 1f },
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = primaryColor.copy(alpha = 0.8f),
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = "+",
                        color = onPrimaryColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Sélection de la taille
            Text(
                text = "Taille (cm): ${taille.toInt()}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp),
                color = onSurfaceColor
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { if (taille > 100f) taille -= 1f },
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = primaryColor.copy(alpha = 0.8f),
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = "-",
                        color = onPrimaryColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Slider(
                    value = taille,
                    onValueChange = { taille = it },
                    valueRange = 100f..250f,
                    steps = 150,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = primaryColor,
                        activeTrackColor = primaryColor,
                        inactiveTrackColor = primaryColor.copy(alpha = 0.3f)
                    )
                )

                IconButton(
                    onClick = { if (taille < 250f) taille += 1f },
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = primaryColor.copy(alpha = 0.8f),
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = "+",
                        color = onPrimaryColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Sélection du type d'activité
            Text(
                text = "Type d'activité",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp),
                color = onSurfaceColor
            )

            LazyRow(
                typesActivite = typesActivite,
                selectedType = typeActivite,
                onTypeSelected = { typeActivite = it },
                primaryColor = primaryColor,
                onPrimaryColor = onPrimaryColor
            )

            if(prenom=="" || nom =="" || dateNaissance==""){
                Text(
                    text = "veuillez  ne pas laisser de champs vide",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            }else {
                // Bouton de calcul
                Button(
                    onClick = {
                        // Calcul de l'IMC
                        val tailleEnMetres = taille / 100
                        imc = poids / (tailleEnMetres * tailleEnMetres)

                        // Détermination de la catégorie
                        categorie = when {
                            imc < 16.5 -> "Dénutrition"
                            imc < 18.5 -> "Maigreur"
                            imc < 25 -> "Poids normal"
                            imc < 30 -> "Surpoids"
                            imc < 35 -> "Obésité modérée"
                            imc < 40 -> "Obésité sévère"
                            else -> "Obésité morbide"
                        }


                        // Calcul des besoins caloriques (formule de Harris-Benedict)
                        val metabolismeDeBase = if (sexe == "Homme") {
                            13.7516 * poids + 5.0033 * taille - 6.7550 * age + 66.4730
                        } else {
                            9.5634 * poids + 1.8496 * taille - 4.6756 * age + 655.0955
                        }

                        val facteurActivite = when (typeActivite) {
                            "Sédentaire" -> 1.2
                            "Faible" -> 1.375
                            "Actif" -> 1.55
                            "Sportif" -> 1.725
                            "Athlète" -> 1.9
                            else -> 1.2
                        }

                        besoinsCaloriques = (metabolismeDeBase * facteurActivite).toInt()

                        showResults = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryColor
                    ),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(
                        text = "Calculer mon IMC",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = onPrimaryColor
                    )
                }
            }
        }
    }



    // Affichage des résultats dans une boîte de dialogue
    if (showResults) {
        ResultsDialog(
            imc = imc,
            name = "$nom $prenom",
            categorie = categorie,
            besoinsCaloriques = besoinsCaloriques,
            onDismiss = { showResults = false },
            primaryColor = primaryColor,
            surfaceColor = surfaceColor,
            onSurfaceColor = onSurfaceColor,
            onPrimaryColor = onPrimaryColor,
            isDarkMode = isDarkMode
        )
    }
}

@Composable
fun LazyRow(
    typesActivite: List<String>,
    selectedType: String,
    onTypeSelected: (String) -> Unit,
    primaryColor: Color,
    onPrimaryColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        typesActivite.forEach { type ->
            SelectableButton(
                text = type,
                isSelected = selectedType == type,
                onClick = { onTypeSelected(type) },
                primaryColor = primaryColor,
                onPrimaryColor = onPrimaryColor
            )
        }
    }
}

@Composable
fun SelectableButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    primaryColor: Color,
    onPrimaryColor: Color,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(4.dp)
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) primaryColor else primaryColor.copy(alpha = 0.2f)
        ),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) onPrimaryColor else primaryColor,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            fontSize = 14.sp
        )
    }
}

@Composable
fun DatePickerDialog(
    onDismiss: () -> Unit,
    onDateSelected: (String) -> Unit,
    primaryColor: Color,
    onSurfaceColor: Color,
    backgroundColor: Color
) {
    var selectedDay by remember { mutableIntStateOf(1) }
    var selectedMonth by remember { mutableIntStateOf(1) }
    var selectedYear by remember { mutableIntStateOf(2000) }

    val days = (1..31).toList()
    val months = (1..12).toList()
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val years = (1920..currentYear).toList().reversed()

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = backgroundColor
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sélectionnez votre date de naissance",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryColor,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Sélection du jour
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Jour",
                            color = onSurfaceColor,
                            fontWeight = FontWeight.Medium
                        )
                        NumberPicker(
                            items = days.map { it.toString() },
                            selectedItem = selectedDay.toString(),
                            onItemSelected = { selectedDay = it.toInt() },
                            primaryColor = primaryColor
                        )
                    }

                    // Sélection du mois
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Mois",
                            color = onSurfaceColor,
                            fontWeight = FontWeight.Medium
                        )
                        NumberPicker(
                            items = months.map { it.toString() },
                            selectedItem = selectedMonth.toString(),
                            onItemSelected = { selectedMonth = it.toInt() },
                            primaryColor = primaryColor
                        )
                    }

                    // Sélection de l'année
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Année",
                            color = onSurfaceColor,
                            fontWeight = FontWeight.Medium
                        )
                        NumberPicker(
                            items = years.map { it.toString() },
                            selectedItem = selectedYear.toString(),
                            onItemSelected = { selectedYear = it.toInt() },
                            primaryColor = primaryColor
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(
                            "Annuler",
                            color = primaryColor
                        )
                    }

                    TextButton(
                        onClick = {
                            val formattedDay = selectedDay.toString().padStart(2, '0')
                            val formattedMonth = selectedMonth.toString().padStart(2, '0')
                            val dateString = "$formattedDay/$formattedMonth/$selectedYear"
                            onDateSelected(dateString)
                            onDismiss()
                        }
                    ) {
                        Text(
                            "OK",
                            color = primaryColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NumberPicker(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    primaryColor: Color
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .height(150.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items.forEach { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemSelected(item) }
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item,
                    fontSize = if (selectedItem == item) 18.sp else 16.sp,
                    fontWeight = if (selectedItem == item) FontWeight.Bold else FontWeight.Normal,
                    color = if (selectedItem == item) primaryColor else primaryColor.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ResultsDialog(
    imc: Float,
    categorie: String,
    besoinsCaloriques: Int,
    onDismiss: () -> Unit,
    primaryColor: Color,
    surfaceColor: Color,
    onSurfaceColor: Color,
    onPrimaryColor: Color,
    isDarkMode: Boolean,
    name: String
) {
    val backgroundColor = if (isDarkMode) Color(0xFF272727) else Color(0xFFFAFAFA)

    // Couleurs pour les différentes catégories d'IMC
    val categorieColor = when (categorie) {
        "Dénutrition" -> Color(0xFFE53935)
        "Maigreur" -> Color(0xFFFFB74D)
        "Poids normal" -> Color(0xFF66BB6A)
        "Surpoids" -> Color(0xFFFFB74D)
        "Obésité modérée" -> Color(0xFFF57C00)
        "Obésité sévère" -> Color(0xFFE53935)
        "Obésité morbide" -> Color(0xFFC62828)
        else -> primaryColor
    }


        val getIMCDescription = when (categorie) {
            "Dénutrition"-> "Votre IMC indique que vous êtes en situation de maigreur sévère. Un suivi médical est nécessaire."
            "Maigreur" -> "Votre IMC indique que vous êtes en situation de maigreur. Un suivi médical pourrait être nécessaire."
            "Poids normal" -> "Votre IMC est dans la fourchette normale. Continuez à maintenir un mode de vie sain."
            "Surpoids" -> "Votre IMC indique un surpoids léger. Pensez à améliorer votre activité physique et votre alimentation."
            "Obésité modérée" -> "Votre IMC indique une obésité modérée. Il est recommandé de consulter un professionnel de santé."
            "Obésité sévère" -> "Votre IMC indique une obésité sévère. Un suivi médical est fortement recommandé."
            "Obésité morbide" -> "Votre IMC indique une obésité morbide. Une consultation médicale est nécessaire."
            else -> ""
        }


    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Résultat",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryColor,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Nom : $name",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryColor,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                // Affichage de l'IMC avec un design circulaire
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(categorieColor)
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "IMC",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = String.format("%.1f", imc),
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = categorie,
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 4.dp)
                        )

                    }
                }

                // Informations supplémentaires
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = surfaceColor
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Informations supplémentaires",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = primaryColor,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )


                        // Besoins caloriques
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Besoins caloriques",
                                color = onSurfaceColor,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "$besoinsCaloriques kcal",
                                color = primaryColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                        }
                        Text(
                            text = getIMCDescription,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }

                // Bouton de fermeture
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryColor
                    ),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(
                        text = "Fermer",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = onPrimaryColor
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun Imc_calculator(){
    IMCCalculatorApp()
}
