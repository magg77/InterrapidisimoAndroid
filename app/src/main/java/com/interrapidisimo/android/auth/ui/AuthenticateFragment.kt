package com.interrapidisimo.android.auth.ui

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.interrapidisimo.android.R
import com.interrapidisimo.android.auth.presentation.AuthenticateViewModel
import com.interrapidisimo.android.core.activitys.MainActivity
import com.interrapidisimo.android.core.utils.GetAppVersion
import com.interrapidisimo.android.core.valueObjet.ResourceState
import com.interrapidisimo.android.databinding.FragmentAuthenticateBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class AuthenticateFragment : Fragment() {

    private var _binding: FragmentAuthenticateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val authenticateViewModel by viewModels<AuthenticateViewModel>()

    private var hasShownDialog = false // Bandera para evitar múltiples diálogos
    private var hasValidationLogin = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAuthenticateBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vpStoreAppControlObserver()

        binding.buttonAuth.setOnClickListener {

            hasValidationLogin = false

            authenticateUser(
                nameApp = binding.tilNameAppData.text.toString(),
                user = binding.tilUserData.text.toString(),
                password = binding.tilPasswordData.text.toString()
            )
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun vpStoreAppControlObserver() {

        authenticateViewModel.getVersionApp()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                authenticateViewModel.uiStateVersionApp.collect() {

                    when (it) {

                        is ResourceState.LoadingState -> {
                            binding.psHome.visibility = View.VISIBLE
                        }

                        is ResourceState.SuccessState -> {

                            binding.psHome.visibility = View.GONE

                            if (!hasShownDialog) { // Solo mostramos el diálogo una vez
                                hasShownDialog = true

                                if (GetAppVersion.getAppVersion(requireContext())
                                        .toInt() < it.data.versionApp.toInt()
                                ) {

                                    // Llamar al diálogo cuando sea necesario
                                    showAlertDialog(updateApp = true)

                                } else {
                                    showAlertDialog(updateApp = false)
                                }
                            }
                        }

                        is ResourceState.FailureState -> {
                            binding.psHome.visibility = View.GONE

                            hasValidationLogin = true
                            Toast.makeText(
                                requireContext(),
                                "Ocurrio un error al mostrar los datos: ${it.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {}
                    }


                }
            }
        }
    }

    private fun authenticateUser(nameApp: String, user: String, password: String) {

        authenticateViewModel.authenticate(nameApp, user, password)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                authenticateViewModel.uiStateAuthenticate.collect() {

                    when (it) {

                        is ResourceState.LoadingState -> {
                            binding.psHome.visibility = View.VISIBLE
                        }

                        is ResourceState.SuccessState -> {
                            binding.psHome.visibility = View.GONE

                            authenticateViewModel.saveAuthenticateBdSQLite(it.data)

                            val intent = Intent(requireContext(), MainActivity::class.java)
                            startActivity(intent)
                            requireActivity().finish()


                        }

                        is ResourceState.FailureState -> {
                            binding.psHome.visibility = View.GONE

                            Log.i("authenticateAppViewModel", "desde fragment failure = $hasValidationLogin")

                            if(!hasValidationLogin){

                                hasValidationLogin = true

                                /*Toast.makeText(
                                    requireContext(),
                                    "${it.message}",
                                    Toast.LENGTH_SHORT
                                ).show()*/

                                showAlertDialog(requireContext(), it.message)
                            }
                        }

                        else -> {}

                    }


                }
            }
        }
    }

    private fun showAlertDialog(updateApp: Boolean) {

        val builder = MaterialAlertDialogBuilder(requireContext(), R.style.CustomAlertDialog)

        // Inflar el layout personalizado
        val inflater = LayoutInflater.from(requireContext())
        val dialogView = inflater.inflate(R.layout.custom_dialog, null)

        // Obtener referencias a los elementos
        val txvShowInfo = dialogView.findViewById<TextView>(R.id.tvDescription)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)
        val btnAccept = dialogView.findViewById<Button>(R.id.btnAccept)

        // Configura la vista antes de crear el diálogo
        builder.setView(dialogView)

        // Crear una única instancia
        val dialog = builder.create()

        // Evita que se cierre al tocar fuera del diálogo
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)

        txvShowInfo.text =
            getString(if (updateApp) R.string.available_update_app else R.string.not_available_update_app)

        btnCancel.setOnClickListener {
            Log.d("AlertDialog", "Botón Cancelar presionado") // Verifica si se ejecuta
            dialog.dismiss() // Cerrar el diálogo
        }

        btnAccept.setOnClickListener {
            Log.d("AlertDialog", "Botón Aceptar presionado") // Verifica si se ejecuta
            Toast.makeText(requireContext(), "Acción confirmada", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        // Mostrar el AlertDialog
        dialog.show()

    }

    private fun showAlertDialog(context: Context, message: String) {

        val dialog = MaterialAlertDialogBuilder(context)
            .setTitle("Información de interés")
            .setMessage(message)
            .setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .create() // Usamos `create()` en lugar de `show()` para obtener el objeto Dialog.


        dialog.setOnShowListener {
            val positiveButton: Button = dialog.getButton(DialogInterface.BUTTON_POSITIVE)

            // Obtener colores de res/values/colors.xml
            val backgroundColor = ContextCompat.getColor(context, R.color.textColorActiveIcons)
            val textColor = ContextCompat.getColor(context, R.color.textColorActiveIcons)

            // Cambiar color de texto
            positiveButton.setTextColor(textColor)

            // Cambiar fondo usando GradientDrawable
            val backgroundDrawable = GradientDrawable()
            backgroundDrawable.setTint(backgroundColor) // Color de fondo del botón
            backgroundDrawable.cornerRadius = 20f  // Esquinas redondeadas

            positiveButton.background = backgroundDrawable
        }

        dialog.show()
    }


}