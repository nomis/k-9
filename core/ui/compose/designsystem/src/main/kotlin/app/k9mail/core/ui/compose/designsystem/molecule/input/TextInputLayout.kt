package app.k9mail.core.ui.compose.designsystem.molecule.input

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.k9mail.core.ui.compose.designsystem.atom.text.TextCaption
import app.k9mail.core.ui.compose.theme.MainTheme

@Composable
internal fun TextInputLayout(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = inputContentPadding(),
    errorMessage: String? = null,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxWidth()
            .then(modifier),
    ) {
        content()

        AnimatedVisibility(visible = errorMessage != null) {
            TextCaption(
                text = errorMessage ?: "",
                modifier = Modifier.padding(start = MainTheme.spacings.double, top = MainTheme.spacings.half),
                color = MainTheme.colors.error,
            )
        }
    }
}
