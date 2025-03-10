package br.com.loboneto.coinapi.ui.ds.button

sealed interface DSButtonType {
    data object Primary : DSButtonType
    data object Secondary : DSButtonType
}
