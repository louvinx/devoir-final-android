package com.stranger


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StrangerBookTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StrangerBookApp()
                }
            }
        }
    }
}

data class Post(
    val id: Int,
    val userName: String,
    val userAvatar: Int,
    val postContent: String,
    val postImage: Int?,
    val likes: Int,
    val comments: Int,
    val shares: Int,
    val timeAgo: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StrangerBookApp() {
    val posts = remember {
        listOf(
            Post(
                id = 1,
                userName = "Eleven",
                userAvatar = R.drawable.eleven,
                postContent = "Je sens quelque chose d'étrange dans l'air aujourd'hui... Les lumières n'arrêtent pas de clignoter chez moi. Est-ce que quelqu'un d'autre a remarqué des choses bizarres?",
                postImage = R.drawable.bats,
                likes = 186,
                comments = 42,
                shares = 12,
                timeAgo = "2h"
            ),
            Post(
                id = 2,
                userName = "Eddie",
                userAvatar = R.drawable.eddie,
                postContent = "Quelqu'un veut jouer à Donjons & Dragons ce weekend? J'ai préparé une nouvelle campagne!",
                postImage = R.drawable.crush,
                likes = 78,
                comments = 23,
                shares = 3,
                timeAgo = "4h"
            ),
            Post(
                id = 3,
                userName = "Lucas",
                userAvatar = R.drawable.lucas,
                postContent = "J'ai trouvé cette créature bizarre dans ma poubelle hier soir. C'est peut-être un demodog? Qu'est-ce que vous en pensez?",
                postImage = R.drawable.vecna,
                likes = 253,
                comments = 87,
                shares = 36,
                timeAgo = "6h"
            ),
            Post(
                id = 4,
                userName = "Eleven",
                userAvatar = R.drawable.snow,
                postContent = "Si quelqu'un voit Will, dites-lui de rentrer à la maison IMMÉDIATEMENT. Il devait être là il y a 1 heure!",
                postImage = R.drawable.father,
                likes = 42,
                comments = 17,
                shares = 8,
                timeAgo = "8h"
            ),
            Post(
                id = 5,
                userName = "Eddie",
                userAvatar = R.drawable.eddie,
                postContent = "Je vends ma batte de baseball. Légèrement utilisée. Quelques taches étranges dessus. Parfait pour se défendre contre... peu importe.",
                postImage = R.drawable.roller,
                likes = 132,
                comments = 28,
                shares = 5,
                timeAgo = "12h"
            ),
            Post(
                id = 6,
                userName = "Lucas",
                userAvatar = R.drawable.lucas,
                postContent = "J'ai battu le record au Palais des Arcades! 763,450 points à Dig Dug! Essayez de me battre si vous pouvez!",
                postImage = R.drawable.marine,
                likes = 198,
                comments = 45,
                shares = 7,
                timeAgo = "1j"
            ),
            Post(
                id = 7,
                userName = "Eleven",
                userAvatar = R.drawable.eleven,
                postContent = "AVIS: Couvre-feu à 20h pour tous les enfants de Hawkins jusqu'à nouvel ordre. Trop de disparitions dernièrement.",
                postImage = R.drawable.haircut,
                likes = 312,
                comments = 76,
                shares = 124,
                timeAgo = "1j"
            ),
            Post(
                id = 8,
                userName = "Eddie",
                userAvatar = R.drawable.eddie,
                postContent = "Quelqu'un a-t-il vu Barb récemment? Elle ne répond pas à mes appels depuis la fête de Steve.",
                postImage = R.drawable.grass,
                likes = 67,
                comments = 38,
                shares = 22,
                timeAgo = "2j"
            ),
            Post(
                id = 9,
                userName = "Lucas",
                userAvatar = R.drawable.lucas,
                postContent = "Si vous voyez des lumières clignoter dans la forêt près du laboratoire, N'Y ALLEZ PAS. Appelez-moi immédiatement.",
                postImage = R.drawable.fireworks,
                likes = 221,
                comments = 53,
                shares = 41,
                timeAgo = "2j"
            ),
            Post(
                id = 10,
                userName = "Eleven",
                userAvatar = R.drawable.eleven,
                postContent = "Nous recherchons des employés au Scoops Ahoy! Réduction de 50% sur les glaces pour le personnel! (PS: Steve, arrête de flirter avec les clientes)",
                postImage = R.drawable.father,
                likes = 146,
                comments = 32,
                shares = 9,
                timeAgo = "3j"
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(

                    ) {
                        Text(
                            text = "Stranger Book",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Louvinx PIERRE & Jenoph ETIENNE",
                            fontSize = 8.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Rechercher",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },

    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF0F2F5))
        ) {
            // Créateur de post
            item {
                CreatePostCard()
            }

            // Liste des posts
            items(posts) { post ->
                PostCard(post = post)
            }

            // Espace en bas
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun CreatePostCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardColors(Color.White, Color.Black, Color.Gray, Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar (placeholder)
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                ){
                    Image(painter = painterResource(R.drawable.roller),
                        contentDescription = "null",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("À quoi pensez-vous?") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountBox,
                        contentDescription = "Photo",
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Photo")
                }

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Face,
                        contentDescription = "Vidéo",
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Vidéo")
                }
            }
        }
    }
}

@Composable
fun PostCard(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardColors(Color.White, Color.Black, Color.Gray, Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // En-tête du post
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Avatar (placeholder)
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    ){
                        Image(painter = painterResource(post.userAvatar),
                            contentDescription = "null",
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        Text(
                            text = post.userName,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = post.timeAgo,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }

                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Plus d'options"
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Contenu du post
            Text(
                text = post.postContent,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp
            )

            // Image du post (si présente)
            if (post.postImage != null) {
                Spacer(modifier = Modifier.height(8.dp))

                // Placeholder pour l'image (normalement on utiliserait l'image réelle)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                ){
                    Image(painter = painterResource(post.postImage),
                        contentDescription = "null",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillWidth
                        )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Statistiques du post
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${post.likes} J'aime",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Text(
                    text = "${post.comments} commentaires · ${post.shares} partages",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()

            )

            // Actions du post
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = "J'aime"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("J'aime")
                }

                TextButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Commenter"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Commenter")
                }

                TextButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Partager"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Partager")
                }
            }
        }
    }
}



@Composable
fun StrangerBookTheme(content: @Composable () -> Unit) {
    val colorScheme = darkColorScheme(
        primary = Color(0xFF0C1D2B),
        secondary = Color(0xFF0C1D2B),
        surface = Color(0xFF0F2731),
        background = Color(0xFFFFFFFF),
        onPrimary = Color.White,
        onSecondary = Color.White,
        onSurface = Color.White,
        onBackground = Color.White           
    )

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}


@Preview
@Composable
fun StrangerBookPreview(){
    StrangerBookApp()
}