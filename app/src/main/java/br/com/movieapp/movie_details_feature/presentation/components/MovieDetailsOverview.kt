package br.com.movieapp.movie_details_feature.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.movieapp.R
import br.com.movieapp.ui.theme.white

@Composable
fun MovieDetailsOverview(
    modifier: Modifier = Modifier,
    overview: String
) {
    var expended = remember {
        mutableStateOf(false)
    }
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.description),
            color = white,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )
        if (expended.value) {
            Text(
                text = overview,
                color = Color.DarkGray,
                fontFamily = FontFamily.SansSerif,
                fontSize = 15.sp,
                modifier = modifier.clickable {
                    expended.value = !expended.value
                }
            )
        } else {
            Text(
                text = overview,
                color = Color.DarkGray,
                fontFamily = FontFamily.SansSerif,
                fontSize = 15.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.clickable {
                    expended.value = !expended.value
                }
            )
        }
    }
}

@Preview
@Composable
fun MovieDetailsOverviewPreview() {
    MovieDetailsOverview(
        overview = "kjsdhf kjsdhfkj hsdfj hsdkj fhskjdfh jkshdfkj hsdkj fhsdjkfh skjdhfkjs dfhjsdjfhsdjf hsjkdfhjksdf kjsd hfs djkfh sjdf kjsd fkjsdfg kj Filmes file oiel oiel rjçljefj elv vlksjdfk jslk slkdflksdj flksjdflksjdfkls jdlk df sdfkhs df sdhf sdf ljhsdf lhs",
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 8.dp)

    )
}