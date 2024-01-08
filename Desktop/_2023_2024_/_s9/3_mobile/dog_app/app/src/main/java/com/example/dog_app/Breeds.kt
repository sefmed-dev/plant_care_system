package com.example.dog_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class Breeds(
    private val context: MainActivity
) : Fragment() {
    private lateinit var spinner: Spinner
    private lateinit var selectedValueTextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_breeds, container, false)
        spinner = view.findViewById(R.id.spinner)
        selectedValueTextView = view.findViewById(R.id.selectedValue)

        // Récupérez les options du sélecteur à partir des ressources
        val optionsArray = resources.getStringArray(R.array.spinner_options)

        // Créez un ArrayAdapter à utiliser avec le Spinner
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item, optionsArray)


        // Associez l'adaptateur au Spinner
        spinner.adapter = adapter

        // Configurez le gestionnaire d'événements pour le Spinner
        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Mettez à jour le TextView avec la valeur sélectionnée
                selectedValueTextView.text = "Selected Value: ${optionsArray[position]}"
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Aucune option n'est sélectionnée
            }
        })
        return view
    }


}