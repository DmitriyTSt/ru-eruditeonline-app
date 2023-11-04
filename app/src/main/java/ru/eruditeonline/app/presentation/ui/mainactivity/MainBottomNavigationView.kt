package ru.eruditeonline.app.presentation.ui.mainactivity

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.core.view.forEach
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.shape.MaterialShapeDrawable
import ru.eruditeonline.app.R

class MainBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : BottomNavigationView(context, attrs, defStyleAttr),
    NavController.OnDestinationChangedListener {

    init {
        // установить скругленные углы с помощью настроек шейпа.
        // BottomNavigationView поддерживает шейпы, но только через код.
        val cornerSize = resources.getDimension(R.dimen.bottom_navigation_view_corner_size)
        val bottomNavigationViewBackground = background as MaterialShapeDrawable
        bottomNavigationViewBackground.shapeAppearanceModel =
            bottomNavigationViewBackground.shapeAppearanceModel.toBuilder()
                .setAllCornerSizes(cornerSize)
                .build()

        // данная настройка отменяет iconTint's, чтобы мы могли испозовать селекторы (только через код)
        itemIconTintList = null
    }

    /** Прикрепить NavController */
    fun attachNavController(navController: NavController) {
        // установка слушателя переходов в графe
        navController.addOnDestinationChangedListener(this)
    }

    /** NavController.OnDestinationChangedListener */
    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?,
    ) {
        // определяем относится ли данный дейстинейшон к основному меню
        val graphId = destination.parent?.id ?: return
        if (menu.findItem(graphId) != null) {
            // новый дестинейшон является пунктом меню, поэтому обрабатываем его.
            menu.forEach { menuItem ->
                if (menuItem.itemId == graphId) {
                    menuItem.isChecked = true
                }
            }
        }
    }
}
