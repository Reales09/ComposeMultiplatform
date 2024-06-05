import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.ExpenseManager
import data.ExpenseRepoImpl
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.viewmodel.viewModel
import presentacion.ExpensesUiState
import presentacion.ExpensesViewModel
import ui.ExpensesScreen

@Composable
@Preview
fun App() {
    PreComposeApp {
        val colors = getColorsTheme()
        val viewModel= viewModel(modelClass = ExpensesViewModel::class){
            ExpensesViewModel(ExpenseRepoImpl(ExpenseManager))
        }
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        AppTheme {
            ExpensesScreen(
                uiState = uiState, onExpenseClick = {})
        }
    }

}
