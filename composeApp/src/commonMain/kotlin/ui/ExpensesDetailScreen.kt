package ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import getColorsTheme
import model.Expense

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpensesDetailScreen(
    expenseToEdit: Expense? = null,
    addExpenseAndNavigateBack: (Expense) -> Unit
){
    val colors = getColorsTheme()
    var price by remember { mutableStateOf(expenseToEdit?.amount ?: 0.0 ) }
    var description by remember { mutableStateOf(expenseToEdit?.description) }
    var expenseCategory by remember { mutableStateOf(expenseToEdit?.category?.name ?: "") }
    var category by remember { mutableStateOf(expenseToEdit?.category?.name ?: "Select a category") }
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(sheetState.targetValue){
        if (sheetState.targetValue == ModalBottomSheetValue.Expanded) {
            keyboardController?.hide()
        }
    }

}