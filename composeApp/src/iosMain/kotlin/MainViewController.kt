import androidx.compose.ui.window.ComposeUIViewController
import com.expenseApp.db.AppDatabase
import data.DataBaseDriverFactory
import di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin(){
    startKoin {
        modules(appModule(AppDatabase.invoke(DataBaseDriverFactory().createDriver())))
    }.koin
}