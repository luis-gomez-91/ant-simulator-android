package com.luis.antsimulator.features.questions

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.luis.ant.screens.home.HomeViewModel
import com.luis.antsimulator.data.network.Question
import org.itb.sga.ui.components.MyFilledTonalButton
import org.itb.sga.ui.components.dashboard.DashBoardScreen
import org.itb.sga.ui.components.shimmer.ShimmerFormLoadingAnimation

@Composable
fun QuestionScreen(
    idLicence: Int,
    homeViewModel: HomeViewModel,
    navHostController: NavHostController,
    questionViewModel: QuestionViewModel = hiltViewModel()
) {
    DashBoardScreen(
        navController = navHostController,
        content = {
            Screen(
                idLicence,
                homeViewModel,
                navHostController,
                questionViewModel
            )
        }
    )
}

@Composable
fun Screen(
    idLicence: Int,
    homeViewModel: HomeViewModel,
    navHostController: NavHostController,
    questionViewModel: QuestionViewModel = hiltViewModel()
) {
    val questions by questionViewModel.questions
    var selectedImage by remember { mutableStateOf<String?>(null) }
    val licence by homeViewModel.licenceSelect
    val isLoading by questionViewModel.isLoading

    LaunchedEffect(idLicence) {
        questionViewModel.getQuestions(idLicence)
    }

    if (isLoading) {
        ShimmerFormLoadingAnimation(15)
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize().weight(1f)
                ) {
                    licence?.let {
                        item {
                            Text(
                                text = "Licencia tipo ${it.name}",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary,
                            )
                            Spacer(Modifier.height(4.dp))
                        }
                        item {
                            Text(
                                text = "Banco de preguntas",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.secondary,
                            )
                            Spacer(Modifier.height(16.dp))
                        }
                    }

                    items(questions) { question ->
                        QuestionItem(
                            question = question,
                            questionViewModel = questionViewModel,
                            onImageClick = { selectedImage = question.image }
                        )
                        Spacer(Modifier.height(16.dp))
                    }
                }

                Spacer(Modifier.height(8.dp))

                MyFilledTonalButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Ir al simulador",
                    enabled = true,
                    icon = Icons.AutoMirrored.Filled.Assignment,
                    iconSize = 24.dp,
                    textColor = MaterialTheme.colorScheme.tertiary,
                    buttonColor = MaterialTheme.colorScheme.tertiaryContainer,
                    onClickAction = {
                        navHostController.navigate("simulator")
                    }
                )
            }

            selectedImage?.let {
                FullScreenImageTransition(
                    imageUrl = it,
                    visible = true,
                    onDismiss = { selectedImage = null }
                )
            }
        }
    }
}


@Composable
fun QuestionItem(
    question: Question,
    questionViewModel: QuestionViewModel,
    onImageClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${question.num}. ${question.text}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )

                question.image?.let {
                    Spacer(Modifier.width(8.dp))
                    AsyncImage(
                        model = it,
                        contentDescription = "Question image",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onImageClick() }
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                question.choices.forEach { choice ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(4.dp)),
                        color = if (choice.is_correct) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surfaceContainer
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = choice.text,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (choice.is_correct) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun FullScreenImageTransition(
    imageUrl: String,
    visible: Boolean,
    onDismiss: () -> Unit
) {
    val transition = updateTransition(targetState = visible, label = "imageTransition")

    val scale by transition.animateFloat(label = "scale") { state ->
        if (state) 1f else 0.3f
    }

    val offset by transition.animateDp(label = "offset") { state ->
        if (state) 0.dp else 100.dp
    }

    val alpha by transition.animateFloat(label = "alpha") { state ->
        if (state) 1f else 0f
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.50f))
            .clickable { onDismiss() },
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Expanded image",
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .aspectRatio(1f)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    this.alpha = alpha
                    translationY = offset.toPx()
                }
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Fit
        )
    }
}


