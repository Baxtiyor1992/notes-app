package uz.polat.noteappatto.utils.navigation


import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.flow.SharedFlow

typealias NavigationArgs = Navigator.() -> Unit

interface NavigationHandler {
    val navigationStack: SharedFlow<NavigationArgs>
}